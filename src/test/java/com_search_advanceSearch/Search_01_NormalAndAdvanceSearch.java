package com_search_advanceSearch;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.HomePageObject;
import pageObject.SearchPageObject;
import testdata.SearchModule;

public class Search_01_NormalAndAdvanceSearch extends AbstractTest {
    WebDriver driver;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url){
        log.info("Pre-condition - STEP 01: Open browser");
        driver = getBrowserDriver(browserName, url);
        log.info("Pre-condition - STEP 02: Open and Init home page");
        homePage = PageGeneratorManager.getHomePage(driver);
        log.info("Pre-condition - STEP 03: Open and Init search page");
        searchPage = (SearchPageObject) homePage.clickToCustomerServiceMenuLink(driver, "Search");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        log.info("Post-condition - STEP 01: Close browser");
        closeBrowserAndDriver();
    }

    @Test
    public void TC_01_searchWithEmptyData(){
        log.info("TC_01_searchWithEmptyData - STEP 01: Click to Search button");
        searchPage.clickToSearchButton();
        log.info("TC_01_searchWithEmptyData - STEP 02: verify error message diplayed");
        verifyEquals(searchPage.getErrorMessage(), "Search term minimum length is 3 characters");
    }

    @Test
    public void TC_02_searchWithDataNotExist(){
        log.info("TC_02_searchWithDataNotExist - STEP 01: Input to search textbox");
        searchPage.sendKeyToSearchTextBox("Macbook Pro 2050");
        log.info("TC_02_searchWithDataNotExist - STEP 02: CLick to search button");
        searchPage.clickToSearchButton();
        log.info("TC_02_searchWithDataNotExist - STEP 03: Verify message displayed");
        verifyEquals(searchPage.getErrorMessage(), "No products were found that matched your criteria.");
    }

    @Test
    public void TC_03_searchRelative(){
        log.info("TC_03_searchRelative - STEP 01: Input to search textbox");
        searchPage.sendKeyToSearchTextBox("Lenovo");
        log.info("TC_03_searchRelative - STEP 02: Click to search button");
        searchPage.clickToSearchButton();
        log.info("TC_03_searchRelative - STEP 03: Verify product displayed");
        verifyTrue(searchPage.isSearchResultDisplayed(SearchModule.LENOVO_PRODUCT));
    }

    @Test
    public void TC_04_searchAbsolute(){
        log.info("TC_04_searchAbsolute - STEP 01: Input to search textbox");
        searchPage.sendKeyToSearchTextBox("Lenovo Thinkpad X1 Carbon");
        log.info("TC_04_searchAbsolute - STEP 02: Click to search button");
        searchPage.clickToSearchButton();
        log.info("TC_04_searchAbsolute - STEP 03: Verify result displayed");
        verifyTrue(searchPage.isSearchResultDisplayed(SearchModule.LENOVO_THINKPAD));
    }

    @Test
    public void TC_05_searchWithParentCategories(){
        log.info("TC_05_searchWithParentCategories - STEP 01: Input to search textbox");
        searchPage.sendKeyToSearchTextBox("Apple Macbook Pro");
        log.info("TC_05_searchWithParentCategories - STEP 02: Select advance search");
        searchPage.selectDynamicCheckBox("Advanced search");
        log.info("TC_05_searchWithParentCategories - STEP 03: Select computer");
        searchPage.dynamicSelect("Computers", "Category");
        log.info("TC_05_searchWithParentCategories - STEP 04: Click to search button");
        searchPage.clickToSearchButton();
        log.info("TC_05_searchWithParentCategories - STEP 05: Verify error message displayed");
        verifyEquals(searchPage.getErrorMessage(), "No products were found that matched your criteria.");
    }

    @Test
    public void TC_06_searchWithSubCategories(){
        log.info("TC_06_searchWithSubCategories - STEP 01: Open Url search");
        searchPage.openUrl(driver, SearchModule.SEARCH_URL);
        log.info("TC_06_searchWithSubCategories - STEP 02: Input to search textbox");
        searchPage.sendKeyToSearchTextBox("Apple Macbook Pro");
        log.info("TC_06_searchWithSubCategories - STEP 03: Select advance search");
        searchPage.selectDynamicCheckBox("Advanced search");
        log.info("TC_06_searchWithSubCategories - STEP 04: Select Computer");
        searchPage.dynamicSelect("Computers", "Category");
        log.info("TC_06_searchWithSubCategories - STEP 05: Select Auto search sub category");
        searchPage.selectDynamicCheckBox("Automatically search sub categories");
        log.info("TC_06_searchWithSubCategories - STEP 06: Click to search button");
        searchPage.clickToSearchButton();
        log.info("TC_06_searchWithSubCategories - STEP 07: Verify result");
        verifyTrue(searchPage.isSearchResultDisplayed(SearchModule.MACBOOK));
    }

    @Test
    public void TC_07_searchWithIncorrectManufacturer(){
        log.info("TC_07_searchWithIncorrectManufacturer - STEP 01: Open search URL");
        searchPage.openUrl(driver, SearchModule.SEARCH_URL);
        log.info("TC_07_searchWithIncorrectManufacturer - STEP 02: Input to search textbox");
        searchPage.sendKeyToSearchTextBox("Apple Macbook Pro");
        log.info("TC_07_searchWithIncorrectManufacturer - STEP 03: Select advance search");
        searchPage.selectDynamicCheckBox("Advanced search");
        log.info("TC_07_searchWithIncorrectManufacturer - STEP 04: Select Computer");
        searchPage.dynamicSelect("Computers", "Category");
        log.info("TC_07_searchWithIncorrectManufacturer - STEP 05: Select auto search sub categoryes");
        searchPage.selectDynamicCheckBox("Automatically search sub categories");
        log.info("TC_07_searchWithIncorrectManufacturer - STEP 06: Select HP");
        searchPage.dynamicSelect("HP", "Manufacturer");
        log.info("TC_07_searchWithIncorrectManufacturer - STEP 07: Click search button");
        searchPage.clickToSearchButton();
        log.info("TC_07_searchWithIncorrectManufacturer - STEP 08: Verify result");
        verifyEquals(searchPage.getErrorMessage(), "No products were found that matched your criteria.");
    }

    @Test
    public void TC_08_searchWithCorrectManufacturer(){
        log.info("TC_08_searchWithCorrectManufacturer - STEP 01: Open search URL");
        searchPage.openUrl(driver, SearchModule.SEARCH_URL);
        log.info("TC_08_searchWithCorrectManufacturer - STEP 02: Input to search textbox");
        searchPage.sendKeyToSearchTextBox("Apple Macbook Pro");
        log.info("TC_08_searchWithCorrectManufacturer - STEP 03: Select advance search");
        searchPage.selectDynamicCheckBox("Advanced search");
        log.info("TC_08_searchWithCorrectManufacturer - STEP 04: Select Computer");
        searchPage.dynamicSelect("Computers", "Category");
        log.info("TC_08_searchWithCorrectManufacturer - STEP 05: Select auto search sub categoryes");
        searchPage.selectDynamicCheckBox("Automatically search sub categories");
        log.info("TC_08_searchWithCorrectManufacturer - STEP 06: Select Apple");
        searchPage.dynamicSelect("Apple", "Manufacturer");
        log.info("TC_08_searchWithCorrectManufacturer - STEP 07: Click search button");
        searchPage.clickToSearchButton();
        log.info("TC_08_searchWithCorrectManufacturer - STEP 08: Verify result");
        verifyTrue(searchPage.isSearchResultDisplayed(SearchModule.MACBOOK));
    }

    @Test
    public void TC_09_searchInPriceRange(){
        log.info("TC_09_searchInPriceRange - STEP 01: Open search URL");
        searchPage.openUrl(driver, SearchModule.SEARCH_URL);
        log.info("TC_09_searchInPriceRange - STEP 02: Input to search textbox");
        searchPage.sendKeyToSearchTextBox("Apple Macbook Pro");
        log.info("TC_09_searchInPriceRange - STEP 03: Select advance search");
        searchPage.selectDynamicCheckBox("Advanced search");
        log.info("TC_09_searchInPriceRange - STEP 04: Select Computer");
        searchPage.dynamicSelect("Computers", "Category");
        log.info("TC_09_searchInPriceRange - STEP 05: Select auto search sub categoryes");
        searchPage.selectDynamicCheckBox("Automatically search sub categories");
        log.info("TC_09_searchInPriceRange - STEP 06: Select Apple");
        searchPage.dynamicSelect("Apple", "Manufacturer");
        log.info("TC_09_searchInPriceRange - STEP 07: Input range");
        searchPage.sendKeyToPriceRange("1000", "from");
        log.info("TC_09_searchInPriceRange - STEP 08: Input range");
        searchPage.sendKeyToPriceRange("2000", "to");
        log.info("TC_09_searchInPriceRange - STEP 09: Click search button");
        searchPage.clickToSearchButton();
        log.info("TC_09_searchInPriceRange - STEP 10: Verify result");
        verifyTrue(searchPage.isSearchResultDisplayed(SearchModule.MACBOOK));
    }

    @Test
    public void TC_10_searchOutPriceRange(){
        log.info("TC_10_searchOutPriceRange - STEP 01: Open search URL");
        searchPage.openUrl(driver, SearchModule.SEARCH_URL);
        log.info("TC_10_searchOutPriceRange - STEP 02: Input to search textbox");
        searchPage.sendKeyToSearchTextBox("Apple Macbook Pro");
        log.info("TC_10_searchOutPriceRange - STEP 03: Select advance search");
        searchPage.selectDynamicCheckBox("Advanced search");
        log.info("TC_10_searchOutPriceRange - STEP 04: Select Computer");
        searchPage.dynamicSelect("Computers", "Category");
        log.info("TC_10_searchOutPriceRange - STEP 05: Select auto search sub categoryes");
        searchPage.selectDynamicCheckBox("Automatically search sub categories");
        log.info("TC_10_searchOutPriceRange - STEP 06: Select Apple");
        searchPage.dynamicSelect("Apple", "Manufacturer");
        log.info("TC_10_searchOutPriceRange - STEP 07: Input range");
        searchPage.sendKeyToPriceRange("1000", "from");
        log.info("TC_10_searchOutPriceRange - STEP 08: Input range");
        searchPage.sendKeyToPriceRange("1700", "to");
        log.info("TC_10_searchOutPriceRange - STEP 09: Click search button");
        searchPage.clickToSearchButton();
        log.info("TC_10_searchOutPriceRange - STEP 10: Verify result");
        verifyEquals(searchPage.getErrorMessage(), "No products were found that matched your criteria.");
    }

    @Test
    public void TC_11_searchOutPriceRange(){
        log.info("TC_11_searchOutPriceRange - STEP 01: Open search URL");
        searchPage.openUrl(driver, SearchModule.SEARCH_URL);
        log.info("TC_11_searchOutPriceRange - STEP 02: Input to search textbox");
        searchPage.sendKeyToSearchTextBox("Apple Macbook Pro");
        log.info("TC_11_searchOutPriceRange - STEP 03: Select advance search");
        searchPage.selectDynamicCheckBox("Advanced search");
        log.info("TC_11_searchOutPriceRange - STEP 04: Select Computer");
        searchPage.dynamicSelect("Computers", "Category");
        log.info("TC_11_searchOutPriceRange - STEP 05: Select auto search sub categoryes");
        searchPage.selectDynamicCheckBox("Automatically search sub categories");
        log.info("TC_11_searchOutPriceRange - STEP 06: Select Apple");
        searchPage.dynamicSelect("Apple", "Manufacturer");
        log.info("TC_11_searchOutPriceRange - STEP 07: Input range");
        searchPage.sendKeyToPriceRange("1900", "from");
        log.info("TC_11_searchOutPriceRange - STEP 08: Input range");
        searchPage.sendKeyToPriceRange("5000", "to");
        log.info("TC_11_searchOutPriceRange - STEP 09: Click search button");
        searchPage.clickToSearchButton();
        log.info("TC_11_searchOutPriceRange - STEP 10: Verify result");
        verifyEquals(searchPage.getErrorMessage(), "No products were found that matched your criteria.");
    }

    private HomePageObject homePage;
    private SearchPageObject searchPage;
}
