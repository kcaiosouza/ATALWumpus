package main.game;

import main.game.map.GameMap;
import main.game.map.Point;
import main.game.map.TreasureChest;
import main.strategies.*;
import main.strategies.binaryTree.BinaryTree;

public class Game {
	private GameMap gameMap;
	private Player player;
	public Game(String type) {
		this.gameMap = new GameMap(8, 8);
		if(type.equals("sort")){
			this.player = new Player(new Sort());
		} else if (type.equals("fewer")) {
			this.player = new Player(new FewerObstacles());
		} else if (type.equals("short")) {
			this.player = new Player(new ShortestDistance());
		}else if (type.equals("voting")) {
			this.player = new Player(new Voting());
		}else if (type.equals("binary")) {
			this.player = new Player(new BinaryTree(this.gameMap));
		}
	}
	
	public void run() {
		this.gameMap.print();
		System.out.println();
		int movimentsNumber = 0;
		while(true) {
			Point nextPoint = this.player.evaluatePossbileNextStep(gameMap);
			if (nextPoint == null) {
				break;
			} else {
				++movimentsNumber;
				String space = this.gameMap.get(nextPoint);
				if (space != null && space.equals(TreasureChest.CHARACTER)) {
					this.gameMap.openTreasureChest(nextPoint);
					this.gameMap.print();
					System.out.println("Movimentos: " + movimentsNumber + "x");
					break;
				} else {
					this.gameMap.moveRobot(nextPoint);
				}
			}
			this.gameMap.print();
			System.out.println();
		}
	}

}
