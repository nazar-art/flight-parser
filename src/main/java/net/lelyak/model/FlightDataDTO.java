package net.lelyak.model;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

/**
 * @author Nazar Lelyak.
 */
@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDataDTO implements BaseDTO {

    private LocalTime departureTime;
    private String destination;
    private String destinationAirport;
    private String flightNumber;
    private String isDeparted;
    private Set<DayOfWeek> days = Sets.newHashSet();
}
