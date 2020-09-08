package test;

/**
 * Created by tmnt on 2020/7/18.
 */
public class Person {
    private int num;
    private String name;
    private int age;

    public Person() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + "--" + String.valueOf(age) + "--"+ String.valueOf(num);
    }
}
