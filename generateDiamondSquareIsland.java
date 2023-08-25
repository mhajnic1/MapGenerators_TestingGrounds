package MapGenerators;

public class generateDiamondSquareIsland {
	
	generateDiamondSquareIsland(MapGeneratorGUI map) {
	    int[][] elevationMap = new int[map.mapHeight][map.mapWidth];

	    // Initialize corner values
	    elevationMap[0][0] = map.rand.nextInt(16);
	    elevationMap[0][map.mapWidth - 1] = map.rand.nextInt(16);
	    elevationMap[map.mapHeight - 1][0] = map.rand.nextInt(16);
	    elevationMap[map.mapHeight - 1][map.mapWidth - 1] = map.rand.nextInt(16);

	    diamondSquareTerrain(elevationMap, 0, 0, map.mapWidth - 1, map.mapHeight - 1, 8, map);

	    // Convert elevation map to land and water cells with coastlines
	    for (int y = 0; y < map.mapHeight; y++) {
	        for (int x = 0; x < map.mapWidth; x++) {
	            if (elevationMap[y][x] > 0.3) { // Adjust threshold to control land presence
	            	map.map[y][x] = ' '; // Land
	            }
	        }
	    }
	}
	    private void diamondSquareTerrain(int[][] elevationMap, int x1, int y1, int x2, int y2, int roughness, MapGeneratorGUI map) {
	    if (x2 - x1 <= 1 || y2 - y1 <= 1) {
	        return;
	    }

	    int centerX = (x1 + x2) / 2;
	    int centerY = (y1 + y2) / 2;

	    int cornerAverage = (elevationMap[y1][x1] + elevationMap[y1][x2] + elevationMap[y2][x1] + elevationMap[y2][x2]) / 4;

	 // Set middle value based on diamond step
	    elevationMap[centerY][centerX] = (int) ((cornerAverage + elevationMap[centerY][centerX]) / 2 + map.rand.nextInt(2 * roughness + 1) - roughness);

	    // Set corner values based on square step
	    elevationMap[y1][centerX] = (int) ((cornerAverage + elevationMap[y1][centerX]) / 2 + map.rand.nextInt(2 * roughness + 1) - roughness);
	    elevationMap[centerY][x1] = (int) ((cornerAverage + elevationMap[centerY][x1]) / 2 + map.rand.nextInt(2 * roughness + 1) - roughness);
	    elevationMap[y2][centerX] = (int) ((cornerAverage + elevationMap[y2][centerX]) / 2 + map.rand.nextInt(2 * roughness + 1) - roughness);
	    elevationMap[centerY][x2] = (int) ((cornerAverage + elevationMap[centerY][x2]) / 2 + map.rand.nextInt(2 * roughness + 1) - roughness);

	    // Recursive calls for diamond and square steps
	    diamondSquareTerrain(elevationMap, x1, y1, centerX, centerY, roughness / 2, map);
	    diamondSquareTerrain(elevationMap, centerX, y1, x2, centerY, roughness / 2, map);
	    diamondSquareTerrain(elevationMap, x1, centerY, centerX, y2, roughness / 2, map);
	    diamondSquareTerrain(elevationMap, centerX, centerY, x2, y2, roughness / 2, map);
	}
	    

}
