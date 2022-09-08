package tests;

import core.BaseTest;
import org.apache.http.HttpStatus;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import pojo.Subject;
import utils.RequestUtils;
import utils.ResponseUtils;

public class SubjectAPITests extends BaseTest {

    @Test()
    public void getAllSubjects() {
        RequestUtils.getAllSubjects();
        assertEquals(ResponseUtils.getResponse().extract().statusCode(), HttpStatus.SC_OK);
    }

    @Test()
    public void getSubjectById() {
        RequestUtils.getSubjectById(2);
        Subject objectFromResponse = ResponseUtils.getObjectFromGetPutAndDeleteResponse(Subject.class, "");
        assertEquals(objectFromResponse.getId(), 2);
    }
}
