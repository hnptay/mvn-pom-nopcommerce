package pageObject;

import PageUI.HomePageUI;
import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends AbstractPage {

    private WebDriver driver;

    public HomePageObject(WebDriver driver){
        this.driver = driver;
    }

    public RegisterPageObject clickToRegisterMenu(String menu) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_MENU_BAR, menu);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, HomePageUI.DYNAMIC_MENU_BAR, menu);
        return new RegisterPageObject(driver);
    }

    public LoginPageObject clickToLoginMenu(String menu){
        waitForElementClickable(driver, HomePageUI.DYNAMIC_MENU_BAR, menu);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, HomePageUI.DYNAMIC_MENU_BAR, menu);
        return new LoginPageObject(driver);
    }

    public MyAccountPageObject clickToMyAccount(){
        waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
        return new MyAccountPageObject(driver);
    }

}
