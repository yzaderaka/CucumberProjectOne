package TestRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.*;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features",
        glue = "StepDefinitions",
        dryRun = false,
        tags = "@smoke2",
        plugin = {"pretty", "html:target/Cucumber.html", "json:target/Cucumber.json", "rerun:target/failed.txt"}

)

public class SmokeRunner {
}


//tags option will execute the tagged testcase as mentioned in your runner class