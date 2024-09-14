package application_at_upstream_mobility.bored.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
public class ActivityController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/activity")
    public ResponseEntity<String> getActivity() {
        String url = "https://bored-api.appbrewery.com/random";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String activity = extractActivityFromResponse(response.getBody());
        return ResponseEntity.ok(activity);
    }

    private String extractActivityFromResponse(String responseBody) {
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            return root.path("activity").asText();
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }
}