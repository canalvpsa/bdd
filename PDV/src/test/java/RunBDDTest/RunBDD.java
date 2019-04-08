package RunBDDTest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features/"},
		glue = "steps",
		tags = {"@executavel"},
		plugin = {"pretty", "html:target/cucumber-reports", 
				"json:target/cucumber-reports/report.json", 
		"junit:target/cucumber-reports/Cucumber.xml"},
		monochrome = true,
		snippets = SnippetType.CAMELCASE,
		dryRun = false,
		strict = false
		)

public class RunBDD {
}
