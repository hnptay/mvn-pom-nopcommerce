package pageObject;

import PageUI.RegisterPageUI;
import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class RegisterPageObject extends AbstractPage {
    WebDriver driver;

    public RegisterPageObject(WebDriver driver){
        this.driver = driver;
    }

    public void clickToRegisterButton(String button) {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON, button);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, RegisterPageUI.REGISTER_BUTTON, button);
    }


    public boolean isMessageOfAllFiledRequiredDisplayed(String firstname, String lastname, String email, String password, String confirmPassword) {
        waitForElementsVisible(driver,RegisterPageUI.DYNAMIC_MESSAGE_ERROR, firstname);
        waitForElementsVisible(driver,RegisterPageUI.DYNAMIC_MESSAGE_ERROR, lastname);
        waitForElementsVisible(driver,RegisterPageUI.DYNAMIC_MESSAGE_ERROR, email);
        waitForElementsVisible(driver,RegisterPageUI.DYNAMIC_MESSAGE_ERROR, password);
        waitForElementsVisible(driver,RegisterPageUI.DYNAMIC_MESSAGE_ERROR, confirmPassword);
        return (isElementDisplay(driver,RegisterPageUI.DYNAMIC_MESSAGE_ERROR, firstname) &&
                isElementDisplay(driver,RegisterPageUI.DYNAMIC_MESSAGE_ERROR, lastname) &&
                isElementDisplay(driver,RegisterPageUI.DYNAMIC_MESSAGE_ERROR, email) &&
                isElementDisplay(driver,RegisterPageUI.DYNAMIC_MESSAGE_ERROR, password) &&
                isElementDisplay(driver,RegisterPageUI.DYNAMIC_MESSAGE_ERROR, confirmPassword));
    }


    public void sendKeyToDynamicTextBox(String value, String textBox) {
        waitForElementVisible(driver, RegisterPageUI.DYNAMIC_TEXBOX, textBox);
        sendKeyToElement(driver, RegisterPageUI.DYNAMIC_TEXBOX, value, textBox);
    }

    public void selectGender(String gender) {
        waitForElementClickable(driver, RegisterPageUI.DYNAMIC_GENDER_RADIOBUTTON, gender);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, RegisterPageUI.DYNAMIC_GENDER_RADIOBUTTON, gender);
    }

    public String getErrorMessage(String email) {
        waitForElementVisible(driver, RegisterPageUI.DYNAMIC_MESSAGE_ERROR, email);
        return getElementText(driver, RegisterPageUI.DYNAMIC_MESSAGE_ERROR, email);
    }

    public void selectDateOfBirth(String value, String dateMonthYear) {
        waitForElementVisible(driver, RegisterPageUI.DYNAMIC_DATEOFBIRTH, dateMonthYear);
        selectItemInDropdown(driver, RegisterPageUI.DYNAMIC_DATEOFBIRTH, value, dateMonthYear);
    }

    public String getSummaryErrorMessage() {
        waitForElementVisible(driver, RegisterPageUI.SUMMARY_MESSAGE_ERROR);
        return getElementText(driver, RegisterPageUI.SUMMARY_MESSAGE_ERROR);
    }

    public String getSuccessMessage() {
        waitForElementVisible(driver, RegisterPageUI.SUCCESS_MESSAGE);
        return getElementText(driver, RegisterPageUI.SUCCESS_MESSAGE);
    }

    public void clickToContinueButton(String button) {
        waitForElementClickable(driver, RegisterPageUI.DYNAMIC_BUTTON, button);
        waitForJqueryReady(driver);
        waitForJavascriptReady(driver);
        clickToElement(driver, RegisterPageUI.DYNAMIC_BUTTON, button);
    }
}
