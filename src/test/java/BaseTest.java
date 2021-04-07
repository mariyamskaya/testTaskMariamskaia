import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import org.junit.BeforeClass;

public class BaseTest {
    @BeforeClass
    public static void setup() {
        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://test-api.d6.dev.devcaz.com";
        }
        RestAssured.baseURI = baseHost;
    }
}
