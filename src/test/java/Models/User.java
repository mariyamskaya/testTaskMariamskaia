package Models;

import Helpers.GuestHelper;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class User {
    private int id;

    private String username;

    private String email;

    private String password;

    private String name;

    private String surname;

    private String authToken;

    public User(String username, String email, String password, String name, String surname) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = "TestUsername";
        this.surname = "TestSurname";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void register() {
        Map<String,String> jsonAsMap = new HashMap<>();

        jsonAsMap.put("username", this.username);
        jsonAsMap.put("password_change", this.password);
        jsonAsMap.put("password_repeat", this.password);
        jsonAsMap.put("email", this.email);
        jsonAsMap.put("name", this.name);
        jsonAsMap.put("surname", this.surname);

        String response =
                given()
                        .contentType("application/json")
                        .auth().oauth2(GuestHelper.generateGuestToken())
                        .body(jsonAsMap)
                        .post("/v2/players")
                .then()
                        .extract()
                        .body()
                        .asString();

        JSONObject responseJson = new JSONObject(response);

        this.id = Integer.parseInt(responseJson.get("id").toString());
    }

    public void authenticate() {
        Map<String,String> jsonAsMap = new HashMap<>();

        jsonAsMap.put("grant_type", "password");
        jsonAsMap.put("username", this.username);
        jsonAsMap.put("password", this.password);

        String response =
                given()
                        .contentType("application/json")
                        .auth().preemptive().basic(GuestHelper.BASE_AUTH_USERNAME, GuestHelper.BASE_AUTH_TOKEN)
                        .body(jsonAsMap)
                        .post("/v2/oauth2/token")
                        .then()
                        .extract()
                        .body()
                        .asString();

        JSONObject responseJson = new JSONObject(response);

        this.authToken = responseJson.get("access_token").toString();
    }
}
