package se.cholmers.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		StateManager stateManager = StateManager.getInstance();
		SpringApplication.run(BackendApplication.class, args);
	}

}
