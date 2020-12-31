package PageUI;

public class WishlistPageUI {
    public static final String DYNAMIC_BUTTON = "//input[@value='%s']";
    public static final String WISHLIST_URL = "//div[@class='share-info']//a";
    public static final String PRODUCT_NAME = "//a[contains(text(),'%s')]";
    public static final String WISHLIST_TITLE = "//div[@class='page-title']";
    public static final String DYNAMIC_ACTION = "//a[contains(text(),'%s')]//ancestor::tr//td[%s]//input";
    public static final String COLUMN_HEADER = "//th[contains(text(),'%s')]//preceding-sibling::th";
    public static final String WISHLIST_BODY = "//div[@class='page-body']//div";
}
