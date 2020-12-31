package PageUI;

public class CheckOutPageUI {
    public static final String DYNAMIC_CHECKBOX = "//label[contains(text(),'%s')]//preceding-sibling::input";
    public static final String DYNAMIC_BILLING_TEXTBOX = "//label[contains(text(),'%s')]/following-sibling::input";
    public static final String DYNAMIC_CONTINUE_BUTTON = "//h2[contains(text(),'%s')]//ancestor::li[@class='tab-section allow active']//input[@value='Continue']";
    public static final String SHIPPING_ADDRESS_DROPDOWN = "//select[@id='shipping-address-select']";
    public static final String PAYMENT_INFORMATION = "//div[@class='info']//p[2]";
    public static final String ORDER_INFO = "//Strong[text()='%s']//parent::div//following-sibling::ul";
    public static final String COLUMN_HEADER = "//th[contains(text(),'%s')]//preceding-sibling::th";
    public static final String PRODUCT_INFO = "//table[@class='cart']//tr[%s]//td[%s]";
    public static final String CART_TOTAL_CHECKOUT = "//label[text()='%s:']//parent::td//following-sibling::td";
    public static final String SUCCESS_CHECKOUT_MESSAGE = "//div[@class='section order-completed']//div[@class='title']//strong";
    public static final String ORDER_NUMBER = "//div[@class='section order-completed']//div[@class='order-number']//strong";
    public static final String GIFT_WRAPPING_INFO = "//div[@class='selected-checkout-attributes']";
    public static final String DYNAMIC_SHIPPING_DROPDOWN = "//div[@class='shipping-addresses']//select[contains(@name,'%s')]";
    public static final String DYNAMIC_SHIPPING_TEXTBOX = "//div[@class='shipping-addresses']//label[contains(text(),'%s')]/following-sibling::input";
    public static final String DYNAMIC_BILLING_DROPDOWN = "//select[contains(@name,'%s')]";
}
