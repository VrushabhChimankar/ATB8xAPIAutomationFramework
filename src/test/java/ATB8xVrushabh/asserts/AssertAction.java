package ATB8xVrushabh.asserts;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;
public class AssertAction {


    public void verifyResponseBody(String actual, String expected, String description){

        assertEquals(actual, expected, description);

    }

    public void verifyStatusCode(Response response, Integer expected){
        assertEquals(response.statusCode(), expected);
    }

    public void verifyStringKey(String keyExpect,String keyActual){
        // AssertJ
        assertThat(keyExpect).isNotNull();
        assertThat(keyExpect).isNotNull().isNotBlank();
        assertThat(keyExpect).isEqualTo(keyActual);

    }


}
