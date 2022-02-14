import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class XmlPathTests {

    @Test
    public void testXmlPath() {
        String xml = "<filmy>\n" +
                "\t<film gatunek=\"komedia\">\n" +
                "\t\t<id>1</id>\n" +
                "\t\t<nazwa>Forrest Gump</nazwa>\n" +
                "\t\t<ocena>8</ocena>\n" +
                "\t</film>\n" +
                "\t<film gatunek=\"komedia\">\n" +
                "\t\t<id>2</id>\n" +
                "\t\t<nazwa>American Pie</nazwa>\n" +
                "\t\t<ocena>7</ocena>\n" +
                "\t</film>\n" +
                "\t<film gatunek=\"dramat\">\n" +
                "\t\t<id>3</id>\n" +
                "\t\t<nazwa>Zielona mila</nazwa>\n" +
                "\t\t<ocena>9</ocena>\n" +
                "\t</film>\n" +
                "</filmy>";

        String nazwa = XmlPath.from(xml).get("filmy.film.nazwa[0]");  /*wyciągamy nazwę pierwszego filmu*/
        List<String> nazwy = XmlPath.from(xml).getList("filmy.film.nazwa");
        String gatunek = XmlPath.from(xml).get("filmy.film[0].@gatunek");  /*odwołujemy się do pierwszego filmu "[0]",
                                                                           a następnie korzystamy ze znaczka atrybut "@"*/

        List<Node> filmy = XmlPath.from(xml).get("filmy.film.findAll {element -> return element}"); /*zwracamy wszystkie nody,
                                                                                                    wszystkie gałęzie, które są oznaczone jako film*/

        String nazwaFilmu = filmy.get(2).get("nazwa").toString(); /*wyciągamy z listy nazwę filmu*/

        List<Node> komedie = XmlPath.from(xml).get("filmy.film.findAll {film -> film.@gatunek =='komedia'}"); /*dla każdego elementu sprawdzamy gatunek, i wartość
                                                                                                               i wartrość tego atrybutu ma być równa komedia*/

        Node filmOcenaDziewiec = XmlPath.from(xml).get("filmy.film.find {film -> def ocena = film.ocena; ocena =='9'}"); /*korzystamy z metody find czyli szukamy pojendynczego elementu.
                                                                                                       Możemy zdefiniować zmienną ocena i ta ocena ma być równa 9*/

        List<Node> wiekszeNizSiedem = XmlPath.from(xml).get("filmy.film.findAll {film -> def ocena = film.ocena.toFloat(); ocena > 7}");/*znajdziemy wszystkie filmy,
                                                                                                                         które będą miały ocenę większą od 7*/

        List<String> nazwyCollect = XmlPath.from(xml).get("filmy.film.collect {it.nazwa}"); /*mamy już nazwy filmów, tylko tym razem skorzystamy z metody collect*/


    }
}
