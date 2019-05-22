package net.lelyak.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lelyak.model.BaseDTO;
import net.lelyak.reader.Reader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Nazar Lelyak.
 */
@Slf4j
@Controller
@AllArgsConstructor
public class MainController {

    private Reader reader;

    /**
     * List all flights.
     */
    @GetMapping("/")
    public String flightsInfo(Model model) {
        List<? extends BaseDTO> sortedFlights = reader.parseFile();
        LocalDate today = LocalDate.now();
        DayOfWeek day = today.getDayOfWeek();

        model.addAttribute("flights", sortedFlights);
        model.addAttribute("date", today);
        model.addAttribute("day", day);

        log.debug("Flights are returned");
        return "/info";
    }


    @GetMapping("/error")
    public String error403() {
        log.error("Error occurred");
        return "/error";
    }
}
