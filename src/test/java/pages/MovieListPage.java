package pages;

import io.appium.java_client.AppiumFluentWait;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import util.ExpectedMobileElementConditions;

import java.time.Duration;
import java.util.List;

public class MovieListPage extends BasePage {

    @AndroidFindBy(id = "org.sco.movieratings:id/bn_my_favorites")
    private AndroidElement favoritesButton;

    @AndroidFindBy(id = "org.sco.movieratings:id/bn_top_rated")
    private AndroidElement topRatedButton;

    @AndroidFindBy(id = "org.sco.movieratings:id/bn_most_popular")
    private AndroidElement popularButton;

    @AndroidFindBy(id = "org.sco.movieratings:id/posterLayout")
    private List<AndroidElement> movielist;

    private TitleBar titleBar;

    public MovieListPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void tapPopularMovies() {
        popularButton.click();
        waitForList();
    }

    public void tapTopMovies() {
        topRatedButton.click();
        waitForList();
    }

    public void tapFavoriteMovies() {
        favoritesButton.click();
        waitForList();
    }

    private void waitForList() {
        new AppiumFluentWait<>(movielist)
                .withTimeout(Duration.ofSeconds(3L))
                .until(ExpectedMobileElementConditions.elementListIsNotEmpty());
    }

    public void getMovieDetails(final int index) {
        movielist.get(index).click();
        titleBar.waitForTitleToBe("Movie Details");
    }

    public String getTitleText() {
        return titleBar.getTitleText();
    }
}
