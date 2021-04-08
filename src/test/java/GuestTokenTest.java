import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GuestTokenTest extends BaseTest {
    @Test
    public void getGuestToken() {
        Map<String,String> jsonAsMap = new HashMap<>();

        jsonAsMap.put("grant_type", "client_credentials");
        jsonAsMap.put("scope", "guest:default");

        String response =
        given()
                .contentType("application/json")
                .auth().preemptive().basic(GuestHelper.BASE_AUTH_USERNAME, GuestHelper.BASE_AUTH_TOKEN)
                .body(jsonAsMap)
        .when()
                .post("/v2/oauth2/token")
        .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        JSONObject responseJson = new JSONObject(response);

        Assert.assertEquals("Bearer", responseJson.get("token_type").toString());
        Assert.assertEquals("86400", responseJson.get("expires_in").toString());
        Assert.assertTrue(responseJson.get("access_token") != JSONObject.NULL && !responseJson.get("access_token").toString().isEmpty());
    }
}
