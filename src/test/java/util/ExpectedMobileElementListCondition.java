package util;

import io.appium.java_client.MobileElement;

import java.util.List;
import java.util.function.Function;

public interface ExpectedMobileElementListCondition<T> extends Function<List<? extends MobileElement>, T> {
}

