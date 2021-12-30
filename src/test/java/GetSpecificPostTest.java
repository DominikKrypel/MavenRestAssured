import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetSpecificPostTest {


    @Test
    public void getPost(){
      when().get("http://localhost:3000/posts/1").then().log().all();
        // tutaj wypisujemy na konsoli/terminalu wszystko, również nagłówki, alle tylko dla pierwszego POST-a
    }
}
