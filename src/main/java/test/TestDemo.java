package test;

/**
 * Created by tmnt on 2020/7/18.
 */


public class TestDemo {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("zzy");
        person.setAge(20);
        person.setNum(001);
        Operator operator = new Operator();
        operator.output(person);
        System.out.println("-------------");
        System.out.println(person.toString());
    }
}
