package main.strategies;

import java.util.List;
import java.util.Random;

import main.game.Player;
import main.game.map.*;

public class Sort implements Strategy{
	/**
	 * N is the next location 
	 * p1 p2 p3
	 * p4 N p5
	 * p6 p7 p8
	 */
	@Override
	public Point evaluatePossbileNextStep(List<Point> possibleNextSteps, Map map) {
		boolean sortPointIsObstacle = true;
		while(sortPointIsObstacle) {
			Random random = new Random();
			int index = random.nextInt(possibleNextSteps.size());
			String space = map.get(possibleNextSteps.get(index));
			if(space == null || space.equals("*") || space.equals(TreasureChest.CHARACTER) && !space.equals(Monster.CHARACTER) && !space.equals(Rock.CHARACTER)) {
				return possibleNextSteps.get(index);
			}
		}
		return null;
	}	
}
