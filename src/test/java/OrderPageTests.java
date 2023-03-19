import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

@RunWith(Parameterized.class)
public class OrderPageTests {
    private WebDriver driver;

    private final String nameValue;
    private final String surnameValue;
    private final String addressValue;
    private final String stationValue;
    private final String phoneValue;
    private final String date;
    private final String termValue;
    private final String colourValue;
    private final String comment;
    private final String browser;

    public OrderPageTests(String browser, String nameValue, String surnameValue, String addressValue, String stationValue, String phoneValue, String date, String termValue, String colourValue, String comment){
        this.browser = browser;
        this.nameValue = nameValue;
        this.surnameValue = surnameValue;
        this.addressValue = addressValue;
        this.stationValue = stationValue;
        this.phoneValue = phoneValue;
        this.date = date;
        this.termValue = termValue;
        this.colourValue = colourValue;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Chrome", "Иван", "Иванов", "г.Москва, ул. Ленина, д.1", "Черкизовская", "+79212346789", "28", "сутки", "black", "комментарий"},
                {"Firefox", "Иван", "Иванов", "г.Москва, ул. Ленина, д.1", "Черкизовская", "+79212346789", "28", "сутки", "black", "комментарий"},
                {"Chrome", "Петр", "Петрович", "г.Москва, ул. Карла Маркса, д.6", "Римская", "+79114328765", "27", "двое суток", "grey", "тест"},
                {"Firefox", "Петр", "Петрович", "г.Москва, ул. Карла Маркса, д.6", "Римская", "+79114328765", "27", "двое суток", "grey", "тест"},
        };
    }

    @Test
    public void checkMakeOrderFromTopButtonReceiveSuccessMessage(){
        if (Objects.equals(browser, "Chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        HomePage objHomePage = new HomePage(driver);
        objHomePage.openHomePage();
        objHomePage.agreeToCookie();
        objHomePage.pressTopOrderButton();
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.fillInFirstPartOrder(nameValue,surnameValue, addressValue, stationValue, phoneValue);
        objOrderPage.pressNextButton();
        objOrderPage.fillInSecondPartOrder(date, termValue, colourValue, comment);
        objOrderPage.pressFinalizeOrderButton();
        objOrderPage.pressYesToOrderButton();
        Assert.assertTrue(objOrderPage.getOrderHeader().contains("Заказ оформлен"));

    }
    @Test
    public void checkMakeOrderFromDownButtonReceiveSuccessMessage(){
        if (Objects.equals(browser, "Chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        HomePage objHomePage = new HomePage(driver);
        objHomePage.openHomePage();
        objHomePage.agreeToCookie();
        objHomePage.pressDownOrderButton();
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.fillInFirstPartOrder(nameValue,surnameValue, addressValue, stationValue, phoneValue);
        objOrderPage.pressNextButton();
        objOrderPage.fillInSecondPartOrder(date, termValue, colourValue, comment);
        objOrderPage.pressFinalizeOrderButton();
        objOrderPage.pressYesToOrderButton();
        Assert.assertTrue(objOrderPage.getOrderHeader().contains("Заказ оформлен"));

    }
    @Test
    public void checkClickOnScooterHeaderOpensScooterHomePage() {
        if (Objects.equals(browser, "Chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        HomePage objHomePage = new HomePage(driver);
        objHomePage.openHomePage();
        objHomePage.agreeToCookie();
        objHomePage.pressTopOrderButton();
        HeaderFragmentPage objHeaderFragmentPage = new HeaderFragmentPage(driver);
        objHeaderFragmentPage.clickScooterHeader();
        Assert.assertEquals("https://qa-scooter.praktikum-services.ru/", objHeaderFragmentPage.getCurrentPageUrl());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
