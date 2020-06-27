package company.name.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Страница личного кабинета тестировщика
 */
public class TesterPersonalAccountPage extends AbstractPage {

    @FindBy(id = "logout")
    private WebElement logout;      //кнопка выхода


    /**
     * Конструктор, считываем из файла конфигурации сайт тестового стенда и получает все веб-элементы
     * @param driver принимает вебдрайвер, с которым мы работаем
     */
    public TesterPersonalAccountPage(WebDriver driver) {
        super(driver);
        this.testStand = super.testStand;
        this.driver = driver;
    }

    private String testStand;
    private WebDriver driver;

    /**
     * Выйти из личного кабинета
     */
    public void logout () {
        logout.click();
    }

}
