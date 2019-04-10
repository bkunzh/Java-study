/**
 * Created by zhbk on 2019/3/8.
 * 对于匿名类，关键词 this 解读为匿名类，而对于Lambda表达式，关键词 this解读为写就 Lambda 的外部类
 */
public class TestThis {
    int n;

    public TestThis(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        TestThis t = new TestThis(108);
        t.fu();
    }

    void fu() {
        //匿名类
        I i1 = new I() {
            public void f(String s) {
                System.out.println("匿名类:");
//                找不到n，编译报错
//               System.out.println(this.n);
                System.out.println(s);
            }
        };

        exec(i1, "test1");

        I i2 = s -> {
            System.out.println("Lambda表达式:");
            System.out.println(this.n);
            System.out.println(s);
        };

        exec(i2, "test2");
    }

    static void exec(I i, String s) {
        i.f(s);
    }

    //内部式函数接口
    @FunctionalInterface
    static interface I {
        void f(String s);
    }
}


