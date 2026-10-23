import java.awt.*;

public class DrawArrow {
    //This class should take a starting point, direction and length and translate it into a full arrow to be drawn.
    public DrawArrow(double startX, double startY, double dx, double dy, double length, Graphics2D paint){

        //First, calculate values
        //line tip:
        double lineTipX = (startX+length*dx);
        double lineTipY = (startY+length*dy);
        //Arrow tip:
        double arrowTipX = (startX+length*1.2*dx);
        double arrowTipY = (startY+length*1.2*dy);
        //Arrow Corner 1:
        double arrowCorn1X = (startX+length*dx+length*0.1*-dy);
        double arrowCorn1Y = (startY+length*dy+length*0.1*dx);
        //Arrow Corner 2:
        double arrowCorn2X = (startX+length*dx-length*0.1*-dy);
        double arrowCorn2Y = (startY+length*dy-length*0.1*dx);

        int[] arrowXVals = {(int) arrowTipX, (int) arrowCorn1X, (int) arrowCorn2X};
        int[] arrowYVals = {(int) arrowTipY, (int) arrowCorn1Y, (int) arrowCorn2Y};

        paint.drawLine((int) startX, (int) startY, (int) lineTipX, (int) lineTipY);
        paint.fillPolygon(arrowXVals,arrowYVals,3);

    }
}
