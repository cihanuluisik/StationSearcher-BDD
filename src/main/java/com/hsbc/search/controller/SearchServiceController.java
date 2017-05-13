package com.hsbc.search.controller;

import com.hsbc.search.domain.Station;
import com.hsbc.search.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchServiceController {

    @Autowired
    private SearchRepository repository;

    @RequestMapping("/stations/searchAll")
    public StationResultWrapper searchAll() {
        try {
            final Iterable<Station> repositoryAll = repository.findAll();
            final List<Station> stationList = new ArrayList<>();
            repositoryAll.forEach(stationList::add);
            return new StationResultWrapper(stationList);
        } catch (Throwable e) {
            throw new SearchServiceException();
        }
    }

    public void setRepository(SearchRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/stations/search/{name}")
    public StationResultWrapper search(@PathVariable(value = "name") String name)  {
        try {
            if ( name==null || name.trim().isEmpty()){
                return null;
            }
            else {
                final List<Station> stationList = repository.findByName(name);
                if (stationList.isEmpty()){
                    return null;
                }
                else {
                    return new StationResultWrapper(stationList);
                }
            }
        } catch (Throwable e) {
            throw new SearchServiceException();
        }

    }

}
