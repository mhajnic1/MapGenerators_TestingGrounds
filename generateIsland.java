package MapGenerators;

public class generateIsland {
	
	
	generateIsland(MapGeneratorGUI map) {
    	int islandWidth = map.rand.nextInt(20) + 10; // Increase the island width (between 10 and 29)
        int islandHeight = map.rand.nextInt(20) + 10; // Increase the island height (between 10 and 29)

        int startX = map.rand.nextInt(map.mapWidth - islandWidth);
        int startY = map.rand.nextInt(map.mapHeight - islandHeight);

        for (int y = startY; y < startY + islandHeight; y++) {
            for (int x = startX; x < startX + islandWidth; x++) {
            	map.map[y][x] = ' '; // Land
            }
        }
    }
}
// islands sa elevation mapom

/*
 * 
 package MapGenerators;

public class generateIsland {
    generateIsland(MapGeneratorGUI map) {
        int islandWidth = map.rand.nextInt(20) + 10;
        int islandHeight = map.rand.nextInt(20) + 10;

        int startX = map.rand.nextInt(map.mapWidth - islandWidth);
        int startY = map.rand.nextInt(map.mapHeight - islandHeight);

        // Create an instance of ElevationMapGenerator and generate the elevation map
        ElevationMapGenerator elevationMapGenerator = new ElevationMapGenerator(map.mapWidth, map.mapHeight);
        elevationMapGenerator.generateElevationMap();
        int[][] elevationMap = elevationMapGenerator.getElevationMap();

        for (int y = startY; y < startY + islandHeight; y++) {
            for (int x = startX; x < startX + islandWidth; x++) {
                // Use elevation values to determine land tiles
                if (elevationMap[y][x] >= 4) { // Adjust threshold to control land presence
                    map.map[y][x] = ' '; // Land
                }
            }
        }
    }
}

 * 
 */
