import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class JsonPathTests {
//tutaj korzystamy z pliku db.json zawierającego listę zwyciężców
    @Test
    public void checkSpecificFieldJsonPath() {
        Response response = RestAssured.get("http://localhost:3000/posts/1");
        System.out.println(response.asString());

        String secretString = response.path("secretString");
        List<Integer> winingNumbers = response.path("wining-numbers");
        String firstWinnerName = response.path("winners.name[0]"); // dostajemy się do winners, następnie dostajemy się do pierwszego imienia
        String firstWinnerName1 = response.path("winners[0].name"); // pobieramy pierwszego zwyciężce i pobieramy imię. To jest druga możliwość.
        String secondWinnerName = response.path("winners[1].name"); // pobieramy imię drugiego zwyciężcy
        String lastWinnerName = response.path("winners[-1].name"); // pobieramy ostatnie imie zwyciężcy
        List<String> winnerNames = response.path("winners.name"); // pobieramy imiona wszystkich zwyciężców
        Map<String,?> winner = response.path("winners[0]"); // Mapa, która będzie zawierała klucz/wartość, która jest nieznana, dlatego zastępujemy znakiem zapytania
        List<Map<String,?>> winners = response.path("winners"); /* pobranie dokładnych informacji o wszystkich zwyciężcach, wtedy korzystamy z listy map
                                                                      w tej lini można uruchomić tryb Debug i sprawdzić jakie wartości zostały przypisane*/

        Map<String,?> winnerInfo = response.path("winners.find {it.name=='Melania'}"); /* |Przejdźmy przez wszystkich zwyciężców i dla każdego zwytciężcy sprawdzili wartość pola imie, i szukamy
                                                                               i szukamy tych zwyciężców, których imie jest John*/

        Integer winnerId = response.path("winners.find {it.name=='John'}.winnerId"); /* szukamy wśród winnerów zwyciężcy, który ma name równy John i następnie mówimy o tym, że od tego
                                                                               zwyciężcy chcemy pobrać wartość pola winnerId*/

        Integer maxNumber = response.path("wining-numbers.max()");
        Integer minNumber = response.path("wining-numbers.min()");

        Map<String,?> winnerMaxId = response.path("winners.max {it.winnerId}"); /* znajdujemy zwyciężcę, którego Id jest maksymalne*/

        Integer moneySum = response.path("winners.collect{it.money}.sum()");

        List<Integer> winnersId = response.path("winners.findAll {it.name=='Melania'}.winnerId"); /*Tutaj możemy wyciągać listę elementów spełniająch nasze założenia*/
    }
}
