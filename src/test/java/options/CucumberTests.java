package options;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/*import cucumber.api.CucumberOptions;*/
/*import cucumber.api.junit.Cucumber;*/

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty",
				"html:target/cucumber-reports/cucumber.html",
				"json:target/cucumber-reports/cucumber.json"},
		glue = {"stepdefs"},
		features = {"src/test/features"})
public class CucumberTests {}
