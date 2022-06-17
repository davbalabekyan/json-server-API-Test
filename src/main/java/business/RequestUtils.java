package business;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import model.User;
import org.json.simple.JSONObject;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

@UtilityClass
public class RequestUtils {

    private ValidatableResponse response;

    public ValidatableResponse getResponse() {
        return response;
    }

    public void getHomePage() {
        response = RestAssured
                .when()
                .get()
                .then();
    }

    public void getAllUsers() {
        response = RestAssured
                .when()
                .get(getValueFromPropertyFile("users"))
                .then();
    }


    public void getUserById(int id) {
        response = RestAssured
                .when()
                .get(getValueFromPropertyFile("users") + id)
                .then();
    }

    public void getUsersBySubjectId(int subjectId) {
        response = RestAssured
                .when()
                .get(getValueFromPropertyFile("subjects") + subjectId + "/" + getValueFromPropertyFile("users"))
                .then();
    }

    //
    @SneakyThrows
    public void createNewUser(User body) {
        ObjectMapper mapper = new ObjectMapper();
        response = RestAssured
                .given()
                .spec(getRequestSpecification())
                .body(mapper.writeValueAsString(body))
                .when()
                .post(getValueFromPropertyFile("users"))
                .then();
    }

    @SneakyThrows
    public void updateUserById(int id, User body) {
        ObjectMapper mapper = new ObjectMapper();
        response = RestAssured
                .given()
                .spec(getRequestSpecification())
                .body(mapper.writeValueAsString(body))
                .when()
                .put(getValueFromPropertyFile("users") + id)
                .then();
    }

    public void updateUserByIdPatch(int id, Map<String, String> user) {
        JSONObject jsonObject = new JSONObject(user);
        response = RestAssured
                .given()
                .spec(getRequestSpecification())
                .body(jsonObject.toJSONString())
                .when()
                .patch(getValueFromPropertyFile("users") + id)
                .then();
    }

    public void deleteUserById(int id) {
        response = RestAssured
                .when()
                .delete(getValueFromPropertyFile("users") + id)
                .then();
    }

    public void getAllSubjects() {
        response = RestAssured
                .when()
                .get(getValueFromPropertyFile("subjects"))
                .then();
    }

    public void getSubjectById(int subjectId) {
        response = RestAssured
                .when()
                .get(getValueFromPropertyFile("subjects") + subjectId)
                .then();
    }

    private RequestSpecification getRequestSpecification() {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        return specBuilder
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    @SneakyThrows
    private String getValueFromPropertyFile(String key) {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/endpoints.properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }
}
