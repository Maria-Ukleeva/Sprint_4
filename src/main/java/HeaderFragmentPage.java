import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class HeaderFragmentPage {
    private WebDriver driver;
    //Логотип Самоката
    private final By scooterHeader = By.xpath(".//img[@alt='Scooter']");
    public HeaderFragmentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickScooterHeader(){
        driver.findElement(scooterHeader).click();
    }
    public String getCurrentPageUrl(){
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        return driver.getCurrentUrl();
    }

}
