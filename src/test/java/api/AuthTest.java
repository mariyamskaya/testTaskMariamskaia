package api;

import Helpers.DataHelper;
import Helpers.GuestHelper;
import Models.User;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthTest extends BaseTest {

    public User player;

    @Before
    public void precondition() {
        Map<String,String> userData = DataHelper.generateUserCredentials();

        this.player = new User(userData.get("username"), userData.get("email"), userData.get("password"));
        this.player.register();
    }

    @Test
    public void playerAuth() {
        Map<String,String> jsonAsMap = new HashMap<>();

        jsonAsMap.put("grant_type", "password");
        jsonAsMap.put("username", this.player.getUsername());
        jsonAsMap.put("password", this.player.getPassword());

        String response =
                given()
                        .contentType("application/json")
                        .auth().preemptive().basic(GuestHelper.BASE_AUTH_USERNAME, GuestHelper.BASE_AUTH_TOKEN)
                        .body(jsonAsMap)
                .when()
                        .post("/v2/oauth2/token")
                .then()
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
