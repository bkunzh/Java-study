package countdownLatch.test2;

/**
 * Created by zhbk on 2019/3/9.
 * 见 http://www.importnew.com/15731.html
 */
public class Main {
    public static void main(String[] args)
    {
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: "+ result);
    }
}