package testdata;

import commons.DataHelper;

public class OrderModule {
    private static DataHelper data = DataHelper.getData();

    public static class BillingAddress{
        public static final String FIRST_NAME = data.getFirstName();
        public static final String LAST_NAME = data.getLastName();
        public static final String EMAIL = data.getEmail();
        public static final String COMPANY = data.getCompanyName();
        public static final String COUNTRY = "United States";
        public static final String STATE = "Hawaii";
        public static final String CITY = data.getCity();
        public static final String ADDRESS = data.getAddress();
        public static final String ZIP_CODE = data.getZipCode();
        public static final String PHONE = data.getPhone();
        public static final String FAX = "1900";
    }

    public static class ShippingAddress {
        public static final String FIRST_NAME = data.getFirstName();
        public static final String LAST_NAME = data.getLastName();
        public static final String EMAIL = data.getEmail();
        public static final String COMPANY = data.getCompanyName();
        public static final String COUNTRY = "United States";
        public static final String STATE = "Florida";
        public static final String CITY = data.getCity();
        public static final String ADDRESS = data.getAddress();
        public static final String ZIP_CODE = data.getZipCode();
        public static final String PHONE = data.getPhone();
        public static final String FAX = "1009";
    }

    public static class Register {
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
}
