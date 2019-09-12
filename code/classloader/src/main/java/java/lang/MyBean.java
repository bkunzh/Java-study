package java.lang;

/**
 * @author bkunzhang
 * @date 2019/9/12
 *
 * java.lang.SecurityException：Prohibited package name: java.lang 被禁止的包名
 */
public class MyBean {
    public static void main(String[] args) {
        System.out.println(new MyBean());
    }

    static int count = 0;
    @Override
    public String toString() {
        return "MyBean_" + (++count);
    }
}
