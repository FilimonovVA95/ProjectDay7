package company.name.pages;

import company.name.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractPage {

    public static String testStand;

    public AbstractPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

        InputStream inputStream = DriverManager.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException((e));
        }
        testStand = properties.getProperty("test.stand");
    }

}
