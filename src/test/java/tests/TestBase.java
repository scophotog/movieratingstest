package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {

    public static AndroidDriver<AndroidElement> driver;

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
        final URL url = new URL(APPIUM_URL);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        driver = new AndroidDriver<AndroidElement>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

}
