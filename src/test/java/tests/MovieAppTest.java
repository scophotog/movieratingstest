package tests;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.MovieListPage;

import static org.testng.Assert.assertEquals;

public class MovieAppTest extends TestBase {

    private MovieListPage movieListPage;

    @BeforeTest
    public void setup() {
        movieListPage = new MovieListPage(driver);
    }

    @AfterTest
    public void restartApp() {
        driver.resetApp();
    }

    @Test
    public void tapTest() {
        movieListPage.popularButton.click();
        movieListPage.waitForList();
        assertEquals(movieListPage.getTitleText(), "Movie Ratings Most Popular");
        movieListPage.topRatedButton.click();
        movieListPage.waitForList();
        movieListPage.waitForList();
        movieListPage.popularButton.click();
        movieListPage.waitForList();
        assertEquals(movieListPage.getTitleText(), "Movie Ratings Most Popular");
    }

    @Test
    public void listTest() {
        final String currentTitle = movieListPage.getTitleText();
        movieListPage.movielist.get(0).click();
        assertEquals(movieListPage.getTitleText(), "Movie Details");
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        assertEquals(currentTitle , movieListPage.getTitleText());
    }
}
