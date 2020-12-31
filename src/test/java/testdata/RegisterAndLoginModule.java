package testdata;

import commons.DataHelper;

public class RegisterAndLoginModule {
    private static final DataHelper data = DataHelper.getData();
    public static final String GENDER = "Male";
    public static final String FIRST_NAME = data.getFirstName();
    public static final String LAST_NAME = data.getLastName();
    public static final String DATE = "3";
    public static final String MONTH = "May";
    public static final String YEAR = "2000";
    public static final String INVALID_EMAIL = "123";
    public static final String NOT_REGISTER_EMAIL = "456@hotmail.com";
    public static final String VALID_EMAIL = data.getEmail();
    public static final String EMAIL_EXISTS = "123@gmail.com";
    public static final String COMPANY_NAME = "TMA";
    public static final String VALID_PASSWORD = "123456";
    public static final String INVALID_PASSWORD = "123";

}
