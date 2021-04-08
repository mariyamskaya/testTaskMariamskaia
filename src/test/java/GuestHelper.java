import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GuestHelper {

    public static final String BASE_AUTH_USERNAME = "front_2d6b0a8391742f5d789d7d915755e09e";

    public static final String BASE_AUTH_TOKEN = "c29tZWNsaWVudDphbmRpdHNzZWNyZXQ=";

    public static String generateGuestToken() {
        Map<String,String> jsonAsMap = new HashMap<>();

        jsonAsMap.put("grant_type", "client_credentials");
        jsonAsMap.put("scope", "guest:default");

        String response =
                given()
                        .contentType("application/json")
                        .auth().preemptive().basic(BASE_AUTH_USERNAME, BASE_AUTH_TOKEN)
                        .body(jsonAsMap)
                        .post("/v2/oauth2/token")
                .then()
                        .extract()
                        .body()
                        .asString();

        JSONObject responseJson = new JSONObject(response);

        return responseJson.get("access_token").toString();
    }
}
