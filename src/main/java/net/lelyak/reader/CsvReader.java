package net.lelyak.reader;

import au.com.bytecode.opencsv.CSVReader;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import net.lelyak.model.BaseDTO;
import net.lelyak.model.FlightDataDTO;
import net.lelyak.utills.FileLocation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

/**
 * @author Nazar Lelyak.
 */
@Slf4j
@Component
public class CsvReader implements Reader {

    @Autowired
    private FileLocation locationProperties;

    @Override
    public String getFilePath() {
//        return locationProperties.getFile();
        return "src/main/resources/data/flights.csv";
    }

    @Override
    public List<FlightDataDTO> parseFile() {
        log.info("FILE PARSING START");
        List<FlightDataDTO> flightData = Lists.newArrayList();
        String csvFile = getFilePath();

        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;

            reader.readNext(); // skip the first column line

            while ((line = reader.readNext()) != null) {
                var dto = FlightDataDTO.builder()
                        .departureTime(parseTime(line[0]))
                        .destination(line[1])
                        .destinationAirport(line[2])
                        .flightNumber(line[3])
                        .days(parseDays(line[4], line[5], line[6], line[7], line[8], line[9], line[10]))
                        .build();

                flightData.add(dto);
            }
        } catch (IOException e) {
            log.error("Error during parsing CSV file: {}", e.getMessage());
        }

        // display all flights for the current day and time, in chronological order.
        // Flights that are in the past should be displayed as "Departed"

        LocalDate today = LocalDate.now();
        DayOfWeek todayDayOfWeek = today.getDayOfWeek();

//        flightData.stream()
//                .map(d -> {
//                    if (d.getDays().contains(todayDayOfWeek)) {
//
//                    }
//                })

        log.info("FILE PARSING END");
        return flightData;
    }

    private LocalTime parseTime(String str) {
        LocalTime time = null;
        String[] arr = str.split(":");

        if (arr.length == 2) {
            time = LocalTime.of(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
            return time;
        } else {
            log.error("Departure time is incorrect: {}", str);
        }
        return time;
    }

    private Set<DayOfWeek> parseDays(String sunday, String monday,
                                      String tuesday, String wednesday,
                                      String thursday, String friday,
                                      String saturday) {
        Set<DayOfWeek> result = Sets.newHashSet();

        if (StringUtils.isNotEmpty(sunday)) result.add(DayOfWeek.SUNDAY);
        if (StringUtils.isNotEmpty(monday)) result.add(DayOfWeek.MONDAY);
        if (StringUtils.isNotEmpty(tuesday)) result.add(DayOfWeek.TUESDAY);
        if (StringUtils.isNotEmpty(wednesday)) result.add(DayOfWeek.WEDNESDAY);
        if (StringUtils.isNotEmpty(thursday)) result.add(DayOfWeek.THURSDAY);
        if (StringUtils.isNotEmpty(friday)) result.add(DayOfWeek.FRIDAY);
        if (StringUtils.isNotEmpty(saturday)) result.add(DayOfWeek.SATURDAY);

        return result;
    }


    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Reader reader = new CsvReader();
        List<BaseDTO> baseDTO = (List<BaseDTO>) reader.parseFile();
        baseDTO.forEach(System.out::println);
    }
}
