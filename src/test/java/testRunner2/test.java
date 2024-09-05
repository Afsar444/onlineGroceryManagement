package testRunner2;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "Feature",
    glue = {"step2"},
    plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class test {

}
