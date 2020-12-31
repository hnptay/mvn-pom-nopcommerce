package com_sort_display_paging;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.ComputersPageObject;
import pageObject.HomePageObject;

public class Sort_01_BasicSort extends AbstractTest {
    WebDriver driver;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url){
        log.info("Pre-condition - STEP 01: Open browser");
        driver = getBrowserDriver(browserName, url);
        log.info("Pre-condition - STEP 02: Open and Init home page");
        homePage = PageGeneratorManager.getHomePage(driver);
        log.info("Pre-condition - STEP 03: Open and Init Computer page");
        computersPage = (ComputersPageObject) homePage.clickToMenuLink(driver, "Computers");
        log.info("Pre-condition - STEP 04: Click to notebooks link");
        computersPage.clickToDynamicSubListLink("Notebooks");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        log.info("Post-condition - STEP 01: Close browser ");
        closeBrowserAndDriver();
    }

    @Test
    public void TC_01_sortWithNameAtoZ(){
        log.info("Pre-TC_01_sortWithNameAtoZ - STEP 01: Select sort name A to Z");
        computersPage.clickToDynamicDropdown("Name: A to Z", "Sort by");
        log.info("Pre-TC_01_sortWithNameAtoZ - STEP 02: Verify name sorted ascending");
        verifyTrue(computersPage.isNameSortAscending());
    }

    @Test
    public void TC_02_sortWithNameZtoA(){
        log.info("Pre-TC_02_sortWithNameZtoA - STEP 01: Select sort name Z to A");
        computersPage.clickToDynamicDropdown("Name: Z to A", "Sort by");
        log.info("Pre-TC_02_sortWithNameZtoA - STEP 02: Verify name sorted descending");
        verifyTrue(computersPage.isNameSortDescending());
    }

    @Test
    public void TC_03_sortWithPriceAtoZ(){
        log.info("Pre-TC_03_sortWithPriceAtoZ - STEP 01: Select price A to Z");
        computersPage.clickToDynamicDropdown("Price: Low to High", "Sort by");
        log.info("Pre-TC_03_sortWithPriceAtoZ - STEP 02: Verify price sorted ascending");
        verifyTrue(computersPage.isPriceSortAscending());
    }

    @Test
    public void TC_04_sortWithPriceZtoA(){
        log.info("Pre-TC_04_sortWithPriceZtoA - STEP 01: Select price Z to A");
        computersPage.clickToDynamicDropdown("Price: High to Low", "Sort by");
        log.info("Pre-TC_04_sortWithPriceZtoA - STEP 02: Verify price sorted descending");
        verifyTrue(computersPage.isPriceSortDescending());
    }

    @Test
    public void TC_05_displayProduct(){
        log.info("Pre-TC_05_displayProduct - STEP 01: Select product display = 3");
        computersPage.clickToDynamicDropdown("3", "Display");
        log.info("Pre-TC_05_displayProduct - STEP 02: Verify number of item");
        verifyTrue(computersPage.isNumberOfItemDisplayed(3));
        log.info("Pre-TC_05_displayProduct - STEP 03: Verify next icon displayed");
        verifyTrue(computersPage.isPagingIconDisplayed("Next"));
        log.info("Pre-TC_05_displayProduct - STEP 04: Click to next icon");
        computersPage.clickToDynamicPaging("Next");
        log.info("Pre-TC_05_displayProduct - STEP 05: Verify previous displayed");
        verifyTrue(computersPage.isPagingIconDisplayed("Previous"));
    }

    @Test
    public void TC_06_displayProduct(){
        log.info("Pre-TC_06_displayProduct - STEP 01: Select product display = 6");
        computersPage.clickToDynamicDropdown("6", "Display");
        log.info("Pre-TC_06_displayProduct - STEP 02: Verify number of product displayed");
        verifyTrue(computersPage.isNumberOfItemDisplayed(6));
        log.info("Pre-TC_06_displayProduct - STEP 04: Verify next icon undisplayed");
        verifyTrue(computersPage.isPagingIconUndisplayed("Next"));
    }

    @Test
    public void TC_07_displayProduct(){
        log.info("Pre-TC_07_displayProduct - STEP 01: Select product display = 9");
        computersPage.clickToDynamicDropdown("9", "Display");
        log.info("Pre-TC_07_displayProduct - STEP 02: Verify product number displayed");
        verifyTrue(computersPage.isNumberOfItemDisplayed(9));
        log.info("Pre-TC_07_displayProduct - STEP 03: Verify next icon undisplayed");
        verifyTrue(computersPage.isPagingIconUndisplayed("Next"));
    }


    private HomePageObject homePage;
    private ComputersPageObject computersPage;

}
