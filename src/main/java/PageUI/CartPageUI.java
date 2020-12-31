package PageUI;

public class CartPageUI {
    public static final String DYNAMIC_BUTTON = "//input[@value='%s']";
    public static final String ESTIMATE_SHIPPING_BUTTON = "//a[@class='estimate-shipping-button']";
    public static final String TERMS_CHECKBOX = "//input[@id='termsofservice']";
    public static final String GIFT_WRAPPING_DROPDOWN = "//select[@id='checkout_attribute_1']";
    public static final String COUNTRY_STATE_DROPDOWN = "//select[@placeholder='%s']";
    public static final String ZIP_CODE_TEXTBOX = "//input[@id='ZipPostalCode']";
    public static final String DYNAMIC_RADIO_BUTTON = "//div[contains(text(),'%s')]//preceding-sibling::div[@class='estimate-shipping-row-item-radio']//label";
    public static final String GIFT_WRAPPING_INFO = "//div[@class='selected-checkout-attributes']";
    public static final String CART_TOTAL_INFO = "//label[text()='%s:']//parent::td//following-sibling::td";
    public static final String PRODUCT_INFO = "//table[@class='cart']//tr[%s]//td[%s]";
    public static final String COLUMN_HEADER = "//th[contains(text(),'%s')]//preceding-sibling::th";
    public static final String CHECKOUT_BUTTON = "//button[@value='checkout']";
    public static final String QUANTITY_NUMBER = "//table[@class='cart']//tr[%s]//td[%s]//input";
}

