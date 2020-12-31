package com_order;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.*;
import testdata.OrderModule;

public class Order_01_Checkout extends AbstractTest {
    private WebDriver driver;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        homePage = PageGeneratorManager.getHomePage(driver);
        registerPage = homePage.clickToRegisterMenu("Register");
        registerPage.selectGender(OrderModule.Register.GENDER);
        registerPage.sendKeyToDynamicTextBox(OrderModule.Register.FIRST_NAME, "First name");
        registerPage.sendKeyToDynamicTextBox(OrderModule.Register.LAST_NAME, "Last name");
        registerPage.selectDateOfBirth(OrderModule.Register.DATE, "DateOfBirthDay");
        registerPage.selectDateOfBirth(OrderModule.Register.MONTH, "DateOfBirthMonth");
        registerPage.selectDateOfBirth(OrderModule.Register.YEAR, "DateOfBirthYear");
        registerPage.sendKeyToDynamicTextBox(OrderModule.Register.EMAIL, "Email");
        registerPage.sendKeyToDynamicTextBox(OrderModule.Register.COMPANY, "Company name");
        registerPage.sendKeyToDynamicTextBox(OrderModule.Register.PASSWORD, "Password");
        registerPage.sendKeyToDynamicTextBox(OrderModule.Register.PASSWORD, "Confirm password");
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
    public void TC_01_checkOut() {
        computersPage.clickToDynamicButton(driver, "Add to cart");
        verifyEquals(computersPage.getNotificationMessage(), "The product has been added to your shopping cart");
        computersPage.closeNotifyBar();
        computersPage.clickToMenuBar(driver, "Shopping cart");
        cartPage = PageGeneratorManager.getCartPage(driver);
        cartPage.clickToEstimateShipping();
        cartPage.selectCountryAndState("United States", "Country");
        cartPage.selectCountryAndState("Florida", "State / province");
        cartPage.inputToZipCodeTextBox(OrderModule.ShippingAddress.ZIP_CODE);
        cartPage.selectShippingMethod("Next Day Air");
        cartPage.clickToDynamicButton(driver, "Apply");
        cartPage.selectGiftWrapping("Yes [+$10.00]");
        cartPage.refreshPage(driver);
        productCode = cartPage.getShoppingCartInfo("SKU", "1");
        productName = cartPage.getShoppingCartInfo("Product", "1");
        productPrice = cartPage.getShoppingCartInfo("Price", "1");
        productQty = cartPage.getQuantityProduct("Qty", "1");
        giftWrapping = cartPage.getGitWrappingInfo();
        subTotal = cartPage.getCartTotalInfo("Sub-Total");
        shippingPrice = cartPage.getCartTotalInfo("Shipping");
        taxPrice = cartPage.getCartTotalInfo("Tax");
        cartPage.selectAgreeCheckBox();
        cartPage.clickToCheckOutButton();
        checkOutPage = PageGeneratorManager.getCheckOutPage(driver);
        checkOutPage.uncheckSameAddress("Ship to the same address");
        checkOutPage.inputToDynamicTextBox("Billing", OrderModule.BillingAddress.FIRST_NAME, "First name");
        checkOutPage.inputToDynamicTextBox("Billing", OrderModule.BillingAddress.LAST_NAME, "Last name");
        checkOutPage.inputToDynamicTextBox("Billing", OrderModule.BillingAddress.EMAIL, "Email");
        checkOutPage.inputToDynamicTextBox("Billing", OrderModule.BillingAddress.COMPANY, "Company");
        checkOutPage.selectCountryAndState("Billing", OrderModule.BillingAddress.COUNTRY, "Country");
        checkOutPage.selectCountryAndState("Billing", OrderModule.BillingAddress.STATE, "State");
        checkOutPage.inputToDynamicTextBox("Billing", OrderModule.BillingAddress.CITY, "City");
        checkOutPage.inputToDynamicTextBox("Billing", OrderModule.BillingAddress.ADDRESS, "Address 1");
        checkOutPage.inputToDynamicTextBox("Billing", OrderModule.BillingAddress.ZIP_CODE, "Zip / postal code");
        checkOutPage.inputToDynamicTextBox("Billing", OrderModule.BillingAddress.PHONE, "Phone number");
        checkOutPage.inputToDynamicTextBox("Billing", OrderModule.BillingAddress.FAX, "Fax number");
        checkOutPage.clickToContinueButtonAtStep("Billing address");
        checkOutPage.selectShippingAddress("New Address");
        checkOutPage.inputToDynamicTextBox("Shipping", OrderModule.ShippingAddress.FIRST_NAME, "First name");
        checkOutPage.inputToDynamicTextBox("Shipping", OrderModule.ShippingAddress.LAST_NAME, "Last name");
        checkOutPage.inputToDynamicTextBox("Shipping", OrderModule.ShippingAddress.EMAIL, "Email");
        checkOutPage.inputToDynamicTextBox("Shipping", OrderModule.ShippingAddress.COMPANY, "Company");
        checkOutPage.selectCountryAndState("Shipping", OrderModule.ShippingAddress.COUNTRY, "Country");
        checkOutPage.selectCountryAndState("Shipping", OrderModule.ShippingAddress.STATE, "State");
        checkOutPage.inputToDynamicTextBox("Shipping", OrderModule.ShippingAddress.CITY, "City");
        checkOutPage.inputToDynamicTextBox("Shipping", OrderModule.ShippingAddress.ADDRESS, "Address 1");
        checkOutPage.inputToDynamicTextBox("Shipping", OrderModule.ShippingAddress.ZIP_CODE, "Zip / postal code");
        checkOutPage.inputToDynamicTextBox("Shipping", OrderModule.ShippingAddress.PHONE, "Phone number");
        checkOutPage.inputToDynamicTextBox("Shipping", OrderModule.ShippingAddress.FAX, "Fax number");
        checkOutPage.clickToContinueButtonAtStep("Shipping address");
        checkOutPage.selectDynamicMethod("Next Day Air");
        checkOutPage.clickToContinueButtonAtStep("Shipping method");
        checkOutPage.selectDynamicMethod("Check / Money Order");
        checkOutPage.clickToContinueButtonAtStep("Payment method");
        verifyEquals(checkOutPage.getPaymentInfo(), "NOP SOLUTIONS\nyour address here,\nNew York, NY 10001\nUSA");
        checkOutPage.clickToContinueButtonAtStep("Payment information");
        verifyEquals(checkOutPage.getOrderInfo("Billing Address"),
                OrderModule.BillingAddress.FIRST_NAME + " "
                        + OrderModule.BillingAddress.LAST_NAME + "\n"
                        + "Email: " + OrderModule.BillingAddress.EMAIL + "\n"
                        + "Phone: " + OrderModule.BillingAddress.PHONE + "\n"
                        + "Fax: " + OrderModule.BillingAddress.FAX + "\n"
                        + OrderModule.BillingAddress.COMPANY + "\n"
                        + OrderModule.BillingAddress.ADDRESS + "\n"
                        + OrderModule.BillingAddress.CITY + ","
                        + OrderModule.BillingAddress.STATE + ","
                        + OrderModule.BillingAddress.ZIP_CODE + "\n"
                        + OrderModule.BillingAddress.COUNTRY);
        verifyEquals(checkOutPage.getOrderInfo("Shipping Address"),
                OrderModule.ShippingAddress.FIRST_NAME + " "
                        + OrderModule.ShippingAddress.LAST_NAME + "\n"
                        + "Email: " + OrderModule.ShippingAddress.EMAIL + "\n"
                        + "Phone: " + OrderModule.ShippingAddress.PHONE + "\n"
                        + "Fax: " + OrderModule.ShippingAddress.FAX + "\n"
                        + OrderModule.ShippingAddress.COMPANY + "\n"
                        + OrderModule.ShippingAddress.ADDRESS + "\n"
                        + OrderModule.ShippingAddress.CITY + ","
                        + OrderModule.ShippingAddress.STATE + ","
                        + OrderModule.ShippingAddress.ZIP_CODE + "\n"
                        + OrderModule.ShippingAddress.COUNTRY);
        verifyEquals(checkOutPage.getOrderInfo("Payment"), "Payment Method: Check / Money Order");
        verifyEquals(checkOutPage.getOrderInfo("Shipping"), "Shipping Method: Next Day Air");
        verifyEquals(checkOutPage.getProductInfo("1", "SKU"), productCode);
        verifyEquals(checkOutPage.getProductInfo("1", "Product"), productName);
        verifyEquals(checkOutPage.getProductInfo("1", "Price"), productPrice);
        verifyEquals(checkOutPage.getProductInfo("1", "Qty"), productQty);
        verifyEquals(checkOutPage.getGitWrappingInfo(), giftWrapping);
        verifyEquals(checkOutPage.getTotalInfo("Sub-Total"), subTotal);
        verifyEquals(checkOutPage.getTotalInfo("Shipping"), shippingPrice);
        verifyEquals(checkOutPage.getTotalInfo("Tax"), taxPrice);
        checkOutPage.clickToDynamicButton(driver, "Confirm");
        verifyEquals(checkOutPage.getSuccessMessage(), "Your order has been successfully processed!");
        orderNumber = checkOutPage.getOrderNumber();
        orderNumber = orderNumber.replaceAll("[A-Za-z: ]", "");
        checkOutPage.clickToDynamicButton(driver, "Continue");
        homePage.clickToMenuBar(driver, "My account");
        myAccountPage = PageGeneratorManager.getMyAccountPage(driver);
        myAccountPage.clickToMenuLink("Orders");
        verifyTrue(myAccountPage.isOrderProductDisplayed(orderNumber));
    }

    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private ComputersPageObject computersPage;
    private CartPageObject cartPage;
    private CheckOutPageObject checkOutPage;
    private MyAccountPageObject myAccountPage;
    private String productCode, productName, productPrice, productQty, productTotal, giftWrapping, subTotal, shippingPrice,
            taxPrice, total, orderNumber;
}
