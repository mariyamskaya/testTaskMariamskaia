package ui.stepsDefinitions;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "message:target/cucumber-report.ndjson",
        features = "src/test/java/ui/features"
)
public class RunCucumberTest {
}