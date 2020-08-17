import cn.hutool.crypto.SecureUtil;
import org.junit.Test;

public class SecureUtilTest {
    @Test
    public void md5() {
        String rs = SecureUtil.md5("akdjfjkadiewiwer12");
        System.out.println(rs);
    }
}
