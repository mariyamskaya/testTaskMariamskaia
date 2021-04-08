package ui.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public String pageUrl = "http://test-app.d6.dev.devcaz.com/admin/login";

    private By usernameField = By.id("UserLogin_username");

    private By passwordField = By.id("UserLogin_password");

    private By signInButton = By.xpath("//input[contains(@type,'submit')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(this.pageUrl);
    }

    public void fillUsernameField(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public HomePage clickSignInButton() {
        driver.findElement(signInButton).click();

        return new HomePage(this.driver);
    }
}
