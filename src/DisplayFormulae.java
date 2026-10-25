import javax.swing.*;

public class DisplayFormulae extends JPanel {
    JLabel angularVel = new JLabel();
    JLabel acceleration = new JLabel();
    JLabel force = new JLabel();
    JLabel timePeriod = new JLabel();

    double ballMass;
    double ballSpeed;
    double pathRad;

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
        angularVel.setText("ω = v / r  :  " + ballSpeed + " / " + pathRad + " = " + ballSpeed/pathRad + " rad•s⁻¹");
        acceleration.setText("a = v² / r  :  " + ballSpeed + "² / " + pathRad + " = " + ballSpeed*ballSpeed/pathRad + " m•s⁻²");
        force.setText("F = m•v² / r  :  " + ballMass + "•" + ballSpeed + "²" + " / " + pathRad + " = " + ballMass*ballSpeed*ballSpeed/pathRad + " N");
        timePeriod.setText("T = 1 / f = 2πr / v  :  " + "2π•" + pathRad + " / " + ballSpeed + " = " + 2*Math.PI*pathRad/ballSpeed + " s");
    }
}