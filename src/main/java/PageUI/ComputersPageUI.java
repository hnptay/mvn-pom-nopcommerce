package PageUI;

public class ComputersPageUI {
    public static final String DYNAMIC_SUBLIST_MENU = "//ul[@class='sublist']//a[contains(text(),'%s')]";
    public static final String DYNAMIC_PRODUCT_LINK = "//div[@class='product-item']//a[contains(text(),'%s')]";
    public static final String REVIEW_LINK = "//div[@class='product-review-links']//a[contains(text(),'%s')]";
    public static final String PRODUCT_NAME_ITEMS = "//div[@class='product-item']//h2[@class='product-title']/a";
    public static final String DYNAMIC_DROPDOWN = "//span[contains(text(),'%s')]/following-sibling::select";
    public static final String DYNAMIC_PAGING = "//div[@class='pager']//a[contains(text(),'%s')]";
    public static final String PRODUCT_PRICE_ITEMS = "//div[@class='product-item']//div[@class='prices']/span";
    public static final String DYNAMIC_OVERVIEW_BUTTON = "//div[@class='overview-buttons']//input[@value='%s']";
    public static final String BAR_NOTIFICATION = "//div[@id='bar-notification']//p";
    public static final String DYNAMIC_BUTTON = "//a[contains(text(),'%s')]//parent::h2[@class='product-title']//following-sibling::div[@class='add-info']//input[@value='%s']";
    public static final String CLOSE_BAR = "//span[@class='close']";
}
