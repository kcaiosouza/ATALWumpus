package main.strategies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.game.map.*;

public class FewerObstacles implements Strategy{

	/**
	 * N is the next location 
	 * p1 p2 p3
	 * p4 N p5
	 * p6 p7 p8
	 */

	@Override
	public Point evaluatePossbileNextStep(List<Point> possibleNextStep, Map map) {
		Iterator<Point> step = possibleNextStep.iterator();
		int min = Integer.MAX_VALUE; // Assume o maior valor possível para no count conseguir fazer a verificação de menor no if logo a baixo
		Point pointSelected = null; // Ou seja, não escolheu nenhum ponto para ir
		while(step.hasNext()){
			Point point = step.next();
			int count =	ratePoint(point, map);
			if(count < min) {
				min = count;
				pointSelected = point;
			}
		}
		return pointSelected;
	}

	private int ratePoint(Point point, Map map) {
		int count = 0;
		List<Point> allPoints = new ArrayList<Point>();
		allPoints.add(new Point(point.getPositionX() - 1, point.getPositionY() - 1));
		allPoints.add(new Point(point.getPositionX() - 1, point.getPositionY()));
		allPoints.add(new Point(point.getPositionX() - 1, point.getPositionY() + 1));
		allPoints.add(new Point(point.getPositionX(), point.getPositionY() - 1));
		allPoints.add(new Point(point.getPositionX(), point.getPositionY() + 1));
		allPoints.add(new Point(point.getPositionX() + 1, point.getPositionY() - 1));
		allPoints.add(new Point(point.getPositionX() + 1, point.getPositionY()));
		allPoints.add(new Point(point.getPositionX() + 1, point.getPositionY() + 1));

		for(int i = 0; i < allPoints.size(); i++) {
			Point currentPoint = allPoints.get(i);
			Point playerPosition = map.getRobotLocation();
			int[] scenarioSize = map.getScenarioSize();
			if(
					!(currentPoint.getPositionX() == playerPosition.getPositionX() && currentPoint.getPositionY() == playerPosition.getPositionY()) ||
					currentPoint.getPositionX() < 0 || currentPoint.getPositionX() >= scenarioSize[0] ||
					currentPoint.getPositionY() < 0 || currentPoint.getPositionY() >= scenarioSize[1]
			) {
				continue;
			}else {
				if(map.get(currentPoint).equals(Rock.CHARACTER) || map.get(currentPoint).equals(Monster.CHARACTER)) {
					count++;
				}else {
					if(map.get(currentPoint).equals(TreasureChest.CHARACTER)) {
						count = 0;
					}
				}
			}
		}

		return count;
	}

}
