package api;

import io.restassured.RestAssured;

import org.junit.BeforeClass;

public class Base {
    public static final String BASE_HOST = "http://test-api.d6.dev.devcaz.com";

    @BeforeClass
    public static void setup() {
        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = BASE_HOST;
        }
        RestAssured.baseURI = baseHost;
    }
}
