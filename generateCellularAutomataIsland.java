package MapGenerators;

public class generateCellularAutomataIsland {
	
	 generateCellularAutomataIsland(int iterations, double landThreshold, MapGeneratorGUI map) {
	        // Initialize the map randomly
	        for (int y = 0; y < map.mapHeight; y++) {
	            for (int x = 0; x < map.mapWidth; x++) {
	            	map.map[y][x] = map.rand.nextDouble() < landThreshold ? ' ' : '~';
	            }
	        }

	        // Apply cellular automata iterations
	        for (int i = 0; i < iterations; i++) {
	            char[][] newMap = new char[map.mapHeight][map.mapWidth];

	            for (int y = 0; y < map.mapHeight; y++) {
	                for (int x = 0; x < map.mapWidth; x++) {
	                    int landNeighbors = countLandNeighbors(x, y, map);

	                    if (landNeighbors >= 4) {
	                        newMap[y][x] = ' ';
	                    } else {
	                        newMap[y][x] = '~';
	                    }
	                }
	            }

	            map.map = newMap; // Update the map for the next iteration
	        }
	    }
	    private int countLandNeighbors(int x, int y, MapGeneratorGUI map) {
	        int count = 0;

	        for (int yOffset = -1; yOffset <= 1; yOffset++) {
	            for (int xOffset = -1; xOffset <= 1; xOffset++) {
	                if (xOffset == 0 && yOffset == 0) {
	                    continue;
	                }

	                int neighborX = x + xOffset;
	                int neighborY = y + yOffset;

	                if (neighborX >= 0 && neighborX < map.mapWidth && neighborY >= 0 && neighborY < map.mapHeight &&
	                		map.map[neighborY][neighborX] == ' ') {
	                    count++;
	                }
	            }
	        }

	        return count;
	    }

}
