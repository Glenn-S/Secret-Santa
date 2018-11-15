package Models;

/**
 * Created by kurtis on 2018-11-14.
 */
public class Person {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
//        return "Person{" +
//                "name='" + name + '\'' +
//                '}';
    }
}
