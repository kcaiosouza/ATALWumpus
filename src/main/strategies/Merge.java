package main.strategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import main.game.map.Map;
import main.game.map.Point;

public class Merge implements Strategy {
    private List<Strategy> strategies;

    public Merge() {
        this.strategies = new ArrayList<>();
        this.strategies.add(new ShortestDistance());
        this.strategies.add(new Sort());
        this.strategies.add(new FewerObstacles());
    }

    @Override
    public Point evaluatePossbileNextStep(List<Point> possibleNextSteps, Map map) {
        HashMap<Point, Integer> votes = new HashMap<>();

        // Avalia cada estratégia e registra seus votos
        for (Strategy strategy : strategies) {
            Point chosenPoint = strategy.evaluatePossbileNextStep(possibleNextSteps, map);
            if (chosenPoint != null) {
                votes.put(chosenPoint, votes.getOrDefault(chosenPoint, 0) + 1);
            }
        }

        // Determina o ponto mais votado
        Point finalDecision = null;
        int maxVotes = 0;

        for (Entry<Point, Integer> entry : votes.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                finalDecision = entry.getKey();
            }
        }

        return finalDecision; // Retorna o ponto mais votado
    }
}
