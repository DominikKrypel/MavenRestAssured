import io.restassured.path.xml.XmlPath;
import org.testng.annotations.Test;

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

        String nazwa = XmlPath.from(xml).get("filmy.film.nazwa[0]");  // wyciągamy nazwę pierwszego filmu
        List<String> nazwy = XmlPath.from(xml).getList("filmy.film.nazwa");
        String gatunek = XmlPath.from(xml).get("filmy.film[0].@gatunek"); // odwołujemy się do pierwszego filmu "[0]", a następnie korzystamy ze znaczka atrybut "@"
    }
}
