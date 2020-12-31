package com_register_login;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.HomePageObject;
import pageObject.RegisterPageObject;
import testdata.RegisterAndLoginModule;

public class Register_Login_01_RegisterAccount extends AbstractTest {

    private WebDriver driver;
    private RegisterPageObject registerPage;
    private HomePageObject homePage;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url){
        log.info("Pre-condition - STEP 01: Open browser");
        driver = getBrowserDriver(browserName, url);
        log.info("Pre-condition - STEP 02: Open home Page");
        homePage = PageGeneratorManager.getHomePage(driver);
        log.info("Pre-condition - STEP 03: Open register page");
        registerPage = homePage.clickToRegisterMenu("Register");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        log.info("Post-condition - Close browser and driver");
        closeBrowserAndDriver();
    }

    @Test
    public void TC_01_registerWithEmptyData() {
        log.info("TC_01_registerWithEmptyData - STEP 01: Click to register button");
        registerPage.clickToRegisterButton("Register");
        log.info("TC_01_registerWithEmptyData - STEP 02: Verify error message displayed");
        verifyTrue(registerPage.isMessageOfAllFiledRequiredDisplayed("FirstName", "LastName", "Email", "Password", "ConfirmPassword"));
    }

    @Test
    public void TC_02_registerWithInvalidEmail(){
        log.info("TC_02_registerWithInvalidEmail - STEP 01: Refresh page");
        registerPage.refreshPage(driver);
        log.info("TC_02_registerWithInvalidEmail - STEP 02: SendKey to email text box");
        registerPage.sendKeyToDynamicTextBox(RegisterAndLoginModule.INVALID_EMAIL, "Email");
        log.info("TC_02_registerWithInvalidEmail - STEP 03: Click to register button");
        registerPage.clickToRegisterButton("Register");
        log.info("TC_02_registerWithInvalidEmail - STEP 04: Verify error message displayed");
        verifyEquals(registerPage.getErrorMessage("Email"), "Wrong email");
    }

    //@Test
    public void TC_03_registerWithExistsEmail(){
        registerPage.refreshPage(driver);
        registerPage.selectGender(RegisterAndLoginModule.GENDER);
        registerPage.sendKeyToDynamicTextBox(RegisterAndLoginModule.FIRST_NAME, "First name");
        registerPage.sendKeyToDynamicTextBox(RegisterAndLoginModule.LAST_NAME, "Last name");
        registerPage.selectDateOfBirth(RegisterAndLoginModule.DATE, "DateOfBirthDay");
        registerPage.selectDateOfBirth(RegisterAndLoginModule.MONTH, "DateOfBirthMonth");
        registerPage.selectDateOfBirth(RegisterAndLoginModule.YEAR, "DateOfBirthYear");
        registerPage.sendKeyToDynamicTextBox(RegisterAndLoginModule.EMAIL_EXISTS, "Email");
        registerPage.sendKeyToDynamicTextBox(RegisterAndLoginModule.COMPANY_NAME, "Company name");
        registerPage.sendKeyToDynamicTextBox(RegisterAndLoginModule.VALID_PASSWORD, "Password");
        registerPage.sendKeyToDynamicTextBox(RegisterAndLoginModule.VALID_PASSWORD, "Confirm password");
        registerPage.clickToRegisterButton("Register");
        verifyEquals(registerPage.getSummaryErrorMessage(), "The specified email already exists");
    }

    @Test
    public void TC_04_registerWithInvalidPassword(){
        log.info("TC_04_registerWithInvalidPassword - STEP 01: Refresh page");
        registerPage.refreshPage(driver);
        log.info("TC_04_registerWithInvalidPassword - STEP 02: SendKey to password text box");
        registerPage.sendKeyToDynamicTextBox(RegisterAndLoginModule.INVALID_PASSWORD, "Password");
        log.info("TC_04_registerWithInvalidPassword - STEP 03: Click to register button");
        registerPage.clickToRegisterButton("Register");
        log.info("TC_04_registerWithInvalidPassword - STEP 04: Verify Error message displayed");
        verifyEquals(registerPage.getErrorMessage("Password"),"Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void TC_05_registerWithInvalidConfirmPassword(){
        log.info("TC_05_registerWithInvalidConfirmPassword - STEP 01: Refresh page");
        registerPage.refreshPage(driver);
        log.info("TC_05_registerWithInvalidConfirmPassword - STEP 02: SendKey to  password text box");
        registerPage.sendKeyToDynamicTextBox(RegisterAndLoginModule.VALID_PASSWORD, "Password");
        log.info("TC_05_registerWithInvalidConfirmPassword - STEP 03: SendKey to confirm password test box");
        registerPage.sendKeyToDynamicTextBox(RegisterAndLoginModule.INVALID_PASSWORD, "Confirm password");
        log.info("TC_05_registerWithInvalidConfirmPassword - STEP 04: Click to register button");
        registerPage.clickToRegisterButton("Register");
        log.info("TC_05_registerWithInvalidConfirmPassword - STEP 05: Verify error message displayed");
        verifyEquals(registerPage.getErrorMessage("ConfirmPassword"), "The password and confirmation password do not match.");
    }

    @Test
    public void TC_06_registerWithValidInfo(){
        log.info("TC_06_registerWithValidInfo - STEP 01: Refresh page");
        registerPage.refreshPage(driver);
        log.info("TC_06_registerWithValidInfo - STEP 02: Select gender");
        registerPage.selectGender(RegisterAndLoginModule.GENDER);
        log.info("TC_06_registerWithValidInfo - STEP 03: Input to first name");
        registerPage.sendKeyToDynamicTextBox(RegisterAndLoginModule.FIRST_NAME, "First name");
        log.info("TC_06_registerWithValidInfo - STEP 04: Input to last name");
        registerPage.sendKeyToDynamicTextBox(RegisterAndLoginModule.LAST_NAME, "Last name");
        log.info("TC_06_registerWithValidInfo - STEP 05: select date");
        registerPage.selectDateOfBirth(RegisterAndLoginModule.DATE, "DateOfBirthDay");
        log.info("TC_06_registerWithValidInfo - STEP 06: Select month");
        registerPage.selectDateOfBirth(RegisterAndLoginModule.MONTH, "DateOfBirthMonth");
        log.info("TC_06_registerWithValidInfo - STEP 07: Select year");
        registerPage.selectDateOfBirth(RegisterAndLoginModule.YEAR, "DateOfBirthYear");
        log.info("TC_06_registerWithValidInfo - STEP 08: Input to email text box");
        registerPage.sendKeyToDynamicTextBox(RegisterAndLoginModule.VALID_EMAIL, "Email");
        log.info("TC_06_registerWithValidInfo - STEP 09: Input to company text box");
        registerPage.sendKeyToDynamicTextBox(RegisterAndLoginModule.COMPANY_NAME, "Company name");
        log.info("TC_06_registerWithValidInfo - STEP 10: Input to password text box");
        registerPage.sendKeyToDynamicTextBox(RegisterAndLoginModule.VALID_PASSWORD, "Password");
        log.info("TC_06_registerWithValidInfo - STEP 11: Input to comfirm password text box");
        registerPage.sendKeyToDynamicTextBox(RegisterAndLoginModule.VALID_PASSWORD, "Confirm password");
        log.info("TC_06_registerWithValidInfo - STEP 12: Click to register button");
        registerPage.clickToRegisterButton("Register");
        log.info("TC_06_registerWithValidInfo - STEP 13: Verify register success");
        verifyEquals(registerPage.getSuccessMessage(), "Your registration completed");
    }

}
