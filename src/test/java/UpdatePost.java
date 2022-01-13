import io.restassured.http.ContentType;
import model.Post;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdatePost {

    @Test
    public void replacePost() {
        //Aktualizacja (PUT) post-a nr 1 przy użyciu Mapy. Zmieniamy tytuł i autora.
        //Tutaj jest ważne, żeby w metodzie PUT podać wszystkie dane inaczej stracimy te, których nie podaliśmy
        Map<String,Object> newPost = new HashMap<>();
        newPost.put("title","tytul po aktualizacji");
        newPost.put("author","Stefan");

        given().
                log().all().contentType(ContentType.JSON).body(newPost).
        when().
                put("http://localhost:3000/posts/1").
        then().
                log().all();
    }

    @Test
    public void replacePostObject() {
        //Aktualizacja (PUT) post-a nr 1 przy użyciu Objektu. Zmieniamy tytuł i autora.
        Post newPost = new Post();
        newPost.setAuthor("Tadeusz z Mapy");
        /* newPost.setTitle("Tytuł z Mapy"); - jak nie podamy tytułu i w klasie Post.java nie damy tagu @JsonInclude(JsonInclude.Include.NON_NULL), to będziemy
        mieli wartość null w pozytcji tytuł */

        given().
                log().all().contentType(ContentType.JSON).body(newPost).
        when().
                put("http://localhost:3000/posts/3").
        then().
                log().all();
    }

    @Test
    public void updatePostObject() {
        //Aktualizacja (PATCH) post-a nr 1 przy użyciu Objektu. Zmieniamy tytuł i autora.
        Post newPost = new Post();
        newPost.setAuthor("Tadeusz z Mapy");
        newPost.setTitle("Tytuł z Mapy");

        given().
                log().all().contentType(ContentType.JSON).body(newPost).
        when().
                patch("http://localhost:3000/posts/3").
        then().
                log().all();
    }
}
