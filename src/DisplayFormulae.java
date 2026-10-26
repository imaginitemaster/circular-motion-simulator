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

    public DisplayFormulae(SimulationModel model){
        this.model = model;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        angularVel.setAlignmentX(Component.LEFT_ALIGNMENT);
        acceleration.setAlignmentX(Component.LEFT_ALIGNMENT);
        force.setAlignmentX(Component.LEFT_ALIGNMENT);
        timePeriod.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(angularVel);
        add(acceleration);
        add(force);
        add(timePeriod);
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
            accelVal =String.format("%.2f",model.getAccelNumValue()) + " m•s⁻²";
            forceVal = String.format("%.2f",model.getForceNumValue()) + " N";
        }

        angularVel.setText("ω = v / r  :  " + angVelVal);
        acceleration.setText("a = v² / r  :  " + accelVal);
        force.setText("F = m•v² / r  :  " + forceVal);
        timePeriod.setText("T = 1 / f = 2πr / v  :  " + timePVal);
    }
}