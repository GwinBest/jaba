package org.javalabs.lab1.logic;

import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

public class Logic {
    public ApiResponse searchPage(String query) {
        String apiUrl = "https://iis.bsuir.by/api/v1/schedule" +
                "?studentGroup=" + query;

        RestTemplate restTemplate = new RestTemplate();

        try {
            return restTemplate.getForObject(apiUrl, ApiResponse.class);
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }
}