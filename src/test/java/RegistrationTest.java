import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

public class RegistrationTest extends BaseTest{
    @Test
    public void registerNewPlayer() {

        Map<String,String> jsonAsMap2 = new HashMap<>();

        jsonAsMap2.put("grant_type", "client_credentials");
        jsonAsMap2.put("scope", "guest:default");
        String getToken =
                given()
                        .contentType("application/json")
                        .auth().preemptive().basic("front_2d6b0a8391742f5d789d7d915755e09e","c29tZWNsaWVudDphbmRpdHNzZWNyZXQ=")
                        .body(jsonAsMap2)
                        .when()
                        .post("/v2/oauth2/token")
                        .then()
                        .extract()
                        .asString();

        JSONObject responseJson = new JSONObject(getToken);

        String token = responseJson.get("access_token").toString();



        Map<String,String> jsonAsMap = new HashMap<>();

        jsonAsMap.put("username", "niki12342");
        jsonAsMap.put("password_change", "amFuZWRvZEyMw==");
        jsonAsMap.put("password_repeat", "amFuZWRvZEyMw==");
        jsonAsMap.put("email", "niki14368657@example.com");
        jsonAsMap.put("name", "Niki");
        jsonAsMap.put("surname", "Doe");
        jsonAsMap.put("currency_code", "CAD");

        String response =
                given()
                        .contentType("application/json")
                        .auth().oauth2("Bearer " + token)
                        .body(jsonAsMap)
                .when()
                        .post("/v2/players")
                .then()
//                        .statusCode(201)
                        .extract()
                        .asString();

        System.out.println(response);
    //    JSONObject responseJson = new JSONObject($response);
//
//        Assert.assertEquals("Bearer", responseJson.get("token_type").toString());
//        Assert.assertEquals("86400", responseJson.get("expires_in").toString());
//        Assert.assertTrue(responseJson.get("access_token") != null && !responseJson.get("access_token").toString().isEmpty());
    }
}
