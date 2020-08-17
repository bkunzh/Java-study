import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RuntimeUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;

public class RuntimeUtilTest {
    @Test
    public void execForStr() {
        try {
//            String rs = RuntimeUtil.execForStr("ipconfig");
            String rs = RuntimeUtil.execForStr("dir");
            System.out.printf(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Process process = Runtime.getRuntime().exec("cmd /c dir");
            String rs2 = IoUtil.read(process.getInputStream(), "GBK");
            System.out.printf(rs2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
