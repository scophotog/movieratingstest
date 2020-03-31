package util;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ExpectedMobileElementConditions {

    private static Logger log = LoggerFactory.getLogger(ExpectedMobileElementConditions.class);

    private ExpectedMobileElementConditions() {}

    public static ExpectedMobileElementCondition<Boolean> isDisplayed() {
        return new ExpectedMobileElementCondition<Boolean>() {
            @Override
            public Boolean apply(MobileElement mobileElement) {
                return mobileElement.isDisplayed();
            }
        };
    }

    public static ExpectedMobileElementCondition<Boolean> textToBe(final String text) {
        return new ExpectedMobileElementCondition<Boolean>() {
            @Override
            public Boolean apply(MobileElement mobileElement) {
                try {
                    String elementText = mobileElement.getText();
                    return elementText.contains(text);
                } catch (StaleElementReferenceException e) {
                    log.debug("StaleElementReferenceException");
                    return null;
                }
            }
        };
    }

    public static ExpectedMobileElementListCondition<Boolean> listElementsToBeMoreThan(final int count) {
        return new ExpectedMobileElementListCondition<Boolean>() {
            @Override
            public Boolean apply(List<? extends MobileElement> elementList) {
                return elementList.size() > count;
            }
        };
    }

    public static ExpectedMobileElementListCondition<Boolean> listElementsToBeLessThan(final int count) {
        return new ExpectedMobileElementListCondition<Boolean>() {
            @Override
            public Boolean apply(List<? extends MobileElement> elementList) {
                return elementList.size() < count;
            }
        };
    }

    public static ExpectedMobileElementListCondition<Boolean> elementListIsNotEmpty() {
        return new ExpectedMobileElementListCondition<Boolean>() {
            @Override
            public Boolean apply(List<? extends MobileElement> mobileElements) {
                return !mobileElements.isEmpty();
            }
        };
    }
}
