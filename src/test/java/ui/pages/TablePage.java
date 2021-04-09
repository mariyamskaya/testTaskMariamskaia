package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TablePage extends BasePage {
    public HashMap<Integer, HashMap<String, String>> table = new HashMap<Integer, HashMap<String, String>>();

    private By sortByUsernameLink = By.xpath("//a[contains(@class,'sort-link') and contains(text(),'Username')]");

    public TablePage(WebDriver driver) {
        this.driver = driver;
    }

    public void parseTable() {
        List<WebElement> rowsList = driver.findElements(By.tagName("tr")); // get all table rows
        List<WebElement> headersList = rowsList.get(0).findElements(By.tagName("th")); // get all table headers
        rowsList.remove(0); // remove headers row

        // Extract header's name from all table headers
        List<String> textHeaders = new ArrayList<String>();
        for (WebElement header : headersList) {
            textHeaders.add(header.getAttribute("textContent").trim());
        }
        // Fill HashMap with values in accordance with existing columns
        int i = 0;
        for (WebElement row : rowsList) {
            List<WebElement> rowColumnsList = row.findElements(By.tagName("td"));
            HashMap<String, String> rowData = new HashMap<String, String>();
            for (String header : textHeaders) {
                rowData.put(header, (rowColumnsList.get(textHeaders.indexOf(header)).getAttribute("textContent").trim()));
            }
            this.table.put(i++, rowData);
        }
    }

    public int getResultTableSize() {
        return this.table.size();
    }

    public TablePage clickUsernameSortingLink() {
        driver.findElement(sortByUsernameLink).click();

        return this;
    }
}
