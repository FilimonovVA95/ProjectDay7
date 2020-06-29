package company.name.pages;

import company.name.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Страница одноразовой почты. Реализует только статичные методы.
 */
public class OneOffEMailPage extends AbstractPage{

    @FindBy(css = "[class='btn-rds icon-btn bg-theme click-to-copy copyIconGreenBtn']")
    private static WebElement mailCopy;       //кнопка для копирования

    @FindBy(id = "click-to-refresh")
    private static WebElement refreshButton;       //кнопка для обновления почтового ящика

    @FindBy(css = ".inbox-dataList li:nth-child(2) .hidden-xs-sm")
    private static WebElement emailFromSite;       //последнее письмо в ящике

    @FindBy(css = ".inbox-dataList li:nth-child(3) .hidden-xs-sm")
    private static WebElement emailFromSite2;       //предпоследнее письмо в ящике

    @FindBy(xpath = "//body//p[5]")
    private static WebElement stringPassword;      //строчка с паролем

    @FindBy(xpath = "//body//p[3]")
    private static WebElement stringRecoveryPassword;      //строчка с восстановленным паролем

    @FindBy(id = "click-to-delete")
    private static WebElement deleteButton;      //кнопка удаления текущего адреса

    /**
     * Поля сайта одноразовой почты, время ожидания появления письма, время ожидания проверок наличия элементов,
     * вебдрайвер и ссылка на тестовый стенд
     */
    private static String oneOffEMailSite = "https://temp-mail.org/ru/";
    private static int timeWaitEmail = 60;    //сколько секунд будем ждать появление письма в ящике
    private static int timeWait = 10;   //время ожидания при проверке
    private static WebDriver driver;
    private static String testStand;
    private static OneOffEMailPage oneOffEMailPage;

    static {
        oneOffEMailPage = new OneOffEMailPage(DriverManager.getDriver());
    }

    /**
     * Конструктор, считываем из файла конфигурации сайт тестового стенда и получает все веб-элементы
     * @param driver принимает вебдрайвер, с которым мы работаем
     */
    public OneOffEMailPage(WebDriver driver) {
        super(driver);
        this.testStand = super.testStand;;
        this.driver = driver;
    }

    /**
     * Обновляет одноразовую почту. Гарантирует что при последующих запросах
     * будет выдана почта, до этого ни разу нигде не использованная
     */
    public static void updateOffEmail() {     //возвращает нам одноразовый ящик
        driver.get(oneOffEMailSite);
        deleteButton.click();
    }

    /**
     * Возвращает одноразовый email
     * @return возвращает строку с email
     */
    public static String getOneOffEmail() {     //возвращает нам одноразовый ящик
        String password = "";
        driver.get(oneOffEMailSite);
        new WebDriverWait(driver, timeWait).withMessage("Get new EMail Exception")
                .until((d) -> mailCopy.isEnabled());
        mailCopy.click();

        try {
            password = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.get(testStand);
        return password;
    }

    /**
     * Ожидает прихода письма в течении минуты. Возвращает строку с паролем от текущего ящика
     * @return возвращает строку с паролем
     */
    public static String getPassword() {    //возвращает пароль
        driver.get(oneOffEMailSite);
        refreshButton.click();

        new WebDriverWait(driver, timeWaitEmail).withMessage("The letter has not arrived exception")
                .until((d) -> emailFromSite.isDisplayed());

        emailFromSite.click();

        new WebDriverWait(driver, timeWait).withMessage("Password was not found in the email exception")
                .until((d) -> stringPassword.getText().contains("Пароль"));

        String[] stringWithPassword = stringPassword.getText().split(" ");
        driver.get(testStand);
        return stringWithPassword[1];
    }

    /**
     * Ожидает прихода письма восстановления пароля в течении минуты. Возвращает строку с паролем от текущего ящика
     * @return возвращает строку с восстановленным паролем
     */
    public static String getRecoveryPassword() {
        driver.get(oneOffEMailSite);
        refreshButton.click();

        new WebDriverWait(driver, timeWaitEmail).withMessage("The letter recovery has not arrived exception")
                .until((d) -> emailFromSite2.isDisplayed());

        emailFromSite.click();

        new WebDriverWait(driver, timeWait).withMessage("Password was not found in the email exception")
                .until((d) -> stringRecoveryPassword.getText().contains("Новый"));

        String[] stringWithPassword = stringRecoveryPassword.getText().split(" ");
        driver.get(testStand);
        return stringWithPassword[2];
    }
}
