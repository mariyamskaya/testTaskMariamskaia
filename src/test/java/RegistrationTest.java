import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class RegistrationTest extends BaseTest{

    protected String username;

    protected String email;

    protected String password;

    protected String name;

    protected String surname;

    @Test
    public void registerNewPlayer() {
        String guestToken = UserHelper.getGuestToken();

        this.username = UUID.randomUUID().toString().split("-")[0];
        this.email = this.username + "@example.com";
        this.password = "amFuZWRvZEyMw==";
        this.name = UUID.randomUUID().toString().split("-")[0];
        this.surname = UUID.randomUUID().toString().split("-")[0];

        Map<String,String> jsonAsMap = new HashMap<>();

        System.out.println(username);
        System.out.println(email);
        System.out.println(name);
        System.out.println(surname);

        jsonAsMap.put("username", this.username);
        jsonAsMap.put("password_change", this.password);
        jsonAsMap.put("password_repeat", this.password);
        jsonAsMap.put("email", this.email);
        jsonAsMap.put("name", this.name);
        jsonAsMap.put("surname", this.surname);

        String response =
                given()
                        .contentType("application/json")
                        .auth().oauth2(guestToken)
                        .body(jsonAsMap)
                .when()
                        .post("/v2/players")
                .then()
                        .statusCode(201)
                        .extract()
                        .body()
                        .asString();

        JSONObject responseJson = new JSONObject(response);

        Assert.assertTrue(responseJson.get("id").toString() != null && !responseJson.get("id").toString().isEmpty());
        Assert.assertNull(responseJson.get("country_id"));
        Assert.assertNull(responseJson.get("timezone_id").toString());
        Assert.assertEquals(this.username, responseJson.get("username").toString());
        Assert.assertEquals(this.email, responseJson.get("email").toString());
        Assert.assertEquals(this.name, responseJson.get("name").toString());
        Assert.assertEquals(this.surname, responseJson.get("surname").toString());
        Assert.assertNull(responseJson.get("gender").toString());
        Assert.assertNull(responseJson.get("phone_number").toString());
        Assert.assertNull(responseJson.get("birthdate").toString());
        Assert.assertEquals(true, responseJson.get("bonuses_allowed"));
        Assert.assertEquals(false, responseJson.get("is_verified"));
    }

}
