package MapGenerators;

public class generateRandomClusterIsland {

	generateRandomClusterIsland(MapGeneratorGUI map) {
        int clusterSize = map.rand.nextInt(50) + 20; // Random cluster size (between 20 and 69)
        int startX = map.rand.nextInt(map.mapWidth - clusterSize);
        int startY = map.rand.nextInt(map.mapHeight - clusterSize);

        for (int y = startY; y < startY + clusterSize; y++) {
            for (int x = startX; x < startX + clusterSize; x++) {
            	map.map[y][x] = ' '; // Land
            }
        }
    }
	
}
