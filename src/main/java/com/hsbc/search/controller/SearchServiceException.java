package com.hsbc.search.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.SERVICE_UNAVAILABLE, reason="There is internal error in service. So not available!")
public class SearchServiceException extends RuntimeException {
}
