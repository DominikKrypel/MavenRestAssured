import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class BasicAuth {

    @Test
    public void basicAuth() {
        given()
                .auth().basic("postman", "password").
        when()
                .get("https://postman-echo.com/basic-auth").
        then()
                .log().all();
    }
}
