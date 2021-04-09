package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.EnvSettings;

public class PlayersPage extends BasePage {
    public static String pageUrl = EnvSettings.BASE_HOST + "/user/player/admin";

    private By playersTable = By.xpath("//table[@class='table table-hover table-striped table-bordered table-condensed']");

    public PlayersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(pageUrl);
    }

    public TablePage playersTableVisible() {
        new WebDriverWait(driver, EnvSettings.WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(driver.findElement(this.playersTable)));

        TablePage resultTable = new TablePage(driver);

        resultTable.parseTable();

        return resultTable;
    }
}
