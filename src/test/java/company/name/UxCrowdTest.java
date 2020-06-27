package company.name;

import company.name.pages.OneOffEMailPage;
import company.name.pages.PopUp;
import company.name.pages.StartPage;
import company.name.pages.TesterPersonalAccountPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class UxCrowdTest {

    private WebDriver driver = DriverManager.getDriver();

    StartPage startPage = new StartPage(driver);
    PopUp popUp = new PopUp(driver);
    TesterPersonalAccountPage testerPersonalAccountPage = new TesterPersonalAccountPage(driver);
    OneOffEMailPage oneOffEMailPage = new OneOffEMailPage(driver);

    @Test
    public void registrationAndLogInTester() {

        OneOffEMailPage.updateOffEmail();
        popUp.registrationTester();
        popUp.logIn();
        testerPersonalAccountPage.logout();

    }
}
