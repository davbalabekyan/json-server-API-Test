import business.RequestUtils;
import business.ResponseUtils;
import core.BaseTest;
import data_provider.DataProviderForUser;
import model.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserAPITests extends BaseTest {

    @Test
    public void getHomePage() {
        RequestUtils.getHomePage();
        Assert.assertEquals(ResponseUtils.getResponse().extract().statusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void getAllUsers() {
        RequestUtils.getAllUsers();
        ResponseUtils.validateResponseAgainstJsonSchema("src/test/resources/schema/getRequestSchema.json");
        Assert.assertEquals(ResponseUtils.getResponse().extract().statusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void getUserById() {
        RequestUtils.getUserById(4);
        User user = ResponseUtils.getObjectFromGetPutAndDeleteResponse(User.class, "");
        Assert.assertEquals(user.getId(), 4);
    }

    @Test
    public void getUsersBySubjectId() {
        RequestUtils.getUsersBySubjectId(2);
        Assert.assertEquals(ResponseUtils.getResponse().extract().statusCode(), HttpStatus.SC_OK);
    }

    @Test(dataProviderClass = DataProviderForUser.class, dataProvider = "dataProviderForPostAndPut")
    public void createNewUser(String firstName, String lastName, String email, String gender, String status, int subjectId) {
        RequestUtils.createNewUser(new User(firstName, lastName, email, gender, status, subjectId));
        User objectFromPostResponse = ResponseUtils.getObjectFromPostResponse(User.class, "");
        Assert.assertEquals(objectFromPostResponse.getFirstName(), firstName);
    }

    @Test(dataProviderClass = DataProviderForUser.class, dataProvider = "dataProviderForPostAndPut")
    public void updateUserById(String firstName, String lastName, String email, String gender, String status, int subjectId) {
        RequestUtils.updateUserById(7, new User(firstName, lastName, email, gender, status, subjectId));
        Assert.assertEquals(ResponseUtils.getResponse().extract().statusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void updateUserByIdByPatch() {
        Map<String, String> mapOfUserFields = new LinkedHashMap<>();
        mapOfUserFields.put("firstName", "name");
        mapOfUserFields.put("lastName", "surname");
        RequestUtils.updateUserByIdPatch(7, mapOfUserFields);
        User objectFromResponse = ResponseUtils.getObjectFromGetPutAndDeleteResponse(User.class, "");
        Assert.assertEquals(objectFromResponse.getFirstName(), "name");
    }

    @Test
    public void deleteUserById() {
        RequestUtils.deleteUserById(7);
        Assert.assertEquals(ResponseUtils.getResponse().extract().statusCode(), HttpStatus.SC_OK);
    }
}
