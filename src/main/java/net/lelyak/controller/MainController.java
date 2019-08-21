package net.lelyak.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lelyak.model.BaseDTO;
import net.lelyak.service.Reader;
import net.lelyak.utills.Clock;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    @GetMapping(value = {"/", "/info", "/info.html", "/flights"})
    public String flightsInfo(Model model) {
        List<? extends BaseDTO> sortedFlights = reader.parseFile();

        log.info("Today's date: {}; time: {}; day: {}",
                Clock.getCurrentDate(), Clock.getCurrentTime(), Clock.getCurrentDay());

        model.addAttribute("flights", sortedFlights);
        model.addAttribute("dateTime", Clock.getCurrentDateTime());
        model.addAttribute("day", Clock.getCurrentDay());

        return "/info";
    }

    /**
     * Handling errors.
     */
    @GetMapping("/error")
    public String error() {
        log.error("Error occurred");
        return "/error";
    }
}
