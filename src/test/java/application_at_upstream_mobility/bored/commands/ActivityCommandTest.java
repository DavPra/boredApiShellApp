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

public class ActivityCommandTest {

    @Mock
    private ActivityController activityController;

    @InjectMocks
    private ActivityCommand activityCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testActivity() {
        String activityJson = "{\"activity\":\"Learn Express.js\"}";
        ResponseEntity<String> responseEntity = ResponseEntity.ok(activityJson);
        when(activityController.getActivity()).thenReturn(responseEntity);

        String activity = activityCommand.activity();
        assertNotNull(activity);
    }
}