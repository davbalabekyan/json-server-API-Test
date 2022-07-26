package data_provider;

import enums.Gender;
import enums.Status;
import org.testng.annotations.DataProvider;

public class DataProviderForUser {

    @DataProvider(name = "userProvider")
    public static Object[][] userProvider() {
        return new Object[][]{
                {"Petros", "Petrosyan", "petrosyan@mail.ru", Gender.MALE.name().toLowerCase(), Status.INACTIVE.name().toLowerCase(), 2}
        };
    }
}
