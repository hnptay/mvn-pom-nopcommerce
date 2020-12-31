package commons;

import org.openqa.selenium.WebDriver;
import pageObject.*;

public class PageGeneratorManager {

    public static RegisterPageObject getRegisterPage(WebDriver driver){
        return new RegisterPageObject(driver);
    }

    public static HomePageObject getHomePage(WebDriver driver){
        return new HomePageObject(driver);
    }

    public static MyAccountPageObject getMyAccountPage(WebDriver driver){
        return new MyAccountPageObject(driver);
    }

    public static LoginPageObject getLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }

    public static WishlistPageObject getWishlistPage(WebDriver driver){
        return new WishlistPageObject(driver);
    }

    public static CartPageObject getCartPage(WebDriver driver){
        return new CartPageObject(driver);
    }

    public static CheckOutPageObject getCheckOutPage(WebDriver driver){
        return new CheckOutPageObject(driver);
    }
}
