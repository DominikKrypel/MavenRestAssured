import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestSpecification {

    io.restassured.specification.RequestSpecification spec;

    @BeforeClass
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:3000")
                .setBasePath("posts")
                .build();
    }

    @Test
    public void getPosts() {
        given()
                .spec(spec).
        when()
                .get().
        then()
                .log().body();
    }

    @Test
    public void getPost() {
        given()
                .spec(spec).
        when()
                .get("/4"). /* tutaj metoda GET stworzy cały endpoint, czyli kombinaja trzech elementów*/
        then()
                .log().body();
    }
}
