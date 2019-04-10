import java.util.*;
import java.util.function.Function;

/**
 * Created by zhbk on 2019/3/8.
 * lambda方法引用：实例上的实例方法引用instanceReference::methodName、类型上的实例方法引用ClassName::methodName
 */
public class Test3 {

    public static void main(String[] args) {
//        t1();
//        t2();
        t3();

    }

    static void t1() {
        List<String> list = Arrays.asList("aaa", "cc", "bb");
        //list.forEach(v -> System.out.println(v));
        list.forEach(v -> System.out.println(v));
    }

    //实例上的实例方法引用
    static void t11() {
        List<String> list = Arrays.asList("aaa", "cc", "bb");
        //list.forEach(System.out::println)
        list.forEach(System.out::println);
    }

    //类型上的实例方法引用
    static void t2() {
        List list = Arrays.asList("aaa", "cc", "bb");
        //List::size
        Function<List, Integer> f = List::size;
        System.out.println("f.apply(list)=" + f.apply(list));
    }

    //类型上的实例方法引用
    static void t3() {
        List list = Arrays.asList("aaa", "cc", "bb");
        //Comparator.comparing(String::toString) 生成lambda表达式
        Comparator<String> comparator = Comparator.comparing(String::toString);
        Collections.sort(list, comparator);
        System.out.println(list);
    }


}
