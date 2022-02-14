import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ResponseTime {

    @Test
    public void getPost() {

       Long time = when()
                .get("http://localhost:3000/posts/{postId}", 4).timeIn(TimeUnit.MICROSECONDS);
                System.out.println(time); /*Drukujemy na konksolę jaki był czas odpowiedzic na POST-a*/

        when()
                .get("http://localhost:3000/posts/{postId}", 4).
        then()
                .time(Matchers.lessThan(50000L), TimeUnit.MICROSECONDS); /*Sprawdzamy czy czas odpowiedzi będzie mniejszy niż 50 000 MICROSECONDS*/
    }
}
