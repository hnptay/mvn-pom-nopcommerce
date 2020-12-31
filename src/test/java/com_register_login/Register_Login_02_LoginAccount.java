package com_register_login;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import testdata.RegisterAndLoginModule;

public class Register_Login_02_LoginAccount extends AbstractTest {
    private WebDriver driver;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url){
        log.info("Pre-condition - STEP 01: Open browser");
        driver = getBrowserDriver(browserName, url);
        log.info("Pre-condition - STEP 02: Init Home page");
        homePage = PageGeneratorManager.getHomePage(driver);
        log.info("Pre-condition - STEP 03: Get home page title");
        homePageTitle = homePage.getPageTitle(driver);
        log.info("Pre-condition - STEP 02: Open login page");
        loginPage = homePage.clickToLoginMenu("Log in");

    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        log.info("Post-condition - Close browser and driver");
        closeBrowserAndDriver();
    }

    @Test
    public void TC_01_loginWithEmptyData(){
        log.info("TC_01_loginWithEmptyData - STEP 01: Click to Login button");
        loginPage.clickToDynamicButton("Log in");
        log.info("TC_01_loginWithEmptyData - STEP 02: Verify error message displayed");
        verifyEquals(loginPage.getEmailErrorMessage(), "Please enter your email");
    }

    @Test
    public void TC_02_loginWithInvalidEmail(){
        log.info("TC_02_loginWithInvalidEmail - STEP 01: Refresh page");
        loginPage.refreshPage(driver);
        log.info("TC_02_loginWithInvalidEmail - STEP 02: Input invalid email to email text box");
        loginPage.inputToDynamicTextBox(RegisterAndLoginModule.INVALID_EMAIL, "Email");
        log.info("TC_02_loginWithInvalidEmail - STEP 03: Click to Login button");
        loginPage.clickToDynamicButton("Log in");
        log.info("TC_02_loginWithInvalidEmail - STEP 04: Verify error message displayed");
        verifyEquals(loginPage.getEmailErrorMessage(), "Wrong email");
    }

    @Test
    public void TC_03_LoginWithEmailNotRegistered(){
        log.info("TC_03_LoginWithEmailNotRegistered - STEP 01: Refresh page");
        loginPage.refreshPage(driver);
        log.info("TC_03_LoginWithEmailNotRegistered - STEP 02: Input invalid email to email text box");
        loginPage.inputToDynamicTextBox(RegisterAndLoginModule.NOT_REGISTER_EMAIL, "Email");
        log.info("TC_03_LoginWithEmailNotRegistered - STEP 03: Click to login button");
        loginPage.clickToDynamicButton("Log in");
        log.info("TC_03_LoginWithEmailNotRegistered - STEP 04: Verify error message displayed");
        verifyEquals(loginPage.getSummaryMessage(),"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
    }

    @Test
    public void TC_04_loginWithEmptyPassword(){
        log.info("TC_04_loginWithEmptyPassword - STEP 01: Refresh page");
        loginPage.refreshPage(driver);
        log.info("TC_04_loginWithEmptyPassword - STEP 02: Input valid email to email text box");
        loginPage.inputToDynamicTextBox(RegisterAndLoginModule.VALID_EMAIL, "Email");
        log.info("TC_04_loginWithEmptyPassword - STEP 03: Click to login button");
        loginPage.clickToDynamicButton("Log in");
        log.info("TC_04_loginWithEmptyPassword - STEP 04: Verify error message displayed");
        verifyEquals(loginPage.getSummaryMessage(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }

    @Test
    public void TC_05_loginWithInvalidPassword(){
        log.info("TC_05_loginWithInvalidPassword - STEP 01: Refresh page");
        loginPage.refreshPage(driver);
        log.info("TC_05_loginWithInvalidPassword - STEP 02: Input valid email to email text box");
        loginPage.inputToDynamicTextBox(RegisterAndLoginModule.VALID_EMAIL, "Email");
        log.info("TC_05_loginWithInvalidPassword - STEP 03: Input invalid password to password text box");
        loginPage.inputToDynamicTextBox(RegisterAndLoginModule.INVALID_PASSWORD, "Password");
        log.info("TC_05_loginWithInvalidPassword - STEP 04: Click to login button");
        loginPage.clickToDynamicButton("Log in");
        log.info("TC_05_loginWithInvalidPassword - STEP 05: Verify error message displayed");
        verifyEquals(loginPage.getSummaryMessage(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }

    @Test
    public void TC_06_loginWithValidEmailAndPassword(){
        log.info("TC_06_loginWithValidEmailAndPassword - STEP 01: Refresh page");
        loginPage.refreshPage(driver);
        log.info("TC_06_loginWithValidEmailAndPassword - STEP 02: Input valid email to email text box");
        loginPage.inputToDynamicTextBox(RegisterAndLoginModule.VALID_EMAIL, "Email");
        log.info("TC_06_loginWithValidEmailAndPassword - STEP 03: Input valid password to password textbox");
        loginPage.inputToDynamicTextBox(RegisterAndLoginModule.VALID_PASSWORD, "Password");
        log.info("TC_06_loginWithValidEmailAndPassword - STEP 04: Click to login button");
        loginPage.clickToDynamicButton("Log in");
        log.info("TC_06_loginWithValidEmailAndPassword - STEP 05: Verify success message displayed");
        verifyEquals(loginPage.getPageTitle(driver), homePageTitle);
    }


    private LoginPageObject loginPage;
    private HomePageObject homePage;
    private String homePageTitle;
}
