package com.hsbc.search;

import com.hsbc.ClientDriver;
import com.hsbc.search.domain.Station;
import com.hsbc.search.repository.SearchRepository;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchServiceSteps {
    private static final ClientDriver clientDriver = new ClientDriver();
    private SearchRepository searchRepository;

    @Given("^I am on a RESTFull client screen$")
    public void i_am_on_a_RESTFull_client_screen() throws Throwable {
        clientDriver.setRestClient(new TestRestTemplate());
    }

    @Given("^the service is up an running$")
    public void the_service_is_up_an_running() throws Throwable {
        ConfigurableApplicationContext applicationContext = clientDriver.bringServiceUpIfDown();
        searchRepository = applicationContext.getBean(SearchRepository.class);
        assertThat(clientDriver.pingHealthForTextResponse(), is("{\"status\":\"UP\"}"));
    }

    @Given("^following train stations exist:$")
    public void following_train_stations_exist(DataTable dataTable) throws Throwable {
        if (clientDriver.getFullStationList().isEmpty())
        {
            List<String> stationNameArray = dataTable.asList(String.class);
            List<Station> stationList = stationNameArray.stream().map(Station::new).collect(Collectors.toList());
            stationList.remove(0);
            clientDriver.setFullStationList(stationList);
            searchRepository.save(stationList);
        }
    }

    @When("^I call the searchAll API$")
    public void i_call_the_searchAll_API() throws Throwable {
        clientDriver.callApiWithoutParams();
    }

    @Then("^the response code should be (\\d+)$")
    public void the_response_code_should_be(int expectedStatusCode) throws Throwable {
        assertThat(clientDriver.getResultStatusCode(), is(expectedStatusCode));
    }
    @Then("^the search should return all the stations available in a JSON format$")
    public void the_search_should_return_all_the_stations_available_in_a_JSON_format() throws Throwable {
        clientDriver.getResultWrapperResponse().getStations().forEach(
                stn -> {
                    assertThat(clientDriver.getFullStationList(), hasItem(stn));
                }
        );
    }

    @When("^I call search API with \"([^\"]*)\" for text output$")
    public void i_call_search_API_with_for_text_output(String stationNameToSearch) throws Throwable {
        clientDriver.callApiWithParamsForString( stationNameToSearch);
    }

    @Then("^the search should return the text \"([^\"]*)\":$")
    public void the_search_should_return_the_text(String expectedTextResponse) throws Throwable {
        assertThat(clientDriver.getResultText(), is(expectedTextResponse.replace("'", "\"")));
    }

    @When("^I call search API with \"([^\"]*)\" for station list output in json format$")
    public void i_call_search_API_with_for_station_list_output_in_json_format(String stationNameToSearch) throws Throwable {
        clientDriver.callApiWithParamsForStationResultWrapper(stationNameToSearch);
    }

    @Then("^the search should return those stations in Json format :\"([^\"]*)\", \"([^\"]*)\" :$")
    public void the_search_should_return_those_stations_in_Json_format(String station1, String station2) throws Throwable {
        assertThat(clientDriver.getResultWrapperResponse().getStationNames(), is(new String[]{station1, station2}));
    }
}
