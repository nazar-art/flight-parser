package net.lelyak.model;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

/**
 * @author Nazar Lelyak.
 */
@Data
@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightDataDTO implements BaseDTO {

    private LocalTime departureTime;
    private String destination;
    private String destinationAirport;
    private String flightNumber;

    private Set<DayOfWeek> days = Sets.newHashSet();

    public static boolean isDeparted(FlightDataDTO dto) {
        LocalDateTime today = LocalDateTime.now();
//        DayOfWeek dayOfWeek = today.getDayOfWeek();

        boolean result = dto.getDepartureTime().isBefore(today.toLocalTime());
        log.info("FLIGHT_TIME: {} is before: {} today: {}", dto.getDepartureTime(), result, today);
        return result;
    }
}
