package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;


public class LoginPage extends HelperClass{
    protected static WebDriver driver;

    static By username = By.id("user-name");
    static By password = By.id("password");
    static By login_btn = By.id("login-button");
    static By error = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");

    public static void login() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(username).sendKeys("standard_user");
        driver.findElement(password).sendKeys("secret_sauce");
        driver.findElement(login_btn).click();
    }

    @Before
    public void setUp() throws MalformedURLException{
        driver = initializeBrowser("chrome");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void loginTest01() {
        driver.findElement(username).sendKeys("standard_user");
        driver.findElement(password).sendKeys("secret_sauce");
        driver.findElement(login_btn).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void loginTest02() {
        driver.findElement(username).sendKeys("wrong_username");
        driver.findElement(password).sendKeys("secret_sauce");
        driver.findElement(login_btn).click();
        Assert.assertTrue(driver.getPageSource().contains("Username and password do not match any user in this service"));
    }

    @Test
    public void loginTest03() {
        driver.findElement(username).sendKeys("standard_user");
        driver.findElement(password).sendKeys("wrong_password");
        driver.findElement(login_btn).click();
        Assert.assertTrue(driver.getPageSource().contains("Username and password do not match any user in this service"));
    }

    @Test
    public void loginTest04() {
        driver.findElement(username).sendKeys("");
        driver.findElement(password).sendKeys("wrong_password");
        driver.findElement(login_btn).click();
        Assert.assertTrue(driver.getPageSource().contains("Username is required"));
    }

    @Test
    public void loginTest05() {
        driver.findElement(username).sendKeys("standard_user");
        driver.findElement(password).sendKeys("");
        driver.findElement(login_btn).click();
        Assert.assertTrue(driver.getPageSource().contains("Password is required"));
    }

    @Test
    public void loginTest06() {
        driver.findElement(username).sendKeys("");
        driver.findElement(password).sendKeys("");
        driver.findElement(login_btn).click();
        Assert.assertTrue(driver.getPageSource().contains("Username is required"));
    }


}
