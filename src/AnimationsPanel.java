import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;

public class AnimationsPanel extends JPanel{

    //Makes an instance of the model to pull variables from
    SimulationModel model;

    public AnimationsPanel(SimulationModel model){

        this.model = model;

        timer.start();
        this.setAngle(0);
        this.repaint();

        this.add(velLabel);
        this.add(accLabel);
        this.add(forLabel);
        this.add(velVal);
        this.add(accVal);
        this.add(forVal);
    }

    //In this code, any variable starting with "c" refers to centre

    //ball's mass, speed and path radius
    int ballMass = 1;
    int ballSpeed = 5;
    int pathRadius = 10;

    //radlength max = 280
    private int radLength = 28*pathRadius;
    private int cX = 600;
    private int cY = 400;
    private int cDiam = 20;
    private double angle = 0;
    boolean drawAccel = true;
    boolean drawVelo = true;

    double sin;
    double cos;

    int[] accelArrowX = {0,0,0};
    int[] accelArrowY = {0,0,0};
    int[] veloArrowX = {0,0,0};
    int[] veloArrowY = {0,0,0};

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

    boolean pause = true;

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D paint = (Graphics2D) g;

        //Draw Big circle showing ball's path
        paint.setStroke(one);
        paint.setColor(Color.black);
        paint.drawOval(cX-radLength,cY-radLength,radLength*2,radLength*2);

        //Acceleration Arrow
        if(drawAccel){
            paint.setStroke(four);
            paint.setColor(Color.MAGENTA);
            //accel should scale off velocity² and radius
            paint.drawLine((int) (cX+ radLength*cos), (int) (cY - radLength*sin), (int) (cX+ (1.1*radLength-(ballSpeed*ballSpeed)-40)*cos), (int) (cY - (1.1*radLength-(ballSpeed*ballSpeed)-40)*sin));
            paint.fillPolygon(accelArrowX,accelArrowY,3);
        }


        //Velocity Arrow
        if(drawVelo){
            paint.setStroke(three);
            paint.setColor(Color.ORANGE);
            //change end point to vary based on velocity
            paint.drawLine((int) (cX+ radLength*cos), (int) (cY - radLength*sin), (int) (cX+ radLength*cos - 20*ballSpeed*sin), (int) (cY - radLength*sin - 20*ballSpeed*cos));
            paint.fillPolygon(veloArrowX,veloArrowY,3);
        }

        //Draw radial line
        paint.setStroke(two);
        paint.setColor(Color.black);
        paint.drawLine(cX,cY, (int) (cX+ radLength*cos), (int) (cY - radLength*sin));

        //Draw centre
        paint.setStroke(two);
        paint.setColor(Color.BLUE);
        paint.fillOval(cX-cDiam/2,cY-cDiam/2,cDiam,cDiam);

        int ballWidth = 15+ballMass;
        //Draw Ball
        paint.setStroke(two);
        paint.setColor(Color.GRAY);
        paint.fillOval((int) (cX+ radLength*cos-0.5*ballWidth), (int) (cY - radLength*sin-0.5*ballWidth), ballWidth,ballWidth);

        //I want to update dialX to be 385
        int dialX = 385;

        //Velocity dial
        paint.setStroke(two);
        paint.setColor(Color.BLACK);
        paint.drawOval(dialX,4,70,70);
        paint.setStroke(three);
        paint.setColor(Color.ORANGE);
        paint.drawLine((dialX+35),(39), (int) (dialX+35- 25*sin), (int) (39- 25*cos));
        paint.fillOval(dialX+32,36,6,6);

        //Acceleration Dial
        paint.setStroke(two);
        paint.setColor(Color.BLACK);
        paint.drawOval(dialX+180,4,70,70);
        paint.setStroke(three);
        paint.setColor(Color.MAGENTA);
        paint.drawLine(dialX+215,39,(int) (dialX+215- 25*cos), (int) (39 + 25*sin));
        paint.fillOval(dialX+212,36,6,6);

        //Force Dial
        paint.setStroke(two);
        paint.setColor(Color.BLACK);
        paint.drawOval(dialX+360,4,70,70);
        paint.setStroke(three);
        paint.setColor(Color.DARK_GRAY);
        paint.drawLine(dialX+395,39,(int) (dialX+395- 25*cos), (int) (39 + 25*sin));
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
        accVal.setText(String.format("%.2f",(double)(ballSpeed*ballSpeed)/(double)(pathRadius))+" m•s⁻²");
        forVal.setText(String.format("%.2f",((double)ballMass*(double)(ballSpeed*ballSpeed)/(double)pathRadius))+ " N");

        velVal.setBounds(dialX+75,24,80,30);
        accVal.setBounds(dialX+255,24,80,30);
        forVal.setBounds(dialX+435,24,80,30);

    }

    //This timer runs code at 60 FPS
    Timer timer = new Timer(50/3,e->{
        //Set angle back to 0
        if(angle>=2*Math.PI){
            angle=0;
        }
        if(!pause){
            angle+=(Math.PI/600)*ballSpeed/pathRadius;
        }
        sin = Math.sin(angle);
        cos = Math.cos(angle);

        ballMass = model.getBallMass();
        ballSpeed = model.getBallSpeed();
        pathRadius = model.getPathRad();
        radLength = 28*pathRadius;

        updateArrowHeads();
        repaint();
    });

    public Timer getTimer(){
        return timer;
    }

    public void setAngle(double angle){
        this.angle = angle;
        sin = Math.sin(angle);
        cos = Math.cos(angle);
        updateArrowHeads();
    }

    public void updateArrowHeads(){

        double velArrowStartX = cX + radLength*cos;
        double velArrowStartY = cY - radLength*sin;

        //Arrow head tip
        accelArrowX[0] = (int) (cX+(1.1*radLength-ballSpeed*ballSpeed-48)*cos);
        accelArrowY[0] = (int) (cY-(1.1*radLength-ballSpeed*ballSpeed-48)*sin);
        //change endpoint to vary off of velocity?
        veloArrowX[0] = (int) (velArrowStartX - (23.0)*ballSpeed*sin);
        veloArrowY[0] = (int) (velArrowStartY - (23.0)*ballSpeed*cos);

//arrow head left side i think
        accelArrowX[1] = (int) (cX +(1.1*radLength - ballSpeed*ballSpeed - 35)*cos - (1/28.0)*radLength*sin);
        accelArrowY[1] = (int) (cY -(1.1*radLength - ballSpeed*ballSpeed - 35)*sin - (1/28.0)*radLength*cos);

        veloArrowX[1] = (int) (velArrowStartX - 18*ballSpeed*sin + ballSpeed*cos);
        veloArrowY[1] = (int) (velArrowStartY - 18*ballSpeed*cos - ballSpeed*sin);

//arrow head right side
        accelArrowX[2] = (int) (cX + (1.1*radLength - ballSpeed*ballSpeed - 35)*cos + (1/28.0)*radLength*sin);
        accelArrowY[2] = (int) (cY - (1.1*radLength - ballSpeed*ballSpeed - 35)*sin + (1/28.0)*radLength*cos);

        veloArrowX[2] = (int) (velArrowStartX - 18*ballSpeed*sin - ballSpeed*cos);
        veloArrowY[2] = (int) (velArrowStartY - 18*ballSpeed*cos + ballSpeed*sin);
    }

    public void setPause(boolean pause){
        this.pause = pause;
    }
}
