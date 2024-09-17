package application_at_upstream_mobility.bored.commands;

import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import application_at_upstream_mobility.bored.controller.ActivityController;
import org.springframework.beans.factory.annotation.Autowired;

@ShellComponent
public class ActivityCommand {

    private final ActivityController activityController;

    @Autowired
    public ActivityCommand(ActivityController activityController) {
        this.activityController = activityController;
    }

    @ShellMethod(key = "activity", value = "Get a random activity")
    public String activity() {return activityController.getActivity().getBody();
    }
}

