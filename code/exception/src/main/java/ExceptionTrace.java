import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bingkun_zhang
 * @date 2020/6/17
 */
public class ExceptionTrace {
    public static void main(String[] args) {
        try {
            int a = 0;
            System.out.println(2 / a);
        } catch (Exception e) {
            System.out.println(exceptionReturnMap(e));
        }
    }

    // 异常返回
    private static Map<String,Object> exceptionReturnMap(Exception e) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code", 500);
        resultMap.put("msg", "服务器异常");
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        resultMap.put("detailMsg", stringWriter.toString());
        return resultMap;
    }
}
