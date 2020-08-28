import com.bkunzh.encode.util.AESUtil;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author bingkun_zhang
 * @date 2020/8/27
 */
public class AESUtilTest {
    @Test
    public void t() {
        String pass = "ajkdj13423478234";
        String iv = "ajkfdf22324j2234";
        String s = "2324tj@#2342232324";
        String enStr = new String(AESUtil.encrypt(s, pass, iv), Charset.forName("utf8"));
        System.out.println(enStr);

        Base64.Decoder decoder = Base64.getDecoder();
        System.out.println(AESUtil.decrypt(decoder.decode(enStr), pass, iv));
    }
}
