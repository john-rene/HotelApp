package testCode.service;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TimeConversion {

    public String convertAndFormatTimes(String time) {
        // Create a DateTimeFormatter for formatting the time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        // Create ZonedDateTime for Eastern Time (ET)
        ZonedDateTime etTime = ZonedDateTime.parse(time).withZoneSameInstant(ZoneId.of("America/New_York"));

        // Convert and format to Mountain Time (MT)
        ZonedDateTime mtTime = etTime.withZoneSameInstant(ZoneId.of("America/Denver"));

        // Convert and format to Coordinated Universal Time (UTC)
        ZonedDateTime utcTime = etTime.withZoneSameInstant(ZoneId.of("UTC"));

        // Format each time zone and add them to an array of strings
        List<String> formattedTimes = new ArrayList<>();
        formattedTimes.add("ET: " + etTime.format(formatter));
        formattedTimes.add("MT: " + mtTime.format(formatter));
        formattedTimes.add("UTC: " + utcTime.format(formatter));

        // Join the array of strings into a single string separated by commas
        return String.join(", ", formattedTimes);
    }
}

