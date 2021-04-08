import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataHelper {

    public static Map<String, String> generateUserCredentials() {
        Map<String,String> data = new HashMap<>();

        String username = UUID.randomUUID().toString().split("-")[0];
        String email = username + "@example.com";
        String password = "amFuZWRvZEyMw==";

        data.put("username", username);
        data.put("email", email);
        data.put("password", password);

        return data;
    }
}
