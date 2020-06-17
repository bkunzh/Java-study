import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author bingkun_zhang
 * @date 2020/6/17
 */
public class ExceptionUtil {
    /**
     * 得到异常栈的信息
     * @param e
     * @return
     */
    public static String getStackTraceStr(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
