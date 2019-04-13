import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Function;

/**
 * Created by zhbk on 2019/2/23.
 *
 * https://segmentfault.com/a/1190000009186509
 * 从 Lambda 表达式到双冒号操作符
 * Function<Person, Integer> getAge = Person::getAge;
 * Person p1 = new Person();
 * p1.setAge(11);
 * getAge.apply(p1)
 */
public class TestFunction {
    public static void main(String[] args) {

//        t1();
//        t2();
        t3();
    }

    //ArrayList
    //ArrayList::size
    static void t1() {
        ArrayList arrayList = new ArrayList(Arrays.asList(1, 2, 5));
        Function<ArrayList, Integer> f = ArrayList::size;
        System.out.println("f.apply(arrayList)=" + f.apply(arrayList));

    }

    //Person::getAge
    static void t2() {
//        Comparator<Person> c = (p1, p2) -> p1.getAge() - p2.getAge();
        Comparator c = Comparator.comparing(Person::getAge);
        Person p1 = new Person();
        p1.setAge(11);
        Person p2 = new Person();
        p2.setAge(18);
        System.out.println(c.compare(p1, p2));;

    }

    static void t3() {
        Person p1 = new Person();
        p1.setAge(11);
        Person p2 = new Person();
        p2.setAge(18);

        Function<Person, Integer> getAge = Person::getAge;
        System.out.println("getAge.apply(p1)=" + getAge.apply(p1));
        System.out.println("getAge.apply(p2)=" + getAge.apply(p2));
    }

    private static class Person {
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
