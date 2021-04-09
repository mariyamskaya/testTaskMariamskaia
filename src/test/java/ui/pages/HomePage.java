package ui.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.EnvSettings;

public class HomePage extends BasePage {
    public static String pageUrl = EnvSettings.BASE_HOST + "/configurator/dashboard/index";

    private By sideBar = By.className("main-side-menu");

    private By dashboard = By.id("content");

    private By profileNav = By.xpath("//li[@class='dropdown text-normal nav-profile']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSideBarVisible() {
        return driver.findElement(this.sideBar).isDisplayed();
    }

    public boolean isDashboardVisible() {
        return driver.findElement(this.dashboard).isDisplayed();
    }

    public HomePage checkUsername(String expectedUsername) {
        String actualUsername = driver.findElement(this.profileNav).getText();

        if (!actualUsername.equals(expectedUsername)) {
            Assert.fail();
        }

        return this;
    }
}
