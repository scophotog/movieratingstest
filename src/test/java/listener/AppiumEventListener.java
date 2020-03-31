package listener;

import io.appium.java_client.events.api.general.SearchingEventListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppiumEventListener implements SearchingEventListener {
    private Logger log = LoggerFactory.getLogger(AppiumEventListener.class);

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        log.info("Finding element using " + by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        if (null == element) {
            log.info("Could not find element with locator: " + by.toString());
        } else {
            log.info("Found element using " + by.toString());
        }
    }
}
