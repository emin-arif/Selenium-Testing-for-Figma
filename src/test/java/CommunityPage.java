import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

class CommunityPage extends PageBase {
    private By webDesignSpanBy = By.xpath("//span[normalize-space()='Web design']");
    private By designDivBy = By.xpath("//div[@class='hub_file_tile_grid--resourcesGrid--2lOP0 hub_file_tile_grid--_resourcesGridTemplateColumns--4YBPG profile_resources_grid--_resourcesGridTemplateColumnsLarge--q8I8C']//div[1]//div[1]//div[2]//a[1]//div[1]//div[1]//div[1]//img[1]");
    private By newDesignCanvasBy = By.xpath("//div[@class='hub_file_viewer--viewer--21Uik']//div//div//canvas");
    public CommunityPage(WebDriver driver) {
        super(driver);
    }
    public void findNewWebDesign(){
        waitAndReturnClickableElement(webDesignSpanBy).click();
        waitPageLoad("web_design");
        waitAndReturnClickableElement(designDivBy).click();
        waitPageLoad("file");
        waitAndReturnClickableElement(newDesignCanvasBy).click();
    }

}

