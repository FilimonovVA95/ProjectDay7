package company.name;

import company.name.pages.*;
import org.openqa.selenium.WebDriver;

public class StartPageTests {

    private WebDriver driver = DriverManager.getDriver();

    StartPage startPage = new StartPage(driver);
    PopUp popUp = new PopUp(driver);
    TesterPersonalAccountPage testerPersonalAccountPage = new TesterPersonalAccountPage(driver);
    ClientPersonalAccountPage clientPersonalAccountPage = new ClientPersonalAccountPage(driver);
    OneOffEMailPage oneOffEMailPage = new OneOffEMailPage(driver);

}
