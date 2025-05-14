package greed;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.*;
import mvc.Command;
import simstation.*;

public class GreedPanel extends SimulationPanel implements ChangeListener {
    private JSlider greedSlider, growBackRateSlider, moveEnergySlider;

    public GreedPanel(SimStationFactory factory) {
        super(factory);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));

        greedSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, ((GreedSimulation) model).getGreed());
        greedSlider.setMajorTickSpacing(10);
        greedSlider.setMinorTickSpacing(5);
        greedSlider.setPaintTicks(true);
        greedSlider.setPaintLabels(true);
        greedSlider.addChangeListener(this);

        growBackRateSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, ((Meadow) model).getGrowBackRate());
        growBackRateSlider.setMajorTickSpacing(2);
        growBackRateSlider.setPaintTicks(true);
        growBackRateSlider.setPaintLabels(true);
        growBackRateSlider.addChangeListener(this);

        moveEnergySlider = new JSlider(JSlider.HORIZONTAL, 0, 50, ((Meadow) model).getMoveEnergy());
        moveEnergySlider.setMajorTickSpacing(10);
        moveEnergySlider.setMinorTickSpacing(5);
        moveEnergySlider.setPaintTicks(true);
        moveEnergySlider.setPaintLabels(true);
        moveEnergySlider.addChangeListener(this);

        sliderPanel.add(addSliderLabel("Greed:", greedSlider));
        sliderPanel.add(addSliderLabel("Grow Back Rate:", growBackRateSlider));
        sliderPanel.add(addSliderLabel("Move Energy:", moveEnergySlider));

        controlPanel.add(sliderPanel, BorderLayout.NORTH);
    }

    private JPanel addSliderLabel(String labelText, JSlider slider) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label, BorderLayout.NORTH);
        panel.add(slider, BorderLayout.CENTER);
        return panel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        GreedSimulation greedSimulation = (GreedSimulation) model;

        if(e.getSource() == greedSlider) {
            greedSimulation.setGreed(greedSlider.getValue());
        } else if(e.getSource() == growBackRateSlider) {
            ((Meadow)model).setGrowBackRate(growBackRateSlider.getValue());
        } else if(e.getSource() == moveEnergySlider) {
            ((Meadow)model).setMoveEnergy(moveEnergySlider.getValue());
        }
        model.changed();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Stats")) {
            GreedFactory newFactory = (GreedFactory) factory;
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
        GreedSimulation greedSimulation = (GreedSimulation) model;

        greedSlider.setValue(greedSimulation.getGreed());
        growBackRateSlider.setValue(((Meadow)model).getGrowBackRate());
        moveEnergySlider.setValue(((Meadow)model).getMoveEnergy());
    }
}
