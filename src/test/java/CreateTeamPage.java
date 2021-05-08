
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


class CreateTeamPage extends PageBase {

    private String emailInputRootXpath = "//input";

    private By teamNameInputBy = By.xpath("//input[@placeholder='Team name']");

    private By createTeamButtonBy = By.xpath("//button[normalize-space()='Create team']");
    private By addAnotherButtonBy = By.xpath("//button[normalize-space()='Add another']");
    private By continueButtonBy = By.xpath("//button[normalize-space()='Continue']");
    private By chooseStarterButtonBy = By.xpath("//button[normalize-space()='Choose Starter']");

    public CreateTeamPage(WebDriver driver) {
        super(driver);
    }
    public void createTeam(String teamName, String[] emails){
        if(teamName!=null && !teamName.isEmpty() && !teamName.isBlank()) {
            waitAndReturnClickableElement(teamNameInputBy).sendKeys(teamName);
            waitAndReturnClickableElement(createTeamButtonBy).click();

            waitPageLoad("add-collaborators");

            for (int i = 1; i <= emails.length; i++) {
                String emailInputFullXpath = emailInputRootXpath + "[" + i + "]";

                if(i > 3)
                    waitAndReturnClickableElement(addAnotherButtonBy).click();

                By emailInputBy = By.xpath(emailInputFullXpath);
                    waitAndReturnClickableElement(emailInputBy).sendKeys(emails[i-1]);
            }
            waitAndReturnClickableElement(continueButtonBy).click();

            waitPageLoad("upgrade-team");
            waitAndReturnClickableElement(chooseStarterButtonBy).click();
        }
    }

}

