package com.hsbc.search;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber/search"},
        features = "classpath:features/search",
        glue = "com/hsbc/search"

)
public class SearchServiceCucumberTest {


}
