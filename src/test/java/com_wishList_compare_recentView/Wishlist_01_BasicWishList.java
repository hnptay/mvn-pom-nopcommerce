package com_wishList_compare_recentView;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.*;
import testdata.WishlistModule;

public class Wishlist_01_BasicWishList extends AbstractTest {

    private WebDriver driver;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        homePage = PageGeneratorManager.getHomePage(driver);
        registerPage = homePage.clickToRegisterMenu("Register");
        registerPage.selectGender(WishlistModule.GENDER);
        registerPage.sendKeyToDynamicTextBox(WishlistModule.FIRST_NAME, "First name");
        registerPage.sendKeyToDynamicTextBox(WishlistModule.LAST_NAME, "Last name");
        registerPage.selectDateOfBirth(WishlistModule.DATE, "DateOfBirthDay");
        registerPage.selectDateOfBirth(WishlistModule.MONTH, "DateOfBirthMonth");
        registerPage.selectDateOfBirth(WishlistModule.YEAR, "DateOfBirthYear");
        registerPage.sendKeyToDynamicTextBox(WishlistModule.EMAIL, "Email");
        registerPage.sendKeyToDynamicTextBox(WishlistModule.COMPANY, "Company name");
        registerPage.sendKeyToDynamicTextBox(WishlistModule.PASSWORD, "Password");
        registerPage.sendKeyToDynamicTextBox(WishlistModule.PASSWORD, "Confirm password");
        registerPage.clickToRegisterButton("Register");
        verifyEquals(registerPage.getSuccessMessage(), "Your registration completed");
        registerPage.clickToContinueButton("Continue");
        computersPage = (ComputersPageObject) homePage.clickToMenuLink(driver, "Computers");
        computersPage.clickToDynamicSubListLink("Notebooks ");
        computersPage.clickToDynamicProduct("Apple MacBook Pro 13-inch");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }

    @Test
    public void TC_01_addToWishlist() {
        computersPage.clickToDynamicOverViewButton("Add to wishlist");
        verifyEquals(computersPage.getNotificationMessage(), "The product has been added to your wishlist");
        computersPage.closeNotifyBar();
        computersPage.clickToMenuBar(driver, "Wishlist");
        wishlistPage = PageGeneratorManager.getWishlistPage(driver);
        verifyTrue(wishlistPage.isProductDisplayed("Apple MacBook Pro 13-inch"));
        wishlistPage.clickToShareLink();
        verifyEquals(wishlistPage.getWishlistTitle(), "Wishlist of " + WishlistModule.FIRST_NAME + " " + WishlistModule.LAST_NAME);
    }

    @Test
    public void TC_02_removeProductInWishlist(){
        wishlistPage.back(driver);
        wishlistPage.checkToCheckBox("Apple MacBook Pro 13-inch", "Remove");
        wishlistPage.clickToDynamicButton(driver, "Update wishlist");
        verifyEquals(wishlistPage.getWishListBody(), "The wishlist is empty!");
    }

    @Test
    public void TC_03_addProductToCompare(){
        computersPage = (ComputersPageObject) wishlistPage.clickToMenuLink(driver, "Computers");
        computersPage.clickToDynamicSubListLink("Notebooks");
        computersPage.clickToDynamicButton("Asus N551JK-XO076H Laptop", "Add to compare list");
        computersPage.closeNotifyBar();
        computersPage.clickToDynamicButton("HP Spectre XT Pro UltraBook", "Add to compare list");
        computersPage.closeNotifyBar();
        compareProductPage = (CompareProductPageObject) computersPage.clickToCustomerServiceMenuLink(driver, "Compare products list");
        verifyTrue(compareProductPage.isProductDisplayed("Asus N551JK-XO076H Laptop"));
        verifyTrue(compareProductPage.isProductDisplayed("HP Spectre XT Pro UltraBook"));
        compareProductPage.clickToClearListButton();
        verifyEquals(compareProductPage.getCompareListBody(), "You have no items to compare.");
    }


    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private ComputersPageObject computersPage;
    private WishlistPageObject wishlistPage;
    private CompareProductPageObject compareProductPage;
}
