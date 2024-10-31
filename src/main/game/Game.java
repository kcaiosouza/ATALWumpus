package main.game;

import main.game.map.Map;
import main.game.map.Point;
import main.game.map.TreasureChest;
import main.strategies.Sort;

public class Game {
	private Map map;
	private Player player;
	public Game() {
		this.map = new Map(8, 8);
		this.player = new Player(new Sort());
	}
	
	public void run() {
		this.map.print();
		System.out.println();
		int movimentsNumber = 0;
		while(true) {
			Point nextPoint = this.player.evaluatePossbileNextStep(map);
			if (nextPoint == null) {
				break;
			} else {
				++movimentsNumber;
				String space = this.map.get(nextPoint);
				if (space != null && space.equals(TreasureChest.CHARACTER)) {
					this.map.openTreasureChest(nextPoint);
					this.map.print();
					System.out.println("Movimentos: " + movimentsNumber + "x");
					break;
				} else {
					this.map.moveRobot(nextPoint);					
				}
			}
			this.map.print();
			System.out.println();
		}
	}

}
