import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetSpecificFieldValue {

    @Test
    public void checkSpecificField() {
        // inne podejście do zapisu.
        Response response = RestAssured.get("http://localhost:3000/posts/4");
        String author = response.path("author");

        Assert.assertEquals(author, "Dominik");

        //Druga opcja, krótsza
        String author2 = RestAssured.get("http://localhost:3000/posts/4").path("author");

        Assert.assertEquals(author2, "Dominik");
    }

    @Test
    public void checkSpecificFieldJsonPath() {
        Response response = RestAssured.get("http://localhost:3000/posts/4");
        JsonPath jsonPath = new JsonPath(response.asString()); // musimy podać response jako String
        String author = jsonPath.get("author");

        Assert.assertEquals(author, "Dominik");

        //Druga opcja, krótsza
        String stringResponse = RestAssured.get("http://localhost:3000/posts/4").asString();
        String author2 = JsonPath.from(stringResponse).get("author");

        Assert.assertEquals(author2, "Dominik");
    }
}
