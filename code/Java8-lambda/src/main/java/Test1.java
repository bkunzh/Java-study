public class Test1 {
    public static void main(String[] args) {

        // Java 8之前：
//        t1();

        //Java 8方式：
        t2();

    }

    static void t1() {
        // Java 8之前：
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();
    }

    static void t2() {
        //Java 8方式：
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
    }
}