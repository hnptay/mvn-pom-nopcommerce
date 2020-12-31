package pageObject;

import PageUI.MyAccountPageUI;
import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class MyAccountPageObject extends AbstractPage {
    WebDriver driver;

    public MyAccountPageObject(WebDriver driver){
        this.driver = driver;
    }

    public void selectGender(String gender) {
        waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_GENDER_RADIO_BUTTON, gender);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, MyAccountPageUI.DYNAMIC_GENDER_RADIO_BUTTON, gender);
    }

    public void inputToDynamicTextBox(String value, String param) {
        waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_TEXTBOX, param);
        sendKeyToElement(driver, MyAccountPageUI.DYNAMIC_TEXTBOX, value, param);
    }


    public void selectDynamicSelector(String value, String param) {
        waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_SELECTOR, param);
        selectItemInDropdown(driver, MyAccountPageUI.DYNAMIC_SELECTOR, value, param);
    }

    public String getValueInDynamicText(String param) {
        waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_TEXTBOX, param);
        return getAttributeValue(driver, MyAccountPageUI.DYNAMIC_TEXTBOX, "value", param);
    }

    public String getDynamicSelectedText(String param) {
        waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_SELECTOR, param);
        return getSelectedItemInDropdown(driver, MyAccountPageUI.DYNAMIC_SELECTOR, param);
    }

    public void clickToMenuLink(String menu) {
        waitForElementClickable(driver, MyAccountPageUI.MY_ACCOUNT_MENU_LINK, menu);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, MyAccountPageUI.MY_ACCOUNT_MENU_LINK, menu);
    }

    public void clickToDynamicButton(String button) {
        waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_BUTTON, button);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, MyAccountPageUI.DYNAMIC_BUTTON, button);
    }

    public String getDynamicAddressInfo(String param) {
         waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_ADDRESS_INFO, param);
         return getElementText(driver, MyAccountPageUI.DYNAMIC_ADDRESS_INFO, param);
    }

    public String getMessageInfo() {
        waitForElementVisible(driver, MyAccountPageUI.MESSAGE_INFO);
        return getElementText(driver, MyAccountPageUI.MESSAGE_INFO);
    }

    public boolean isReviewDisplayed(String title){
        waitForElementVisible(driver, MyAccountPageUI.REVIEW_TITLE, title);
        return isElementDisplay(driver, MyAccountPageUI.REVIEW_TITLE, title);
    }

    public boolean isOrderProductDisplayed(String orderNumber) {
        waitForElementVisible(driver, MyAccountPageUI.ORDER_NUMBER, orderNumber);
        return isElementDisplay(driver, MyAccountPageUI.ORDER_NUMBER, orderNumber);
    }
}
