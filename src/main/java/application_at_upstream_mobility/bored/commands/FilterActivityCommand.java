package application_at_upstream_mobility.bored.commands;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import application_at_upstream_mobility.bored.controller.ActivityController;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@ShellComponent
public class FilterActivityCommand {

    private final ActivityController activityController;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Random random = new Random();

    @Autowired
    public FilterActivityCommand(ActivityController activityController) {
        this.activityController = activityController;
    }

    @ShellMethod(key = "education", value = "Get an educational activity.")
    public String education() {
        return getRandomActivity("education");
    }

    @ShellMethod(key = "recreational", value = "Get a recreational activity.")
    public String recreation() {
        return getRandomActivity("recreational");
    }

    @ShellMethod(key = "social", value = "Get a social activity.")
    public String social() {
        return getRandomActivity("social");
    }

    @ShellMethod(key = "charity", value = "Get a charitable activity.")
    public String charity() {
        return getRandomActivity("charity");
    }

    @ShellMethod(key = "cooking", value = "Get a cooking activity.")
    public String cooking() {
        return getRandomActivity("cooking");
    }

    @ShellMethod(key = "relaxation", value = "Get a relaxed activity.")
    public String relaxation() {
        return getRandomActivity("relaxation");
    }

    @ShellMethod(key = "busywork", value = "Get a busywork activity.")
    public String busywork() {
        return getRandomActivity("busywork");
    }

    private String getRandomActivity(String filter) {
        String activitiesJson = activityController.getFilterActivity(filter).getBody();
        try {
            List<String> activities = objectMapper.readValue(activitiesJson, new TypeReference<List<String>>() {});
            return activities.get(random.nextInt(activities.size()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse activities JSON", e);
        }
    }
}