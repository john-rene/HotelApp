package testCode.service;

import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class WelcomeMessageService {

    public String getWelcomeMessage(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("welcome", locale);
        return bundle.getString("welcome");
    }
}
