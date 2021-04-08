package ui.Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.TablePage;


public class PlayersPage extends BasePage {
    public String pageUrl = "http://test-app.d6.dev.devcaz.com/user/player/admin";

    private By playersTable = By.xpath("//table[@class='table table-hover table-striped table-bordered table-condensed']");

    public PlayersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(this.pageUrl);
    }

    public TablePage playersTableVisible() {
        if (!this.driver.findElement(playersTable).isDisplayed()) {
            Assert.fail();
        }

        TablePage resultTable = new TablePage(this.driver);
        resultTable.parseTable();

        return resultTable;
    }
}
