/**
 * Created by bkunzhang on 2019/3/31.
 */
public class TestFunctionalInterface {

    public static void main(String[] args) {
        I i = (s) -> s;
        System.out.println(i.f("hello1"));
        System.out.println(i.say("hello2"));
    }


    //FunctionalInterface注解声明的接口只有一个抽象方法，但可以有多的默认方法
    @FunctionalInterface
    static interface I {
        String f(String s);

        default String say(String s) {
            return this.getClass().getName() + ":" + f(s);
        }

    }
}
