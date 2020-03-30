package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import util.Constants;

import java.time.Duration;

public abstract class BasePage {
    protected final AndroidDriver<AndroidElement> driver;

    protected BasePage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(
                new AppiumFieldDecorator(
                        driver,
                        Duration.ofSeconds(Constants.getDefaultTimeout())),
                this);
    }
}
