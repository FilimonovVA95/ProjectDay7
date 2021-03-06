package company.name;

import company.name.pages.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

import java.io.IOException;

/**
 * Набор тестов для проверки работы авторизации и регистрации
 */
public class LoginPageUserTests {

    private WebDriver driver = DriverManager.getDriver();

    /**
     * Набор используемых страниц
     */
    private LoginPage loginPage = new LoginPage(driver);
    private TesterPersonalAccountPage testerPersonalAccountPage = new TesterPersonalAccountPage(driver);
    private ClientPersonalAccountPage clientPersonalAccountPage = new ClientPersonalAccountPage(driver);


    @Test
    public void testEmail() throws IOException {
        String s = OneOffMailPageThroughRequests.getNewEmail();
    }

    @Test
    public void registrationAndLogInTester() {
        OneOffEMailPage.updateOffEmail();
        loginPage.registrationTester();
        String password = OneOffEMailPage.getPassword();
        String email = OneOffEMailPage.getOneOffEmail();
        loginPage.logIn(email, password);
        testerPersonalAccountPage.logout();
    }

    @Test
    public void registrationAndLogInClientToNameCompanyPhone() {
        OneOffEMailPage.updateOffEmail();
        loginPage.registrationClient("Name", "companyName", "71234567890");
        String password = OneOffEMailPage.getPassword();
        String email = OneOffEMailPage.getOneOffEmail();
        loginPage.logIn(email, password);
        clientPersonalAccountPage.logout();
    }

    @Test
    public void registrationAndLogInClientToNameCompanyPhonePositionSite() {
        OneOffEMailPage.updateOffEmail();
        loginPage.registrationClient("Name", "companyName", "71234567890", "position", "site");
        String password = OneOffEMailPage.getPassword();
        String email = OneOffEMailPage.getOneOffEmail();
        loginPage.logIn(email, password);
        clientPersonalAccountPage.logout();
    }

    @Test
    public void checkLinkInformationTransferCondition() {
        loginPage.linkToInformation();
    }

    @Test
    public void recoveryPasswordTester() {
        registrationAndLogInTester();
        loginPage.recoveryPassword();
        String email = OneOffEMailPage.getOneOffEmail();
        String recoveryPassword = OneOffEMailPage.getRecoveryPassword();
        loginPage.logIn(email, recoveryPassword);
        testerPersonalAccountPage.logout();
    }


    @AfterTest
    public static void quit() {
        DriverManager.quit();
    }

}
