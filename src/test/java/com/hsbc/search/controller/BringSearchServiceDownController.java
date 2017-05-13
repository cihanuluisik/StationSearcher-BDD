package com.hsbc.search.controller;


import com.hsbc.search.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("test")
@RestController
public class BringSearchServiceDownController {

    @Autowired
    SearchServiceController searchServiceController;

    @Autowired
    private SearchRepository searchRepository;

    @Profile("test")
    @RequestMapping("/stations/search/bring/down")
    public boolean bringDownSearchService(){
        searchServiceController.setRepository(null);
        return true;
    }

    @Profile("test")
    @RequestMapping("/stations/search/bring/up")
    public boolean bringUpSearchService(){
        searchServiceController.setRepository(searchRepository);
        return true;
    }


}
