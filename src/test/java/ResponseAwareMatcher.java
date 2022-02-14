import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class ResponseAwareMatcher {

    @Test
    public void getPost() {

        when().
                get("http://localhost:3000/posts/{postId}", 1). /*Tutaj walidauję json z numerami zwycięzcy*/
        then().
                body("specificField", new io.restassured.matcher.ResponseAwareMatcher<Response>() {
                    @Override
                    public Matcher<?> matcher(Response response) throws Exception {
                        return equalTo("10192020" + response.path("secretString"));
                    }
                });

        when().
                get("http://localhost:3000/posts/{postId}", 1).
        then().
                body("specificField", response -> equalTo("10192020" + response.path("secretString"))); /*Tutaj ten test wykonuje to samo co powyższy
                                                                                                                               z tą różnicą, że tutaj korzystamy z wyrażenia lambda*/

    }
}
