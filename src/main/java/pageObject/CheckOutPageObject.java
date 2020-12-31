package pageObject;

import PageUI.CheckOutPageUI;
import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class CheckOutPageObject extends AbstractPage {
    private WebDriver driver;

    public CheckOutPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void uncheckSameAddress(String param) {
        waitForElementClickable(driver, CheckOutPageUI.DYNAMIC_CHECKBOX, param);
        clickToElement(driver, CheckOutPageUI.DYNAMIC_CHECKBOX, param);
    }

    public void inputToDynamicTextBox(String typeAddress, String value, String filed) {
        String locator = (typeAddress.equals("Billing")) ? CheckOutPageUI.DYNAMIC_BILLING_TEXTBOX : CheckOutPageUI.DYNAMIC_SHIPPING_TEXTBOX;
        waitForElementVisible(driver, locator, filed);
        sendKeyToElement(driver, locator, value, filed);
    }

    public void selectCountryAndState(String typeAddress, String value, String filed) {
        String locator = (typeAddress.equals("Billing")) ? CheckOutPageUI.DYNAMIC_BILLING_DROPDOWN : CheckOutPageUI.DYNAMIC_SHIPPING_DROPDOWN;
        waitForElementVisible(driver, locator, filed);
        selectItemInDropdown(driver, locator, value, filed);
        sleepInSecond(1);
    }

    public void clickToContinueButtonAtStep(String step) {
        waitForElementClickable(driver, CheckOutPageUI.DYNAMIC_CONTINUE_BUTTON, step);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, CheckOutPageUI.DYNAMIC_CONTINUE_BUTTON, step);
    }

    public void selectShippingAddress(String value) {
        waitForElementVisible(driver, CheckOutPageUI.SHIPPING_ADDRESS_DROPDOWN);
        selectItemInDropdown(driver, CheckOutPageUI.SHIPPING_ADDRESS_DROPDOWN, value);
    }

    public void selectDynamicMethod(String method) {
        waitForElementVisible(driver, CheckOutPageUI.DYNAMIC_CHECKBOX, method);
        clickToElement(driver, CheckOutPageUI.DYNAMIC_CHECKBOX, method);
    }

    public String getPaymentInfo() {
        waitForElementVisible(driver, CheckOutPageUI.PAYMENT_INFORMATION);
        return getElementText(driver, CheckOutPageUI.PAYMENT_INFORMATION);
    }

    public String getOrderInfo(String info) {
        waitForElementVisible(driver, CheckOutPageUI.ORDER_INFO, info);
        return getElementText(driver, CheckOutPageUI.ORDER_INFO, info);
    }

    public String getProductInfo(String row, String info) {
        int count;
        if (info.equals("SKU")) {
            count = 1;
        } else {
            waitForElementVisible(driver, CheckOutPageUI.COLUMN_HEADER, info);
            count = countElementNumber(driver, CheckOutPageUI.COLUMN_HEADER, info) + 1;
        }
        waitForElementVisible(driver, CheckOutPageUI.PRODUCT_INFO, row, String.valueOf(count));
        return getElementText(driver, CheckOutPageUI.PRODUCT_INFO, row, String.valueOf(count));
    }

    public String getGitWrappingInfo() {
        waitForElementVisible(driver, CheckOutPageUI.GIFT_WRAPPING_INFO);
        return getElementText(driver, CheckOutPageUI.GIFT_WRAPPING_INFO);
    }

    public String getTotalInfo(String info) {
        waitForElementVisible(driver, CheckOutPageUI.CART_TOTAL_CHECKOUT, info);
        return getElementText(driver, CheckOutPageUI.CART_TOTAL_CHECKOUT, info);
    }

    public String getSuccessMessage() {
        waitForElementVisible(driver, CheckOutPageUI.SUCCESS_CHECKOUT_MESSAGE);
        return getElementText(driver, CheckOutPageUI.SUCCESS_CHECKOUT_MESSAGE);
    }

    public String getOrderNumber() {
        waitForElementVisible(driver, CheckOutPageUI.ORDER_NUMBER);
        return getElementText(driver, CheckOutPageUI.ORDER_NUMBER);
    }

}
