package company.name.pages;

import company.name.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StartPage {

    // элементы стартовой страницы

    @FindBy(id = "header-lk-button")   //кнопка открытия окна входа
    private WebElement loginButton;

    @FindBy(id = "mobileAvatar")        //кнопка открытия окна входа в мобильном режиме
    private WebElement miniLoginButton;

    @FindBy(id = "modal-content")       //локатор на окошко входа
    private By modalContentLocator;

    @FindBy(css = ".switch-login-type:nth-child(2)")
    private WebElement startRegistrationButton;     //кнопка регистрации

    @FindBy(css = ".button-wrapper:nth-child(1) button")
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

    @FindBy(css = ".button-wrapper:nth-child(2) button")
    private WebElement startRegistrationTesterButton;       //кнопка регистрации тестера

    @FindBy(name = "lk-enter-btn ng-scope")
    private WebElement RegistrationButton;                  //зарегестрироваться (после введения всех данных)

    @FindBy(id = "login")
    private WebElement loginFiled;              //поле для ввода логина при входе

    @FindBy(css = "[type=password]")
    private WebElement passwordField;           //поле для ввода пароля

    @FindBy(css = "#form_auth button")
    private WebElement submitButton;        //кнопка для входа

    @FindBy(css = "button.lk-enter-btn:nth-child(5)")
    private WebElement startPasswordRecoveryButton;      //кнопка для начала восстановления пароля

    @FindBy(id = "email_valid")
    private WebElement emailRecoverField;      //поле для ввода почты на восстановление пароля

    @FindBy(css = ".lk-enter-btn:nth-child(5)")
    private WebElement passwordRecoveryButton;      //кнопка для восстановления пароля

    @FindBy(id = "logout")
    private WebElement logout;      //кнопка выхода после успешного входа

    @FindBy(id = "logout")          //локатор на кнопку выхода
    private By logoutLocator;

    // элементы одноразовой почты

    @FindBy(id = "mail")
    private WebElement mailField;       //поле с одноразовой почтой

    @FindBy(id = "click-to-refresh")
    private WebElement refreshButton;       //кнопка для обновления почтового ящика

    @FindBy(css = ".inbox-dataList li:nth-child(2) .hidden-xs-sm")
    private WebElement emailFromSite;       //последнее письмо в ящике

    @FindBy(css = ".inbox-dataList li:nth-child(2) .hidden-xs-sm")
    private By emailFromSiteLocator;        //локатор последнего письма в ящике

    @FindBy(xpath = "//body//p[5]")
    private WebElement stringPassword;      //строчка с паролем

    @FindBy(id = "click-to-delete")
    private WebElement deleteButton;      //кнопка удаления текущего адреса

    /**
     * Конструктор, считываем из файла конфигурации сайты тестового стенда и получаем все веб-элементы.
     */
    public StartPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);

        InputStream inputStream = DriverManager.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException((e));
        }
        testStand = properties.getProperty("test.stand");
    }

    WebDriver driver = DriverManager.getDriver();
    String testStand;
    String oneOffEMailSite = "https://temp-mail.org/ru/";
    int timeWait = 5;

    /**
     * открыть тестовый стенд
     */
    public void open() {
        driver.get(testStand);
    }

    /**
     * зарегестрировать клиента с указание имени, названия компании и номера телефона
     * @param youName имя клиента
     * @param companyName название компании
     * @param phoneNumber номер телефона
     */
    public void registrationClient(String youName, String companyName, String phoneNumber) {
        String email = getNewOneOffEmail();
        openPopUp();
        startRegistrationButton.click();
        startRegistrationClientButton.click();
        registrationEMailField.sendKeys(email);
        registrationNameField.sendKeys(youName);
        registrationCompanyField.sendKeys(companyName);
        registrationPhoneNumberField.sendKeys(phoneNumber);
        RegistrationButton.click();
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
        String email = getOneOffEmail();;
        openPopUp();
        startRegistrationButton.click();
        startRegistrationTesterButton.click();
        registrationEMailField.sendKeys(email);
        RegistrationButton.click();
    }

    /**
     * вход на сайт
     */
    public void logIn() {
        String password = getPassword();
        String email = getOneOffEmail();
        openPopUp();
        loginFiled.sendKeys(email);
        passwordField.sendKeys(password);
        submitButton.click();

        new WebDriverWait(driver, timeWait).withMessage("Log in exception")
                .until(ExpectedConditions.presenceOfElementLocated(logoutLocator));

    }

    /**
     * выйти из личного кабинета
     */
    public void logout () {
        logout.click();
    }


    /**
     * открыть окошко для входа, регистрации и тд.
     */
    private void openPopUp() {
        if (loginButton.isDisplayed())
            loginButton.click();
        else
            miniLoginButton.click();

        new WebDriverWait(driver, timeWait).withMessage("Open popUp exception")
                .until(ExpectedConditions.presenceOfElementLocated(modalContentLocator));
    }

    /**
     * вернуть одноразовый email, перед этим проверив, что раньше эта почта не использовалась
     * @return возвращает строку с новым email
     */
    private String getNewOneOffEmail() {     //возвращает нам одноразовый ящик
        if (emailFromSite.isDisplayed()) {     //проверяем, использовался ли этот ящик, если использовался, то обновить ящик
            deleteButton.click();
            new WebDriverWait(driver, timeWait).withMessage("Get new EMail Exception")
                    .until(ExpectedConditions.textToBePresentInElement(mailField,"@"));
        }
        return getOneOffEmail();
    }

    /**
     * возвращает email
     * @return возвращает строку с email
     */
    private String getOneOffEmail() {     //возвращает нам одноразовый ящик
        driver.get(oneOffEMailSite);
        String oneOffEmail = mailField.getText();
        driver.get(testStand);
        return oneOffEmail;
    }

    /**
     * Ожидает прихода письма в течении минуты. Возвращает строку с паролем от текущего ящика.
     * @return
     */
    private String getPassword() {    //возвращает пароль
        driver.get(oneOffEMailSite);
        refreshButton.click();
        String password;

        new WebDriverWait(driver, 60).withMessage("The letter has not arrived exception")
                .until(ExpectedConditions.presenceOfElementLocated(emailFromSiteLocator));

        emailFromSite.click();

        new WebDriverWait(driver, timeWait).withMessage("password was not found in the email exception")
                .until(ExpectedConditions.textToBePresentInElement(stringPassword,"Пароль"));

        String[] stringWithPassword = stringPassword.getText().split(" ");
        password = stringWithPassword[1];

        driver.get(testStand);

        return password;
    }

}
