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
public class HomePageTests {
    private WebDriver driver;
    private final String question;
    private final String answer;
    private final String browser;

    public HomePageTests(String browser, String question, String answer){
        this.browser = browser;
        this.question = question;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getData(){
        return new Object[][] {
                {"Chrome", "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Chrome", "Хочу сразу несколько самокатов! Так можно?","Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Chrome", "Как рассчитывается время аренды?","Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Chrome", "Можно ли заказать самокат прямо на сегодня?","Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Chrome", "Можно ли продлить заказ или вернуть самокат раньше?","Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Chrome", "Вы привозите зарядку вместе с самокатом?","Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Chrome", "Можно ли отменить заказ?","Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Chrome", "Я жизу за МКАДом, привезёте?","Да, обязательно. Всем самокатов! И Москве, и Московской области."},
                {"Firefox", "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Firefox", "Хочу сразу несколько самокатов! Так можно?","Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Firefox", "Как рассчитывается время аренды?","Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Firefox", "Можно ли заказать самокат прямо на сегодня?","Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Firefox", "Можно ли продлить заказ или вернуть самокат раньше?","Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Firefox", "Вы привозите зарядку вместе с самокатом?","Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Firefox", "Можно ли отменить заказ?","Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Firefox", "Я жизу за МКАДом, привезёте?","Да, обязательно. Всем самокатов! И Москве, и Московской области."},};
    }

    @Test
    public void checkClickOnQuestionOpensCorrectAnswer(){
        if (Objects.equals(browser, "Chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        HomePage objHomePage = new HomePage(driver);
        objHomePage.openHomePage();
        objHomePage.agreeToCookie();
        String actualAnswer = objHomePage.getAnswer(question);
        Assert.assertEquals(answer, actualAnswer);

    }

    @Test
    public void checkClickOnScooterHeaderOpensScooterHomePage(){
        if (Objects.equals(browser, "Chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        HomePage objHomePage = new HomePage(driver);
        objHomePage.openHomePage();
        objHomePage.agreeToCookie();
        HeaderFragmentPage objHeaderFragmentPage = new HeaderFragmentPage(driver);
        objHeaderFragmentPage.clickScooterHeader();
        Assert.assertEquals("https://qa-scooter.praktikum-services.ru/", objHeaderFragmentPage.getCurrentPageUrl());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
