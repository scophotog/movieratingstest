package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class MovieDetailPage extends BasePage {

    TitleBar titleBar;

    public MovieDetailPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public String getTitleText() {
        return titleBar.getTitleText();
    }

    public void waitForPageToBeDisplayed() {
        titleBar.waitForTitleToBe("Movie Details");
    }
}
