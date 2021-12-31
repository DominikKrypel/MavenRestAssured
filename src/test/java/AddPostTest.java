import io.restassured.http.ContentType;
import model.Post;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AddPostTest {

    @Test
    public void addPost() {
        //Definiujemy ciało metody
        String newPost = "{\n" +
                "    \"title\": \"Pierwszy POST\",\n" +
                "    \"author\": \"Dominik\"\n" +
                "}";

        given().log().all().contentType(ContentType.JSON).body(newPost). //ustawiamy w "content.type" wartość JSON"
                 when().post("http://localhost:3000/posts").
                 then().log().all();
        //Tutaj dodatkowo dałem "log().all()" do given, dzięki temu zostanie wypisany mój request na ekranie razem z nagłówkami
    }

    @Test
    public void addPostFromFile() {
        //Definiujemy ciało metody, tym razem podajemy namiar na JSON
        File newPost = new File("src/test/resources/post.json");

        given().log().all().contentType(ContentType.JSON).body(newPost).
                when().post("http://localhost:3000/posts").
                then().log().all();
    }

    @Test
    public void addPostMap() {
        //Definiujemy ciało metody, tym razem podajemy w formie mapy
        Map<String,Object> newPost = new HashMap<>();
        newPost.put("title","tytul z mapy");
        newPost.put("author","Maniek");

        given().log().all().contentType(ContentType.JSON).body(newPost).
                when().post("http://localhost:3000/posts").
                then().log().all();
    }

    @Test
    public void addPostObject() {
        //Definiujemy ciało metody, tym razem podajemy w formie objektu
        Post newPost = new Post();
        newPost.setAuthor("Tytuł obiektowy");
        newPost.setTitle("Autor obiektowy");

        given().log().all().contentType(ContentType.JSON).body(newPost).
                when().post("http://localhost:3000/posts").
                then().log().all();
    }
}
