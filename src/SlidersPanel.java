import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlidersPanel extends JPanel implements ChangeListener, ActionListener {
    JSlider massSlider;
    JSlider speedSlider;
    JSlider radSlider;

    JButton resetButton;

    JLabel massLabel;
    JLabel speedLabel;
    JLabel radLabel;

    Dimension sliderSize = new Dimension(210,80);
    Dimension buttonSize = new Dimension(30,30);

    SimulationModel model;

    public SlidersPanel(SimulationModel model){

        this.model = model;

        ImageIcon resetIcon = new ImageIcon(Main.class.getResource("restart.png"));

        //create resetButton
        resetButton = new JButton();
        resetButton.setPreferredSize(buttonSize);
        resetButton.addActionListener(this);
        resetButton.setIcon(resetIcon);

        //Give sliders mins and maxes
        massSlider = new JSlider(1,10,1);
        speedSlider = new JSlider(1,15,5);
        radSlider = new JSlider(5,10,10);

        //give sliders sizes
        massSlider.setPreferredSize(sliderSize);
        speedSlider.setPreferredSize(sliderSize);
        radSlider.setPreferredSize(sliderSize);

        //makes sliders all horizontal
        massSlider.setOrientation(SwingConstants.HORIZONTAL);
        speedSlider.setOrientation(SwingConstants.HORIZONTAL);
        radSlider.setOrientation(SwingConstants.HORIZONTAL);

        //give sliders increments
        massSlider.setPaintTicks(true);
        massSlider.setMinorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setMinorTickSpacing(1);
        radSlider.setPaintTicks(true);
        radSlider.setMinorTickSpacing(1);

        //give sliders major increments
        massSlider.setPaintTrack(true);
        massSlider.setMajorTickSpacing(9);
        speedSlider.setPaintTrack(true);
        speedSlider.setMajorTickSpacing(14);
        radSlider.setPaintTrack(true);
        radSlider.setMajorTickSpacing(5);

        massSlider.setPaintLabels(true);
        speedSlider.setPaintLabels(true);
        radSlider.setPaintLabels(true);

        massLabel = new JLabel("Mass / kg");
        speedLabel = new JLabel("Speed / m•s⁻¹");
        radLabel = new JLabel("Radius / m");

        massSlider.addChangeListener(this);
        speedSlider.addChangeListener(this);
        radSlider.addChangeListener(this);

        this.add(massLabel);
        this.add(massSlider);
        this.add(speedLabel);
        this.add(speedSlider);
        this.add(radLabel);
        this.add(radSlider);
        this.add(resetButton);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        //if mass, speed or radius are changed, change the values in model
        if(e.getSource()==massSlider){model.setBallMass(massSlider.getValue());}
        if(e.getSource()==speedSlider){model.setBallSpeed(speedSlider.getValue());}
        if(e.getSource()==radSlider){model.setPathRad(radSlider.getValue());}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if reset is pressed, reset sliders and the model's values.
        if(e.getSource()==resetButton){
            model.setBallMass(1);
            model.setBallSpeed(5);
            model.setPathRad(10);
            massSlider.setValue(1);
            speedSlider.setValue(5);
            radSlider.setValue(10);
        }
    }
}