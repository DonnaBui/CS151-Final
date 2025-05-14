package simstation;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import mvc.*;


public class SimulationPanel extends AppPanel {

    public SimulationPanel(AppFactory factory) {
        super(factory); 
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 0));
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(this);
        buttonsPanel.add(startButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JButton suspendButton = new JButton("Suspend");
        suspendButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        suspendButton.addActionListener(this);
        buttonsPanel.add(suspendButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JButton resumeButton = new JButton("Resume");
        resumeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resumeButton.addActionListener(this);
        buttonsPanel.add(resumeButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JButton stopButton = new JButton("Stop");
        stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        stopButton.addActionListener(this);
        buttonsPanel.add(stopButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JButton statsButton = new JButton("Stats");
        statsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsButton.addActionListener(this);
        buttonsPanel.add(statsButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        controlPanel.add(buttonsPanel, BorderLayout.CENTER);
    }

    @Override
    public void setModel(Model model) {
        super.setModel(model); // calling AppPanel.setModel(m)
        Simulation s = (Simulation)model;
        for (Agent a : s.getAgents()) {
            Thread t = new Thread(a);
            t.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        Simulation simulation = (Simulation) model;
        if ((command.equals("Save") || command.equals("SaveAs")) && simulation.running() && !simulation.suspended()) {
            Utilities.error("Simulation must be suspended before saving");
            return;
        }
        super.actionPerformed(e);
    }

}
