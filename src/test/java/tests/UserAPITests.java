package tests;

import data_provider.DataProviderForUser;
import io.qameta.allure.Description;
import utils.RequestUtils;
import utils.ResponseUtils;
import core.BaseTest;
import pojo.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserAPITests extends BaseTest {

    @Test(priority = 3)
    @Description("Get home page")
    public void getHomePage() {
        RequestUtils.getHomePage();
        Assert.assertEquals(ResponseUtils.getResponse().extract().statusCode(), HttpStatus.SC_OK);
    }

    @Test(priority = 3)
    @Description("Get all users")
    public void getAllUsers() {
        RequestUtils.getAllUsers();
        ResponseUtils.validateResponseAgainstJsonSchema("src/test/resources/schema/getRequestSchema.json");
        Assert.assertEquals(ResponseUtils.getResponse().extract().statusCode(), HttpStatus.SC_OK);
    }

    @Test(priority = 3)
    @Description("Get user by id")
    public void getUserById() {
        RequestUtils.getUserById(4);
        User user = ResponseUtils.getObjectFromGetPutAndDeleteResponse(User.class, "");
        Assert.assertEquals(user.getId(), 4);
    }

    @Test(priority = 3)
    @Description("Get users by subject id")
    public void getUsersBySubjectId() {
        RequestUtils.getUsersBySubjectId(2);
        Assert.assertEquals(ResponseUtils.getResponse().extract().statusCode(), HttpStatus.SC_OK);
    }

    @Test(dataProviderClass = DataProviderForUser.class, dataProvider = "dataProviderForPost", priority = -1)
    @Description("Create new user")
    public void createNewUser(String firstName, String lastName, String email, String gender, String status, int subjectId) {
        RequestUtils.createNewUser(new User(firstName, lastName, email, gender, status, subjectId));
        User objectFromPostResponse = ResponseUtils.getObjectFromPostResponse(User.class, "");
        Assert.assertEquals(objectFromPostResponse.getFirstName(), firstName);
    }

    @Test(dataProviderClass = DataProviderForUser.class, dataProvider = "dataProviderForPut")
    @Description("Update user by id")
    public void updateUserById(String firstName, String lastName, String email, String gender, String status, int subjectId) {
        RequestUtils.updateUserById(9, new User(firstName, lastName, email, gender, status, subjectId));
        Assert.assertEquals(ResponseUtils.getResponse().extract().statusCode(), HttpStatus.SC_OK);
    }

    @Test(priority = 1)
    @Description("Update user by id by PATCH method")
    public void updateUserByIdByPatch() {
        Map<String, Object> mapOfUserFields = new LinkedHashMap<>();
        mapOfUserFields.put("firstName", "name");
        mapOfUserFields.put("lastName", "surname");
        RequestUtils.updateUserByIdPatch(9, mapOfUserFields);
        User objectFromResponse = ResponseUtils.getObjectFromGetPutAndDeleteResponse(User.class, "");
        Assert.assertEquals(objectFromResponse.getFirstName(), "name");
    }

    @Test(priority = 2)
    @Description("Delete user by id")
    public void deleteUserById() {
        RequestUtils.deleteUserById(9);
        Assert.assertEquals(ResponseUtils.getResponse().extract().statusCode(), HttpStatus.SC_OK);
    }
}
