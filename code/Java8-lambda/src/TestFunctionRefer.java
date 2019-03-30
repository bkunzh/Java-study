/**
 * Created by bkunzhang on 2019/3/31.
 * https://wizardforcel.gitbooks.io/modern-java/content/ch1.html
 */
public class TestFunctionRefer {

    public static void main(String[] args) {

        //1
//        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
//        Integer converted = converter.convert("123");
//        System.out.println(converted);    // 123

        //2 Integer::valueOf 静态方法引用
//        Converter<String, Integer> converter = Integer::valueOf;
//        Integer converted = converter.convert("123");
//        System.out.println(converted);    // 123

        //3 something::startsWith 对象的方法进行引用
//        Something something = new Something();
//        Converter<String, String> converter = something::startsWith;
//        String converted = converter.convert("Java");
//        System.out.println(converted);    // "J"

        // 4 构造函数引用
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person.firstName + " " + person.lastName);

    }
}

interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}

class Person {
    String firstName;
    String lastName;

    Person() {}

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

class Something {
    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
}


@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}
