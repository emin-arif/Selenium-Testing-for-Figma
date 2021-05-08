import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;


class FilePage extends PageBase {
    private By userNameSpanBy = By.xpath("//span[@class='navbar--ellipsis--3SO1g ellipsis--ellipsis--1RWY6']");
    private By userEmailDivBy = By.xpath("//div[@class='navbar--workspaceSubtitle--GGsAf text--fontNeg9--jzHz0 text--_fontBase--YWDo0 text--_whiteText--2PkdM navbar--ellipsis--3SO1g ellipsis--ellipsis--1RWY6']");
    private By userProfileDropdownBy = By.xpath("//div[@class='action--enabled--16Cku action--root--1ClVW toolbar_styles--enabledButton--2cWGq navbar--profileAction--3Jn4_ navbar--navbarAction--3J65x chevron--chevronContainer--3xT09']");
    private By logoutBy = By.xpath ("//div[contains(text(),'Log out')]");

    private By createTeamButtonBy = By.xpath("//div[@class='new_team_link--icon--1yQ1_ sidebar--icon--1Z2rN']");
    private By lastTeamDivBy = By.xpath("//div[@class='team_overview--metaTeamName--29dvj org_home_view_meta_content--orgName--3XeZo text--fontPos18--3M8-H text--_fontBase--YWDo0']");

    private By toCommunityHrefBy = By.xpath("//a[@class='community_hub_link--communitySection--10KFH sidebar--sidebarSection--3XvYF text--fontPos11--RSei3 text--_fontBase--YWDo0']");
    public FilePage(WebDriver driver) {
        super(driver);
    }    
    
    public String[] getUserNameAndEmailGreeting(){
        String[] result =  { waitAndReturnElement(userNameSpanBy).getText(),
                             waitAndReturnElement(userEmailDivBy).getText()};
        
        return result;
    }

    public void logout() {
        waitAndReturnClickableElement(userProfileDropdownBy).click();
        driver.switchTo().activeElement();
        WebElement logoutElm = waitAndReturnClickableElement(logoutBy);
        Actions a = new Actions(driver);
        a.moveToElement(logoutElm).click().build().perform();
    }

    public CreateTeamPage createTeam(){
        waitAndReturnClickableElement(createTeamButtonBy).click();
        return new CreateTeamPage(driver);
    }

    public String getLastTeamName(){
        return waitAndReturnElement(lastTeamDivBy).getText();
    }

    public CommunityPage goToCommunityPage(){
        waitAndReturnClickableElement(toCommunityHrefBy).click();
        waitPageLoad("community");
        return new CommunityPage(driver);
    }
}
