package com.hsbc.health;

import com.hsbc.ClientDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.TestRestTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchHealthSteps {

    private static final ClientDriver clientDriver = new ClientDriver();

    @Given("^the service is up and running$")
    public void the_service_is_up_and_running() throws Throwable {
        clientDriver.bringServiceUpIfDown();
    }

    @Given("^I am on a RESTFull client screen$")
    public void i_am_on_a_RESTFull_client_screen() throws Throwable {
        clientDriver.setRestClient(new TestRestTemplate());
    }

    @When("^I call the \"([^\"]*)\" API$")
    public void i_call_the_API(String apiToCall) throws Throwable {
        clientDriver.callServiceApi(apiToCall);
    }

    @Then("^the response code should be (\\d+)$")
    public void the_response_code_should_be(int expectedStatusCode) throws Throwable {
        assertThat(clientDriver.getStatusCode(), is(expectedStatusCode));
    }

    @Then("^the response body should be given \"([^\"]*)\"$")
    public void the_response_body_should_be_given(String epectedResponseText) throws Throwable {
        if (epectedResponseText!=null && !epectedResponseText.isEmpty()) {
            assertThat(clientDriver.getResponseBodyAsText(), is(epectedResponseText.replace("'", "\"")));
        }
    }

    @Given("^the service is not up and running$")
    public void the_service_is_not_up_and_running() throws Throwable {
        clientDriver.bringSearchService("down");
    }

}
