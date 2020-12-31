package PageUI;

public class AbstractPageUI {
    public static final String DYNAMIC_CATEGORY_MENU = "//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]";
    public static final String DYNAMIC_BUTTON = "//input[@value='%s']";
    public static final String DYNAMIC_LOGOUT_LOGIN_LINK = "//div[@class='header-links']//a[contains(text(),'%s')]";
    public static final String DYNAMIC_WISHLIST_SHOPPING_LINK = "//div[@class='header-links']//span[contains(text(),'%s')]";
    public static final String REVIEW_TEXTBOX = "//label[contains(text(),'%s')]/following-sibling::input";
    public static final String REVIEW_TEXTAREA = "//label[contains(text(),'%s')]/following-sibling::textarea";
    public static final String REVIEW_DYNAMIC_RADIO_BUTTON = "//input[@aria-label='%s']";
    public static final String SUMMARY_MESSAGE_INFO = "//div[@class='result']";
    public static final String CUSTOMER_SERVICE_MENU_LINK = "//div[@class='footer-block customer-service']//a[contains(text(),'%s')]";
}
