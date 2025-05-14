package plague;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.*;
import mvc.Command;
import simstation.*;

public class PlaguePanel extends SimulationPanel implements ChangeListener {
    // based on Tournament code example in lecture
    private JSlider populationSlider, virulenceSlider, infectionLengthSlider, initialInfectedSlider;
    private JButton fatalToggle;

    public PlaguePanel(SimStationFactory factory) {
        super(factory);
        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
        sliderPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        populationSlider = new JSlider(JSlider.HORIZONTAL, 10, 250, PlagueSimulation.getPopulation());
        populationSlider.setMajorTickSpacing(25);
        populationSlider.setMinorTickSpacing(10);
        populationSlider.setPaintTicks(true);
        populationSlider.setPaintLabels(true);
        populationSlider.setLabelTable(populationSlider.createStandardLabels(50));
        populationSlider.addChangeListener(this);

        virulenceSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, PlagueSimulation.getVirulence());
        virulenceSlider.setMajorTickSpacing(10);
        virulenceSlider.setMinorTickSpacing(5);
        virulenceSlider.setPaintTicks(true);
        virulenceSlider.setPaintLabels(true);
        virulenceSlider.setLabelTable(virulenceSlider.createStandardLabels(20));
        virulenceSlider.addChangeListener(this);

        infectionLengthSlider = new JSlider(JSlider.HORIZONTAL, 1, 60, PlagueSimulation.getInfectionLength());
        infectionLengthSlider.setMajorTickSpacing(10);
        infectionLengthSlider.setMinorTickSpacing(5);
        infectionLengthSlider.setPaintTicks(true);
        infectionLengthSlider.setPaintLabels(true);
        infectionLengthSlider.setLabelTable(infectionLengthSlider.createStandardLabels(30));
        infectionLengthSlider.addChangeListener(this);
        
        initialInfectedSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, PlagueSimulation.getInitialInfected());
        initialInfectedSlider.setMajorTickSpacing(20);
        initialInfectedSlider.setMinorTickSpacing(5);
        initialInfectedSlider.setPaintTicks(true);
        initialInfectedSlider.setPaintLabels(true);
        initialInfectedSlider.setLabelTable(initialInfectedSlider.createStandardLabels(20));
        initialInfectedSlider.addChangeListener(this);
       
        fatalToggle = new JButton();
        updateFatalButton();
        fatalToggle.addActionListener(this);

        sliderPanel.add(addSliderLabel("Initial % Infected:", initialInfectedSlider));
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sliderPanel.add(addSliderLabel("Infection Probability %:", virulenceSlider));
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sliderPanel.add(addSliderLabel("Initial Population Size:", populationSlider));
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sliderPanel.add(addSliderLabel("Fatality/Recovery Time:", infectionLengthSlider));
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sliderPanel.add(fatalToggle);

        controlPanel.add(sliderPanel, BorderLayout.CENTER);
    }

    private JPanel addSliderLabel(String labelText, JSlider slider) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label, BorderLayout.NORTH);
        panel.add(slider, BorderLayout.CENTER);
        return panel;
    }

    private void updateFatalButton() {
        if (PlagueSimulation.getFatal()) {
            fatalToggle.setText("Fatal");
        } else {
            fatalToggle.setText("Not Fatal");
        }
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == populationSlider) {
            PlagueSimulation.setPopulation(populationSlider.getValue());
        } else if (e.getSource() == virulenceSlider) {
            PlagueSimulation.setVirulence(virulenceSlider.getValue());
        } else if (e.getSource() == infectionLengthSlider) {
            PlagueSimulation.setInfectionLength(infectionLengthSlider.getValue());
        } else if (e.getSource() == initialInfectedSlider) {
            PlagueSimulation.setInitialInfected(initialInfectedSlider.getValue());
        }
        model.changed();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fatalToggle) {
            Command cmd = factory.makeEditCommand(model, "Toggle Fatal", fatalToggle);
            try {
                cmd.execute();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            model.changed();
        } 
        else if(e.getActionCommand().equals("Stats")) {
            PlagueFactory newFactory = (PlagueFactory) factory;
            Command statsCmd = newFactory.makeEditCommand(model, "Stats", null);
            try {
                statsCmd.execute();
            } catch(Exception exception) {
                exception.printStackTrace();
            }
        }
        else super.actionPerformed(e);
    }
    
    @Override
    public void update() {
        super.update();
        populationSlider.setValue(PlagueSimulation.getPopulation());
        virulenceSlider.setValue(PlagueSimulation.getVirulence());
        infectionLengthSlider.setValue(PlagueSimulation.getInfectionLength());
        initialInfectedSlider.setValue(PlagueSimulation.getInitialInfected());
        updateFatalButton();
        
    }
}






