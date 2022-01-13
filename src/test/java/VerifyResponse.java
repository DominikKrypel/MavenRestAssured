import io.restassured.http.ContentType;
import model.Post;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class VerifyResponse {


    @Test
    public void getPost(){

        String expected = "{\n" +
                "  \"title\": \"Pierwszy POST\",\n" +
                "  \"author\": \"Dominik\",\n" +
                "  \"id\": 4\n" +
                "}";

        given().
                log().all().
        when().
                get("http://localhost:3000/posts/{postId}", 4).
        then().
                log().all().body(equalTo(expected)); // Sprawdzenie całości ciała
                                                                     // porównujemy czy w odpowiedzi w Body znajduje się wartość przez nas oczekwiana.
                                                                     // Wartość oczekiwana jest zachowana w zmiennej "expected"
    }

    @Test
    public void getPostContains(){

        given().
                log().all().
        when().
                get("http://localhost:3000/posts/{postId}", 4).
        then().
                log().all().body(Matchers.containsString("Dominik")); // Tutaj szukamy czy odpowiedź w body zawiera słowo "Dominik"
    }

    @Test
    public void checkSpecificField(){

        when().
                get("http://localhost:3000/posts/{postId}", 4).
        then().
                assertThat().body("title", equalTo("Pierwszy POST")).// tutaj sprawdzamy wartość konkretnego pola, w naszym przypadku title
        and().
                assertThat().body("author", equalTo("Dominik")); // jeżeli skorzystamny z "and()" po sekcji "then()" to robimy kolejną weryfikację
    }

    @Test
    public void getPostObject(){
        //|Weryfikacja ciała odpowiedzi - obiekt klasy Post
        Integer id = 4;

       Post newPost = given().log().all().

       when().
               get("http://localhost:3000/posts/{postId}", id).
       then().
               log().all().body("title", equalTo("Pierwszy POST")).
       and().
               body("author", equalTo("Dominik")).extract().body().as(Post.class); // Robimy ekstrakt body i musimy podać nazwę klasy "(Post.class);"

        Assert.assertEquals(newPost.getAuthor(), "Dominik"); // pobieramy wartości z klasy "Post" i porównujemy z wartościamy, które wpisaliśmy w expected
        Assert.assertEquals(newPost.getTitle(), "Pierwszy POST");
        Assert.assertEquals(newPost.getId(), id);
    }

    @Test
    public void addPostObjectAndCompareTwoObjects() {
        //Definiujemy ciało metody, tym razem podajemy w formie objektu i porównujemy dwa obiekty
        Post newPost = new Post();
        newPost.setAuthor("Tytuł obiektowy Drugi");
        newPost.setTitle("Autor obiektowy Drugi");

        Post createdPost =
        given()
                .log().all().contentType(ContentType.JSON).body(newPost).
        when().
                post("http://localhost:3000/posts").
        then().
                log().all().extract().body().as(Post.class);

        Assert.assertEquals(newPost,createdPost); // dzięki dwóm metodom w clasie "Post" - "equals() and hashCode()" -  możemy porównać dwa obiekty.
                                                  // czytamy ten zapis tak: "gdy autor i tytuł dwóch obiektów będzie sobie równy, to wtedy dwa obiekty będą uznawane za równe "
    }
}
