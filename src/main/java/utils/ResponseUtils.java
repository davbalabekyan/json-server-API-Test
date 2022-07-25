package utils;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@UtilityClass
public class ResponseUtils {

    public ValidatableResponse getResponse() {
        return RequestUtils.getResponse();
    }

    public <T> T getObjectFromGetPutAndDeleteResponse(Class<T> type, String path) {
        return getResponse()
                .spec(getResponseSpecification())
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getObject(path, type);
    }

    public <T> T getObjectFromPostResponse(Class<T> type, String path) {
        return getResponse()
                .spec(getResponseSpecification())
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .jsonPath()
                .getObject(path, type);
    }

    public <T> List<T> getListOfObjectsFromResponse(Class<T> type, String path) {
        return getResponse()
                .extract()
                .jsonPath()
                .getList(path, type);
    }

    private ResponseSpecification getResponseSpecification() {
        ResponseSpecBuilder specBuilder = new ResponseSpecBuilder();
        return specBuilder
                .expectResponseTime(Matchers.lessThan(10L), TimeUnit.SECONDS)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public void validateResponseAgainstJsonSchema(String path) {
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
