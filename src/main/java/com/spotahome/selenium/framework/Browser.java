package com.spotahome.selenium.framework;

import com.spotahome.selenium.framework.enums.BrowserType;
import com.spotahome.selenium.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Browser {
    private WebDriver driver;

    public Browser(BrowserType browserType) {
        System.setProperty("webdriver.chrome.driver", "C:/webdrivers/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "C:/webdrivers/geckodriver.exe");

        WebDriver driver;
        switch (browserType) {
            case CHROME:
                driver = createChromeDriver();
                break;
            case FIREFOX:
                driver = createFirefoxDriver();
                break;
            default:
                driver = createChromeDriver();
                break;
        }

        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public <T> T getPage(Class<T> classType) {
        try {
            T page = classType.getConstructor().newInstance();
            ((BasePage) page).setBrowser(this);
            PageFactory.initElements(driver, page);

            return page;
        } catch (Exception ex) {
            return null;
        }
    }

    public void close() {
        driver.quit();
    }

    private WebDriver createChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/webdrivers/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        //Setup desired options here

        return new ChromeDriver(options);
    }

    private WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("start-maximized");
        //Setup desired options here

        return new FirefoxDriver(options);
    }
}
