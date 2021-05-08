import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class FigmaTest {
    public WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testPageTitle() {
        MainPage mainPage = new MainPage(this.driver);
        CookieModal cookieModal = mainPage.waitCookieModal();
        cookieModal.acceptAll();

        String expectedTitle = "Figma: the collaborative interface design tool.";
        String actualTitle = mainPage.driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testLoginAndLogout() {
        MainPage mainPage = new MainPage(this.driver);
        CookieModal cookieModal = mainPage.waitCookieModal();
        cookieModal.acceptAll();
        LoginPage loginPage = mainPage.openLogin();
        FilePage filePage = loginPage.login("voyeciv903@ffuqzt.com", "voyeciv903");


        String[] greeting = filePage.getUserNameAndEmailGreeting();

        System.out.println(greeting[0]);
        System.out.println(greeting[1]);
        Assert.assertTrue(greeting[0].contains("Tester"));
        Assert.assertTrue(greeting[1].contains("voyeciv903@ffuqzt.com"));

        filePage.logout();
    }

    @Test
    public void testTeamCreation() {
        MainPage mainPage = new MainPage(this.driver);
        CookieModal cookieModal = mainPage.waitCookieModal();
        cookieModal.acceptAll();
        LoginPage loginPage = mainPage.openLogin();
        FilePage filePage = loginPage.login("voyeciv903@ffuqzt.com", "voyeciv903");

        CreateTeamPage createTeamPage = filePage.createTeam();

        String randomTeamName = createTeamPage.getRandomString(10);
        int emailCount = 10;
        String[] randomEmails = new String[emailCount];

        for (int i = 0; i < emailCount; i++) {
            randomEmails[i] = createTeamPage.getRandomEmail(8);
        }

        createTeamPage.createTeam(randomTeamName, randomEmails);

        String actualTeamName = filePage.getLastTeamName();
        Assert.assertEquals(randomTeamName, actualTeamName);

        filePage.logout();
    }

    @Test
    public void testFindingNewDesign() {
        MainPage mainPage = new MainPage(this.driver);
        CookieModal cookieModal = mainPage.waitCookieModal();
        cookieModal.acceptAll();
        LoginPage loginPage = mainPage.openLogin();
        FilePage filePage = loginPage.login("voyeciv903@ffuqzt.com", "voyeciv903");

        CommunityPage communityPage = filePage.goToCommunityPage();
        communityPage.findNewWebDesign();
        Assert.assertTrue(this.driver.getCurrentUrl().contains("fullscreen"));

        this.driver.navigate().back();
        Assert.assertTrue(this.driver.getCurrentUrl().contains("web_design"));
        this.driver.navigate().back();
        Assert.assertTrue(this.driver.getCurrentUrl().contains("community"));
        this.driver.navigate().back();
        Assert.assertTrue(this.driver.getCurrentUrl().contains("files"));
    }

    @Test
    public void testMultiplePageTest() {
        MainPage mainPage = new MainPage(this.driver);
        CookieModal cookieModal = mainPage.waitCookieModal();
        cookieModal.acceptAll();
        String[][] pageLinksAndTitles = {
                {"https://www.figma.com/design/", "Design"},
                {"https://www.figma.com/organization/", "Organization"},
                {"https://www.figma.com/pricing/", "Pricing"},
                {"https://www.figma.com/events/", "events"},
                {"https://www.figma.com/blog/", "blog"}
        };

        for (String[] linkAndTitle:pageLinksAndTitles) {
            driver.navigate().to(linkAndTitle[0]);
            Assert.assertTrue(driver.getTitle().contains(linkAndTitle[1]));
        }
    }


    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
