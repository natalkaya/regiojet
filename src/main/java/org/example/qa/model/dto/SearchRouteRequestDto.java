package org.example.qa.model.dto;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import org.example.qa.model.LocationType;
import org.example.qa.model.Tariff;
import org.example.qa.util.DataGenerator;

import java.util.Map;

/**
 * tariffs: REGULAR
 * toLocationType: CITY
 * toLocationId: 10202002
 * fromLocationType: CITY
 * fromLocationId: 10202000
 * departureDate: 2021-11-08
 */
@Builder
@Getter
public class SearchRouteRequestDto {
    String tariffs;
    String toLocationType;
    Long toLocationId;
    String fromLocationType;
    Long fromLocationId;
    String departureDate;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public Map<String, ?> asQueryParams() {
        return objectMapper.convertValue(this, Map.class);
    }

    public static SearchRouteRequestDto nextMonday(Long toLocationId, Long fromLocationId) {
        return SearchRouteRequestDto
                .builder()
                .tariffs(Tariff.REGULAR.name())
                .toLocationType(LocationType.CITY.name())
                .toLocationId(toLocationId)
                .fromLocationType(LocationType.CITY.name())
                .fromLocationId(fromLocationId)
                .departureDate(DataGenerator.nextMondayDateAsString())
                .build();
    }
}
