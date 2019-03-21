import java.lang.ref.WeakReference;

public class TestWeakReference {

    WeakReference<String> stringWeakReference = new WeakReference<String>("abc");

    public static void main(String[] args) {

        TestWeakReference t = new TestWeakReference();
        new Thread(() -> {
            System.gc();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("thread " + Thread.currentThread().getName() + " :" + t.stringWeakReference.get());
        }).start();

        System.out.println(t.stringWeakReference.get());

    }
}
