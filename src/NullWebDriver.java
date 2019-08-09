import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NullWebDriver implements WebDriver {

    @Override
    public void close() {

    }

    @Override
    public WebElement findElement(By arg0) {

        return null;
    }

    @Override
    public List<WebElement> findElements(By arg0) {

        return new ArrayList<>();
    }

    @Override
    public void get(String arg0) {

    }

    @Override
    public String getCurrentUrl() {

        return null;
    }

    @Override
    public String getPageSource() {

        return null;
    }

    @Override
    public String getTitle() {

        return null;
    }

    @Override
    public String getWindowHandle() {

        return null;
    }

    @Override
    public Set<String> getWindowHandles() {

        return new HashSet<>();
    }

    @Override
    public Options manage() {

        return null;
    }

    @Override
    public Navigation navigate() {
        return new Navigation() {

            @Override
            public void back() {
                return;

            }

            @Override
            public void forward() {
                return;

            }

            @Override
            public void refresh() {
                return;

            }

            @Override
            public void to(String arg0) {
                return;

            }

            @Override
            public void to(URL arg0) {

                return;
            }

        };
    }

    @Override
    public void quit() {

    }

    @Override
    public TargetLocator switchTo() {

        return null;
    }

}
