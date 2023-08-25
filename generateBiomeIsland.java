package MapGenerators;


public class generateBiomeIsland {
	public Biome[][] biomeMap;
	
	public enum Biome {
        OCEAN, BEACH, FOREST, DESERT // Add more biomes as needed
    }
        generateBiomeIsland(MapGeneratorGUI map) {
            NoiseGenerator noiseGenerator = new NoiseGenerator();
            biomeMap = new Biome[map.mapHeight][map.mapWidth];

            for (int y = 0; y < map.mapHeight; y++) {
                for (int x = 0; x < map.mapWidth; x++) {
                    double noiseValue = Math.random();
                    						// noiseGenerator.generate(x, y);      //  Generate noise value

                    // Adjust the biome thresholds to control cluster formation
                    Biome biome;

                    if (noiseValue < 0.2) {
                        biome = Biome.OCEAN;
                    } else if (noiseValue < 0.5) {
                        biome = Biome.BEACH;
                    } else {
                        biome = Biome.FOREST;
                    }

                    biomeMap[y][x] = biome;

                    if (biome == Biome.BEACH || biome == Biome.FOREST) {
                    	map.map[y][x] = ' ';
                    }
                }
            }
        }
        
        /// implementirat perlin noise
        public class NoiseGenerator {
            //private PerlinNoise perlinNoise; // You need to implement or obtain a PerlinNoise class
			public double NoiseGenerator() {
			               // perlinNoise = new PerlinNoise();
			           
        	/*
            

            // Implement your noise generation logic using the PerlinNoise class
            public double generate(int x, int y) {
                // Replace this with actual Perlin noise generation
              //  return perlinNoise.noise(x * 0.1, y * 0.1);
            }
            */
            
            return Math.random();
        }

        

}
        
}
