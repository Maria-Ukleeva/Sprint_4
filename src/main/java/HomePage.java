import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    //Кнопка принятия куки
    private final By acceptCookiesButton = By.id("rcc-confirm-button");
    //Верхняя кнопка заказа
    private final By topOrderButton = By.xpath(".//div[contains(@class, 'Header_Nav')]/button[contains(@class, 'Button_Button')]");
    //Нижняя кнопка заказа
    private final By downOrderButton = By.xpath(".//div[contains(@class, 'Home_FinishButton')]/button[contains(@class, 'Button_Button')]");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openHomePage(){
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    public String getAnswer(String question){
        WebElement questionPanel=  driver.findElement(By.xpath(".//div[text()='"+question+"']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", questionPanel);
        new WebDriverWait(driver, 10).until(driver -> questionPanel.isEnabled());
        questionPanel.click();
        new WebDriverWait(driver, 10).until(driver -> driver.findElement(By.xpath(".//div[text()='"+question+"']/../following-sibling::div/p")).isDisplayed());
        return driver.findElement(By.xpath(".//div[text()='"+question+"']/../following-sibling::div/p")).getText();
    }

    public void pressTopOrderButton(){
        driver.findElement(topOrderButton).click();

    }
    public void pressDownOrderButton(){
        driver.findElement(downOrderButton).click();

    }

    public void agreeToCookie(){
        driver.findElement(acceptCookiesButton).click();
    }


}
