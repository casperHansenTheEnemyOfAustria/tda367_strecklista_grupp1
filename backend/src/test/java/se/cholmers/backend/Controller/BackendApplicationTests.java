package se.cholmers.backend.Controller;

import org.junit.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import se.cholmers.backend.AdminController;
import se.cholmers.backend.BackendApplication;
import se.cholmers.backend.UserController;

@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.MOCK,
	classes = BackendApplication.class)
  @AutoConfigureMockMvc
  @TestPropertySource(
	locations = "classpath:application-integrationtest.properties")
class BackendApplicationTests {

	@Autowired
    private MockMvc mvc;

	@Autowired
	private AdminController adminController;

	@Test
	void contextLoads() {
	}

	@Test
	void testLogin() throws Exception {
		mvc.perform(get("/login/1234/1234")).andExpect(status().isOk());
	}

}
