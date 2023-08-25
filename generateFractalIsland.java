package MapGenerators;

public class generateFractalIsland {
	MapGeneratorGUI map;

	generateFractalIsland(MapGeneratorGUI map) {
		this.map = map;
	    int[][] elevationMap = new int[map.mapHeight][map.mapWidth];

	    // Initialize corner values
	    elevationMap[0][0] = map.rand.nextInt(16);
	    elevationMap[0][map.mapWidth - 1] = map.rand.nextInt(16);
	    elevationMap[map.mapHeight - 1][0] = map.rand.nextInt(16);
	    elevationMap[map.mapHeight - 1][map.mapWidth - 1] = map.rand.nextInt(16);

	    generateFractalTerrain(elevationMap, 0, 0, map.mapWidth - 1, map.mapHeight - 1, 8);

	    // Convert elevation map to land and water cells
	    for (int y = 0; y < map.mapHeight; y++) {
	        for (int x = 0; x < map.mapWidth; x++) {
	            if (elevationMap[y][x] > 4) { // Adjust threshold to control land presence
	            	map.map[y][x] = ' '; // Land
	            }
	        }
	    }
	}
	    private void generateFractalTerrain(int[][] elevationMap, int x1, int y1, int x2, int y2, int roughness) {
	        if (x2 - x1 <= 1 || y2 - y1 <= 1) {
	            return;
	        }

	        int centerX = (x1 + x2) / 2;
	        int centerY = (y1 + y2) / 2;

	        int cornerAverage = (elevationMap[y1][x1] + elevationMap[y1][x2] + elevationMap[y2][x1] + elevationMap[y2][x2]) / 4;
	        int middleValue = (elevationMap[centerY][x1] + elevationMap[centerY][x2] + elevationMap[y1][centerX] + elevationMap[y2][centerX]) / 4;

	        //int offset = rand.nextInt(2 * roughness) - roughness;
	        int offset = map.rand.nextInt(Math.max(1, 2 * roughness)) - roughness;

	        elevationMap[centerY][centerX] = Math.min(Math.max(middleValue + offset, 0), 15);
	        
	        // Adjust corner elevation based on cornerAverage
	        elevationMap[y1][x1] = Math.min(Math.max(cornerAverage + offset, 0), 15);
	        elevationMap[y1][x2] = Math.min(Math.max(cornerAverage + offset, 0), 15);
	        elevationMap[y2][x1] = Math.min(Math.max(cornerAverage + offset, 0), 15);
	        elevationMap[y2][x2] = Math.min(Math.max(cornerAverage + offset, 0), 15);

	        int edgeAvg1 = (elevationMap[y1][centerX] + elevationMap[centerY][x1]) / 2;
	        int edgeAvg2 = (elevationMap[y1][centerX] + elevationMap[centerY][x2]) / 2;
	        int edgeAvg3 = (elevationMap[y2][centerX] + elevationMap[centerY][x1]) / 2;
	        int edgeAvg4 = (elevationMap[y2][centerX] + elevationMap[centerY][x2]) / 2;

	        elevationMap[centerY][x1] = Math.min(Math.max(edgeAvg1 + offset, 0), 15);
	        elevationMap[centerY][x2] = Math.min(Math.max(edgeAvg2 + offset, 0), 15);
	        elevationMap[y1][centerX] = Math.min(Math.max(edgeAvg3 + offset, 0), 15);
	        elevationMap[y2][centerX] = Math.min(Math.max(edgeAvg4 + offset, 0), 15);

	        // Recursive calls for four sub-squares
	        generateFractalTerrain(elevationMap, x1, y1, centerX, centerY, roughness / 2);
	        generateFractalTerrain(elevationMap, centerX, y1, x2, centerY, roughness / 2);
	        generateFractalTerrain(elevationMap, x1, centerY, centerX, y2, roughness / 2);
	        generateFractalTerrain(elevationMap, centerX, centerY, x2, y2, roughness / 2);
	    }
}
