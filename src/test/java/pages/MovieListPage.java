package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MovieListPage extends BasePage {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='org.sco.movieratings:id/toolbar']/android.widget.TextView")
    public AndroidElement titleBar;

    @AndroidFindBy(id = "org.sco.movieratings:id/bn_my_favorites")
    public AndroidElement favoritesButton;

    @AndroidFindBy(id = "org.sco.movieratings:id/bn_top_rated")
    public AndroidElement topRatedButton;

    @AndroidFindBy(id = "org.sco.movieratings:id/bn_most_popular")
    public AndroidElement popularButton;

    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout")
    public List<AndroidElement> movielist;

    public MovieListPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void waitForList() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return !movielist.isEmpty();
            }
        });
    }

    public String getTitleText() {
        return titleBar.getText();
    }
}
