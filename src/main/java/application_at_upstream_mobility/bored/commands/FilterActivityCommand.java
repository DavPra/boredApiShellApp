package application_at_upstream_mobility.bored.commands;

import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import application_at_upstream_mobility.bored.controller.ActivityController;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;

@ShellComponent
public class FilterActivityCommand {

    private final ActivityController activityController;

    @Autowired
    public FilterActivityCommand(ActivityController activityController) {
        this.activityController = activityController;
    }

    /*education, recreational, social, charity, cooking, relaxation, busywork*/

    @ShellMethod(key = "education", value = "Get an educational activity.")
    public String education() {return activityController.getFilterActivity("education").getBody().toString();
    }

   /* @ShellMethod(key = "recreational", value = "Get a recreational activity.")
    public String recreation() {return activityController.getFilterActivity("recreational").getBody();
    }

    @ShellMethod(key = "social", value = "Get a social activity.")
    public String social() {return activityController.getFilterActivity("social").getBody();
    }

    @ShellMethod(key = "charity", value = "Get a charitable activity.")
    public String charity() {return activityController.getFilterActivity("charity").getBody();
    }

    @ShellMethod(key = "cooking", value = "Get a cooking activity.")
    public String cooking() {return activityController.getFilterActivity("cooking").getBody();
    }

    @ShellMethod(key = "relaxation", value = "Get a relaxed activity.")
    public String relaxation() {return activityController.getFilterActivity("relaxation").getBody();
    }

    @ShellMethod(key = "busywork", value = "Get a busywork activity.")
    public String busywork() {return activityController.getFilterActivity("busywork").getBody();
    } */
}

