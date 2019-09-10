public class MyThread extends Thread {
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < Short.MAX_VALUE * 10; i++) {
            stringBuffer.append("s" + i);
        }
        System.out.println("MyThread run costTime=" + (System.currentTimeMillis() - start));
    }
}
