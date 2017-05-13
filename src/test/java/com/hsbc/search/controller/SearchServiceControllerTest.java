package com.hsbc.search.controller;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class SearchServiceControllerTest {

    private StationResultWrapper result;

    @Before
    public void setUp() throws Exception {
        result = new SearchServiceController().search(null);
    }

    @Test
    public void given_null_param_to_search_then_return_null() throws Exception {
        assertThat(result,is(nullValue()));
    }

    @Test
    public void given_empty_param_to_search_then_return_null() throws Exception {
        StationResultWrapper result = new SearchServiceController().search("");
        assertThat(result,is(nullValue()));
    }

    @Test
    public void given_empty_space_param_to_search_then_return_null() throws Exception {
        StationResultWrapper result = new SearchServiceController().search("   ");
        assertThat(result,is(nullValue()));
    }

}