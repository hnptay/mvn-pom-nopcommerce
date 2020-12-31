package pageObject;

import PageUI.LoginPageUI;
import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends AbstractPage {

    WebDriver driver;

    public LoginPageObject(WebDriver driver){
        this.driver = driver;
    }

    public void clickToDynamicButton(String button) {
        waitForElementVisible(driver, LoginPageUI.DYNAMIC_BUTTON, button);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, LoginPageUI.DYNAMIC_BUTTON, button);
    }

    public String getEmailErrorMessage() {
        waitForElementVisible(driver, LoginPageUI.EMAIL_MESSAGE_ERROR);
        return getElementText(driver, LoginPageUI.EMAIL_MESSAGE_ERROR);
    }


    public void inputToDynamicTextBox(String value, String param) {
        waitForElementVisible(driver, LoginPageUI.DYNAMIC_TEXBOX, param);
        sendKeyToElement(driver, LoginPageUI.DYNAMIC_TEXBOX, value, param);
    }

    public String getSummaryMessage() {
        waitForElementVisible(driver, LoginPageUI.SUMMARY_ERROR_MESSAGE);
        return getElementText(driver, LoginPageUI.SUMMARY_ERROR_MESSAGE);
    }

}
