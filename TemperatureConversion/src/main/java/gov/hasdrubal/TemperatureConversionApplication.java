package gov.hasdrubal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.SessionScope;

@SpringBootApplication
public class TemperatureConversionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemperatureConversionApplication.class, args);
	}

	@Bean
	@SessionScope
	SessionManager sessionManager() {
		return new SessionManager();
	}
}
