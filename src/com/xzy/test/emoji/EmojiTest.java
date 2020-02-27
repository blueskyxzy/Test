package com.xzy.test.emoji;

/**
 * Created by xzy on 2020/2/27  .
 */

public class EmojiTest {

    public static void main(String[] args) {
        String str1 = "adajk大幅139&(*%%$  很高反46";
        String s1 = EmojiUtil.filterEmoji(str1);
        System.out.println("s1:" + s1);
        String str2 = "adajk大幅139&(*%%$ \uD83D\uDC7F 1\uD83D\uDE37";
        String s2 = EmojiUtil.filterEmoji(str2);
        System.out.println("s1:" + s2);
    }


}


