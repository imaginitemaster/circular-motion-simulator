import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel implements ChangeListener, ActionListener {
    JSlider massSlider;
    JSlider speedSlider;
    JSlider radSlider;

    JButton resetButton;
    JButton releaseBall;

    JLabel massLabel;
    JLabel speedLabel;
    JLabel radLabel;

    Dimension sliderSize = new Dimension(210,80);
    Dimension buttonSize = new Dimension(30,30);

    SimulationModel model;

    Information settingsInfo;

    public SettingsPanel(SimulationModel model){
        this.model = model;

        ImageIcon resetIcon = new ImageIcon(Main.class.getResource("restart.png"));
        ImageIcon releaseIcon = new ImageIcon(Main.class.getResource("release.png"));

        settingsInfo = new Information("Settings Info","Sliders:\nThe sliders change the values related to the ball. They change its mass, speed and the radius of its path.\nRelease Button:\nThe release button releases the ball from the circular motion, allowing it to move in the same way it would after being released in real life   \nReset Button:\nThe reset button resets the values of the sliders to how they started.\n\nBall Release:\n- After releasing the ball, pressing the reset button will return the ball to circular motion instead of resetting values\n- After releasing the ball, the release button and sliders will be locked until after returning to circular motion","Settings");

        //create resetButton
        resetButton = new JButton();
        resetButton.setPreferredSize(buttonSize);
        resetButton.addActionListener(this);
        resetButton.setIcon(resetIcon);

        //create releaseBall button
        releaseBall = new JButton();
        releaseBall.setPreferredSize(buttonSize);
        releaseBall.addActionListener(this);
        releaseBall.setIcon(releaseIcon);

        //Give sliders mins and maxes
        massSlider = new JSlider(1,10,1);
        speedSlider = new JSlider(1,15,10);
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
        this.add(releaseBall);
        this.add(resetButton);
        this.add(settingsInfo);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        //if mass, speed or radius are changed, change the values in model
        if(e.getSource()==massSlider){
            model.setBallMass(massSlider.getValue());
        }
        if(e.getSource()==speedSlider){
            model.setBallSpeed(speedSlider.getValue());
        }
        if(e.getSource()==radSlider){
            model.setPathRad(radSlider.getValue());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==resetButton){
            if(model.getCircularPath()){
                //if reset is pressed, reset sliders and the model's values.
                model.setBallMass(1);
                model.setBallSpeed(10);
                model.setPathRad(10);
                massSlider.setValue(1);
                speedSlider.setValue(10);
                radSlider.setValue(10);
            }else{
                model.setCircularPath(true);
                model.setDrawAccel(true);
                massSlider.setEnabled(true);
                speedSlider.setEnabled(true);
                radSlider.setEnabled(true);
                releaseBall.setEnabled(true);
            }
        }
        if(e.getSource()==releaseBall){
            model.setCircularPath(false);
            model.setPauseLinear(false);
            massSlider.setEnabled(false);
            speedSlider.setEnabled(false);
            radSlider.setEnabled(false);
            releaseBall.setEnabled(false);
        }
    }
}