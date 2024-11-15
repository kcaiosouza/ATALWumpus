package main.game;

import main.game.map.Map;
import main.game.map.Point;
import main.game.map.TreasureChest;
import main.strategies.FewerObstacles;
import main.strategies.Merge;
import main.strategies.ShortestDistance;
import main.strategies.Sort;

public class Game {
	private Map map;
	private Player player;
	public Game(String type) {
		this.map = new Map(8, 8);
		if(type.equals("sort")){
			this.player = new Player(new Sort());
		} else if (type.equals("fewer")) {
			this.player = new Player(new FewerObstacles());
		} else if (type.equals("short")) {
			this.player = new Player(new ShortestDistance());
		}else if (type.equals("merge")) {
			this.player = new Player(new Merge());
		}
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
	                // Se o baú encontrado for vazio, continuar a busca
	                if (this.map.get(nextPoint).equals(TreasureChest.CHEST_EMPTY_CHARACTER)) {
	                    System.out.println("Baú vazio, continue procurando...");
	                    continue;  // Continua a procura sem terminar o jogo
	                }
	                break; // Encerra o jogo se o baú for o tesouro ou uma armadilha
	            } else {
	                this.map.moveRobot(nextPoint);                    
	            }
	        }
	        this.map.print();
	        System.out.println();
	    }
	}


}
