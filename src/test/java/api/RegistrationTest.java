package api;

import helpers.DataHelper;
import helpers.GuestHelper;
import models.User;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RegistrationTest extends BaseTest{
    @Test
    public void registerNewPlayer() {
        Map<String,String> userData = DataHelper.generateUserCredentials();

        User player = new User(userData.get("username"), userData.get("email"), userData.get("password"));

        Map<String,String> jsonAsMap = new HashMap<>();

        jsonAsMap.put("username", player.getUsername());
        jsonAsMap.put("password_change", player.getPassword());
        jsonAsMap.put("password_repeat", player.getPassword());
        jsonAsMap.put("email", player.getEmail());
        jsonAsMap.put("name", player.getName());
        jsonAsMap.put("surname", player.getSurname());

        String response =
                given()
                        .contentType("application/json")
                        .auth().oauth2(GuestHelper.generateGuestToken())
                        .body(jsonAsMap)
                .when()
                        .post("/v2/players")
                .then()
                        .statusCode(201)
                        .extract()
                        .body()
                        .asString();

        JSONObject responseJson = new JSONObject(response);

        Assert.assertTrue(responseJson.get("id") != null && !responseJson.get("id").toString().isEmpty());
        Assert.assertEquals(JSONObject.NULL, responseJson.get("country_id"));
        Assert.assertEquals(JSONObject.NULL, responseJson.get("timezone_id"));
        Assert.assertEquals(player.getUsername(), responseJson.get("username").toString());
        Assert.assertEquals(player.getEmail(), responseJson.get("email").toString());
        Assert.assertEquals(player.getName(), responseJson.get("name").toString());
        Assert.assertEquals(player.getSurname(), responseJson.get("surname").toString());
        Assert.assertEquals(responseJson.get("gender"), JSONObject.NULL);
        Assert.assertEquals(responseJson.get("phone_number"), JSONObject.NULL);
        Assert.assertEquals(responseJson.get("birthdate"), JSONObject.NULL);
        Assert.assertEquals(true, responseJson.get("bonuses_allowed"));
        Assert.assertEquals(false, responseJson.get("is_verified"));
    }
}
