package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.EnvSettings;

public class LoginPage extends BasePage {
    public static String pageUrl = EnvSettings.BASE_HOST + "/admin/login";

    private By usernameField = By.id("UserLogin_username");

    private By passwordField = By.id("UserLogin_password");

    private By signInButton = By.xpath("//input[contains(@type,'submit')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(pageUrl);
    }

    public void fillUsernameField(String username) {
        driver.findElement(this.usernameField).sendKeys(username);
    }

    public void fillPasswordField(String password) {
        driver.findElement(this.passwordField).sendKeys(password);
    }

    public HomePage clickSignInButton() {
        driver.findElement(this.signInButton).click();

        return new HomePage(driver);
    }
}
