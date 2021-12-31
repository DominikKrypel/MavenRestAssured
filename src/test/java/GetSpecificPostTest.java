import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GetSpecificPostTest {


    @Test
    public void getPost(){
      given().log().all().pathParam("postId", "4").
              when().get("http://localhost:3000/posts/{postId}").
              then().log().all(); // definiujemy metodę pathParam po to, żeby nie hardkodować numeru post-a
        // dzieki metodzie ".log().all()" wypisujemy na konsoli/terminalu wszystko, również nagłówki, w tym przypadku dla konkretnego POST-a

      given().log().all().
              when().get("http://localhost:3000/posts/{postId}", 1).
              then().log().all();//drugi sposób na to, żeby nie hardkodować id-ka
    }
}
