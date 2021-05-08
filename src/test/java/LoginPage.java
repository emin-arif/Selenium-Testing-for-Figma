import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;


class LoginPage extends PageBase {

    private By loginModalBy = By.xpath("//iframe[@src='/login_iframe?autofocus=true&type=modal&form_state=sign_in']");
    private By nameInputBoxBy = By.name("email");
    private By passwordInputBoxBy = By.name("password");

    public LoginPage(WebDriver driver) {
        super(driver);
    }    
    
    public FilePage login(String username, String password){
        WebElement loginModal = this.waitAndReturnClickableElement(loginModalBy);
        driver.switchTo().frame(loginModal);
        this.waitAndReturnClickableElement(nameInputBoxBy).sendKeys(username);
        this.waitAndReturnClickableElement(passwordInputBoxBy).sendKeys(password+"\n");
        return new FilePage(this.driver);
    }
}

