package company.name.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PopUp extends AbstractPage{

    @FindBy(id = "header-lk-button")   //кнопка открытия окна входа
    private WebElement loginButton;

    @FindBy(css = "[for=registrationLk]")
    private WebElement startRegistrationButton;     //кнопка регистрации

    @FindBy(id = "logout")
    private WebElement logout;      //кнопка выхода после успешного входа

    /**
     * регистрация
     */

    @FindBy(css = "[ng-tr=\"WHE1.WHE12\"]")
    private WebElement startRegistrationClientButton;       //кнопка регистрации клиента

    @FindBy(id = "name")
    private WebElement registrationNameField;       //поле для ввода имени

    @FindBy(id = "position")
    private WebElement registrationPositionField;       //поле для ввода должности

    @FindBy(id = "company")
    private WebElement registrationCompanyField;       //поле для ввода компания

    @FindBy(id = "emails")
    private WebElement registrationEMailField;       //поле для ввода емайла

    @FindBy(id = "phoneNumber")
    private WebElement registrationPhoneNumberField;       //поле для ввода номера телефона

    @FindBy(id = "site")
    private WebElement registrationSiteField;       //поле для ввода ссылки на сайт

    @FindBy(css = "[href=\"assets/files/tester_conditions.pdf\"]")
    private WebElement linfToInformation;       //ссылка на условия передачи информации

    @FindBy(css = "[ng-tr=\"WHE1.WHE13\"]")
    private WebElement startRegistrationTesterButton;       //кнопка регистрации тестера

    @FindBy(css = "[ng-tr=\"WHE.WHE26\"]")
    private WebElement RegistrationButton;                  //зарегестрироваться (после введения всех данных)

    @FindBy(id = "Галочка")
    private WebElement check;                  //галочка проверки успешной регистрации

    /**
     * вход и восстановление пароля
     */

    @FindBy(id = "login")
    private WebElement loginFiled;         //поле для ввода логина при входе

    @FindBy(css = "[type=password]")
    private WebElement passwordField;           //поле для ввода пароля

    @FindBy(css = "#form_auth button")
    private WebElement submitButton;        //кнопка для входа

    @FindBy(css = "[for=forgetLk]")
    private WebElement startPasswordRecoveryButton;      //кнопка для начала восстановления пароля

    @FindBy(id = "email_valid")
    private WebElement emailRecoverField;      //поле для ввода почты на восстановление пароля

    @FindBy(css = "[ng-click=\"passwordReset()\"]")
    private WebElement passwordRecoveryButton;      //кнопка для восстановления пароля


    public PopUp(WebDriver driver) {
        super(driver);
        this.testStand = super.testStand;;
        this.driver = driver;
    }

    private String testStand;
    private WebDriver driver;
    private int timeWait = 5;

    /**
     * открыть окошко для входа, регистрации и тд.
     */
    public void openPopUp() {
        driver.get(testStand);
        loginButton.click();
    }

    /**
     * зарегестрировать клиента с указание имени, названия компании и номера телефона
     * @param youName имя клиента
     * @param companyName название компании
     * @param phoneNumber номер телефона
     */
    public void registrationClient(String youName, String companyName, String phoneNumber) {
        String email = OneOffEMailPage.getOneOffEmail();
        openPopUp();

        new WebDriverWait(driver, timeWait).withMessage("Click register exception")
                .until((d) -> startRegistrationButton.isDisplayed());

        startRegistrationButton.click();
        startRegistrationClientButton.click();
        registrationEMailField.sendKeys(email);
        registrationNameField.sendKeys(youName);
        registrationCompanyField.sendKeys(companyName);
        registrationPhoneNumberField.sendKeys(phoneNumber);
        RegistrationButton.click();

        assert check.isDisplayed(): "Client registration exception";
    }

    /**
     * зарегестрировать клиента с указание имени, названия компании, должности и номера телефона
     * @param youName имя клиента
     * @param companyName название компании
     * @param phoneNumber номер телефона
     * @param position должность
     */
    public void registrationClient(String youName, String companyName, String phoneNumber, String position) {
        registrationPositionField.sendKeys(position);
        registrationClient(youName,companyName,phoneNumber);
    }

    /**
     * зарегестрировать клиента с указание имени, названия компании, должности, веб-сайта и номера телефона
     * @param youName имя клиента
     * @param companyName название компании
     * @param phoneNumber номер телефона
     * @param position должность
     * @param youSite веб-сайт
     */
    public void registrationClient(String youName, String companyName, String phoneNumber, String position, String youSite) {
        registrationSiteField.sendKeys(youSite);
        registrationClient(youName,companyName,phoneNumber,position);
    }

    /**
     * регистрация тестера
     */
    public void registrationTester() {
        String email = OneOffEMailPage.getOneOffEmail();
        openPopUp();

        new WebDriverWait(driver, timeWait).withMessage("Click register exception")
                .until((d) -> startRegistrationButton.isDisplayed());

        startRegistrationButton.click();
        startRegistrationTesterButton.click();
        registrationEMailField.sendKeys(email);
        RegistrationButton.click();

        assert check.isDisplayed(): "Tester registration exception";
    }

    /**
     * вход на сайт
     */
    public void logIn() {
        String password = OneOffEMailPage.getPassword();
        String email = OneOffEMailPage.getOneOffEmail();
        openPopUp();
        loginFiled.sendKeys(email);
        passwordField.sendKeys(password);
        submitButton.click();

        assert logout.isDisplayed(): "LogIn exception";
    }

    public void recoveryPassword() {
        String email = OneOffEMailPage.getOneOffEmail();
        openPopUp();
        startPasswordRecoveryButton.click();
        emailRecoverField.sendKeys(email);
        passwordRecoveryButton.click();

        assert check.isDisplayed(): "Password recovery exception";
    }

    public void linkToInfomation() {
        openPopUp();
        startRegistrationButton.click();
        startRegistrationClientButton.click();
        linfToInformation.click();

        assert driver.getCurrentUrl().endsWith("pdf"): "Link to information exception";
    }


}
