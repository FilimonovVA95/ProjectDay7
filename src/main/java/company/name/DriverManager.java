package company.name;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class DriverManager {
    private static WebDriver driver;

    private DriverManager(){};

    public static WebDriver getDriver() {
        if (driver != null) return driver;

        InputStream inputStream = DriverManager.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException((e));
        }
        String hubUrlString = properties.getProperty("selenium.hub.url");
        URL hubUrl;
        try {hubUrl= new URL(hubUrlString); }
        catch (MalformedURLException e) {throw  new RuntimeException(e); }

        String browserName = properties.getProperty("selenium.browser.name");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        driver = new RemoteWebDriver(hubUrl, capabilities);

        return driver;
    }
}