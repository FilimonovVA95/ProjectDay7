package company.name.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends AbstractPage {

    @FindBy(id = "header-lk-button")   //кнопка открытия окна входа
    private WebElement loginButton;

    

    /**
     * Конструктор, считываем из файла конфигурации сайт тестового стенда и получаем все веб-элементы.
     */
    public StartPage(WebDriver driver) {
        super(driver);
        this.testStand = super.testStand;
        this.driver = driver;
    }

    private String testStand;
    private WebDriver driver;


    /**
     * открыть стартовую страницу
     */
    public void openStartPage() {
        driver.get(testStand);
    }


}
