package testdata;

import commons.DataHelper;


public class MyAccountModule {
    private static final DataHelper data = DataHelper.getData();

    public static class EditInfo{
        public static final String GENDER = "female";
        public static final String FIRST_NAME = data.getFirstName();
        public static final String LAST_NAME = data.getLastName();
        public static final String DATE = "4";
        public static final String MONTH = "July";
        public static final String YEAR = "2002";
        public static final String VALID_EMAIL = data.getEmail();
        public static final String COMPANY_NAME = "MAT";
        public static final String COUNTRY = "United States";
        public static final String STATE = "Hawaii";
        public static final String CITY = "New York";
        public static final String ADDRESS1 = data.getAddress();
        public static final String ADDRESS2 = data.getAddress();
        public static final String PHONE = data.getPhone();
        public static final String FAX = "911991";
        public static final String ZIP_CODE = data.getZipCode();
        public static final String NEW_PASSWORD = "456456";
    }

    public static class Register{
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
}
