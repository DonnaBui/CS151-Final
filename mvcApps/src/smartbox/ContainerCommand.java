package smartbox;

import mvc.*;

public class ContainerCommand extends Command {
    String cmd;

    public ContainerCommand(Model model, String cmd) {
        super(model);
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        try {
            String name = Utilities.ask("Enter component name to " + cmd.toLowerCase());
            Container c = (Container) model;
            switch(cmd) {
                case "Add" -> c.addComponent(name);
                case "Remove" -> c.remComponent(name);
                case "Run" -> c.launch(name);
            }
        } catch (Exception e) {
            Utilities.error(e);
        }
    }
}     

