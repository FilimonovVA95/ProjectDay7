package company.name;

import company.name.pages.StartPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UxCrowdTest {
    private int timeWait = 5;


    @Test
    public void firstTest() {

        WebDriver driver = DriverManager.getDriver();

        // Получаем одноразовый ящик
        driver.get("https://temp-mail.org/ru/");
        String oneOffMailName = driver.findElement(By.id("mail")).getText();

        StartPage startPage = new StartPage();

        startPage.open();




    }
}
