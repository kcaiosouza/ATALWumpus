package main.strategies;

import java.util.List;

import main.game.map.GameMap;
import main.game.map.Point;

public class ShortestDistance implements Strategy {

	@Override
	public Point evaluatePossbileNextStep(List<Point> possibleNextSteps, GameMap gameMap) {
		
	    Point currentPoint = gameMap.getRobotLocation();
	    Point closestTreasure = null;
	    double shortestDistance = Double.MAX_VALUE;

	    
	    //percorre o array
	    for (Point step : possibleNextSteps) {
	        
	        double distance = calculateDistance(step, currentPoint);
	        //calcula distancia entre a localização do robo e o tesouro
	        if (distance < shortestDistance) {
	        	//shortestDistance vai assumindo os valores do tesouro até o menor ser encontrado
	            shortestDistance = distance;
	            closestTreasure = step;
	        }
	    }

	    return closestTreasure;
	}

	
	private double calculateDistance(Point p1, Point p2) {
		
		double distance = Math.sqrt(Math.pow(p1.getPositionX() - p2.getPositionX(), 2) +
                Math.pow(p1.getPositionY() - p2.getPositionY(), 2));
	    return distance;
	}


    

}
