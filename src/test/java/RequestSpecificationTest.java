import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestSpecificationTest {

    io.restassured.specification.RequestSpecification spec;
    ResponseSpecification resSpec;

    @BeforeClass
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:3000")
                .setBasePath("posts")
                .build();
        resSpec = new ResponseSpecBuilder() /*tworzymy obiekt klasy response specification*/
                .expectStatusCode(200)
                .build();
        ResponseLoggingFilter respoFilter = new ResponseLoggingFilter();
        RequestLoggingFilter reqpoFilter = new RequestLoggingFilter();
        RestAssured.filters(reqpoFilter,respoFilter); /*tutaj ustawiłem globalnie filtr, w tym przypadku ustawiamy logowanie dla responsów i requestów */
    }

    @Test
    public void getPosts() {
        given()
                .spec(spec).
        when()
                .get().
        then()
                .spec(resSpec);
    }

    @Test
    public void getPost() {
        given()
                .spec(spec).
        when()
                .get("/4"). /* tutaj metoda GET stworzy cały endpoint, czyli kombinaja trzech elementów*/
        then()
                .spec(resSpec);
    }
}
