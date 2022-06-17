package data_provider;

import enums.Gender;
import enums.Status;
import org.testng.annotations.DataProvider;

public class DataProviderForUser {

    @DataProvider(name = "dataProviderForPostAndPut")
    public Object[][] dataForPostAndPut() {
        return new Object[][]{
                {"firstName", "lastName", "email@dsd.com", Gender.MALE.name().toLowerCase(), Status.ACTIVE.name().toLowerCase(), 1},
        };
    }
}
