package MapGenerators;
import java.util.Random;

public class ElevationMapGenerator {
    private int[][] elevationMap;
    private int mapWidth;
    private int mapHeight;
    private Random rand = new Random();

    public ElevationMapGenerator(int width, int height) {
        mapWidth = width;
        mapHeight = height;
        elevationMap = new int[mapHeight][mapWidth];
    }

    public void generateElevationMap() {
        double frequency = 0.1;
        double amplitude = 30.0;

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                double perlinValue = generatePerlinNoise(x * frequency, y * frequency) * amplitude;
                elevationMap[y][x] = (int) perlinValue;
            }
        }
    }

    private double generatePerlinNoise(double x, double y) {
        // Implement your Perlin noise function here
        // This is a simple example, you might need a more complex implementation
        return rand.nextDouble() * 2 - 1; // Replace with your Perlin noise function
    }

    public int[][] getElevationMap() {
        return elevationMap;
    }
}
