package MapGenerators;

import java.util.Arrays;
import java.util.Collections;

public class generateRecursiveGrowthIsland {
	
	generateRecursiveGrowthIsland(int numStartingPoints, MapGeneratorGUI map) {
        for (int i = 0; i < numStartingPoints; i++) {
            int startX = map.rand.nextInt(map.mapWidth);
            int startY = map.rand.nextInt(map.mapHeight);
            
            map.map[startY][startX] = ' '; // Set starting point as land
            recursiveGrowth(startX, startY, 5, map); // Adjust recursion depth as needed
        }
    }
    private void recursiveGrowth(int x, int y, int depth, MapGeneratorGUI map) {
        if (depth <= 0) {
            return;
        }

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Collections.shuffle(Arrays.asList(directions)); // Randomize direction order

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (newX >= 0 && newX < map.mapWidth && newY >= 0 && newY < map.mapHeight && map.map[newY][newX] != ' ') {
            	map.map[newY][newX] = ' '; // Set new point as land
                recursiveGrowth(newX, newY, depth - 1, map);
            }
        }
    }

}
