package MapGenerators;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class MapGeneratorGUI extends JFrame {
	 /**
	 * 
	 */
	
	primGrowingTree tree;
	generateFractalIsland fractal;
	generateIsland island;
	generateRandomClusterIsland clusterIsland;
	generateIndividualIslandCluster individualIslands;
	generateVoronoiIslands voronoiIslands;
	generateRoadMapIsland roadmapIslands;
	generateBlobIsland blobIslands;
	generateRecursiveGrowthIsland recIslands;
	generateCellularAutomataIsland cellIsland;
	generateDiamondSquareIsland diamondIslands;
	generateBiomeIsland biomeIslands;
	
	public static final long serialVersionUID = 1L;
	public int cellSize = 10;
	public int mapWidth = 100; // Adjust map width
	public int mapHeight = 100; // Adjust map height
    public char[][] map;
    public Random rand = new Random();
    //public Biome[][] biomeMap;
    
    //generateFractalIsland fractalIsland = new generateFractalIsland();
    
    public MapGeneratorGUI() {
        map = new char[mapHeight][mapWidth];
        generateMap("Basic Generate Islands"); // Initialize with a default function

        JPanel buttonPanel = new JPanel();
        
        JComboBox<String> islandFunctionsComboBox = new JComboBox<>();
        islandFunctionsComboBox.addItem("Basic Generate Islands");
        islandFunctionsComboBox.addItem("Random Cluster Islands");
        islandFunctionsComboBox.addItem("Individual Island Clusters");
        islandFunctionsComboBox.addItem("Fractal Island Generation");
        islandFunctionsComboBox.addItem("Road Map Island Generation");
        islandFunctionsComboBox.addItem("Prim Growing Tree");
        islandFunctionsComboBox.addItem("Blob Island Generation");
        islandFunctionsComboBox.addItem("Recursive Growth Island Generation");
        islandFunctionsComboBox.addItem("Diamond-Square Island Generation");
        islandFunctionsComboBox.addItem("Voronoi Islands");
        islandFunctionsComboBox.addItem("Cellular Automata Island Generation");
        islandFunctionsComboBox.addItem("Biome Island Generation");
        islandFunctionsComboBox.setSelectedIndex(0);
        
        JButton generateButton = new JButton("Generate Islands");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFunction = (String) islandFunctionsComboBox.getSelectedItem();
                generateMap(selectedFunction);
                repaint();
            }
        });
        
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFunction = (String) islandFunctionsComboBox.getSelectedItem();
                resetMap(selectedFunction);
                repaint();
            }
        });
        
        buttonPanel.add(islandFunctionsComboBox);
        buttonPanel.add(generateButton);
        buttonPanel.add(resetButton);

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);
        add(new MapPanel(), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

 
   
    
    private void generateMap(String selectedFunction) {
        // Clear the map
    	
    	map = new char[mapHeight][mapWidth];
    	//biomeMap = new Biome[mapHeight][mapWidth];
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                map[y][x] = '~'; // Water
            }
        }

        int numIslands = rand.nextInt(10) + 5;

        switch (selectedFunction) {
            case "Basic Generate Islands":
                for (int i = 0; i < numIslands; i++) {
                    island = new generateIsland(this);
                }
                break;

            case "Random Cluster Islands":
                for (int i = 0; i < numIslands; i++) {
                    clusterIsland = new generateRandomClusterIsland(this);
                }
                break;

            case "Individual Island Clusters":
                for (int i = 0; i < numIslands; i++) {
                    individualIslands = new generateIndividualIslandCluster(this);
                }
                break;
                
            case "Fractal Island Generation":
                for (int i = 0; i < numIslands; i++) {
                	fractal = new generateFractalIsland(this);
                }
                break;
            
            case "Road Map Island Generation":
                for (int i = 0; i < numIslands; i++) {
                    roadmapIslands = new generateRoadMapIsland(this);
                }
                break;
               
            case "Prim Growing Tree":
                for (int i = 0; i < numIslands; i++) {
                	tree = new primGrowingTree(0,0, this);
                }
                break;
                
            case "Blob Island Generation":
                for (int i = 0; i < numIslands; i++) {
                	blobIslands = new generateBlobIsland(this);
                }
                break;
                
            case "Recursive Growth Island Generation":
                for (int i = 0; i < numIslands; i++) {
                    recIslands = new generateRecursiveGrowthIsland(numIslands, this);
                }
                break;

            case "Diamond-Square Island Generation":
                for (int i = 0; i < numIslands; i++) {
                    diamondIslands = new generateDiamondSquareIsland(this);
                }
                break;
            case "Voronoi Islands": // Add this case
            	voronoiIslands = new generateVoronoiIslands(this);
                break;

            case "Cellular Automata Island Generation":
                cellIsland = new generateCellularAutomataIsland(5, 0.5, this); // Adjust iterations and land threshold as needed
                break;
                
            case "Biome Island Generation":
            	biomeIslands = new generateBiomeIsland(this);
                break;


            // Add more island generation functions here if needed

            default:
                break;
        }

        addTrees();
    }


    private void addTrees() {
        for (int y = 1; y < mapHeight - 1; y++) {
            for (int x = 1; x < mapWidth - 1; x++) {
                if (map[y][x] == ' ' && rand.nextDouble() < 0.05) {
                    map[y][x] = 'X'; // Tree
                }
            }
        }
    }

    private void resetMap(String selectedFunction) {
        generateMap(selectedFunction);
        repaint();
    }


    private class MapPanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                if (map[y][x] == '~') {
                    g.setColor(Color.BLUE); // Water color
                } else if (map[y][x] == ' ') {
                    g.setColor(Color.GREEN); // Land color for cellular automata-generated land
                } else if (map[y][x] >= 'A' && map[y][x] <= 'Z') {
                    // Assign colors based on island letters
                    int islandIndex = map[y][x] - 'A';
                    g.setColor(getIslandColor(islandIndex));
                } else if (map[y][x] == 'X') {
                    g.setColor(Color.DARK_GRAY); // Tree color
                }
                
                /*
                if (biomeMap[y][x] == Biome.OCEAN) {
                    g.setColor(Color.BLUE);
                } else if (biomeMap[y][x] == Biome.BEACH) {
                    g.setColor(Color.YELLOW);
                } else if (biomeMap[y][x] == Biome.FOREST) {
                    g.setColor(Color.GREEN);
                } else if (biomeMap[y][x] == Biome.DESERT) {
                    g.setColor(Color.ORANGE);
                }
                */
                // biome map NE RADI, FIX
                // ... (existing code to handle other cases)

            
                g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(mapWidth * cellSize, mapHeight * cellSize);
    }
}


    private Color getIslandColor(int islandIndex) {
        // Define an array of colors for islands
        Color[] islandColors = {
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
            Color.BLUE, Color.MAGENTA, Color.CYAN, Color.PINK
        };
        
        // Ensure the index is within the range of available colors
        islandIndex = islandIndex % islandColors.length;
        return islandColors[islandIndex];
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MapGeneratorGUI();
        });
    }
}
