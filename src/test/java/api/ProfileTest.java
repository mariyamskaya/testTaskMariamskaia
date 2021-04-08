package api;

import Helpers.DataHelper;
import Models.User;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProfileTest extends BaseTest {

    public User player;

    @Before
    public void precondition() {
        Map<String,String> userData = DataHelper.generateUserCredentials();

        this.player = new User(userData.get("username"), userData.get("email"), userData.get("password"));

        this.player.register();
        this.player.authenticate();
    }

    @Test
    public void getOwnProfileData() {
        Map<String,String> jsonAsMap = new HashMap<>();

        String response =
                given()
                        .contentType("application/json")
                        .auth().preemptive().oauth2(this.player.getAuthToken())
                        .body(jsonAsMap)
                .when()
                        .get("/v2/players/" + player.getId())
                .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .asString();

        JSONObject responseJson = new JSONObject(response);

        Assert.assertEquals(player.getId(), Integer.parseInt(responseJson.get("id").toString()));
        Assert.assertEquals(JSONObject.NULL, responseJson.get("country_id"));
        Assert.assertEquals(JSONObject.NULL, responseJson.get("timezone_id"));
        Assert.assertEquals(player.getUsername(), responseJson.get("username").toString());
        Assert.assertEquals(player.getEmail(), responseJson.get("email").toString());
        Assert.assertEquals(player.getName(), responseJson.get("name").toString());
        Assert.assertEquals(player.getSurname(), responseJson.get("surname").toString());
        Assert.assertEquals(JSONObject.NULL, responseJson.get("gender"));
        Assert.assertEquals(JSONObject.NULL, responseJson.get("phone_number"));
        Assert.assertEquals(JSONObject.NULL, responseJson.get("birthdate"));
        Assert.assertEquals(true, responseJson.get("bonuses_allowed"));
        Assert.assertEquals(false, responseJson.get("is_verified"));
    }

    @Test
    public void getNotExistingProfile() {
        Map<String,String> jsonAsMap = new HashMap<>();

        given()
                .contentType("application/json")
                .auth().preemptive().oauth2(this.player.getAuthToken())
                .body(jsonAsMap)
        .when()
                .get("/v2/players/1")
        .then()
                .statusCode(404);
    }
}
