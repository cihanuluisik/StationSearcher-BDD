package com.hsbc.health;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber/health"},
        features = "classpath:features/health",
        glue = "com/hsbc/health"

)
public class SearchHealthCucumberTest {

}
