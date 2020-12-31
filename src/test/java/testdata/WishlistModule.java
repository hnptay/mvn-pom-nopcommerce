package testdata;

import commons.DataHelper;


public class WishlistModule {
    private static DataHelper data = DataHelper.getData();
    public static final String GENDER = "Male";
    public static final String FIRST_NAME = data.getFirstName();
    public static final String LAST_NAME = data.getLastName();
    public static final String DATE = "11";
    public static final String MONTH = "November";
    public static final String YEAR = "2001";
    public static final String EMAIL = data.getEmail();
    public static final String COMPANY = "TMA";
    public static final String PASSWORD = "123456";
}
