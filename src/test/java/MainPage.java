import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


class MainPage extends PageBase {

    private By cookieModalBy = By.xpath("/html/body/div[1]/div[1]");
    private By loginMenuButtonBy = By.xpath("//button[normalize-space()='Log in']");
    private By mainPageMessageH1By = By.xpath("//h1[contains(text(),'Minds meeting minds is how great ideas meet the world')]");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.figma.com/");
    }    
    
    public CookieModal waitCookieModal() {
        this.waitAndReturnElement(cookieModalBy);
        return new CookieModal(this.driver);
    }
    
    public LoginPage openLogin() {
        this.waitAndReturnElement(loginMenuButtonBy).click();
        return new LoginPage(this.driver);
    }

    public String getMainPageMessage(){
        return this.waitAndReturnElement(mainPageMessageH1By).getText();
    }
}
