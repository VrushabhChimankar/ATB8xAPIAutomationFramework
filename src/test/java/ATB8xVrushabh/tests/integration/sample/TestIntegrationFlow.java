package ATB8xVrushabh.tests.integration.sample;

import ATB8xVrushabh.base.BaseTest;
import ATB8xVrushabh.endpoints.APIConstants;
import ATB8xVrushabh.pojos.Booking;
import ATB8xVrushabh.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;



public class TestIntegrationFlow extends BaseTest {


    // Create A Booking, Create a Token
    // Get booking
    // Update the Booking
    // Delete the Booking

    @Test(groups = "qa", priority = 1)
    @Owner("Vrushabh")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext){

        //String Postpath = APIConstants.BASE_URL + APIConstants.CREATE_UPDATE_BOOKING_URL;
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        //Response
        response = RestAssured
                .given(requestSpecification)
                .when().body(payloadManager.CreateBookingPayladAsString()).post();
        validatableResponse = response.then().log().all();
       // validatableResponse.statusCode(200);
        String actual = "200";
        Assert.assertEquals(actual, "200");

        BookingResponse bookingResponse = payloadManager.bookingResponse(response.asString());
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());



    }

    @Test(groups = "qa", priority = 2)
    @Owner("Vrushabh")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext){


        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        //create get request
        String basePathGet = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathGet);

        requestSpecification.basePath(basePathGet);

        response = RestAssured.given(requestSpecification).when().get();
        validatableResponse = response.then().log().all();

        String actual = "200";
        Assert.assertEquals(actual, "200");


        Booking booking = payloadManager.GetResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("James");


    }

    @Test(groups = "qa", priority = 3)
    @Owner("Vrushabh")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext){

        String token = getToken();

        iTestContext.setAttribute("token", token);
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String basePUT = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(basePUT);

        response = RestAssured.given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();

        validatableResponse = response.then().log().all();
       // validatableResponse.statusCode(200);
        String actual = "200";
        Assert.assertEquals(actual, "200");

        Booking booking = payloadManager.GetResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Vrushabh");
        assertThat(booking.getLastname()).isEqualTo("Chimankar");


    }

    @Test(groups = "qa", priority = 4)
    @Owner("Vrushabh")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext){


        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

       String basePathDelete = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

       requestSpecification.basePath(basePathDelete).cookie("token", token);
       validatableResponse = RestAssured.given().spec(requestSpecification).when().delete().then().log().all();
      // validatableResponse.statusCode(201);

       String actual = "201";
      Assert.assertEquals(actual, "201");


    }






}
