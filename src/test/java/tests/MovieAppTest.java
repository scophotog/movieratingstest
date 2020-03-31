package tests;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.annotations.*;
import pages.MovieDetailPage;
import pages.MovieListPage;

import static org.testng.Assert.assertEquals;

public class MovieAppTest extends TestBase {

    private MovieListPage movieListPage;
    private MovieDetailPage movieDetailPage;

    @BeforeTest
    public void setup() {
        movieListPage = new MovieListPage(driver);
        movieDetailPage = new MovieDetailPage(driver);
    }

    @Test
    public void tapTest() {
        movieListPage.tapPopularMovies();
        assertEquals(movieListPage.getTitleText(), "Movie Ratings Most Popular");
        movieListPage.tapTopMovies();
        assertEquals(movieListPage.getTitleText(), "Movie Ratings Top Rated");
        movieListPage.tapPopularMovies();
        assertEquals(movieListPage.getTitleText(), "Movie Ratings Most Popular");
    }

    @Test
    public void failTest() {
        movieListPage.tapPopularMovies();
        assertEquals(movieListPage.getTitleText(), "Movie Ratings Most Popular");
        movieListPage.tapTopMovies();
        assertEquals(movieListPage.getTitleText(), "Movie Ratings Top Rated");
        movieListPage.tapPopularMovies();
        assertEquals(movieListPage.getTitleText(), "Movie Ratings Least Popular");
    }

    @Test
    public void listTest() {
        final String currentTitle = movieListPage.getTitleText();
        movieListPage.getMovieDetails(0);
        assertEquals(movieDetailPage.getTitleText(), "Movie Details");
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        assertEquals(currentTitle, movieListPage.getTitleText());
    }
}
