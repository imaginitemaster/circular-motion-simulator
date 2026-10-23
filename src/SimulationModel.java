public class SimulationModel {
    int ballMass = 1;
    int ballSpeed = 5;
    int pathRad = 10;
    public SimulationModel(){

    }

    public void setBallMass(int ballMass){this.ballMass = ballMass;}
    public  void setBallSpeed(int ballSpeed){this.ballSpeed = ballSpeed;}
    public void setPathRad(int pathRad){this.pathRad = pathRad;}

    public int getBallMass(){return ballMass;}
    public int getBallSpeed(){return ballSpeed;}
    public int getPathRad(){return pathRad;}

}
