package org.example.qa.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * actionPrice: false
 * arrivalStationId: 10204002
 * arrivalTime: "2021-11-08T03:20:00.000+01:00"
 * bookable: true
 * creditPriceFrom: 235
 * creditPriceTo: 235
 * delay: null
 * departureStationId: 372825007
 * departureTime: "2021-11-08T01:15:00.000+01:00"
 * freeSeatsCount: 6
 * id: "5705143237"
 * nationalTrip: true
 * notices: false
 * priceFrom: 255
 * priceTo: 255
 * pricesCount: 1
 * support: false
 * surcharge: false
 * transfersCount: 0
 * travelTime: "02:05 h"
 * vehicleStandardKey: "FUN_RELAX_SELF_SERVICE"
 * vehicleTypes: ["BUS"]
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteDetail {
    private Long arrivalStationId;
    private String arrivalTime;
    private Long departureStationId;
    private String departureTime;
    private String travelTime;
    private Double priceFrom;
    private Double priceTo;

    @Override
    public String toString() {
        return String.format(
                "RouteDetail: \n" +
                        "arrivalTime = %s,\n" +
                        "departureTime = %s,\n" +
                        "travelTime = %s,\n" +
                        "priceFrom = %f \n" +
                        "priceTo = %f",
                this.arrivalTime,
                this.departureTime,
                this.travelTime,
                this.priceFrom,
                this.priceTo
        );
    }

    public String departureDateWithoutTime() {
        return LocalDateTime.parse(this.departureTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"))
                .toLocalDate().toString();
    }
}
