import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class JiraGetIssue {

    @Test
    public void geyIssue() {
        when()
                .get("https://domdom.atlassian.net/rest/issue/TES-1"). /*Host to jest: https://domdom.atlassian.net : */
        then()
                .log().all();
    }

    /*test niedokończony z uwagi na konieczność podania hasła*/
}
