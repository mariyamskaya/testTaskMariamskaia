package ui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ui.Pages.HomePage;
import ui.Pages.LoginPage;
import ui.Pages.PlayersPage;

import java.util.HashMap;
import java.util.Map;

public class StepsDefinition {
    public static WebDriver driver;

    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage;

    PlayersPage playersPage = new PlayersPage(driver);
    TablePage resultTable;

    @Given("I setup browser")
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Given("open login page")
    public void open_login_page() {
        loginPage.open();
    }

    @And("fill username field {string}")
    public void fill_username_field(String username) {
        loginPage.fillUsernameField(username);
    }

    @And("fill password field {string}")
    public void fillPasswordField(String password) {
        loginPage.fillPasswordField(password);
    }

    @When("click sign-in button")
    public void clickSignInButton() {
        this.homePage = loginPage.clickSignInButton();
    }

    @Then("I should be logged-in")
    public void ishouldBeLoggedIn() {
        Assert.assertTrue(this.homePage.isSideBarVisible());
        Assert.assertTrue(this.homePage.isDashboardVisible());
        this.homePage.checkUsername("admin1");
    }

    @Given("I open players page")
    public void i_open_players_page() {
        playersPage.open();
    }

    @When("I see players table")
    public void iSeePlayersTable() {
        this.resultTable = playersPage.playersTableVisible();
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
