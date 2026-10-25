import javax.swing.*;

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
        accelVal = String.format("%.2f",model.getAccelNumValue());
        forceVal = String.format("%.2f",model.getForceNumValue());
        timePVal = model.getTimePVal();

        angularVel.setText("ω = v / r  :  " + ballSpeed + " / " + pathRad + " = " + angVelVal + " rad•s⁻¹");
        acceleration.setText("a = v² / r  :  " + ballSpeed + "² / " + pathRad + " = " + accelVal + " m•s⁻²");
        force.setText("F = m•v² / r  :  " + ballMass + "•" + ballSpeed + "²" + " / " + pathRad + " = " + forceVal + " N");
        timePeriod.setText("T = 1 / f = 2πr / v  :  " + "2π•" + pathRad + " / " + ballSpeed + " = " + timePVal + " s");
    }
}