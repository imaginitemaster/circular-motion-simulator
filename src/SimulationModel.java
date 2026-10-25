public class SimulationModel {
    double ballMass = 1;
    double ballSpeed = 10;
    double pathRad = 10;
    public SimulationModel(){

    }

    public void setBallMass(int ballMass){this.ballMass = ballMass;}
    public  void setBallSpeed(int ballSpeed){this.ballSpeed = ballSpeed;}
    public void setPathRad(double pathRad){this.pathRad = pathRad;}

    public double getBallMass(){return ballMass;}
    public double getBallSpeed(){return ballSpeed;}
    public double getPathRad(){return pathRad;}

    private int cX = 600;
    private int cY = 400;

    private double angle = 0;
    double sin;
    double cos;
    int radLength = (int)(28*pathRad);
    double[] ballPos = new double[2];
    int ballWidth = (int)(15+ballMass);
    double accelNumValue;
    double forceNumValue;
    String angVelVal;
    String timePVal;

    boolean drawAccel =true;
    boolean drawAccelDial = true;
    boolean drawForceDial = true;

    boolean circularPath = true;

    //This update method will be called by the timer in order to update values
    public void update(){

        //Set angle back to 0
        if(angle>=2*Math.PI){
            angle-=2*Math.PI;
        }
        if(!pauseCircular){
            angle+=(0.017)*ballSpeed/pathRad;
        }

        sin = Math.sin(angle);
        cos = Math.cos(angle);

        radLength = (int)(28*pathRad);

        ballWidth = (int)(15+ballMass);
        if (circularPath) {
            ballPos = new double[]{(cX + radLength * cos), (cY - radLength * sin)};
            accelNumValue = (ballSpeed*ballSpeed)/(pathRad);
            forceNumValue = (ballMass*(ballSpeed*ballSpeed)/pathRad);
            angVelVal = String.format("%.2f",(ballSpeed/pathRad));
            timePVal = String.format("%.2f",2*Math.PI*pathRad/ballSpeed);
            drawAccel =true;
            drawAccelDial = true;
            drawForceDial = true;
        }else{
            if(!pauseLinear){
                ballPos[0]-= (sin*ballSpeed);
                ballPos[1]-= (cos*ballSpeed);
            }

            accelNumValue = 0;
            forceNumValue = 0;
            angVelVal = "N/A";
            timePVal = "N/A";

            drawAccel =false;
            drawAccelDial = false;
            drawForceDial = false;

            this.setPauseCircular(true);
        }
    }


    boolean pauseCircular = true;
    //Pause angle being increased
    public void setPauseCircular(boolean pauseCircular){
        this.pauseCircular = pauseCircular;
    }

    boolean pauseLinear = true;
    public void setPauseLinear(boolean pauseLinear){this.pauseLinear = pauseLinear;}

    public void setAngle(double angle){
        this.angle = angle;
        sin = Math.sin(angle);
        cos = Math.cos(angle);
    }

    public double getSin(){
        return sin;
    }
    public double getCos() {
        return cos;
    }
    public double[] getBallPos(){return ballPos;}
    public void resetBallPos() {this.ballPos = new double[]{(cX + radLength * cos),(cY - radLength * sin)};}
    public double getAccelNumValue(){return accelNumValue;}
    public double getForceNumValue() {return forceNumValue;}
    public String getAngVelVal(){return angVelVal;}
    public String getTimePVal(){return timePVal;}

    public boolean getDrawAccel(){return drawAccel;}
    public void setDrawAccel(boolean drawAccel){this.drawAccel=drawAccel;}
    public boolean getDrawAccelDial(){return drawAccelDial;}
    public boolean getDrawForceDial(){return drawForceDial;}

    public void setCircularPath(boolean circularPath){this.circularPath=circularPath;}
    public boolean getCircularPath(){return circularPath;}
}
