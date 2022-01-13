import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class FilterPosts {

    @Test
    public void filterPosts() {
        given().
                log().all().queryParam("author", "Dominik").  // stosujemy queryParam, chcemy znaleźć posty, których autorem jest Dominik
        when().
                get("http://localhost:3000/posts").
        then().
                log().all(); // jak chcemy logować odpowiedź na post-a, to w sekcji then wstawiamy wartość ".log().all()"
    }

    @Test
    public void filterPostsBuId() {
        given().
                log().all().queryParam("id", "5","6"). // szukamy posta z id 5 i 6
        when().
                get("http://localhost:3000/posts").
        then().
                log().all().statusCode(Matchers.equalTo(200)); // sprawdzenie czy w odpowiedzi jest status 200.
        // Zamiast "equal to"  można dać "greaterThan" i wtedy będzie sprawdzał czy status jest większy od wskazanego
    }

    @Test
    public void filterPostsAuthorTitle() {
        Map<String,Object> params = new HashMap<>();
        params.put("author","Tomek");
        params.put("title","Pierwszy POST z plik");

        given().
                log().all().queryParams(params). // tutaj szukamy parametrów więc wskazujemy liczbę mnogą : .queryParams
        when().
                get("http://localhost:3000/posts").
        then().
                log().all();
        //w taki sposób możemy za pomocą Rest Assured korzystać z query params
    }
}
