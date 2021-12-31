import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FilterPosts {

    @Test
    public void filterPosts() {
        given().log().all().queryParam("author", "Dominik").
                when().get("http://localhost:3000/posts").
                then().log().all(); // jak chcemy logować odpowiedź na post-a, to w sekcji then wstawiamy wartość ".log().all()"
        // stosujemy queryParam, chcemy znaleźć posty, których autorem jest Dominik
    }

    @Test
    public void filterPostsBuId() {
        given().log().all().queryParam("id", "5","6"). // szukamy posta z id 5 i 6
                when().get("http://localhost:3000/posts").
                then().log().all();
    }
}
