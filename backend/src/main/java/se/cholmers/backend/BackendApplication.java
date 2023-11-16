package se.cholmers.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import se.cholmers.backend.Model.StateManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		StateManager stateManager = StateManager.getInstance();
		SpringApplication.run(BackendApplication.class, args);
	}

	// @Bean
	// public UserController userController() {
	// 	return new UserController(StateManager());
	// }
	// @Bean
	// public StateManager StateManager() {
	// 	return new StateManager();
	// }
}


@Configuration
class AppConfig {


    @Bean
	//change pstate to model/application
    public StateManager stateManager() {
		return StateManager.getInstance();
	}

	@Bean
	public DatabaseInterface databaseInterface() {
		return DatabaseInterface.getInstance();
	}
}