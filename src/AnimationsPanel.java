import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;

public class AnimationsPanel extends JPanel{

    //Makes an instance of the model to pull variables from
    SimulationModel model;
    DisplayFormulae formulae;

    //ball's mass, speed and path radius
    double ballMass;
    double ballSpeed;
    double pathRadius;

    Information dialInfo;

    public AnimationsPanel(SimulationModel model, DisplayFormulae formulae){
        this.setLayout(null);
        this.model = model;
        this.formulae = formulae;

        timer.start();
        model.setAngle(0);
        this.repaint();

        ballMass = model.getBallMass();
        ballSpeed = model.getBallSpeed();
        pathRadius = model.getPathRad();

        dialInfo = new Information("Dial Info","Dials: The dials here display the magnitude of different values present in the simulation as well as the direction those values point","Dials");
        dialInfo.setBounds(870,29,20,20);

        this.add(dialInfo);
        this.add(velLabel);
        this.add(accLabel);
        this.add(forLabel);
        this.add(velVal);
        this.add(accVal);
        this.add(forVal);
    }

    //In this code, any variable starting with "c" refers to centre

    //radlength max = 280
    private int radLength = (int)(28*pathRadius);
    private int cX = 600;
    private int cY = 400;
    private int cDiam = 20;
    boolean drawVelo = true;

    double sin;
    double cos;

    int[] ballPos = new int[2];

    JLabel velLabel = new JLabel();
    JLabel accLabel = new JLabel();
    JLabel forLabel = new JLabel();

    JLabel velVal = new JLabel();
    JLabel accVal = new JLabel();
    JLabel forVal = new JLabel();

    BasicStroke one = new BasicStroke(1f);
    BasicStroke two = new BasicStroke(2f);
    BasicStroke four = new BasicStroke(4f);
    BasicStroke three = new BasicStroke(3f);

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D paint = (Graphics2D) g;
        radLength = 28*(int)pathRadius;
        sin = model.getSin();
        cos = model.getCos();

        //Draw Big circle showing ball's path
        paint.setStroke(one);
        paint.setColor(Color.black);
        paint.drawOval(cX-radLength,cY-radLength,radLength*2,radLength*2);

        //Acceleration Arrow
        if(model.getDrawAccel()){
            paint.setStroke(four);
            paint.setColor(Color.MAGENTA);
            //accel should scale off velocity² and radius
            //in length, 3 represents the number of pixels per m•s⁻²
            DrawArrow accel = new DrawArrow(cX+ radLength*cos,cY - radLength*sin,-cos,sin, (double) (3*ballSpeed * ballSpeed) /pathRadius,paint);
        }


        //Velocity Arrow
        if(drawVelo){
            paint.setStroke(three);
            paint.setColor(Color.ORANGE);
            //change end point to vary based on velocity
            //in length, 10 represents number of pixels per m•s⁻¹
            DrawArrow velo = new DrawArrow(model.getBallPos()[0],model.getBallPos()[1],-sin,-cos,10*ballSpeed,paint);
        }

        //Draw radial line
        paint.setStroke(two);
        paint.setColor(Color.black);
        paint.drawLine(cX,cY, (int) (cX+ radLength*cos), (int) (cY - radLength*sin));

        //Draw centre
        paint.setStroke(two);
        paint.setColor(Color.BLUE);
        paint.fillOval(cX-cDiam/2,cY-cDiam/2,cDiam,cDiam);

        int ballWidth = (int)(15+ballMass);
        //Draw Ball
        paint.setStroke(two);
        paint.setColor(Color.GRAY);
        paint.fillOval((int) (model.getBallPos()[0]- 0.5 * ballWidth), (int) (model.getBallPos()[1]- 0.5 * ballWidth), ballWidth,ballWidth);

        //dialX is the x position of the first dial
        int dialX = 385;

        //Velocity dial
        paint.setStroke(two);
        paint.setColor(Color.BLACK);
        paint.drawOval(dialX,4,70,70);
        paint.setStroke(three);
        paint.setColor(Color.ORANGE);
        DrawArrow velDial = new DrawArrow(dialX+35,39,-sin,-cos,25,paint);
        paint.fillOval(dialX+32,36,6,6);

        //Acceleration Dial
        paint.setStroke(two);
        paint.setColor(Color.BLACK);
        paint.drawOval(dialX+180,4,70,70);
        paint.setStroke(three);
        paint.setColor(Color.MAGENTA);
        if(model.getDrawAccelDial()){DrawArrow accelDial = new DrawArrow(dialX+215,39,-cos,sin,25,paint);}
        paint.fillOval(dialX+212,36,6,6);

        //Force Dial
        paint.setStroke(two);
        paint.setColor(Color.BLACK);
        paint.drawOval(dialX+360,4,70,70);
        paint.setStroke(three);
        paint.setColor(Color.DARK_GRAY);
        if(model.getDrawForceDial()){DrawArrow forceDial = new DrawArrow(dialX+395,39,-cos,sin,25,paint);}
        paint.fillOval(dialX+392,36,6,6);


        velLabel.setText("Velocity");
        accLabel.setText("Acceleration");
        forLabel.setText("Centripetal Force");

        velLabel.setBounds(dialX+12,75,100,20);
        accLabel.setBounds(dialX+181,75,100,20);
        forLabel.setBounds(dialX+350,75,100,20);

        //m•s⁻¹
        //m•s⁻²
        velVal.setText(String.format("%.2f",(double)ballSpeed)+" m•s⁻¹");
        accVal.setText(String.format("%.2f",model.getAccelNumValue())+" m•s⁻²");
        forVal.setText(String.format("%.2f",model.getForceNumValue())+ " N");

        velVal.setBounds(dialX+75,24,80,30);
        accVal.setBounds(dialX+255,24,80,30);
        forVal.setBounds(dialX+435,24,80,30);
    }

    //This timer runs code at 59 FPS (17ms)
    //you can't get an exact 60FPS here
    Timer timer = new Timer(17,e->{
        ballMass = model.getBallMass();
        ballSpeed = model.getBallSpeed();
        pathRadius = model.getPathRad();
        model.update();
        formulae.update();
        repaint();
    });
}
