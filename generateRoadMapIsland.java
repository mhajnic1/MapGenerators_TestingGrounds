package MapGenerators;

public class generateRoadMapIsland {

	generateRoadMapIsland(MapGeneratorGUI map) {
        int islandWidth = map.rand.nextInt(20) + 10; // Random island width (between 10 and 29)
        int islandHeight = map.rand.nextInt(20) + 10; // Random island height (between 10 and 29)

        int startX = map.rand.nextInt(map.mapWidth - islandWidth);
        int startY = map.rand.nextInt(map.mapHeight - islandHeight);

        // Generate vertical roads
        for (int x = startX + islandWidth / 2; x < startX + islandWidth; x++) {
            for (int y = startY; y < startY + islandHeight; y++) {
            	map.map[y][x] = ' '; // Land
            }
        }

        // Generate horizontal roads
        for (int y = startY + islandHeight / 2; y < startY + islandHeight; y++) {
            for (int x = startX; x < startX + islandWidth; x++) {
            	map.map[y][x] = ' '; // Land
            }
        }
    }
	
}
