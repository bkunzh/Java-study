import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author bingkun_zhang
 * @date 2020/8/27
 */
public class Base64Test {
    @Test
    public void t() throws UnsupportedEncodingException {
        String s = "13aaaaa22434234a";

        Base64.Encoder encoder = Base64.getEncoder();
        String enStr2 = new String(encoder.encode(s.getBytes("utf8")));
        System.out.println(enStr2);

        Base64.Decoder decoder = Base64.getDecoder();
        System.out.println(new String(decoder.decode(enStr2), "utf8"));;
    }
}
