package MapGenerators;

import java.awt.Point;
import java.util.ArrayList;

public class generateVoronoiIslands {

	generateVoronoiIslands(MapGeneratorGUI map) {
        int numIslands = map.rand.nextInt(10) + 5; // Adjust the number of islands
        ArrayList<Point> islandCenters = new ArrayList<>();

        // Generate random island centers
        for (int i = 0; i < numIslands; i++) {
            int centerX = map.rand.nextInt(map.mapWidth);
            int centerY = map.rand.nextInt(map.mapHeight);
            islandCenters.add(new Point(centerX, centerY));
        }

        // Assign each cell to the nearest island
        for (int y = 0; y < map.mapHeight; y++) {
            for (int x = 0; x < map.mapWidth; x++) {
                int nearestIslandIndex = findNearestIslandIndex(x, y, islandCenters);
                map.map[y][x] = (char) ('A' + nearestIslandIndex); // Use letters to represent islands
            }
        }
    }
    private int findNearestIslandIndex(int x, int y, ArrayList<Point> islandCenters) {
        int nearestIndex = 0;
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < islandCenters.size(); i++) {
            Point center = islandCenters.get(i);
            double distance = Math.sqrt(Math.pow(center.x - x, 2) + Math.pow(center.y - y, 2));
            
            if (distance < minDistance) {
                minDistance = distance;
                nearestIndex = i;
            }
        }

        return nearestIndex;
    }

    
}
