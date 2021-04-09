package ui.stepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ui.pages.HomePage;
import ui.pages.LoginPage;
import ui.pages.PlayersPage;
import ui.pages.TablePage;

public class StepsDefinitions {
    public static WebDriver driver;

    public LoginPage loginPage;

    public HomePage homePage;

    public PlayersPage playersPage;

    public TablePage resultTable;

    @Given("I setup browser")
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        driver = new ChromeDriver(options);
    }

    @Given("open login page")
    public void open_login_page() {
        this.loginPage = new LoginPage(driver);
        this.loginPage.open();
    }

    @And("fill username field {string}")
    public void fill_username_field(String username) {
        this.loginPage.fillUsernameField(username);
    }

    @And("fill password field {string}")
    public void fillPasswordField(String password) {
        this.loginPage.fillPasswordField(password);
    }

    @When("click sign-in button")
    public void clickSignInButton() {
        this.homePage = this.loginPage.clickSignInButton();
    }

    @Then("I should be logged-in")
    public void ishouldBeLoggedIn() {
        Assert.assertTrue(this.homePage.isSideBarVisible());
        Assert.assertTrue(this.homePage.isDashboardVisible());

        this.homePage.checkUsername("admin1");
    }

    @Given("I open players page")
    public void iOpenPlayersPage() {
        this.playersPage = new PlayersPage(driver);

        this.playersPage.open();
    }

    @When("I see players table")
    public void iSeePlayersTable() {
        this.resultTable = this.playersPage.playersTableVisible();
    }

    @Then("It should be loaded")
    public void itShouldBeLoaded() {
        Assert.assertFalse(this.resultTable.getResultTableSize() == 0);
    }

    @Given("I sort table by username")
    public void sortByUsername() {
        this.resultTable.clickUsernameSortingLink();
    }

    @Then("close browser")
    public void closeBrowser() {
        driver.quit();
    }
}
