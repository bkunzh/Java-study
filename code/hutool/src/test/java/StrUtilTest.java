import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.util.Date;

public class StrUtilTest {
    @Test
    public void format() {
        String s = StrUtil.format("ab={}, cd={}, {}", 1234, "eee", new Object[]{1, new Date(), 3});
        System.out.printf(s);
    }
}
