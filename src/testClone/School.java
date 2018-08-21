package testClone;

/**
 * Created by xzy on 18/8/21  .
 */

public class School{
    public School(String name, User principle) {
        this.name = name;
        this.principle = principle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getPrinciple() {
        return principle;
    }

    public void setPrinciple(User principle) {
        this.principle = principle;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", principle=" + principle +
                '}';
    }

    private String name;
    private User principle;


}
