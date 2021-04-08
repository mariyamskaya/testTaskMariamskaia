import java.util.UUID;

public class DataHelper {
    public static String generateRandomString() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
