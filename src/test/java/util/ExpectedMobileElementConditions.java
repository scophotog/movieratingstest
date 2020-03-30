package util;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;

public class ExpectedMobileElementConditions {

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
