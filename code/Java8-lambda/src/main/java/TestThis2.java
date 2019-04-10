/**
 * Created by bkunzhang on 2019/3/31.
 */
public class TestThis2 {

    int v = 15;
    static float staticF = 12.5F;

    public static void main(String[] args) {

        //1 访问局部变量
        int a = 5;
        Converter<Integer, String> converter = from -> String.valueOf(from + a);
        System.out.println(converter.convert(1));
//        a = 8; //can't

        TestThis2 testThis2 = new TestThis2();
        testThis2.f2();

        System.out.println("testThis2.v=" + testThis2.v);
        System.out.println("staticF=" + staticF);


    }

    void f2() {
        //2 访问成员变量和静态变量
        Converter<String, String> c2 = from -> {
            v = 22;
            return from;
        };
        System.out.println(c2.convert("a1"));

        Converter<String, String> c3 = from -> {
            staticF = 88F;
            return from;
        };
        System.out.println(c3.convert("a2"));
    }


}
