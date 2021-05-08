import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class CookieModal extends PageBase {

    private By acceptAllButtonBy = By.xpath("//button[normalize-space()='Accept All']");

    public CookieModal(WebDriver driver) {
        super(driver);
    }    
    
    public void acceptAll(){
        this.waitAndReturnClickableElement(acceptAllButtonBy).click();
    }
}

