package utils;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static utils.Specifications.getResponseSpecification;

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
}
