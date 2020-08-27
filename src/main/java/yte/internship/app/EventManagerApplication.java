package yte.internship.app;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import yte.internship.app.service.EventService;
import yte.internship.app.util.UserPopulator;

import javax.annotation.PostConstruct;
import java.text.ParseException;


@SpringBootApplication
@RequiredArgsConstructor
public class EventManagerApplication {

	private final UserPopulator userPopulator;
	public static void main(String[] args) {
		SpringApplication.run(EventManagerApplication.class, args);
	}

	@PostConstruct
	public void createUser() throws ParseException {
		userPopulator.createUser();
		//eventService.addEvents();

	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowCredentials(true).allowedOrigins("*").allowedMethods("*");
			}
		};
	}
}
