package com_myAccount;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.*;
import testdata.MyAccountModule;

public class MyAccount_01_CustomerInfo extends AbstractTest {
    private WebDriver driver;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        log.info("Pre-condition - STEP 01: Open browser");
        driver = getBrowserDriver(browserName, url);
        log.info("Pre-condition - STEP 02: Init home page");
        homePage = PageGeneratorManager.getHomePage(driver);
        log.info("Pre-condition - STEP 03: Get home page title");
        homePageTitle = homePage.getPageTitle(driver);
        log.info("Pre-condition - STEP 04: Open and Init register page");
        registerPage = homePage.clickToRegisterMenu("Register");
        log.info("Pre-condition - STEP 05: Select Gender");
        registerPage.selectGender(MyAccountModule.Register.GENDER);
        log.info("Pre-condition - STEP 06: Input to first name");
        registerPage.sendKeyToDynamicTextBox(MyAccountModule.Register.FIRST_NAME, "First name");
        log.info("Pre-condition - STEP 07: Input to last name");
        registerPage.sendKeyToDynamicTextBox(MyAccountModule.Register.LAST_NAME, "Last name");
        log.info("Pre-condition - STEP 08: Select day");
        registerPage.selectDateOfBirth(MyAccountModule.Register.DATE, "DateOfBirthDay");
        log.info("Pre-condition - STEP 09: Select month");
        registerPage.selectDateOfBirth(MyAccountModule.Register.MONTH, "DateOfBirthMonth");
        log.info("Pre-condition - STEP 10: Select year");
        registerPage.selectDateOfBirth(MyAccountModule.Register.YEAR, "DateOfBirthYear");
        log.info("Pre-condition - STEP 11: input to email textbox");
        registerPage.sendKeyToDynamicTextBox(MyAccountModule.Register.EMAIL, "Email");
        log.info("Pre-condition - STEP 12: Input company textbox");
        registerPage.sendKeyToDynamicTextBox(MyAccountModule.Register.COMPANY, "Company name");
        log.info("Pre-condition - STEP 13: Input to password texbox");
        registerPage.sendKeyToDynamicTextBox(MyAccountModule.Register.PASSWORD, "Password");
        log.info("Pre-condition - STEP 14: Input to confim password textbox");
        registerPage.sendKeyToDynamicTextBox(MyAccountModule.Register.PASSWORD, "Confirm password");
        log.info("Pre-condition - STEP 15: Click to register button");
        registerPage.clickToRegisterButton("Register");
        log.info("Pre-condition - STEP 16: Verify register success message displayed");
        verifyEquals(registerPage.getSuccessMessage(), "Your registration completed");
        log.info("Pre-condition - STEP 17: CLick to continue button");
        registerPage.clickToContinueButton("Continue");
        log.info("Pre-condition - STEP 18: Verify return to Home page success");
        verifyEquals(registerPage.getPageTitle(driver), homePageTitle);
        log.info("Pre-condition - STEP 19: Init home page");
        homePage = PageGeneratorManager.getHomePage(driver);
        log.info("Pre-condition - STEP 20: Init my account page");
        myAccountPage = homePage.clickToMyAccount();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        log.info("Post-condition - Close browser and driver");
        closeBrowserAndDriver();
    }

    @Test
    public void TC_01_updateCustomerInfo() {
        log.info("TC_01_updateCustomerInfo - STEP 01: Select gender");
        myAccountPage.selectGender(MyAccountModule.EditInfo.GENDER);
        log.info("TC_01_updateCustomerInfo - STEP 02: Input to first name");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.EditInfo.FIRST_NAME, "First name");
        log.info("TC_01_updateCustomerInfo - STEP 03: Input to last name textbox");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.EditInfo.LAST_NAME, "Last name");
        log.info("TC_01_updateCustomerInfo - STEP 04: Select date");
        myAccountPage.selectDynamicSelector(MyAccountModule.EditInfo.DATE, "DateOfBirthDay");
        log.info("TC_01_updateCustomerInfo - STEP 06: Select month");
        myAccountPage.selectDynamicSelector(MyAccountModule.EditInfo.MONTH, "DateOfBirthMonth");
        log.info("TC_01_updateCustomerInfo - STEP 07: Select year");
        myAccountPage.selectDynamicSelector(MyAccountModule.EditInfo.YEAR, "DateOfBirthYear");
        log.info("TC_01_updateCustomerInfo - STEP 08: Input to email textbox");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.EditInfo.VALID_EMAIL, "Email");
        log.info("TC_01_updateCustomerInfo - STEP 09: Input to company textbox");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.EditInfo.COMPANY_NAME, "Company name");
        log.info("TC_01_updateCustomerInfo - STEP 10: Verify first name");
        verifyEquals(myAccountPage.getValueInDynamicText("First name"), MyAccountModule.EditInfo.FIRST_NAME);
        log.info("TC_01_updateCustomerInfo - STEP 11: Verify last name ");
        verifyEquals(myAccountPage.getValueInDynamicText("Last name"), MyAccountModule.EditInfo.LAST_NAME);
        log.info("TC_01_updateCustomerInfo - STEP 12: Verify Date");
        verifyEquals(myAccountPage.getDynamicSelectedText("DateOfBirthDay"), MyAccountModule.EditInfo.DATE);
        log.info("TC_01_updateCustomerInfo - STEP 13: Verify month");
        verifyEquals(myAccountPage.getDynamicSelectedText("DateOfBirthMonth"), MyAccountModule.EditInfo.MONTH);
        log.info("TC_01_updateCustomerInfo - STEP 14: Verify year");
        verifyEquals(myAccountPage.getDynamicSelectedText("DateOfBirthYear"), MyAccountModule.EditInfo.YEAR);
        log.info("TC_01_updateCustomerInfo - STEP 15: Verify email");
        verifyEquals(myAccountPage.getValueInDynamicText("Email"), MyAccountModule.EditInfo.VALID_EMAIL);
        log.info("TC_01_updateCustomerInfo - STEP 16: Verify company name");
        verifyEquals(myAccountPage.getValueInDynamicText("Company name"), MyAccountModule.EditInfo.COMPANY_NAME);
    }

    @Test
    public void TC_02_addNewAddress(){
        log.info("TC_02_addNewAddress - STEP 01: Click to Address link");
        myAccountPage.clickToMenuLink("Addresses");
        log.info("TC_02_addNewAddress - STEP 02: Click to Add new button");
        myAccountPage.clickToDynamicButton("Add new");
        log.info("TC_02_addNewAddress - STEP 03: Input to first name");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.EditInfo.FIRST_NAME, "First name");
        log.info("TC_02_addNewAddress - STEP 04: Input to last name");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.EditInfo.LAST_NAME, "Last name");
        log.info("TC_02_addNewAddress - STEP 05: Input to Email");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.EditInfo.VALID_EMAIL, "Email");
        log.info("TC_02_addNewAddress - STEP 06: Input to company");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.EditInfo.COMPANY_NAME, "Company");
        log.info("TC_02_addNewAddress - STEP 07: Select Country");
        myAccountPage.selectDynamicSelector(MyAccountModule.EditInfo.COUNTRY, "Country");
        log.info("TC_02_addNewAddress - STEP 08: Select State");
        myAccountPage.selectDynamicSelector(MyAccountModule.EditInfo.STATE, "State");
        log.info("TC_02_addNewAddress - STEP 09: input to City texbox");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.EditInfo.CITY, "City");
        log.info("TC_02_addNewAddress - STEP 10: Input to address 1 textbox");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.EditInfo.ADDRESS1, "Address 1");
        log.info("TC_02_addNewAddress - STEP 11: Input to address 2 textbox");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.EditInfo.ADDRESS2, "Address 2");
        log.info("TC_02_addNewAddress - STEP 12: Inout to Zip code");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.EditInfo.ZIP_CODE, "Zip");
        log.info("TC_02_addNewAddress - STEP 13: Input to phone textbox");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.EditInfo.PHONE, "Phone number");
        log.info("TC_02_addNewAddress - STEP 14: Input to fax number textbox");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.EditInfo.FAX, "Fax number");
        log.info("TC_02_addNewAddress - STEP 15: Click to SAVE button");
        myAccountPage.clickToDynamicButton("Save");
        log.info("TC_02_addNewAddress - STEP 16: Verify name");
        verifyEquals(myAccountPage.getDynamicAddressInfo("name"), MyAccountModule.EditInfo.FIRST_NAME + " " + MyAccountModule.EditInfo.LAST_NAME);
        log.info("TC_02_addNewAddress - STEP 17: Verify email");
        verifyEquals(myAccountPage.getDynamicAddressInfo("email"), "Email: " + MyAccountModule.EditInfo.VALID_EMAIL);
        log.info("TC_02_addNewAddress - STEP 18: Verify phone number");
        verifyEquals(myAccountPage.getDynamicAddressInfo("phone"), "Phone number: " + MyAccountModule.EditInfo.PHONE);
        log.info("TC_02_addNewAddress - STEP 19: Verify fax number");
        verifyEquals(myAccountPage.getDynamicAddressInfo("fax"), "Fax number: " + MyAccountModule.EditInfo.FAX);
        log.info("TC_02_addNewAddress - STEP 20: Verify company name");
        verifyEquals(myAccountPage.getDynamicAddressInfo("company"), MyAccountModule.EditInfo.COMPANY_NAME);
        log.info("TC_02_addNewAddress - STEP 21: Verify address 1");
        verifyEquals(myAccountPage.getDynamicAddressInfo("address1"), MyAccountModule.EditInfo.ADDRESS1);
        log.info("TC_02_addNewAddress - STEP 22: Verify address 2");
        verifyEquals(myAccountPage.getDynamicAddressInfo("address2"), MyAccountModule.EditInfo.ADDRESS2);
        log.info("TC_02_addNewAddress - STEP 23: Verify zip code");
        verifyEquals(myAccountPage.getDynamicAddressInfo("city-state-zip"), MyAccountModule.EditInfo.CITY + ", " + MyAccountModule.EditInfo.STATE + ", " + MyAccountModule.EditInfo.ZIP_CODE);
        log.info("TC_02_addNewAddress - STEP 24: Verify country");
        verifyEquals(myAccountPage.getDynamicAddressInfo("country"), MyAccountModule.EditInfo.COUNTRY);

    }

    @Test
    public void TC_03_changePassword(){
        log.info("TC_03_changePassword - STEP 01: Click to change password link");
        myAccountPage.clickToMenuLink("Change password");
        log.info("TC_03_changePassword - STEP 02: Input to password textbox");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.Register.PASSWORD, "Old password");
        log.info("TC_03_changePassword - STEP 03: Input to new password textbox");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.EditInfo.NEW_PASSWORD, "New password");
        log.info("TC_03_changePassword - STEP 04: Input to confirm new password");
        myAccountPage.inputToDynamicTextBox(MyAccountModule.EditInfo.NEW_PASSWORD, "Confirm password");
        log.info("TC_03_changePassword - STEP 05: Click to change password button");
        myAccountPage.clickToDynamicButton("Change password");
        log.info("TC_03_changePassword - STEP 06: Verify password chang success");
        verifyEquals(myAccountPage.getMessageInfo(), "Password was changed");
        log.info("TC_03_changePassword - STEP 07: click to Logout link");
        myAccountPage.clickToMenuBar(driver, "Log out");
        log.info("TC_03_changePassword - STEP 08: Click to login link");
        homePage.clickToMenuBar(driver, "Log in");
        log.info("TC_03_changePassword - STEP 09: Open and Init login page");
        loginPage = PageGeneratorManager.getLoginPage(driver);
        log.info("TC_03_changePassword - STEP 10: Input to Email textbox");
        loginPage.inputToDynamicTextBox(MyAccountModule.Register.EMAIL, "Email");
        log.info("TC_03_changePassword - STEP 11: Input invalid password to password textbox");
        loginPage.inputToDynamicTextBox(MyAccountModule.Register.PASSWORD, "Password");
        log.info("TC_03_changePassword - STEP 12: Click to Login button");
        loginPage.clickToDynamicButton("Log in");
        log.info("TC_03_changePassword - STEP 13: Verify login false");
        verifyEquals(loginPage.getSummaryMessage(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
        log.info("TC_03_changePassword - STEP 14: Input valid password to password textbox");
        loginPage.inputToDynamicTextBox(MyAccountModule.EditInfo.NEW_PASSWORD, "Password");
        log.info("TC_03_changePassword - STEP 15: Click to login button");
        loginPage.clickToDynamicButton("Log in");
        log.info("TC_03_changePassword - STEP 16: Verify login success");
        verifyEquals(homePage.getPageTitle(driver),"nopCommerce demo store");
    }

    @Test
    public void TC_04_myProductReview(){
        log.info("TC_04_myProductReview - STEP 01: Open and Init computer page");
        computersPage = (ComputersPageObject) homePage.clickToMenuLink(driver, "Computers");
        log.info("TC_04_myProductReview - STEP 02: Click to Desktop link");
        computersPage.clickToDynamicSubListLink("Desktops");
        log.info("TC_04_myProductReview - STEP 03: CLick to Build your own computer product link");
        computersPage.clickToDynamicProduct("Build your own computer");
        log.info("TC_04_myProductReview - STEP 04: Click to Add your review link");
        computersPage.clickToReviewLink("Add your review");
        log.info("TC_04_myProductReview - STEP 05: Input to review textbox");
        computersPage.sendKeyToReviewTextBox(driver, "Review computer", "Review title");
        log.info("TC_04_myProductReview - STEP 06: Input text to textarea");
        computersPage.sendKeyToReviewTextArea(driver, "No comment", "Review text");
        log.info("TC_04_myProductReview - STEP 07: Select rating");
        computersPage.selectReviewRating(driver, "Good");
        log.info("TC_04_myProductReview - STEP 08: Click to submit button");
        computersPage.clickToDynamicButton(driver, "Submit review");
        log.info("TC_04_myProductReview - STEP 09: Verify success message displayed");
        verifyEquals(computersPage.getSummaryMessageInfo(driver), "Product review is successfully added.");
        log.info("TC_04_myProductReview - STEP 10: Click to My account link");
        computersPage.clickToMenuBar(driver, "My account");
        log.info("TC_04_myProductReview - STEP 11: Click to My product reviews link");
        myAccountPage.clickToMenuLink("My product reviews");
        log.info("TC_04_myProductReview - STEP 12: Verify review text displayed");
        verifyTrue(myAccountPage.isReviewDisplayed("Review computer"));
    }

    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private String homePageTitle;
    private MyAccountPageObject myAccountPage;
    private LoginPageObject loginPage;
    private ComputersPageObject computersPage;
}
