package company.name;

import company.name.pages.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class PopUpTests {

    private WebDriver driver = DriverManager.getDriver();

    PopUp popUp = new PopUp(driver);
    TesterPersonalAccountPage testerPersonalAccountPage = new TesterPersonalAccountPage(driver);
    ClientPersonalAccountPage clientPersonalAccountPage = new ClientPersonalAccountPage(driver);
    OneOffEMailPage oneOffEMailPage = new OneOffEMailPage(driver);

    @Test
    public void registrationAndLogInTester() {
        OneOffEMailPage.updateOffEmail();
        popUp.registrationTester();
        String password = OneOffEMailPage.getPassword();
        String email = OneOffEMailPage.getOneOffEmail();
        popUp.logIn(email, password);
        testerPersonalAccountPage.logout();
    }

    @Test
    public void registrationAndLogInClientToNameCompanyPhone() {
        OneOffEMailPage.updateOffEmail();
        popUp.registrationClient("Name", "companyName", "71234567890");
        String password = OneOffEMailPage.getPassword();
        String email = OneOffEMailPage.getOneOffEmail();
        popUp.logIn(email, password);
        clientPersonalAccountPage.logout();
    }

    @Test
    public void registrationAndLogInClientToNameCompanyPhonePositionSite() {
        OneOffEMailPage.updateOffEmail();
        popUp.registrationClient("Name", "companyName", "71234567890", "position", "site");
        String password = OneOffEMailPage.getPassword();
        String email = OneOffEMailPage.getOneOffEmail();
        popUp.logIn(email, password);
        clientPersonalAccountPage.logout();
    }

    @Test
    public void checkLinkInformationTransferCondition() {
        popUp.linkToInformation();
    }

    @Test
    public void recoveryPasswordTester() {
        registrationAndLogInTester();
        popUp.recoveryPassword();
        String email = OneOffEMailPage.getOneOffEmail();
        String recoveryPassword = OneOffEMailPage.getRecoveryPassword();
        popUp.logIn(email, recoveryPassword);
        testerPersonalAccountPage.logout();
    }


}
