package company.name.pages;

import company.name.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    private WebElement passwordRecoveryButton;      //кнопка для восстановления пароля

    @FindBy(id = "logout")
    private WebElement logout;      //кнопка выхода после успешного входа

    // элементы одноразовой почты

    @FindBy(id = "mail")
    private WebElement mailFoild;       //поле с одноразовой почтой

    @FindBy(id = "click-to-refresh")
    private WebElement refreshButton;       //кнопка для обновления почтового ящика

    @FindBy(css = ".inbox-dataList li:nth-child(2) .hidden-xs-sm")
    private WebElement emailFromSite;       //письмо от сайта

    @FindBy(xpath = "//body//p[5]")
    private WebElement stringPassword;      //строчка с паролем



    public StartPage() {PageFactory.initElements(DriverManager.getDriver(), this); }

    WebDriver driver = DriverManager.getDriver();


    public void open() {
        driver.get("http://217.14.197.81:82/");
    }

    public void registrationClient(String youName, String companyName, String phone) {
        openPopUp();
        startRegistrationButton.click();
        startRegistrationClientButton.click();



    }

    public void registrationClient(String youName, String companyName, String phone, String position) {

    }

    public void registrationClient(String youName, String companyName, String phone, String position, String youSite) {

    }

    private void openPopUp() {
        if (loginButton.isDisplayed())
            loginButton.click();
        else
            miniLoginButton.click();

        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(modalContentLocator));
    }

}
