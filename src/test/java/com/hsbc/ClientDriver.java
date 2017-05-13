package com.hsbc;

import com.hsbc.search.SearchApplication;
import com.hsbc.search.controller.StationResultWrapper;
import com.hsbc.search.domain.Station;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientDriver {

    private RestTemplate restClient  = new TestRestTemplate();
    private ResponseEntity<String> responseEntity;
    private String stationsBaseUrl;
    private static ConfigurableApplicationContext configurableApplicationContext;
    private static List<Station> fullStationList = new ArrayList<>();
    private StationResultWrapper resultWrapperResponse;
    private int resultStatusCode;
    private String resultText;
    private String hostUrl;

    public ConfigurableApplicationContext bringServiceUpIfDown() {
        if ( configurableApplicationContext == null){
            configurableApplicationContext = SpringApplication.run(SearchApplication.class, new String[]{});
        }
        String serverPort = configurableApplicationContext.getEnvironment().getProperty("local.server.port");
        hostUrl = "http://localhost:" + serverPort+"/";
        stationsBaseUrl = hostUrl + "stations/";
        bringSearchService("up");
        return configurableApplicationContext;
    }

    public void callServiceApi(String serviceApi) {
        responseEntity = restClient.getForEntity((serviceApi.equals("health")?hostUrl :stationsBaseUrl )+ serviceApi, String.class);
    }

    public int getStatusCode() {
        return responseEntity.getStatusCode().value();
    }

    public String getResponseBodyAsText() {
        return responseEntity.getBody();
    }

    public void setRestClient(TestRestTemplate restClient) {
        this.restClient = restClient;
    }

    public void  bringSearchService(String upOrDown) {
        restClient.getForEntity(stationsBaseUrl +"search/bring/" + upOrDown , String.class);
    }

    public <T> T getForObject(String url, Class<T> responseType, Object... urlVariables) throws RestClientException {
        return restClient.getForObject(url, responseType, urlVariables);
    }
    public List<Station> getFullStationList() {
        return fullStationList;
    }

    public void setFullStationList(List<Station> fullStationList) {
        this.fullStationList = fullStationList;
    }

    public String getStationsBaseUrl() {
        return stationsBaseUrl;
    }

    public void callApiWithoutParams() throws URISyntaxException, MalformedURLException {
        final ResponseEntity<StationResultWrapper> forEntity = restClient.getForEntity(new URL(stationsBaseUrl +"searchAll").toURI(), StationResultWrapper.class);
        resultWrapperResponse = forEntity.getBody();
        resultStatusCode = forEntity.getStatusCode().value();
    }

    public int getResultStatusCode() {
        return resultStatusCode;
    }

    public StationResultWrapper getResultWrapperResponse() {
        return resultWrapperResponse;
    }

    public String pingHealthForTextResponse() {
        return getForObject(getHostUrl() + "health", String.class);
    }

    public void callApiWithParamsForString(String stationNameToSearch) {
        final ResponseEntity<String> forEntity = getStringResponseEntity(stationNameToSearch, String.class);
        resultText = forEntity.getBody()==null ? "" :forEntity.getBody();
    }

    private <T> ResponseEntity<T> getStringResponseEntity(String stationNameToSearch, Class<T> responseType) {
        final HashMap paramMap = new HashMap();
        paramMap.putIfAbsent("name", stationNameToSearch);
        final ResponseEntity<T> forEntity = restClient.getForEntity(stationsBaseUrl +"search/{name}", responseType, paramMap);
        resultStatusCode = forEntity.getStatusCode().value();
        return forEntity;
    }

    public String getResultText() {
        return resultText;
    }

    public void callApiWithParamsForStationResultWrapper( String stationNameToSearch) {
        final ResponseEntity<StationResultWrapper> forEntity = getStringResponseEntity(stationNameToSearch, StationResultWrapper.class);
        resultWrapperResponse = forEntity.getBody();
    }


    public String getHostUrl() {
        return hostUrl;
    }
}
