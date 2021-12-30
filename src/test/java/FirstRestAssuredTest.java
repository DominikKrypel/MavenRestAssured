import static io.restassured.RestAssured.when;

public class FirstRestAssuredTest {

    public static void main(String[] args) {
        System.out.println("#################### Wypisujemy z poziomu konsoli tylko body ################################");
        when().get("http://localhost:3000/posts").then().log().body(); //tutaj wypisujemy na konsoli tylko body

        System.out.println("#################### Wypisujemy z poziomu konsoli wszystko ################################");
        when().get("http://localhost:3000/posts").then().log().all(); // tutaj wypisujemy wszystko, również nagłówki

        System.out.println("################## Sprawdzamy/walidujemy czy odpowiedź ma status 200 ##################################");
        when().get("http://localhost:3000/posts").then().log().ifValidationFails().statusCode(200); /* tutaj w odpowiedzi otrzymujemy : "Process finished with exit code 0",
                                                                                                         oznacza to, że walidacja przeszła pomyślnie                                                                      */
    }
}
