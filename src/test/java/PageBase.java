import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import java.util.Random;


class PageBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    protected By bodyBy = By.tagName("body");
    
    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }
    
    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    } 

    protected WebElement waitAndReturnClickableElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        this.wait.until(ExpectedConditions.elementToBeClickable(locator));
        return this.driver.findElement(locator);
    }

    protected void waitPageLoad(String url){
        this.wait.until(ExpectedConditions.urlContains(url));
    }
    
    public String getBodyText() {
        WebElement bodyElement = this.waitAndReturnElement(bodyBy);
        return bodyElement.getText();
    }

    public String getRandomString(int length){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    public String getRandomEmail(int length){
        return getRandomString(length) + "@gmail.com";
    }
   
}
