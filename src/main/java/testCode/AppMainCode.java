package testCode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Locale;
import java.util.ResourceBundle;

@SpringBootApplication
public class AppMainCode {

	public static void main(String[] args) {
		SpringApplication.run(AppMainCode.class, args);

		// Create and start threads for each language
		Thread englishThread = new Thread(() -> displayWelcomeMessage(new Locale("en", "US")));
		Thread frenchThread = new Thread(() -> displayWelcomeMessage(new Locale("fr", "CA")));

		englishThread.start();
		frenchThread.start();

		try {
			englishThread.join();
			frenchThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void displayWelcomeMessage(Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("welcome", locale);
		System.out.println("[" + locale.getDisplayLanguage() + "] " + bundle.getString("welcome"));
	}
}
