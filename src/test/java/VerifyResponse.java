import model.Post;
import org.hamcrest.Matchers;
import org.testng.Assert;
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

    @Test
    public void getPostObject(){
        //|Weryfikacja ciała odpowiedzi - obiekt klasy Post
        Integer id = 4;

       Post newPost = given().log().all().
                when().get("http://localhost:3000/posts/{postId}", id).
                then().log().all().body("title", Matchers.equalTo("Pierwszy POST")).
                and().body("author", Matchers.equalTo("Dominik")).extract().body().as(Post.class); // Robimy ekstrakt body i musimy podać nazwę klasy "(Post.class);"
        Assert.assertEquals(newPost.getAuthor(), "Dominik"); // pobieramy wartości z klasy "Post" i porównujemy z wartościamy, które wpisaliśmy w expected
        Assert.assertEquals(newPost.getTitle(), "Pierwszy POST");
        Assert.assertEquals(newPost.getId(), id);
    }
    


}
