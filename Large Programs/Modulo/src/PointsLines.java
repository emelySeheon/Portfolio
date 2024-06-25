//*****************************************************************
//* Emely Seheon
//* CS351 Project1
//* This class create the points and lines around the circle
//*****************************************************************

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Screen;

public class PointsLines {
    private final int pointNum;
    private final double numX;
    private final double numY;

    public PointsLines(int pointNum, double numX, double numY) {
        this.pointNum = pointNum;
        this.numX = numX;
        this.numY = numY;
    }

    //Create points around the circle
    private static PointsLines[] CreatePoints(int totPoints) {
        PointsLines[] circlePoints = new PointsLines[totPoints+1];
        double distance = 360.0/totPoints;
        double currentDist = distance;

        for (int i = 0; i <= totPoints; ++i){
            double currentX = (Math.cos(Math.toRadians(currentDist)) * 250) + (Screen.getPrimary().getVisualBounds().getWidth() / 2);
            double currentY = Math.sin(Math.toRadians(currentDist)) * 250 + ((Screen.getPrimary().getVisualBounds().getHeight() / 2) - 100);
            circlePoints[i] = new PointsLines(i, currentX, currentY);
            currentDist = currentDist + distance;
        }

        return circlePoints;
    }

    //Create lines around the circle
    public Line[] CreateLines (int totPoints, double multBy, Color color) {


        //points
        PointsLines[] circlePoints = CreatePoints(totPoints);

        //lines
        Line[] circleLines = new Line[totPoints];
        for(int i = 0; i < totPoints; ++i) {
            PointsLines from = circlePoints[i];
            int toNum = (int) ((multBy * from.pointNum) % totPoints);
            PointsLines to = circlePoints[toNum];
            Line currentLine = new Line(from.numX, from.numY, to.numX, to.numY);
            currentLine.setStroke(color);
            circleLines[i] = currentLine;
        }

        return circleLines;
    }
}
