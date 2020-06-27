package company.name.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Главная страница, всплывающее окошко авторизации
 */
public class LoginPage extends AbstractPage{

    @FindBy(id = "header-lk-button")   //кнопка открытия окна входа
    private WebElement loginButton;

    @FindBy(css = "[for=registrationLk]")
    private WebElement startRegistrationButton;     //кнопка регистрации

    @FindBy(id = "logout")
    private WebElement logout;      //кнопка выхода после успешного входа

    /**
     * Регистрация
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
    private WebElement linkToInformation;       //ссылка на условия передачи информации

    @FindBy(css = "[type=\"application/pdf\"]")
    private WebElement checkPDF;       //проверка что открыт pdf

    @FindBy(css = "[ng-tr=\"WHE1.WHE13\"]")
    private WebElement startRegistrationTesterButton;       //кнопка регистрации тестера

    @FindBy(css = "[ng-tr=\"WHE.WHE26\"]")
    private WebElement RegistrationButton;                  //зарегестрироваться (после введения всех данных)

    @FindBy(id = "Галочка")
    private WebElement check;                  //галочка проверки успешной регистрации

    /**
     * Вход и восстановление пароля
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

    /**
     * Конструктор, считываем из файла конфигурации сайт тестового стенда и получает все веб-элементы
     * @param driver принимает вебдрайвер, с которым мы работаем
     */
    public LoginPage(WebDriver driver) {
        super(driver);
        this.testStand = super.testStand;;
        this.driver = driver;
    }

    private String testStand;
    private WebDriver driver;
    private int timeWait = 5;

    /**
     * Открыть окошко для входа, регистрации и тд.
     */
    public void openPopUp() {
        driver.get(testStand);
        loginButton.click();
    }

    /**
     * Зарегестрировать клиента с указание имени, названия компании и номера телефона
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
     * Зарегестрировать клиента с указание имени, названия компании, должности, веб-сайта и номера телефона
     * @param youName имя клиента
     * @param companyName название компании
     * @param phoneNumber номер телефона
     * @param position должность
     * @param youSite веб-сайт
     */
    public void registrationClient(String youName, String companyName, String phoneNumber, String position, String youSite) {
        registrationPositionField.sendKeys(position);
        registrationSiteField.sendKeys(youSite);
        registrationClient(youName,companyName,phoneNumber);
    }

    /**
     * Регистрация тестера
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
     * Вход на сайт
     */
    public void logIn(String email, String password) {
        openPopUp();
        loginFiled.sendKeys(email);
        passwordField.sendKeys(password);
        submitButton.click();

        assert logout.isDisplayed(): "LogIn exception";
    }

    /**
     * Отправка заявки на восстановление пароля.
     */
    public void recoveryPassword() {
        String email = OneOffEMailPage.getOneOffEmail();
        openPopUp();

        new WebDriverWait(driver, timeWait).withMessage("Click recovery register exception")
                .until((d) -> startPasswordRecoveryButton.isDisplayed());

        startPasswordRecoveryButton.click();
        emailRecoverField.sendKeys(email);
        passwordRecoveryButton.click();

        assert check.isDisplayed(): "Password recovery exception";
    }

    /**
     * Открыть условия передачи информации при регистрации клиента
     */
    public void linkToInformation() {
        openPopUp();

        new WebDriverWait(driver, timeWait).withMessage("Click register exception")
                .until((d) -> startRegistrationButton.isDisplayed());

        startRegistrationButton.click();
        startRegistrationClientButton.click();
        linkToInformation.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new WebDriverWait(driver, timeWait).withMessage("Link to information exception")
                .until((d) -> checkPDF.isEnabled());
    }

}
