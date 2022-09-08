package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import java.util.concurrent.TimeUnit;

public final class Specifications {

    private Specifications() {
    }

    public static RequestSpecification getRequestSpecification() {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        return specBuilder
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification getResponseSpecification() {
        ResponseSpecBuilder specBuilder = new ResponseSpecBuilder();
        return specBuilder
                .expectResponseTime(Matchers.lessThan(10L), TimeUnit.SECONDS)
                .expectContentType(ContentType.JSON)
                .build();
    }
}
