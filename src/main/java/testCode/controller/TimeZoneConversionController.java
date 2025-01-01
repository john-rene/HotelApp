package testCode.controller;

import testCode.service.TimeConversion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeZoneConversionController {

    private final TimeConversion timeConversion;

    public TimeZoneConversionController() {
        this.timeConversion = new TimeConversion();
    }

    @GetMapping("/api/convert-time")
    public String getConvertedTimes(@RequestParam String time) {
        return timeConversion.convertAndFormatTimes(time);
    }
}
