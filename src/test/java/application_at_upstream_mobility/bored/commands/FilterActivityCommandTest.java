package application_at_upstream_mobility.bored.commands;

import application_at_upstream_mobility.bored.controller.ActivityController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class FilterActivityCommandTest {

    @Mock
    private ActivityController activityController;

    @InjectMocks
    private FilterActivityCommand filterActivityCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRandomActivity() {
        String activitiesJson = "[\"Learn Express.js\", \"Learn to greet someone in a new language\"]";
        ResponseEntity<String> responseEntity = ResponseEntity.ok(activitiesJson);
        when(activityController.getFilterActivity("education")).thenReturn(responseEntity);

        String activity = filterActivityCommand.education();
        assertNotNull(activity);
    }
}