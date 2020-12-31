package pageObject;

import PageUI.WishlistPageUI;
import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class WishlistPageObject extends AbstractPage {

    private WebDriver driver;

    public WishlistPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void checkToCheckBox(String productName, String action){
        int count;
        if(action.equals("Remove")){
            count = 1;
        } else {
            waitForElementVisible(driver, WishlistPageUI.COLUMN_HEADER, action);
            count = countElementNumber(driver, WishlistPageUI.COLUMN_HEADER, action) + 1;
        }
        waitForElementClickable(driver, WishlistPageUI.DYNAMIC_ACTION, productName, String.valueOf(count));
        clickToElement(driver, WishlistPageUI.DYNAMIC_ACTION, productName, String.valueOf(count));
    }

    public boolean isProductDisplayed(String product) {
        waitForElementVisible(driver, WishlistPageUI.PRODUCT_NAME, product);
        return isElementDisplay(driver, WishlistPageUI.PRODUCT_NAME, product);
    }

    public void clickToShareLink() {
        waitForElementClickable(driver, WishlistPageUI.WISHLIST_URL);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        sleepInSecond(1);
        clickToElement(driver, WishlistPageUI.WISHLIST_URL);
    }

    public String getWishlistTitle() {
        waitForElementVisible(driver, WishlistPageUI.WISHLIST_TITLE);
        return getElementText(driver, WishlistPageUI.WISHLIST_TITLE);
    }

    public String getWishListBody() {
        waitForElementVisible(driver, WishlistPageUI.WISHLIST_BODY);
        return getElementText(driver, WishlistPageUI.WISHLIST_BODY);
    }

}
