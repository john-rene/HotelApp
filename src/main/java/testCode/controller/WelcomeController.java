package testCode.controller;

import testCode.service.WelcomeMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/welcome")
public class WelcomeController {

    private final WelcomeMessageService welcomeMessageService;

    public WelcomeController(WelcomeMessageService welcomeMessageService) {
        this.welcomeMessageService = welcomeMessageService;
    }

    @GetMapping
    public Map<String, String> getWelcomeMessages() {
        Map<String, String> messages = new HashMap<>();

        // Retrieve welcome messages for English and French
        String englishMessage = welcomeMessageService.getWelcomeMessage(new Locale("en", "US"));
        String frenchMessage = welcomeMessageService.getWelcomeMessage(new Locale("fr", "CA"));

        // Store messages in the map
        messages.put("english", englishMessage);
        messages.put("french", frenchMessage);

        return messages;
    }
}
