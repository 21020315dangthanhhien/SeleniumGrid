package org.example;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.MalformedURLException;
import java.util.List;

public class HomePage extends HelperClass {
    public static WebDriver driver;


    static By addProductButton = By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]");
    static By productCount = By.xpath("//*[@id=\"shopping_cart_container\"]/a/span");

    static By productList = By.xpath("//*[@id=\"inventory_container\"]/div");

    static By sortList = By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select");
    static By productName = By.className("inventory_item_name");
    static By productPrice = By.className("inventory_item_price");
    static By product = By.className("inventory_item");

    static By cart = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
    static By checkoutButton = By.xpath("//*[@id=\"checkout\"]");
    static By firstName = By.xpath("//*[@id=\"first-name\"]");
    static By lastName  = By.xpath("//*[@id=\"last-name\"]");
    static By zipCode = By.xpath("//*[@id=\"postal-code\"]");
    static By continueButton = By.xpath("//*[@id=\"continue\"]");
    static By finishButton = By.xpath("//*[@id=\"finish\"]");




    @Before
    public void setUp() throws MalformedURLException {
        driver = initializeBrowser("firefox");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(LoginPage.username).sendKeys("standard_user");
        driver.findElement(LoginPage.password).sendKeys("secret_sauce");
        driver.findElement(LoginPage.login_btn).click();
    }

    @After
    public  void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void addProductTest() {
        driver.findElement(addProductButton).click();
        String number = driver.findElement(productCount).getText();
        Assert.assertEquals("1", number);
    }

    @Test
    public void sortAzTest() {
        Select sortOptions = new Select(driver.findElement(sortList));
        sortOptions.selectByValue("az");
        List<WebElement> products = driver.findElement(productList).findElements(product);
        Assert.assertTrue(products.get(0).findElement(productName).getText().compareTo(products.get(1).findElement(productName).getText()) < 0);
    }

    @Test
    public void sortZaTest() {
        Select sortOptions = new Select(driver.findElement(sortList));
        sortOptions.selectByValue("za");
        List< WebElement> products = driver.findElement(productList).findElements(product);
        Assert.assertTrue(products.get(0).findElement(productName).getText().compareTo(products.get(1).findElement(productName).getText()) > 0);
    }

    @Test
    public void sortLohiTest() {
        Select sortOptions = new Select(driver.findElement(sortList));
        sortOptions.selectByValue("lohi");
        List< WebElement> products = driver.findElement(productList).findElements(product);

        double firstPrice = Double.parseDouble(products.get(0).findElement(productPrice).getText().substring(1));
        double secondPrice = Double.parseDouble(products.get(1).findElement(productPrice).getText().substring(1));
        Assert.assertTrue(firstPrice <= secondPrice);
    }

    @Test
    public void sortHiloTest() {
        Select sortOptions = new Select(driver.findElement(sortList));
        sortOptions.selectByValue("hilo");
        List< WebElement> products = driver.findElement(productList).findElements(product);

        double firstPrice = Double.parseDouble(products.get(0).findElement(productPrice).getText().substring(1));
        double secondPrice = Double.parseDouble(products.get(1).findElement(productPrice).getText().substring(1));
        Assert.assertTrue(firstPrice >= secondPrice);
    }


    @Test
    public void checkOutTest() {
        driver.findElement(addProductButton).click();
        driver.findElement(cart).click();
        driver.findElement(checkoutButton).click();
        driver.findElement(firstName).sendKeys("test_first_name");
        driver.findElement(lastName).sendKeys("test_last_name");
        driver.findElement(zipCode).sendKeys("100000");
        driver.findElement(continueButton).click();
        driver.findElement(finishButton).click();

    }
}
