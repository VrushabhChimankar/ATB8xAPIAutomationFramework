package ATB8xVrushabh.base;

import ATB8xVrushabh.asserts.AssertAction;
import ATB8xVrushabh.endpoints.APIConstants;
import ATB8xVrushabh.modules.PayloadManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {



    public RequestSpecification requestSpecification;
    public AssertAction assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setup(){

        payloadManager = new PayloadManager();
        assertActions = new AssertAction();

        requestSpecification = RestAssured.given().
                baseUri(APIConstants.BASE_URL).
                contentType(ContentType.JSON).
                log().all();

    }

    public String getToken(){

        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL);

        //Set the payload
        String payload = payloadManager.setAuthPayload();

        //get the payload
        response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();

        //String extraction
        String token = payloadManager.getTokenFromJSON(response.asString());

        return token;







    }












}
