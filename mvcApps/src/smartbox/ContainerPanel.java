package smartbox;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import javax.swing.*;
import mvc.*;

public class ContainerPanel extends AppPanel {

	public ContainerPanel(AppFactory factory) {
		super(factory);
		controlPanel.setLayout(new GridLayout(3, 1, 10, 10));

		String[] commands = factory.getEditCommands();
		for (String cmd : commands) {
			JPanel subPanel = new JPanel(); // Prevent buttons from getting stretched out by gridlayout
            subPanel.setOpaque(false);
			JButton button = new JButton(cmd);
			subPanel.add(button, BorderLayout.CENTER);
			button.addActionListener(this);
			controlPanel.add(subPanel);
		}

		this.setLayout((new GridLayout(1, 2)));
        this.add(controlPanel);
        this.add(view);

	}

    @Override    // this override needed to re-initialize component fields table: 
	public void setModel(Model m) {
           super.setModel(m);
           ((Container) m).initContainer(); // restore fields of components
        }

	@Override
	public void update() {
		ContainerView cview = (ContainerView) view;
		cview.update();
	}
	public static void main(String[] args) {
		AppFactory factory = new ContainerFactory();
        AppPanel panel = new ContainerPanel(factory);
        panel.display();
	}
}