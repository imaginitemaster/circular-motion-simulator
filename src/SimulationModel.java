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


    private double angle = 0;
    double sin;
    double cos;

    //This update method will be called by the timer in order to update values
    public void update(){

        //Set angle back to 0
        if(angle>=2*Math.PI){
            angle-=2*Math.PI;
        }
        if(!pause){
            angle+=(0.017)*ballSpeed/pathRad;
        }

        sin = Math.sin(angle);
        cos = Math.cos(angle);

    }


    boolean pause = true;
    //Pause angle being increased
    public void setPause(boolean pause){
        this.pause = pause;
    }

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
}
