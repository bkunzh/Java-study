package configuration;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;

import java.util.ResourceBundle;

/**
 * @author bingkun_zhang
 * @date 2020/7/9
 */
public class ConfigFileTest {
    @Test
    public void t() throws ConfigurationException {
        PropertiesConfiguration configuration =  new PropertiesConfiguration("test.properties");
        System.out.println(configuration.getString("t.key"));
        System.out.println(configuration.getInt("t.a"));
    }
}
