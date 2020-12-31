package pageObject;

import PageUI.ComputersPageUI;
import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ComputersPageObject extends AbstractPage {
    private WebDriver driver;

    public ComputersPageObject (WebDriver driver){
        this.driver = driver;
    }

    public void clickToDynamicSubListLink(String linkName){
        waitForElementClickable(driver, ComputersPageUI.DYNAMIC_SUBLIST_MENU, linkName);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, ComputersPageUI.DYNAMIC_SUBLIST_MENU, linkName);
    }

    public void clickToDynamicProduct(String productName){
        waitForElementClickable(driver, ComputersPageUI.DYNAMIC_PRODUCT_LINK, productName);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, ComputersPageUI.DYNAMIC_PRODUCT_LINK, productName);
    }

    public void clickToReviewLink(String linkName){
        waitForElementClickable(driver, ComputersPageUI.REVIEW_LINK, linkName);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, ComputersPageUI.REVIEW_LINK, linkName);
    }

    public void clickToDynamicDropdown(String value, String param) {
        waitForElementVisible(driver, ComputersPageUI.DYNAMIC_DROPDOWN, param);
        selectItemInDropdown(driver, ComputersPageUI.DYNAMIC_DROPDOWN, value, param);
    }

    public boolean isNameSortAscending() {
        waitForElementsVisible(driver, ComputersPageUI.PRODUCT_NAME_ITEMS);
        return isDataSortAscending(driver, ComputersPageUI.PRODUCT_NAME_ITEMS);
    }

    public boolean isNameSortDescending() {
        waitForElementsVisible(driver, ComputersPageUI.PRODUCT_NAME_ITEMS);
        return isDataSortDescending(driver, ComputersPageUI.PRODUCT_NAME_ITEMS);
    }


    public boolean isPriceSortAscending() {
        waitForElementsVisible(driver, ComputersPageUI.PRODUCT_PRICE_ITEMS);
        return isPriceDataSortAscending(driver, ComputersPageUI.PRODUCT_PRICE_ITEMS);
    }


    public boolean isPriceSortDescending() {
        waitForElementsVisible(driver, ComputersPageUI.PRODUCT_PRICE_ITEMS);
        return isPriceDataSortDescending(driver, ComputersPageUI.PRODUCT_PRICE_ITEMS);
    }

    public boolean isNumberOfItemDisplayed(int itemNumber) {
        waitForElementsVisible(driver, ComputersPageUI.PRODUCT_NAME_ITEMS);
        List<WebElement> items = findElementsByXpath(driver, ComputersPageUI.PRODUCT_NAME_ITEMS);
        return items.size() <= itemNumber;
    }

    public boolean isPagingIconDisplayed(String param) {
        waitForElementVisible(driver, ComputersPageUI.DYNAMIC_PAGING, param);
        return isElementDisplay(driver, ComputersPageUI.DYNAMIC_PAGING, param);
    }


    public void clickToDynamicPaging(String param) {
        waitForElementClickable(driver, ComputersPageUI.DYNAMIC_PAGING, param);
        clickToElement(driver, ComputersPageUI.DYNAMIC_PAGING, param);
    }

    public boolean isPagingIconUndisplayed(String param) {
        return isElementUndisplayed(driver, ComputersPageUI.DYNAMIC_PAGING, param);
    }

    public void clickToDynamicButton(String productName, String button){
        waitForElementClickable(driver, ComputersPageUI.DYNAMIC_BUTTON, productName, button);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, ComputersPageUI.DYNAMIC_BUTTON, productName, button);
        sleepInSecond(1);
    }

    public void clickToDynamicOverViewButton(String button) {
        waitForElementClickable(driver, ComputersPageUI.DYNAMIC_OVERVIEW_BUTTON, button);
        waitForJqueryReady(driver);
        waitForJavascriptReady(driver);
        clickToElement(driver, ComputersPageUI.DYNAMIC_OVERVIEW_BUTTON, button);
    }

    public String getNotificationMessage() {
        waitForElementVisible(driver, ComputersPageUI.BAR_NOTIFICATION);
        return getElementText(driver, ComputersPageUI.BAR_NOTIFICATION);
    }

    public void closeNotifyBar() {
        waitForElementClickable(driver, ComputersPageUI.CLOSE_BAR);
        clickToElement(driver, ComputersPageUI.CLOSE_BAR);
    }
}
