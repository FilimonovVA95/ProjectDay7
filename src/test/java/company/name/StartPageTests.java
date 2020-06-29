package company.name;

import company.name.pages.ClientPersonalAccountPage;
import company.name.pages.LoginPage;
import company.name.pages.TesterPersonalAccountPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class StartPageTests {

    private static WebDriver driver = DriverManager.getDriver();

    /**
     * Набор используемых страниц
     */
    private static LoginPage loginPage;
    private static TesterPersonalAccountPage testerPersonalAccountPage;
    private static ClientPersonalAccountPage clientPersonalAccountPage;

    @BeforeTest
    public static void setup() {
        loginPage = new LoginPage(driver);
        testerPersonalAccountPage = new TesterPersonalAccountPage(driver);
        clientPersonalAccountPage = new ClientPersonalAccountPage(driver);
    }



    @AfterTest
    public static void quit() {
        DriverManager.quit();
    }

}
