package application_at_upstream_mobility.bored;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoredApplicationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	public void setUp() {
		assertNotNull(restTemplate, "TestRestTemplate should be autowired and not null");
	}

	@Test
	@Timeout(value = 10, unit = TimeUnit.SECONDS)
	public void testGetActivity() {
		ResponseEntity<String> response = restTemplate.getForEntity("/activity", String.class);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@Timeout(value = 10, unit = TimeUnit.SECONDS)
	public void testGetFilterActivity() {
		String filter = "education";
		ResponseEntity<String> response = restTemplate.getForEntity("/filterActivity?filter=" + filter, String.class);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}