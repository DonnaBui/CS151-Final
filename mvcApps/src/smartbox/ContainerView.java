package smartbox;
import mvc.*;

import java.awt.BorderLayout;

public class ContainerView extends View {

    private java.awt.List components;

    public ContainerView(Model model) {
        super(model);
        components = new java.awt.List(10);
        this.add(components, BorderLayout.CENTER);
    }

    public void update() {
        components.removeAll();
        for (Component c : ((Container) model).getComponents()) {
            components.add(c.toString());
        }
    }

}