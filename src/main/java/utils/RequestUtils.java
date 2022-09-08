package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.User;

import java.util.Map;

import static utils.PropertiesReader.getValueFromPropertyFile;
import static utils.Specifications.getRequestSpecification;

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

    public static void createNewUser(User body) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            response = RestAssured
                    .given()
                    .spec(getRequestSpecification())
                    .body(mapper.writeValueAsString(body))
                    .when()
                    .post(getValueFromPropertyFile("users"))
                    .then();
        } catch (JsonProcessingException e) {
            log.error("Error occurred while sending body");
        }
    }

    public static void updateUserById(int id, User body) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            response = RestAssured
                    .given()
                    .spec(getRequestSpecification())
                    .body(mapper.writeValueAsString(body))
                    .when()
                    .put(getValueFromPropertyFile("users") + id)
                    .then();
        } catch (JsonProcessingException e) {
            log.error("Error occurred while sending body");
        }
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
}
