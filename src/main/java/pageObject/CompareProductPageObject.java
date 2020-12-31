package pageObject;

import PageUI.CompareProductPageUI;
import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class CompareProductPageObject extends AbstractPage {

    private WebDriver driver;

    public CompareProductPageObject(WebDriver driver) {
        this.driver = driver;
    }


    public boolean isProductDisplayed(String product) {
        waitForElementVisible(driver, CompareProductPageUI.PRODUCT_NAME, product);
        return isElementDisplay(driver, CompareProductPageUI.PRODUCT_NAME, product);
    }

    public void clickToClearListButton() {
        waitForElementClickable(driver, CompareProductPageUI.CLEAR_LIST_BUTTON);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, CompareProductPageUI.CLEAR_LIST_BUTTON);
    }

    public String getCompareListBody() {
        waitForElementVisible(driver, CompareProductPageUI.PRODUCT_BODY);
        return getElementText(driver, CompareProductPageUI.PRODUCT_BODY);
    }
}
