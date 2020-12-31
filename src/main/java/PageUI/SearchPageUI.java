package PageUI;

public class SearchPageUI {
    public static final String SEARCH_BUTTON = "//div[@class='buttons']//input";
    public static final String SEARCH_TEXTBOX = "//label[contains(text(),'Search keyword')]//following-sibling::input";
    public static final String DYNAMIC_CHECKBOX = "//label[contains(text(),'%s')]//preceding-sibling::input";
    public static final String DYNAMIC_DROPDOWN = "//label[contains(text(),'%s')]//following-sibling::select";
    public static final String PRICE_RANGE = "//input[@class='price-%s']";
    public static final String SEARCH_RESULT_ERROR = "//div[@class='search-results']/div";
    public static final String SEARCH_RESULT_PRODUCT_TITLE = "//div[@class='search-results']//h2[@class='product-title']/a";
}
