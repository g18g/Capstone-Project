package runner;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;
 
@RunWith(Cucumber.class)

@CucumberOptions(

features = {"src/test/java/navigation.feature", 
		 "src/test/java/login&register.feature",
		"src/test/java/Formfill.feature"
		},


glue = "stepdef",

plugin = {"pretty", "html:target/cucumber-reports"},

monochrome = true,

tags = "@login" 
)
public class runner {

}
