package smartbox;

import java.io.Serializable;

import mvc.*;

public class ContainerFactory implements AppFactory, Serializable {

 public Model makeModel() {
        return new Container();
    }

    public View makeView(Model model) {
        return new ContainerView(model);
    }

    public String getTitle() {
        return "Smartbox";
    }

    public String[] getHelp() {
        return new String[]{
            "Add - Adds the provided component to the container.",
            "Remove - Removes the provided component from the container.",
            "Run - Runs the provided component if it's an App."
        };
    }

    public String about() {
        return "Smartbox by Donna Bui, CS151 SP25";
    }

    public String[] getEditCommands() {
        return new String[]{"Add", "Remove", "Run"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        return switch (type) { 
            // seems a little bit redundant, but might as well maintain the MVC structure
            case "Add" -> new ContainerCommand(model, type);
            case "Remove" -> new ContainerCommand(model, type);
            case "Run" -> new ContainerCommand(model, type);
            default -> null;
        };
    }
}
