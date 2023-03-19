import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OrderPage {

    private WebDriver driver;
    //Поле "Имя"
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //Поле "Фамилия"
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле "Адрес"
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Выпадающий список "Станция метро"
    private final By stationField = By.xpath(".//input[@placeholder='* Станция метро']");
    //Поле "Телефон"
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    //Поле "Когда привезти заказ"
    private final By calendar = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Окно выбора даты
    private final By calendarDate = By.className("react-datepicker__day");
    //Выпадающий список "Срок аренды"
    private final By termFrield = By.xpath(".//div[text()='* Срок аренды']");
    //Поле "Комментарий"
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка "Заказать" в диалоговом окне
    private final By finalizeOrderButtton = By.xpath(".//div[contains(@class, 'Order_Buttons')]/button[text()='Заказать']");
    //Кнопка "Да" в диалоговом окне
    private final By yesToOrderButton = By.xpath(".//div[contains(@class, 'Order_Buttons')]/button[text()='Да']");
    //Заголовок окна с заказом
    private final By orderHeader = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillInName(String nameValue){
        driver.findElement(nameField).sendKeys(nameValue);
    }
    public void fillInSurname(String surnameValue){
        driver.findElement(surnameField).sendKeys(surnameValue);
    }
    public void fillInAddress(String addressValue){
        driver.findElement(addressField).sendKeys(addressValue);
    }
    public void fillInStation(String stationValue) {
        driver.findElement(stationField).click();
        driver.findElement(stationField).sendKeys(stationValue, Keys.ARROW_DOWN, Keys.ENTER);
    }
    public void fillInPhoneNumber(String phoneValue){
        driver.findElement(phoneField).sendKeys(phoneValue);
    }

    public void fillInFirstPartOrder(String nameValue, String surnameValue, String addressValue, String stationValue, String phoneValue){
        fillInName(nameValue);
        fillInSurname(surnameValue);
        fillInAddress(addressValue);
        fillInStation(stationValue);
        fillInPhoneNumber(phoneValue);
    }

    public void pressNextButton(){
        driver.findElement(nextButton).click();
    }

    public void fillInCalendar(String date){
        driver.findElement(calendar).click();
        new WebDriverWait(driver, 10).until(driver -> driver.findElement(calendarDate).isDisplayed());
        List<WebElement> elements = driver.findElements(calendarDate);
        for(WebElement element : elements){
            driver.findElement(By.xpath(".//div[contains(text(),'"+date+"')]")).click();
            break;
        }
    }
    public void fillInReturnTerm(String termValue){
        driver.findElement(termFrield).click();
        driver.findElement(By.xpath(".//div[text()='"+termValue+"']")).click();
    }
    public void chooseColour(String colourValue){
        driver.findElement(By.id(""+colourValue+"")).click();

    }
    public void fillInComment(String comment){
        driver.findElement(commentField).sendKeys(comment);
    }
    public void fillInSecondPartOrder(String date, String termValue, String colourValue, String comment){
        fillInCalendar(date);
        fillInReturnTerm(termValue);
        chooseColour(colourValue);
        fillInComment(comment);

    }
    public void pressFinalizeOrderButton(){
        driver.findElement(finalizeOrderButtton).click();
    }
    public void pressYesToOrderButton(){
        driver.findElement(yesToOrderButton).click();
    }
    public String getOrderHeader(){
        return  driver.findElement(orderHeader).getText();
    }

}
