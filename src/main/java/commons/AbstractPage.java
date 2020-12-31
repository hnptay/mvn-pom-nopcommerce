package commons;

import PageUI.AbstractPageUI;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.*;


import java.util.*;
import java.util.concurrent.TimeUnit;

public abstract class AbstractPage {

    //WebBrowser
    public void openUrl(WebDriver driver, String urlValue) {
        driver.get(urlValue);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    protected String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public void back(WebDriver driver) {
        driver.navigate().back();
    }

    protected void forward(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    //Alert
    protected void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    protected void dismissAlert(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    protected void sendKeyToAlert(WebDriver driver, String value) {
        driver.switchTo().alert().sendKeys(value);
    }

    protected String getTextInAlert(WebDriver driver) {
        return driver.switchTo().alert().getText();
    }

    protected void waitForAlertPresence(WebDriver driver) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    //Wait
    protected void waitForElementVisible(WebDriver driver, String locator) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(locator))));
    }

    protected void waitForElementVisible(WebDriver driver, String locator, String... values) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(castToObject(locator, values)))));
    }

    protected void waitForElementsVisible(WebDriver driver, String locator) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElements(findElementsByXpath(driver, locator)));
    }

    protected void waitForElementsVisible(WebDriver driver, String locator, String... values) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElements(findElementsByXpath(driver, castToObject(locator, values))));
    }

    protected void waitForElementInvisible(WebDriver driver, String locator) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
    }

    protected void waitForElementsInvisible(WebDriver driver, String locator) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.invisibilityOfAllElements(findElementsByXpath(driver, locator)));
    }

    protected void waitForElementInvisible(WebDriver driver, String locator, String... values) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(castToObject(locator, values))));
    }

    protected void waitForElementPresence(WebDriver driver, String locator) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(byXpath(locator)));
    }

    protected void waitForElementPresence(WebDriver driver, String locator, String... values) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(byXpath(castToObject(locator, values))));
    }

    protected void waitForElementClickable(WebDriver driver, String locator) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
    }

    protected void waitForElementClickable(WebDriver driver, String locator, String... values) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(byXpath(castToObject(locator, values))));
    }

    protected void waitForJQueryToLoad(WebDriver driver) {
        (new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                try {
                    assert d != null;
                    return (Boolean) ((JavascriptExecutor) d).executeScript("return (window.jQuery != null) && return jQuery.active == 0;");
                } catch (Exception e) {
                    //No jQuery present
                    return true;
                }
            }
        });
    }

    protected void waitForJavascriptReady(WebDriver driver) {
        (new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                assert d != null;
                return ((JavascriptExecutor) d).executeScript("return document.readyState").toString().equals("complete");
            }
        });
    }

    protected void waitForJqueryReady(WebDriver driver) {
        Boolean jQueryDefined = (Boolean) ((JavascriptExecutor) driver).executeScript("return typeof jQuery != 'undefined'");
        if (jQueryDefined) {
            poll(200);
            waitForJQueryToLoad(driver);
            poll(200);
        }
    }

    protected void poll(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Windows
    protected String getWindowHandle(WebDriver driver) {
        return driver.getWindowHandle();
    }

    protected Set<String> getWindowsHandle(WebDriver driver) {
        return driver.getWindowHandles();
    }

    protected void switchToWindowByID(WebDriver driver, String parentWindow) {
        Set<String> allWin = driver.getWindowHandles();
        for (String runWin : allWin) {
            if (!runWin.equals(parentWindow)) {
                driver.switchTo().window(runWin);
                break;
            }
        }
    }

    protected void switchToWindowByTitle(WebDriver driver, String Title) {
        Set<String> allWin = driver.getWindowHandles();
        for (String runWin : allWin) {
            driver.switchTo().window(runWin);
            String currentWin = driver.getTitle();
            if (runWin.equals(currentWin)) {
                break;
            }
        }
    }

    protected boolean areAllSubWindowsClose(WebDriver driver, String parentWindow) {
        Set<String> allWin = driver.getWindowHandles();
        for (String runWin : allWin) {
            if (!runWin.equals(parentWindow)) {
                driver.switchTo().window(runWin);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        return driver.getWindowHandles().size() == 1;
    }

    protected void sleepInSecond(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //WebElement
    protected By byXpath(String locator) {
        return By.xpath(locator);
    }

    protected String castToObject(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    protected WebElement findElementByXpath(WebDriver driver, String locator) {
        WebElement element = null;
        for (int i = 0; i <= 2; i++) {
            try {
                element = driver.findElement(byXpath(locator));
                break;
            } catch(StaleElementReferenceException e){
                e.printStackTrace();
            }
        }
        return element;
    }

    protected List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
        return driver.findElements(byXpath(locator));
    }

    protected void clickToElement(WebDriver driver, String locator) {
        findElementByXpath(driver, locator).click();
    }

    protected void clickToElement(WebDriver driver, String locator, String... values) {
        findElementByXpath(driver, castToObject(locator, values)).click();
    }

    protected String getElementText(WebDriver driver, String locator) {
        return findElementByXpath(driver, locator).getText().trim();
    }

    protected String getElementText(WebDriver driver, String locator, String... values) {
        return findElementByXpath(driver, castToObject(locator, values)).getText().trim();
    }

    protected void sendKeyToElement(WebDriver driver, String locator, String value) {
        element = findElementByXpath(driver, locator);
        element.clear();
        element.sendKeys(value);
    }

    protected void sendKeyToElement(WebDriver driver, String locator, String value, String... values) {
        element = findElementByXpath(driver, castToObject(locator, values));
        element.clear();
        element.sendKeys(value);
    }

    protected void clearKeyInElement(WebDriver driver, String locator) {
        findElementByXpath(driver, locator).clear();
    }

    protected void clearKeyInElement(WebDriver driver, String locator, String... values) {
        findElementByXpath(driver, castToObject(locator, values)).clear();
    }

    protected void selectItemInDropdown(WebDriver driver, String locator, String value) {
        select = new Select(findElementByXpath(driver, locator));
        select.selectByVisibleText(value);
    }

    protected void selectItemInDropdown(WebDriver driver, String locator, String value, String... values) {
        select = new Select(findElementByXpath(driver, castToObject(locator, values)));
        select.selectByVisibleText(value);
    }

    protected String getSelectedItemInDropdown(WebDriver driver, String locator) {
        select = new Select(findElementByXpath(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    protected String getSelectedItemInDropdown(WebDriver driver, String locator, String... values) {
        select = new Select(findElementByXpath(driver, castToObject(locator, values)));
        return select.getFirstSelectedOption().getText();
    }

    protected boolean isDropdownMultiple(WebDriver driver, String locator) {
        select = new Select(findElementByXpath(driver, locator));
        return select.isMultiple();
    }

    protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String allItemLocator, String expectedItem) {
        js = (JavascriptExecutor) driver;
        clickToElement(driver, parentLocator);
        sleepInSecond(1);
        List<WebElement> allItem = findElementsByXpath(driver, allItemLocator);
        for (WebElement currentItem : allItem) {
            if (currentItem.getText().equals(expectedItem)) {
                if (currentItem.isDisplayed()) {
                    currentItem.click();
                } else {
                    js.executeScript("arguments[0].scrollIntoView();", currentItem);
                    sleepInSecond(1);
                    js.executeScript("arguments[0].click();", currentItem);
                }
                sleepInSecond(1);
                break;
            }
        }
    }

    protected void selectMultipleItemInCustomDropDown(WebDriver driver, String locator, String allItemLocator, String allItemSelectedLocator, String[] expectedItems) {
        js = (JavascriptExecutor) driver;
        clickToElement(driver, locator);
        sleepInSecond(1);
        List<WebElement> allItem = findElementsByXpath(driver, allItemLocator);
        for (WebElement currentItem : allItem) {
            for (String expectedItem : expectedItems) {
                if (currentItem.getText().equals(expectedItem)) {
                    if (currentItem.isDisplayed()) {
                        currentItem.click();
                    } else {
                        js.executeScript("arguments[0].scrollIntoView();", currentItem);
                        sleepInSecond(1);
                        js.executeScript("arguments[0].click();", currentItem);
                    }
                }
            }
            List<WebElement> allItemSelected = findElementsByXpath(driver, allItemSelectedLocator);
            if (allItemSelected.size() == expectedItems.length) {
                break;
            }
        }
    }

    protected boolean checkItemSelectedInCustomDropDown(WebDriver driver, String allItemSelectedLocator, String[] expectedItems) {
        js = (JavascriptExecutor) driver;
        int numberOfItem = 0;
        List<WebElement> allItemSelected = findElementsByXpath(driver, allItemSelectedLocator);
        for (WebElement itemSelected : allItemSelected) {
            for (String expectedItem : expectedItems) {
                if (itemSelected.getText().contains(expectedItem)) {
                    numberOfItem++;
                } else if (js.executeScript("arguments[0].textContent();", itemSelected).equals(expectedItem)) {
                    numberOfItem++;
                }
            }
        }
        return numberOfItem == expectedItems.length;
    }

    protected String getTextByJs(WebDriver driver, String locator) {
        js = (JavascriptExecutor) driver;
        return js.executeScript("return arguments[0].textContent();", findElementByXpath(driver, locator)).toString();
    }

    protected int countElementNumber(WebDriver driver, String locator) {
        return findElementsByXpath(driver, locator).size();
    }

    protected int countElementNumber(WebDriver driver, String locator, String... values) {
        return findElementsByXpath(driver, castToObject(locator, values)).size();
    }

    protected String getAttributeValue(WebDriver driver, String locator, String ID) {
        return findElementByXpath(driver, locator).getAttribute(ID);
    }

    protected String getAttributeValue(WebDriver driver, String locator, String ID, String... values) {
        return findElementByXpath(driver, castToObject(locator, values)).getAttribute(ID);
    }

    protected void checkTheCheckbox(WebDriver driver, String locator) {
        element = findElementByXpath(driver, locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void unCheckTheCheckbox(WebDriver driver, String locator) {
        element = findElementByXpath(driver, locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    protected boolean isElementDisplay(WebDriver driver, String locator) {
        try {
            return findElementByXpath(driver, locator).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected boolean isElementDisplay(WebDriver driver, String locator, String... values) {
        try {
            return findElementByXpath(driver, castToObject(locator, values)).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected boolean isElementSelected(WebDriver driver, String locator) {
        return findElementByXpath(driver, locator).isSelected();
    }

    protected boolean isElementEnable(WebDriver driver, String locator) {
        return findElementByXpath(driver, locator).isEnabled();
    }

    protected void overrideGlobalTimeout(WebDriver driver, long timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    protected boolean isElementUndisplayed(WebDriver driver, String locator) {
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = findElementsByXpath(driver, locator);
        if (elements.size() == 0) {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return true;
        } else {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return false;
        }
    }

    protected boolean isElementUndisplayed(WebDriver driver, String locator, String... values) {
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = findElementsByXpath(driver, castToObject(locator, values));
        if (elements.size() == 0) {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return true;
        } else {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return false;
        }
    }

    //Frame Iframe
    protected void switchToFrameOrIframe(WebDriver driver, String locator) {
        driver.switchTo().frame(findElementByXpath(driver, locator));
    }

    protected void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    //Actions
    protected void hoverMouseToElement(WebDriver driver, String locator) {
        actions = new Actions(driver);
        actions.moveToElement(findElementByXpath(driver, locator)).perform();
    }

    protected void doubleClickToElement(WebDriver driver, String locator) {
        actions = new Actions(driver);
        actions.doubleClick(findElementByXpath(driver, locator)).perform();
    }

    protected void rightClick(WebDriver driver, String locator) {
        actions = new Actions(driver);
        actions.contextClick(findElementByXpath(driver, locator)).perform();
    }

    protected void sendKeyboardToElement(WebDriver driver, String locator, Keys keys) {
        actions = new Actions(driver);
        element = findElementByXpath(driver, locator);
        actions.sendKeys(element, keys).perform();
    }

    protected void sendKeyboardToElement(WebDriver driver, String locator, Keys keys, String... values) {
        actions = new Actions(driver);
        element = findElementByXpath(driver, castToObject(locator, values));
        actions.sendKeys(element, keys).perform();
    }

    protected void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
        actions = new Actions(driver);
        WebElement from = findElementByXpath(driver, sourceLocator);
        WebElement to = findElementByXpath(driver, targetLocator);
        actions.dragAndDrop(from, to).build().perform();
    }

    //JavascriptExecutor
    protected void clickToElementByJs(WebDriver driver, String locator) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        js.executeScript("arguments[0].click();", element);
    }

    protected Object executeForBrowser(WebDriver driver, String javaScript) {
        js = (JavascriptExecutor) driver;
        return js.executeScript(javaScript);
    }

    protected boolean verifyInnerText(WebDriver driver, String expectedText) {
        js = (JavascriptExecutor) driver;
        String actualText = (String) js.executeScript(
                "return document.documentElement.innerText.match('" + expectedText + "')[0]");
        return actualText.equals(expectedText);
    }

    protected void scrollIntoView(WebDriver driver, String locator) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected void highlightElement(WebDriver driver, String locator) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])",
                element, "style", "border: 5px solid red; border-style: dashed;");
        sleepInSecond(1);
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])",
                element, "style", originalStyle);
    }

    protected void sendKeyToElementByJs(WebDriver driver, String locator, String value) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        js.executeScript("arguments[0].setAttribute('value','" + value + "')", element);
    }

    protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "')", element);
    }

    protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String... values) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, castToObject(locator, values));
        js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "')", element);
    }

    protected boolean isImageLoaded(WebDriver driver, String locator) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        return (boolean) js.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
    }

    protected void uploadMultipleFiles(WebDriver driver, String locator, String... fileNames) {
        String allFiles = "";
        for (String file : fileNames) {
            allFiles += GlobalConstants.UPLOAD_FOLDER + file + "\n";
        }
        allFiles = allFiles.trim();
        sendKeyToElement(driver, locator, allFiles);
    }

    //Sort
    protected boolean isDataSortAscending(WebDriver driver, String locator) {
        ArrayList<String> listData = new ArrayList<>();
        List<WebElement> allElement = findElementsByXpath(driver, locator);
        for (WebElement element : allElement) listData.add(element.getText());
        ArrayList<String> sortData = new ArrayList<>(listData);
        Collections.sort(sortData);
        return listData.equals(sortData);
    }

    protected boolean isDataSortDescending(WebDriver driver, String locator) {
        ArrayList<String> listData = new ArrayList<>();
        List<WebElement> allElement = findElementsByXpath(driver, locator);
        for (WebElement element : allElement) listData.add(element.getText());
        ArrayList<String> sortData = new ArrayList<>(listData);
        Collections.sort(sortData);
        Collections.reverse(sortData);
        return listData.equals(sortData);
    }

    protected boolean isPriceDataSortAscending(WebDriver driver, String locator) {
        ArrayList<Float> listPrice = new ArrayList<>();
        List<WebElement> allElement = findElementsByXpath(driver, locator);
        for (WebElement element : allElement) {
            listPrice.add(Float.parseFloat(element.getText().trim().replaceAll("[$,]", "")));
        }
        System.out.println("---------------UI-----------------");
        for (Float price : listPrice) {
            System.out.println(price);
        }
        ArrayList<Float> sortPrice = new ArrayList<>(listPrice);
        Collections.sort(sortPrice);
        System.out.println("---------------nonUI---------------");
        for (Float price : sortPrice) {
            System.out.println(price);
        }
        return listPrice.equals(sortPrice);
    }

    protected boolean isPriceDataSortDescending(WebDriver driver, String locator) {
        ArrayList<Float> listPrice = new ArrayList<>();
        List<WebElement> allElement = findElementsByXpath(driver, locator);
        for (WebElement element : allElement) {
            listPrice.add(Float.parseFloat(element.getText().trim().replaceAll("[$,]", "")));
        }
        System.out.println("---------------UI-----------------");
        for (Float price : listPrice) {
            System.out.println(price);
        }
        ArrayList<Float> sortPrice = new ArrayList<>(listPrice);
        Collections.sort(sortPrice);
        Collections.reverse(sortPrice);
        System.out.println("---------------nonUI---------------");
        for (Float price : sortPrice) {
            System.out.println(price);
        }
        return listPrice.equals(sortPrice);
    }

    /* Nopcommerce Dynamic Page Component */
    public void clickToMenuBar(WebDriver driver, String param) {
        String locator = (param.equals("Wishlist")) || (param.equals("Shopping cart")) ? AbstractPageUI.DYNAMIC_WISHLIST_SHOPPING_LINK : AbstractPageUI.DYNAMIC_LOGOUT_LOGIN_LINK;
        waitForElementClickable(driver, locator, param);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, locator, param);
    }

    public AbstractPage clickToMenuLink(WebDriver driver, String menuLink) {
        waitForElementClickable(driver, AbstractPageUI.DYNAMIC_CATEGORY_MENU, menuLink);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, AbstractPageUI.DYNAMIC_CATEGORY_MENU, menuLink);
        switch (menuLink) {
            case "Computers" -> {
                return new ComputersPageObject(driver);
            }
            case "Electronics" -> {
                return new ElectronicsPageObject(driver);
            }
            default -> {
                return new HomePageObject(driver);
            }
        }
    }

    public void clickToDynamicButton(WebDriver driver, String button) {
        waitForElementClickable(driver, AbstractPageUI.DYNAMIC_BUTTON, button);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, AbstractPageUI.DYNAMIC_BUTTON, button);
    }

    public void sendKeyToReviewTextBox(WebDriver driver, String value, String param){
        waitForElementVisible(driver, AbstractPageUI.REVIEW_TEXTBOX, param);
        sendKeyToElement(driver, AbstractPageUI.REVIEW_TEXTBOX, value, param);
    }

    public void sendKeyToReviewTextArea(WebDriver driver, String value, String param){
        waitForElementVisible(driver, AbstractPageUI.REVIEW_TEXTAREA, param);
        sendKeyToElement(driver, AbstractPageUI.REVIEW_TEXTAREA, value, param);
    }

    public void selectReviewRating(WebDriver driver, String param){
        waitForElementClickable(driver, AbstractPageUI.REVIEW_DYNAMIC_RADIO_BUTTON, param);
        clickToElement(driver, AbstractPageUI.REVIEW_DYNAMIC_RADIO_BUTTON, param);
    }

    public String getSummaryMessageInfo(WebDriver driver){
        waitForElementVisible(driver, AbstractPageUI.SUMMARY_MESSAGE_INFO);
        return getElementText(driver, AbstractPageUI.SUMMARY_MESSAGE_INFO);
    }

    public AbstractPage clickToCustomerServiceMenuLink(WebDriver driver, String menuLink){
        waitForElementClickable(driver, AbstractPageUI.CUSTOMER_SERVICE_MENU_LINK, menuLink);
        waitForJavascriptReady(driver);
        waitForJqueryReady(driver);
        clickToElement(driver, AbstractPageUI.CUSTOMER_SERVICE_MENU_LINK, menuLink);
        switch(menuLink){
            case "Compare products list" -> {
                return new CompareProductPageObject(driver);
            }
            default -> {
                return new SearchPageObject(driver);
            }
        }
    }


    private Select select;
    private JavascriptExecutor js;
    private WebDriverWait wait;
    private WebElement element;
    private Actions actions;
}
