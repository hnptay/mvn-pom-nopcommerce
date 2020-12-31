package commons;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {
    private Faker faker = new Faker(new Locale("en"));

    public static DataHelper getData(){
        return new DataHelper();
    }

    public String getFirstName(){
        return faker.address().firstName();
    }

    public String getLastName(){
        return faker.address().lastName();
    }

    public String getCompanyName(){
        return faker.company().name();
    }

    public String getFullName(){
        return faker.name().fullName();
    }

    public String getAddress(){
        return faker.address().streetAddress();
    }

    public String getEmail(){
        return faker.internet().emailAddress();
    }

    public String getPhone(){
        return faker.phoneNumber().phoneNumber();
    }

    public String getCity(){
        return faker.address().city();
    }

    public String getCityName(){
        return faker.address().cityName();
    }

    public String getZipCode(){
        return faker.address().zipCode();
    }

}
