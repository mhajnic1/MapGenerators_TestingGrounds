package MapGenerators;

public class generateIndividualIslandCluster {
	
	generateIndividualIslandCluster(MapGeneratorGUI map) {
        int islandWidth = map.rand.nextInt(20) + 10; // Random island width (between 10 and 29)
        int islandHeight = map.rand.nextInt(20) + 10; // Random island height (between 10 and 29)

        int startX = map.rand.nextInt(map.mapWidth - islandWidth);
        int startY = map.rand.nextInt(map.mapHeight - islandHeight);

        for (int y = startY; y < startY + islandHeight; y++) {
            for (int x = startX; x < startX + islandWidth; x++) {
            	map.map[y][x] = ' '; // Land
            }
        }
    }

}
