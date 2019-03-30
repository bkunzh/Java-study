/**
 * Created by bkunzhang on 2019/3/31.
 */
public class TestDefaultFunction {

    public static void main(String[] args) {
        I i = (s) -> s;
        System.out.println(i.f("hello1"));
        System.out.println(i.say("hello2"));
    }


    static interface I {
        String f(String s);


        default String say(String s) {
            return this.getClass().getName() + ":" + f(s);
        }

    }
}
