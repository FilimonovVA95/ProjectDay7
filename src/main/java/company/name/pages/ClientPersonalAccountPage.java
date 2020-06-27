package company.name.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClientPersonalAccountPage extends AbstractPage {

    @FindBy(id = "logout")
    private WebElement logout;      //кнопка выхода

    public ClientPersonalAccountPage (WebDriver driver) {
        super(driver);
        this.testStand = super.testStand;
        this.driver = driver;
    }

    private String testStand;
    private WebDriver driver;

    /**
     * выйти из личного кабинета
     */
    public void logout () {
        logout.click();
    }

}
