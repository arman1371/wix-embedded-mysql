package com.wix.mysql.config;

import com.wix.mysql.distribution.Version;
import de.flapdoodle.embed.process.distribution.IVersion;
import org.junit.Test;

import static com.wix.mysql.config.MysqldConfig.Defaults;
import static com.wix.mysql.config.MysqldConfig.SystemDefaults;

/**
 * @author viliusl
 * @since 10/12/14
 */
public class MysqldConfigTest {

    private static IVersion validVersion = Version.v5_6_21;

    @Test(expected = IllegalArgumentException.class)
    public void failOnNullUsername() {
        new MysqldConfig(validVersion, null, Defaults.PASSWORD, Defaults.SCHEMA, Defaults.PORT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failOnEmptyUsername() {
        new MysqldConfig(validVersion, "", Defaults.PASSWORD, Defaults.SCHEMA, Defaults.PORT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failOnNullSchema() {
        new MysqldConfig(validVersion, Defaults.USERNAME, Defaults.PASSWORD, (String)null, Defaults.PORT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failOnEmptySchema() {
        new MysqldConfig(validVersion, Defaults.USERNAME, Defaults.PASSWORD, "", Defaults.PORT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void informationSchemaIsForbidden() {
        new MysqldConfig(validVersion, Defaults.USERNAME, Defaults.PASSWORD, SystemDefaults.SCHEMA, Defaults.PORT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeOfRootPasswordIsForbidden() {
        new MysqldConfig(validVersion, SystemDefaults.USERNAME, Defaults.PASSWORD, SystemDefaults.SCHEMA, Defaults.PORT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failOnNegativePort() {
        new MysqldConfig(validVersion, Defaults.USERNAME, Defaults.PASSWORD, Defaults.SCHEMA, -1);
    }

    @Test
    public void nullPasswordIsAllowed() {
        new MysqldConfig(validVersion, Defaults.USERNAME, null, Defaults.SCHEMA, Defaults.PORT);
    }

    @Test
    public void allowToSpecifyMultipleSchemas() {
        new MysqldConfig(validVersion, Defaults.USERNAME, null, new String[] {"aaa", "bbb"}, Defaults.PORT);
    }

}