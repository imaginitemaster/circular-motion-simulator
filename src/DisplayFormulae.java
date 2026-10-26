import javax.swing.*;
import java.awt.*;

public class DisplayFormulae extends JPanel {
    JLabel angularVel = new JLabel();
    JLabel acceleration = new JLabel();
    JLabel force = new JLabel();
    JLabel timePeriod = new JLabel();

    double ballMass;
    double ballSpeed;
    double pathRad;

    String angVelVal;
    String accelVal;
    String forceVal;
    String timePVal;

    SimulationModel model;
    Information info;

    public DisplayFormulae(SimulationModel model){
        this.model = model;
        info = new Information("Formulae Info","These formulae are the same as what is provided in the formula booklet (The time period formula is an exception, being a variation of ω=2πf)\nThe formulae update live to the changes in the sliders to help show how they relate\n\nTeaching Note:\nWhen the ball is released, centripetal acceleration (a), centripetal force (F), angular velocity (ω) and time period (T) all become N/A since they only \nexist for circular motion (which doesn't occur when the ball is released)","Formulae");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        angularVel.setAlignmentX(Component.LEFT_ALIGNMENT);
        acceleration.setAlignmentX(Component.LEFT_ALIGNMENT);
        force.setAlignmentX(Component.LEFT_ALIGNMENT);
        timePeriod.setAlignmentX(Component.LEFT_ALIGNMENT);

        angularVel.setForeground(Color.BLACK);
        acceleration.setForeground(Color.MAGENTA);
        force.setForeground(Color.DARK_GRAY);
        timePeriod.setForeground(Color.BLACK);

        add(angularVel);
        add(acceleration);
        add(force);
        add(timePeriod);
        add(info);
    }

    public void update(){
        ballSpeed = model.getBallSpeed();
        ballMass = model.getBallMass();
        pathRad = model.getPathRad();

        angVelVal = model.getAngVelVal();
        timePVal = model.getTimePVal();

        if(model.getCircularPath()){
            accelVal = ballSpeed + "² / " + pathRad + " = " + String.format("%.2f",model.getAccelNumValue()) + " m•s⁻²";
            forceVal = ballMass + "•" + ballSpeed + "²" + " / " + pathRad + " = " + String.format("%.2f",model.getForceNumValue()) + " N";
        }else{
            accelVal = "N/A";
            forceVal = "N/A";
        }

        angularVel.setText("ω = v / r  :  " + angVelVal);
        acceleration.setText("a = v² / r  :  " + accelVal);
        force.setText("F = m•v² / r  :  " + forceVal);
        timePeriod.setText("T = 1 / f = 2πr / v  :  " + timePVal);
    }
}