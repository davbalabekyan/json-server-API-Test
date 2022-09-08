package utils;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class ResponseUtils {

    private static final Logger log = LoggerFactory.getLogger(ResponseUtils.class);

    private ResponseUtils() {
    }

    public static ValidatableResponse getResponse() {
        return RequestUtils.getResponse();
    }

    public static <T> T getObjectFromGetPutAndDeleteResponse(Class<T> type, String path) {
        return getResponse()
                .spec(getResponseSpecification())
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getObject(path, type);
    }

    public static <T> T getObjectFromPostResponse(Class<T> type, String path) {
        return getResponse()
                .spec(getResponseSpecification())
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .jsonPath()
                .getObject(path, type);
    }

    public static <T> List<T> getListOfObjectsFromResponse(Class<T> type, String path) {
        return getResponse()
                .extract()
                .jsonPath()
                .getList(path, type);
    }

    private static ResponseSpecification getResponseSpecification() {
        ResponseSpecBuilder specBuilder = new ResponseSpecBuilder();
        return specBuilder
                .expectResponseTime(Matchers.lessThan(10L), TimeUnit.SECONDS)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static void validateResponseAgainstJsonSchema(String path) {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory
                .newBuilder()
                .setValidationConfiguration(ValidationConfiguration.newBuilder()
                        .setDefaultVersion(SchemaVersion.DRAFTV4)
                        .freeze())
                .freeze();
        getResponse()
                .assertThat()
                .body(JsonSchemaValidator
                        .matchesJsonSchema(new File(path))
                        .using(jsonSchemaFactory));
    }
}
