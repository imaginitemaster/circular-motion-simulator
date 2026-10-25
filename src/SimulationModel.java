public class SimulationModel {
    int ballMass = 1;
    int ballSpeed = 10;
    int pathRad = 10;
    public SimulationModel(){

    }

    public void setBallMass(int ballMass){this.ballMass = ballMass;}
    public  void setBallSpeed(int ballSpeed){this.ballSpeed = ballSpeed;}
    public void setPathRad(int pathRad){this.pathRad = pathRad;}

    public int getBallMass(){return ballMass;}
    public int getBallSpeed(){return ballSpeed;}
    public int getPathRad(){return pathRad;}

    private int cX = 600;
    private int cY = 400;

    private double angle = 0;
    double sin;
    double cos;
    int radLength = 28*pathRad;
    int[] ballPos = new int[2];
    int ballWidth = 15+ballMass;
    double accelNumValue;
    double forceNumValue;

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

        radLength = 28*pathRad;

        ballWidth = 15+ballMass;
        if (circularPath) {
            ballPos = new int[]{(int)(cX + radLength * cos), (int) (cY - radLength * sin)};
            accelNumValue = (double)(ballSpeed*ballSpeed)/(double)(pathRad);
            forceNumValue = ((double)ballMass*(double)(ballSpeed*ballSpeed)/(double)pathRad);
            drawAccel =true;
            drawAccelDial = true;
            drawForceDial = true;
        }else{
            if(!pauseLinear){
                ballPos[0]-= (int) (sin*ballSpeed);
                ballPos[1]-= (int) (cos*ballSpeed);
            }

            accelNumValue = 0;
            forceNumValue = 0;

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
    public int[] getBallPos(){return ballPos;}
    public void resetBallPos() {this.ballPos = new int[]{(int)(cX + radLength * cos), (int) (cY - radLength * sin)};}
    public double getAccelNumValue(){return accelNumValue;}
    public double getForceNumValue() {return forceNumValue;}

    public boolean getDrawAccel(){return drawAccel;}
    public boolean getDrawAccelDial(){return drawAccelDial;}
    public boolean getDrawForceDial(){return drawForceDial;}

    public void setCircularPath(boolean circularPath){this.circularPath=circularPath;}
    public boolean getCircularPath(){return circularPath;}
}
