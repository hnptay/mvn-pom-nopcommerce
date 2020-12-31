package pageObject;

import PageUI.SearchPageUI;
import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends AbstractPage {

    WebDriver driver;

    public SearchPageObject(WebDriver driver){
        this.driver = driver;
    }

    public boolean isSearchResultDisplayed(String[] products){
        int count = 0;
        waitForElementsVisible(driver, SearchPageUI.SEARCH_RESULT_PRODUCT_TITLE);
        List<WebElement> items = findElementsByXpath(driver, SearchPageUI.SEARCH_RESULT_PRODUCT_TITLE);
        for(WebElement item:items){
            for (String product:products){
                if(item.getText().trim().equals(product)) count++;
            }
        }
        return count == products.length;
    }

    public void clickToSearchButton() {
        waitForElementClickable(driver, SearchPageUI.SEARCH_BUTTON);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, SearchPageUI.SEARCH_BUTTON);
    }

    public String getErrorMessage() {
        waitForElementVisible(driver, SearchPageUI.SEARCH_RESULT_ERROR);
        return getElementText(driver, SearchPageUI.SEARCH_RESULT_ERROR);
    }

    public void sendKeyToSearchTextBox(String value) {
        waitForElementVisible(driver, SearchPageUI.SEARCH_TEXTBOX);
        sendKeyToElement(driver, SearchPageUI.SEARCH_TEXTBOX, value);
    }

    public void selectDynamicCheckBox(String param) {
        waitForElementClickable(driver, SearchPageUI.DYNAMIC_CHECKBOX, param);
        clickToElement(driver, SearchPageUI.DYNAMIC_CHECKBOX, param);
    }

    public void dynamicSelect(String value, String param) {
        waitForElementVisible(driver, SearchPageUI.DYNAMIC_DROPDOWN, param);
        selectItemInDropdown(driver, SearchPageUI.DYNAMIC_DROPDOWN, value, param);
    }

    public void sendKeyToPriceRange(String value, String param) {
        waitForElementVisible(driver, SearchPageUI.PRICE_RANGE, param);
        sendKeyToElement(driver, SearchPageUI.PRICE_RANGE, value, param);
    }
}
