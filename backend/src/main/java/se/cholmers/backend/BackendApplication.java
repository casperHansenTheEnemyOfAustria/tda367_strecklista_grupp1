package se.cholmers.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import se.cholmers.backend.Model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	// @Bean
	// public UserController userController() {
	// 	return new UserController(programState());
	// }
	// @Bean
	// public ProgramState programState() {
	// 	return new ProgramState();
	// }
}


@Configuration
class AppConfig {
    @Bean
    public ProgramState programState() {
		return new ProgramState();
	}
}