package util;

import io.appium.java_client.MobileElement;

import java.util.function.Function;

public interface ExpectedMobileElementCondition<T> extends Function<MobileElement, T> {
}

