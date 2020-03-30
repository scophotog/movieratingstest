package pages;

import io.appium.java_client.AppiumFluentWait;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import util.Constants;
import util.ExpectedMobileElementConditions;

import java.time.Duration;

@AndroidFindBy(id = "org.sco.movieratings:id/toolbar")
public class TitleBar extends Widget {

    @AndroidFindBy(className = "android.widget.TextView")
    private AndroidElement titleBar;

    protected TitleBar(WebElement element) {
        super(element);
    }

    public String getTitleText() {
        return titleBar.getText();
    }

    public void waitForTitleToBe(final String title) {
        new AppiumFluentWait<>(titleBar)
                .withTimeout(Duration.ofSeconds(Constants.getDefaultTimeout()))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NotFoundException.class)
                .withMessage(String.format("Expected element text to contain: %s", title))
                .until(ExpectedMobileElementConditions.textToBe(title));
    }

}
