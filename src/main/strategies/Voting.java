package main.strategies;

import main.game.map.GameMap;
import main.game.map.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Voting implements Strategy {
    @Override
    public Point evaluatePossbileNextStep(List<Point> possibleNextStep, GameMap map) {
        Sort sort = new Sort();
        FewerObstacles fewerObstacles = new FewerObstacles();
        ShortestDistance shortestDistance = new ShortestDistance();

        Point sortPoint = sort.evaluatePossbileNextStep(possibleNextStep, map);
        Point fewerPoint = fewerObstacles.evaluatePossbileNextStep(possibleNextStep, map);
        Point shortestPoint = shortestDistance.evaluatePossbileNextStep(possibleNextStep, map);

        HashMap<String, Integer> voting = new HashMap<>();
        HashMap<String, Point> points = new HashMap<>();
        String sortPointId = getPointId(sortPoint);
        String fewerPointId = getPointId(fewerPoint);
        String shortestPointId = getPointId(shortestPoint);

        points.put(sortPointId, sortPoint);
        points.put(fewerPointId, fewerPoint);
        points.put(shortestPointId, shortestPoint);

        voting.put(sortPointId, voting.getOrDefault(sortPointId, 0) + 1);
        voting.put(fewerPointId, voting.getOrDefault(fewerPointId, 0) + 1);
        voting.put(shortestPointId, voting.getOrDefault(shortestPointId, 0) + 1);

        return getMostVotedPoint(voting, points);
    }

    private Point getMostVotedPoint(HashMap<String, Integer> voting, HashMap<String, Point> points) {
        Integer biggestValue = Integer.MIN_VALUE;
        String biggestKey = null;
         for(Map.Entry<String, Integer> entry : voting.entrySet()) {
             if(entry.getValue() > biggestValue) {
                 biggestValue = entry.getValue();
                 biggestKey = entry.getKey();
             }
         }

         return points.get(biggestKey);
    }

    public String getPointId(Point p) {
        return p.getPositionX() + "," + p.getPositionY();
    }

}
