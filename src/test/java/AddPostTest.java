import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddPostTest {

    @Test
    public void addPost() {
        //Definiujemy ciało metody
        String newPost = "{\n" +
                "    \"title\": \"Pierwszy POST\",\n" +
                "    \"author\": \"Dominik\"\n" +
                "}";

        given().log().all().body(newPost).when().post("http://localhost:3000/posts").then().log().all();
        //Tutaj dodatkowo dałem "log().all()" do given, dzięki temu zostanie wypisany mój request na ekranie razem z nagłówkami
    }


}
