import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class FirstRestAssuredTest {

    public static void main(String[] args) {
    }

    @Test
    public void getPostsWithBodyOnly() {
        when().get("http://localhost:3000/posts").then().log().body(); //tutaj wypisujemy na konsoli tylko body
    }

    @Test
    public void getPostsWithBodyAndHeaders() {
        when().get("http://localhost:3000/posts").then().log().all(); // tutaj wypisujemy wszystko, również nagłówki
    }

    @Test
    public void checkStatusOfResponse() {
        when().get("http://localhost:3000/posts").then().log().ifValidationFails().statusCode(200); /* tutaj w odpowiedzi otrzymujemy : "Process finished with exit code 0",
                                                                                                         oznacza to, że walidacja przeszła pomyślnie */
    }

}