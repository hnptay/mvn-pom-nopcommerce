package pageObject;

import PageUI.CartPageUI;
import commons.AbstractPage;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.openqa.selenium.WebDriver;

public class CartPageObject extends AbstractPage {
    private WebDriver driver;

    public CartPageObject(WebDriver driver){
        this.driver = driver;
    }


    public void clickToEstimateShipping() {
        waitForElementClickable(driver, CartPageUI.ESTIMATE_SHIPPING_BUTTON);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, CartPageUI.ESTIMATE_SHIPPING_BUTTON);
    }

    public void selectCountryAndState(String value, String param) {
        waitForElementVisible(driver, CartPageUI.COUNTRY_STATE_DROPDOWN, param);
        selectItemInDropdown(driver, CartPageUI.COUNTRY_STATE_DROPDOWN, value, param);
    }

    public void selectShippingMethod(String method) {
        waitForElementClickable(driver, CartPageUI.DYNAMIC_RADIO_BUTTON, method);
        clickToElement(driver, CartPageUI.DYNAMIC_RADIO_BUTTON, method);
    }

    public void selectGiftWrapping(String value) {
        waitForElementVisible(driver, CartPageUI.GIFT_WRAPPING_DROPDOWN);
        selectItemInDropdown(driver, CartPageUI.GIFT_WRAPPING_DROPDOWN, value);
        sleepInSecond(1);
    }

    public String getShoppingCartInfo(String info, String row) {
        waitForElementVisible(driver, CartPageUI.COLUMN_HEADER, info);
        int count = countElementNumber(driver, CartPageUI.COLUMN_HEADER, info) + 1;
        waitForElementVisible(driver, CartPageUI.PRODUCT_INFO, row, String.valueOf(count));
        return getElementText(driver, CartPageUI.PRODUCT_INFO, row, String.valueOf(count));
    }

    public String getGitWrappingInfo() {
        waitForElementVisible(driver, CartPageUI.GIFT_WRAPPING_INFO);
        return getElementText(driver, CartPageUI.GIFT_WRAPPING_INFO);
    }

    public String getCartTotalInfo(String info) {
        waitForElementVisible(driver, CartPageUI.CART_TOTAL_INFO, info);
        return getElementText(driver, CartPageUI.CART_TOTAL_INFO, info);
    }

    public void selectAgreeCheckBox() {
        waitForElementClickable(driver, CartPageUI.TERMS_CHECKBOX);
        clickToElement(driver, CartPageUI.TERMS_CHECKBOX);
    }

    public void clickToCheckOutButton() {
        waitForElementClickable(driver, CartPageUI.CHECKOUT_BUTTON);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, CartPageUI.CHECKOUT_BUTTON);
    }

    public void inputToZipCodeTextBox(String value) {
        waitForElementVisible(driver, CartPageUI.ZIP_CODE_TEXTBOX);
        sendKeyToElement(driver, CartPageUI.ZIP_CODE_TEXTBOX, value);
    }

    public String getQuantityProduct(String info, String row) {
        waitForElementVisible(driver, CartPageUI.COLUMN_HEADER, info);
        int count = countElementNumber(driver, CartPageUI.COLUMN_HEADER, info) + 1;
        waitForElementVisible(driver, CartPageUI.QUANTITY_NUMBER, row, String.valueOf(count));
        return getAttributeValue(driver, CartPageUI.QUANTITY_NUMBER, "value", row, String.valueOf(count));
    }
}
