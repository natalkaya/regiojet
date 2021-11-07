package org.example.qa.api;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import org.example.qa.model.dto.RouteDetail;
import org.example.qa.model.dto.RoutesResponseDto;
import org.example.qa.model.dto.SearchRouteRequestDto;
import org.example.qa.test.TestBase;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Optional;

import static org.example.qa.model.Location.BRNO;
import static org.example.qa.model.Location.OSTRAVA;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Search for a direct connection between Ostrava and Brno using https://shop.regiojet.sk/ domain,
 * departing on Monday and get additional information such as:
 * • departure time
 * • arrival time
 * • number of stops
 * • the price of the journey (accept value price from)
 * At the same time make sure that all the connections that have been found meet the input criteria
 * (departure time, place of the departure, place of the arrival and whether it is a direct or indirect
 * connection).
 * Create 3 cases which will choose optimal connection, based on the following criteria:
 * a) the fastest arrival time
 * b) the shortest time spent with travelling
 * c) the lowest price of the journey
 */
public class Test_FindRoutesByCriteria extends TestBase {

    private final ApiHttpClient apiHttpClient = new ApiHttpClient();

    private final SearchRouteRequestDto nextMondayRoutesRequest =
            SearchRouteRequestDto.nextMonday(OSTRAVA.id, BRNO.id);

    private RoutesResponseDto sendSearchRoutesRequest(SearchRouteRequestDto searchRouteRequestDto) {
        return apiHttpClient
                .getRoutesSearchSimple(searchRouteRequestDto)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(RoutesResponseDto.class);
    }

    @Test
    void checkDepartureDate() {
        assertTrue(
                sendSearchRoutesRequest(nextMondayRoutesRequest)
                        .getRoutes()
                        .stream()
                        .map(RouteDetail::departureDateWithoutTime)
                        .allMatch(departureDate -> {
                            System.out.printf("from response: %s, from req: %s",
                                    departureDate,
                                    nextMondayRoutesRequest.getDepartureDate()
                            );
                            return departureDate.equals(nextMondayRoutesRequest.getDepartureDate());
                        })
        );
    }

    @Test
    void theFastestArrivalTime() {
        final Optional<RouteDetail> foundRoute =
                sendSearchRoutesRequest(nextMondayRoutesRequest)
                        .getRoutes()
                        .stream()
                        .min(Comparator.comparing(RouteDetail::getArrivalTime));

        assertTrue(foundRoute.isPresent());
        printFoundRoute(foundRoute.get());
    }

    @Test
    void theShortestTimeSpentInTravelling() {
        final Optional<RouteDetail> foundRoute =
                sendSearchRoutesRequest(nextMondayRoutesRequest)
                        .getRoutes()
                        .stream()
                        .min(Comparator.comparing(RouteDetail::getTravelTime));

        assertTrue(foundRoute.isPresent());
        printFoundRoute(foundRoute.get());
    }

    @Test
    void theLowestPriceOfJourney() {
        RoutesResponseDto responseActual = sendSearchRoutesRequest(nextMondayRoutesRequest);

        final Optional<RouteDetail> foundRoute = responseActual
                .getRoutes()
                .stream()
                .filter(routeDetail -> routeDetail.getPriceFrom() != 0)
                .min(Comparator.comparing(RouteDetail::getPriceFrom));

        assertTrue(foundRoute.isPresent());
        printFoundRoute(foundRoute.get());
    }

    @Step("Show The found route")
    private void printFoundRoute(RouteDetail foundRoute) {
        log.info("\nThe found route is " + foundRoute);
    }

}
