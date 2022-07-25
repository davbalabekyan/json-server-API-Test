package core;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setup() {
//        JsonServer.startJsonServer();
        RestAssured.baseURI = "http://localhost:3000/";
    }
}
