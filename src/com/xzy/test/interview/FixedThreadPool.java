package com.xzy.test.interview;

/**
 * @author: xzy
 * @create: 2021-01-22
 **/

//评测题目: 无
//手写一个线程池

import java.util.HashSet;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class FixedThreadPool implements Executor {

    private volatile int corePoolSize;   // 核心线程数
    private volatile int maximumPoolSize;   // 最大线程数
    private final BlockingQueue<Runnable> workQueue; // 存等待执行的任务的阻塞队列
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0)); // 初始化进入运行期，线程数为0
    private final ReentrantLock mainLock = new ReentrantLock();
    private final HashSet<Worker> workers = new HashSet<Worker>();
    private int largestPoolSize;
    private volatile ThreadFactory threadFactory = Executors.defaultThreadFactory();

    private static final int COUNT_BITS = Integer.SIZE - 3; // 用来标记线程池状态（高3位），线程个数（低29位）
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1; //00011111111111 最大线程数

    //（高3位）：11100000000000000000000000000000  接受新任务并且处理阻塞队列里的任务；
    private static final int RUNNING    = -1 << COUNT_BITS;
    //（高3位）：00000000000000000000000000000000  拒绝新任务但是处理阻塞队列里的任务
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    //（高3位）：00100000000000000000000000000000  拒绝新任务并且抛弃阻塞队列里的任务，同时会中断正在处理的任务
    private static final int STOP       =  1 << COUNT_BITS;
    //（高3位）：01000000000000000000000000000000  所有任务都执行完（包含阻塞队列里面任务）当前线程池活动线程为 0，将要调用 terminated 方法
    private static final int TIDYING    =  2 << COUNT_BITS;
    //（高3位）：01100000000000000000000000000000  终止状态，terminated方法调用完成以后的状态。
    private static final int TERMINATED =  3 << COUNT_BITS;
    // SHUTDOWN -> TIDYING：当线程池和任务队列都为空的时候。
    // STOP -> TIDYING：当线程池为空的时候。
    // TIDYING -> TERMINATED：当 terminated() hook 方法执行完成时候。

    // 线程池状态
    // 关闭：不接受新任务，池和队列为空
    // 运行期：接受新任务

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; } // 高3位状态
    private static int workerCountOf(int c)  { return c & CAPACITY; } // 获取低29位 线程个数
    private static int ctlOf(int rs, int wc) { return rs | wc; }  // 线程状态 与 线程个数

    private static boolean isRunning(int c) {
        return c < SHUTDOWN;
    }

    public FixedThreadPool(int nThreads) {
        // 1.threadPoolExecutor写法
        // Thread new threadPoolExecutor(nThreads, nThreads, 0, As..Strategy)
        if (nThreads < 0)
            throw new IllegalArgumentException();
        this.corePoolSize = nThreads;
        this.maximumPoolSize = nThreads;
        LinkedBlockingQueue<Runnable> workerQueue = new LinkedBlockingQueue<>();
        workQueue = workerQueue;
    }

    private static boolean runStateAtLeast(int c, int s) {
        return c >= s;
    }

    private boolean compareAndIncrementWorkerCount(int expect) {
        return ctl.compareAndSet(expect, expect + 1);
    }

    /**
     * Attempts to CAS-decrement the workerCount field of ctl.
     */
    private boolean compareAndDecrementWorkerCount(int expect) {
        return ctl.compareAndSet(expect, expect - 1);
    }

    /**
     * Decrements the workerCount field of ctl. This is called only on
     * abrupt termination of a thread (see processWorkerExit). Other
     * decrements are performed within getTask.
     */
    private void decrementWorkerCount() {
        do {} while (! compareAndDecrementWorkerCount(ctl.get()));
    }


    @Override
    public void execute(Runnable command) {
        if(command == null)
            throw new NullPointerException();
        int c = ctl.get();
        // 线程池线程个数是否小于corePoolSize,小于则开启新线程运行
        if (workerCountOf(c) < corePoolSize) {
            if (addWorker(command, true))
                return;
            c = ctl.get();
        }
//        如果线程池处于RUNNING状态，则添加任务到阻塞队列
        if (c < SHUTDOWN && workQueue.offer(command)) {
//            二次检查
            int recheck = ctl.get();
//            如果当前线程池状态不是RUNNING则从队列删除任务，并执行拒绝策略
//            if (! isRunning(recheck) && remove(command))
//                reject(command);
            // 当前线程池线程空，则添加一个线程
            if (workerCountOf(recheck) == 0)
                addWorker(null, false);
        }
        // 如果队列满了，则新增线程，新增失败则执行拒绝策略
    }

    // addWorker
    private boolean addWorker(Runnable firstTask, boolean core) {
        // 自旋循环标记，方便break退出自旋
        // 第一部分的双重循环目的是通过 cas 操作增加线程池线程数，第二部分主要是并发安全的把任务添加到 workers 里面，
        retry:
        for (;;) {
            int c = ctl.get();
            int rs = runStateOf(c);

            // 检查队列是否只在必要时为空
            if (rs >= SHUTDOWN &&
                    ! (rs == SHUTDOWN &&
                            firstTask == null &&
                            ! workQueue.isEmpty()))
                return false;

            // 自旋增加线程个数
            for (;;) {
                int wc = workerCountOf(c);
                if (wc >= CAPACITY ||
                        wc >= (core ? corePoolSize : maximumPoolSize))
                    return false;
                if (compareAndIncrementWorkerCount(c))
                    break retry;
                c = ctl.get();  // Re-read ctl
                if (runStateOf(c) != rs)
                    continue retry;
                // else CAS failed due to workerCount change; retry inner loop
            }
        }

        // 增加线程数成功，创建worker
        boolean workerStarted = false;
        boolean workerAdded = false;
        Worker w = null;
        try {
            w = new Worker(firstTask);
            final Thread t = w.thread;
            if (t != null) {
                final ReentrantLock mainLock = this.mainLock;
                // 加独占锁，为了workers同步，因为可能多个线程调用了线程池的execute方法。
                mainLock.lock();
                try {
                    // Recheck while holding lock.
                    // Back out on ThreadFactory failure or if
                    // shut down before lock acquired.
                    // 重新检查线程池状态，为了避免在获取锁前调用了shutdown接口
                    int rs = runStateOf(ctl.get());

                    if (rs < SHUTDOWN ||
                            (rs == SHUTDOWN && firstTask == null)) {
                        if (t.isAlive()) // precheck that t is startable
                            throw new IllegalThreadStateException();
                        // 添加任务
                        workers.add(w);
                        int s = workers.size();
                        if (s > largestPoolSize)
                            largestPoolSize = s;
                        workerAdded = true;
                    }
                } finally {
                    mainLock.unlock();
                }
                if (workerAdded) {
                    // 添加任务成功，开启线程任务，执行runWorker
                    t.start();
                    workerStarted = true;
                }
            }
        } finally {
            if (!workerStarted)
                System.out.println("failed");
//                addWorkerFailed(w);
        }
        return workerStarted;
    }


    private final class Worker
            extends AbstractQueuedSynchronizer
            implements Runnable {
        /**
         * This class will never be serialized, but we provide a
         * serialVersionUID to suppress a javac warning.
         */
        private static final long serialVersionUID = 6138294804551838833L;

        /**
         * Thread this worker is running in.  Null if factory fails.
         */
        final Thread thread;
        /**
         * Initial task to run.  Possibly null.
         */
        Runnable firstTask;
        /**
         * Per-thread task counter
         */
        volatile long completedTasks;

        /**
         * Creates with given first task and thread from ThreadFactory.
         *
         * @param firstTask the first task (null if none)
         */
        Worker(Runnable firstTask) {
            setState(-1); // inhibit interrupts until runWorker
            this.firstTask = firstTask;
            this.thread = getThreadFactory().newThread(this);
        }

        /**
         * Delegates main run loop to outer runWorker
         */
        public void run() {
            runWorker(this);
        }

        // Lock methods
        //
        // The value 0 represents the unlocked state.
        // The value 1 represents the locked state.

        protected boolean isHeldExclusively() {
            return getState() != 0;
        }

        protected boolean tryAcquire(int unused) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        public void lock()        { acquire(1); }
        public boolean tryLock()  { return tryAcquire(1); }
        public void unlock()      { release(1); }
        public boolean isLocked() { return isHeldExclusively(); }
    }

    public ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    final void runWorker(Worker w) {
        Thread wt = Thread.currentThread();
        Runnable task = w.firstTask;
        w.firstTask = null;
        // status设置为0，允许中断
        w.unlock(); // allow interrupts
        boolean completedAbruptly = true;
        try {
            while (task != null || (task = getTask()) != null) {
                w.lock();
                // If pool is stopping, ensure thread is interrupted;
                // if not, ensure thread is not interrupted.  This
                // requires a recheck in second case to deal with
                // shutdownNow race while clearing interrupt
                if ((runStateAtLeast(ctl.get(), STOP) ||
                        (Thread.interrupted() &&
                                runStateAtLeast(ctl.get(), STOP))) &&
                        !wt.isInterrupted())
                    wt.interrupt();
                try {
                    Throwable thrown = null;
                    try {
                        task.run();
                    } catch (RuntimeException x) {
                        thrown = x; throw x;
                    } catch (Error x) {
                        thrown = x; throw x;
                    } catch (Throwable x) {
                        thrown = x; throw new Error(x);
                    } finally {
                        System.out.println("run task");
                    }
                } finally {
                    task = null;
                    // 完成数累计
                    w.completedTasks++;
                    w.unlock();
                }
            }
            completedAbruptly = false;
        } finally {
            System.out.println("runWorker");
        }
    }

    private Runnable getTask() {
        boolean timedOut = false; // Did the last poll() time out?

        for (;;) {
            int c = ctl.get();
            int rs = runStateOf(c);

            // Check if queue empty only if necessary.
            if (rs >= SHUTDOWN && (rs >= STOP || workQueue.isEmpty())) {
                decrementWorkerCount();
                return null;
            }

            int wc = workerCountOf(c);

            // Are workers subject to culling?
            boolean timed = false || wc > corePoolSize;

            if ((wc > maximumPoolSize || (timed && timedOut))
                    && (wc > 1 || workQueue.isEmpty())) {
                if (compareAndDecrementWorkerCount(c))
                    return null;
                continue;
            }

            try {
                Runnable r = timed ?
                        workQueue.poll(0, TimeUnit.NANOSECONDS) :
                        workQueue.take();
                if (r != null)
                    return r;
                timedOut = true;
            } catch (InterruptedException retry) {
                timedOut = false;
            }
        }
    }
}
