package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public final class Constants {

    private Constants() {}

    private static Integer DEFAULT_TIMEOUT;
    private static String SERVER_URL;
    private static final Properties PAGE_PROPERTIES = new Properties();
    private static final String PROPERTIES_FILE = "src/test/resources/properties/testdefaults.properties";

    public static int getDefaultTimeout() {
        if (null == DEFAULT_TIMEOUT) {
            try {
                FileInputStream propertyFile = new FileInputStream(PROPERTIES_FILE);
                PAGE_PROPERTIES.load(propertyFile);
            } catch (IOException e) {
                System.out.println("Properties file error");
                System.exit(0);
            }
            DEFAULT_TIMEOUT = Integer.parseInt(PAGE_PROPERTIES.getProperty("test.defaultTimeout", "0"));
        }
        return DEFAULT_TIMEOUT;
    }

    public static URL getAppiumUrl() {
        if (null == SERVER_URL) {
            SERVER_URL = PAGE_PROPERTIES.getProperty("test.appiumServerUrl", "http://127.0.0.1:4723/wd/hub");
        }
        try {
            return new URL(SERVER_URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Check appium server url");
        }
    }
}
