package com.hsbc.search.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hsbc.search.domain.Station;

import java.util.List;

public class StationResultWrapper {

    private List<Station> stations;

    public StationResultWrapper(List<Station> stations) {
        this.stations = stations;
    }

    public StationResultWrapper() {
    }

    public List<Station> getStations() {
        return stations;
    }

    @JsonIgnore
    public String[] getStationNames() {
        return stations.stream().map(Station::getName).toArray(size -> new String[size]);
    }


}
