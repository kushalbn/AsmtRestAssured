package assignment.tests;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


/**
 * Just pure rest-assured tests
 */

public class DemoTests {
    public static ResponseSpecification verifyStatusCode;
    public static ResponseSpecification verifyContentType;
    public static ResponseSpecification verifyResponseBody;
    public static ResponseSpecification verifyCompanyName;


    @BeforeClass
    public static void verifyStatusCode() {
        verifyStatusCode = new ResponseSpecBuilder().
        expectStatusCode(200).build();
    }

    @BeforeClass
    public static void verifyContentType() {
        verifyContentType = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).build();
    }

//    @BeforeClass
//    public static void verifyResponseBody() {
//        verifyResponseBody = new ResponseSpecBuilder().
//        expectBody()["status", equalTo(200),
//                "employeeData[0].dob", equalTo("25-02-1994"),
//                "message", equalTo("data retrieved successful"),
//                "employeeData[0].age", equalTo(25),
//                "employeeData[0].role", equalTo("QA Automation Developer"]).build();
//    }

    @BeforeClass
    public static void verifyCompany() {
        verifyCompanyName = new ResponseSpecBuilder().
        expectBody("employeeData[0].company",equalTo("ABC Infotech")).build();
    }

    @Test
    public void getDemo() {
        given()
                .baseUri(Constants.BASE_URL)
                .log().everything()
                .contentType(ContentType.JSON)
                .get("/apitest")
                .then()
                .log().body()
                .spec(verifyStatusCode)
                .spec(verifyContentType)
                .spec(verifyCompanyName)
                .and()
                .assertThat()
                .body("status", CoreMatchers.equalTo(200),
                        "employeeData[0].dob", CoreMatchers.equalTo("25-02-1994"),
                        "message", CoreMatchers.equalTo("data retrieved successful"),
                        "employeeData[0].age", CoreMatchers.equalTo(25),
                        "employeeData[0].role", CoreMatchers.equalTo("QA Automation Developer"));
    }
}
