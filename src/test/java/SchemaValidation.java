import org.testng.annotations.Test;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidation {
    @Test
    public void getPost() {

        when()
                .get("http://localhost:3000/posts/{postId}", 4).
        then()
                .assertThat().body(matchesJsonSchemaInClasspath("postSchema.json")); /* ta metoda "matchesJsonSchemaClasspath" nie jest dostarczona w restAssured, więc musimyh
                                                                                    pobrać kolejną dependencje : "json-schema-validator" */

    }
}
