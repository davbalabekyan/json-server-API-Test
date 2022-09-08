package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.User;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

public final class RequestUtils {

    private static final Logger log = LoggerFactory.getLogger(RequestUtils.class);
    private static ValidatableResponse response;

    private RequestUtils() {
    }

    public static ValidatableResponse getResponse() {
        return response;
    }

    public static void getHomePage() {
        log.info("Get Home Page");
        response = RestAssured
                .when()
                .get()
                .then();
    }

    public static void getAllUsers() {
        response = RestAssured
                .when()
                .get(getValueFromPropertyFile("users"))
                .then();
    }


    public static void getUserById(int id) {
        response = RestAssured
                .when()
                .get(getValueFromPropertyFile("users") + id)
                .then();
    }

    public static void getUsersBySubjectId(int subjectId) {
        response = RestAssured
                .when()
                .get(getValueFromPropertyFile("subjects") + subjectId + "/" + getValueFromPropertyFile("users"))
                .then();
    }

    @SneakyThrows
    public static void createNewUser(User body) {
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
    public static void updateUserById(int id, User body) {
        ObjectMapper mapper = new ObjectMapper();

        response = RestAssured
                .given()
                .spec(getRequestSpecification())
                .body(mapper.writeValueAsString(body))
                .when()
                .put(getValueFromPropertyFile("users") + id)
                .then();
    }

    public static void updateUserByIdPatch(int id, Map<String, Object> user) {
        JSONObject jsonObject = new JSONObject(user);
        response = RestAssured
                .given()
                .spec(getRequestSpecification())
                .body(jsonObject.toJSONString())
                .when()
                .patch(getValueFromPropertyFile("users") + id)
                .then();
    }

    public static void deleteUserById(int id) {
        response = RestAssured
                .when()
                .delete(getValueFromPropertyFile("users") + id)
                .then();
    }

    public static void getAllSubjects() {
        response = RestAssured
                .when()
                .get(getValueFromPropertyFile("subjects"))
                .then();
    }

    public static void getSubjectById(int subjectId) {
        response = RestAssured
                .when()
                .get(getValueFromPropertyFile("subjects") + subjectId)
                .then();
    }

    private static RequestSpecification getRequestSpecification() {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        return specBuilder
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    @SneakyThrows
    private static String getValueFromPropertyFile(String key) {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/endpoints.properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }
}
