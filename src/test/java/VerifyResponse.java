import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class VerifyResponse {


    @Test
    public void getPost(){

        String expected = "{\n" +
                "  \"title\": \"Pierwszy POST\",\n" +
                "  \"author\": \"Dominik\",\n" +
                "  \"id\": 4\n" +
                "}";

        given().log().all().
                when().get("http://localhost:3000/posts/{postId}", 4).
                then().log().all().body(Matchers.equalTo(expected)); // Sprawdzenie całości ciała
                                                                     // porównujemy czy w odpowiedzi w Body znajduje się wartość przez nas oczekwiana.
                                                                     // Wartość oczekiwana jest zachowana w zmiennej "expected"
    }

    @Test
    public void getPostContains(){

        given().log().all().
                when().get("http://localhost:3000/posts/{postId}", 4).
                then().log().all().body(Matchers.containsString("Dominik")); // Tutaj szukamy czy odpowiedź w body zawiera słowo "Dominik"
    }

    @Test
    public void checkSpecificField(){

        given().log().all().
                when().get("http://localhost:3000/posts/{postId}", 4).
                then().log().all().body("title", Matchers.equalTo("Pierwszy POST")).// tutaj sprawdzamy wartość konkretnego pola, w naszym przypadku title
                and().body("author", Matchers.equalTo("Dominik")); // jeżeli skorzystamny z "and()" po sekcji "then()" to robimy kolejną weryfikację
    }
}
