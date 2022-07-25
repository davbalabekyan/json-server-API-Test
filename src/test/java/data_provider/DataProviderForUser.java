package data_provider;

import enums.Gender;
import enums.Status;
import org.testng.annotations.DataProvider;

public class DataProviderForUser {

    @DataProvider(name = "dataProviderForPost")
    public static Object[][] dataForPost() {
        return new Object[][]{
                {"Petros", "Petrosyan", "petrosyan@mail.ru", Gender.MALE.name().toLowerCase(), Status.INACTIVE.name().toLowerCase(), 2}
        };
    }

    @DataProvider(name = "dataProviderForPut")
    public static Object[][] dataForPut() {
        return new Object[][]{
                {"New", "User", "newueser@mail.ru", Gender.FEMALE.name().toLowerCase(), Status.INACTIVE.name().toLowerCase(), 2}
        };
    }
}
