import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class DeletePost {

    @Test
    public void deletePost() {
        when().delete("http://localhost:3000/posts/1").then().log().all();
    }
}
