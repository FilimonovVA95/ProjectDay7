package company.name;

import company.name.pages.StartPage;
import org.junit.jupiter.api.Test;

public class UxCrowdTest {

    @Test
    public void firstTest() {

    //    WebDriver driver = DriverManager.getDriver();

        StartPage startPage = new StartPage();

        startPage.open();
        startPage.registrationTester();
        startPage.logIn();
        startPage.logout();

    }
}
