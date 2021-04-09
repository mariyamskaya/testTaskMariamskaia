package ui.pages;

import api.Base;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.EnvSettings;

import java.time.Duration;


public class PlayersPage extends BasePage {
    public String pageUrl = EnvSettings.BASE_HOST + "/user/player/admin";

    private By playersTable = By.xpath("//table[@class='table table-hover table-striped table-bordered table-condensed']");

    public PlayersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(this.pageUrl);
    }

    public TablePage playersTableVisible() {
        WebElement playersTableElement = new WebDriverWait(driver, EnvSettings.WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(this.driver.findElement(playersTable)));

        if (!playersTableElement.isDisplayed()) {
            Assert.fail();
        }

        TablePage resultTable = new TablePage(this.driver);
        resultTable.parseTable();

        return resultTable;
    }
}
