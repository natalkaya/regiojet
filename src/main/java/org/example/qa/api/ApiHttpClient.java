package org.example.qa.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.qa.model.dto.SearchRouteRequestDto;
import org.example.qa.util.ConfigReader;

public class ApiHttpClient {

    private final String baseUrl;

    public ApiHttpClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ApiHttpClient() {
        this.baseUrl = ConfigReader.apiBaseUrl;
    }

    public Response getRoutesSearchSimple(SearchRouteRequestDto searchRouteRequestDto) {
        return RestAssured.given()
                .log()
                .all()
                .queryParams(searchRouteRequestDto.asQueryParams())
                .get(baseUrl + "/routes/search/simple");
    }

}
