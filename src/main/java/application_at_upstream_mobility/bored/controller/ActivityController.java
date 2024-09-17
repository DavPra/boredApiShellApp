package application_at_upstream_mobility.bored.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/filterActivity")
    public ResponseEntity<String> getFilterActivity(String filter) {
        String url = "https://bored-api.appbrewery.com/filter?type=" + filter;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        List<String> activities = extractActivitiesFromResponse(response.getBody());
        try {
            String activitiesJson = objectMapper.writeValueAsString(activities);
            return ResponseEntity.ok(activitiesJson);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert activities to JSON", e);
        }
    }

    private String extractActivityFromResponse(String responseBody) {
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            return root.path("activity").asText();
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }
    private List<String> extractActivitiesFromResponse(String responseBody) {
        try {
            List<Map<String, String>> activitiesList = objectMapper.readValue(responseBody, new TypeReference<List<Map<String, String>>>() {});
            return activitiesList.stream().map(activity -> activity.get("activity")).toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }
}