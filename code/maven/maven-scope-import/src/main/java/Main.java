import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class Main {
    @Test
    public void t() {
        log.info("{}", StrUtil.containsAnyIgnoreCase("abddcc", "aB", "ee", "ca"));
    }
}
