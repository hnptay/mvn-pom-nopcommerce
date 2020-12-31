package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class ElectronicsPageObject extends AbstractPage {
    WebDriver driver;

    public ElectronicsPageObject(WebDriver driver){
        this.driver = driver;
    }
}
