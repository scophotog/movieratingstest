package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import listener.AppiumEventListener;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import util.Constants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public abstract class TestBase {

    public static AndroidDriver<AndroidElement> driver;

    @BeforeSuite
    public void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("disableWindowAnimation", true);
        capabilities.setCapability("deviceName", "android");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("appActivity",".activity.MainActivity");
        capabilities.setCapability("appPackage","org.sco.movieratings");
        driver = new AndroidDriver<>(Objects.requireNonNull(Constants.getAppiumUrl()), capabilities);
        driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new AppiumEventListener());
    }

    @BeforeMethod
    public void startRecording() {
        driver.startRecordingScreen(new AndroidStartScreenRecordingOptions().enableBugReport());
    }

    @AfterMethod
    public void saveRecordingOnFailure(final ITestResult result) throws IOException {
        if (!result.isSuccess()) {
            saveScreenRecording(result);
        }
    }

    @AfterMethod
    public void resetAppAfterTest() {
        driver.resetApp();
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

    public void saveScreenRecording(final ITestResult result) throws IOException {
        final byte[] data = Base64.decodeBase64(driver.stopRecordingScreen());
        if (data.length > 0) {
            Reporter.setCurrentTestResult(result);
            String pathStart = "test-output" + File.separatorChar;
            final String htmlDestination = "video" + File.separatorChar + result.getMethod().getMethodName() + ".mp4";
            final String fileDestination = pathStart.concat(htmlDestination);
            final String directoryName = pathStart.concat("video").concat(String.valueOf(File.separatorChar));
            // Create directory
            Path directory = Paths.get(directoryName);
            Files.createDirectories(directory);
            //Write File
            Path path = Paths.get(fileDestination);
            Files.write(path, data);
            String html = "<br><video width=\"480\" height=\"640\" controls>" +
                    "<source src=\"" + htmlDestination + "\" type=\"video/mp4\"></video><br>" +
                    "<a href=\"" + htmlDestination + "\" download>Download Video</a>";
            Reporter.log(html);
        } else {
            System.out.println("No video to save.");
        }
    }

}
