package ui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.Pages.HomePage;
import ui.Pages.LoginPage;

public class StepDefinitions {
    public static WebDriver driver = new ChromeDriver();

    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage;

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

    @Then("close browser")
    public void closeBrowser() {
        driver.quit();
    }
}
