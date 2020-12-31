package PageUI;

public class RegisterPageUI {
    public static final String DYNAMIC_GENDER_RADIOBUTTON = "//label[contains(text(),'Male')]//preceding-sibling::input";
    public static final String DYNAMIC_TEXBOX = "//label[contains(text(),'%s')]/following-sibling::input";
    public static final String OPTION_CHECKBOX = "//input[@type='checkbox']";
    public static final String REGISTER_BUTTON = "//input[@id='register-button']";
    public static final String DYNAMIC_MESSAGE_ERROR = "//span[@id='%s-error']";
    public static final String SUMMARY_MESSAGE_ERROR = "//div[contains(@class,'summary-error')]//li";
    public static final String DYNAMIC_DATEOFBIRTH = "//select[@name='%s']";
    public static final String SUCCESS_MESSAGE = "//div[@class='result']";
    public static final String DYNAMIC_BUTTON = "//input[@value='%s']";
}
