package se.cholmers.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import se.cholmers.backend.Model.StateManager;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		StateManager stateManager = StateManager.getInstance();
		SpringApplication.run(BackendApplication.class, args);
	}

}
