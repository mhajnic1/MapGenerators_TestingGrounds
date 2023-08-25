package MapGenerators;

import java.awt.Point;
import java.util.ArrayList;

public class primGrowingTree {
	MapGeneratorGUI gui;
	
	primGrowingTree(int startX, int startY, MapGeneratorGUI gui) {
		this.gui = gui;
        ArrayList<Point> cells = new ArrayList<>();
        cells.add(new Point(startX, startY));

        while (!cells.isEmpty()) {
            int index = gui.rand.nextInt(cells.size());
            Point currentCell = cells.get(index);
            cells.remove(index);

            ArrayList<Point> neighbors = getUnvisitedNeighbors(currentCell);
            if (!neighbors.isEmpty()) {
                int neighborIndex = gui.rand.nextInt(neighbors.size());
                Point neighbor = neighbors.get(neighborIndex);

                int midX = (currentCell.x + neighbor.x) / 2;
                int midY = (currentCell.y + neighbor.y) / 2;
                gui.map[midY][midX] = ' '; // Land

                gui.map[neighbor.y][neighbor.x] = ' '; // Land
                cells.add(neighbor);
            }
        }
    }


    private ArrayList<Point> getUnvisitedNeighbors(Point cell) {
        ArrayList<Point> neighbors = new ArrayList<>();
        int x = cell.x;
        int y = cell.y;

        if (x >= 3 && gui.map[y][x - 2] == '~') {
            neighbors.add(new Point(x - 2, y));
        }
        if (x <= gui.mapWidth - 4 && gui.map[y][x + 2] == '~') {
            neighbors.add(new Point(x + 2, y));
        }
        if (y >= 3 && gui.map[y - 2][x] == '~') {
            neighbors.add(new Point(x, y - 2));
        }
        if (y <= gui.mapHeight - 4 && gui.map[y + 2][x] == '~') {
            neighbors.add(new Point(x, y + 2));
        }

        return neighbors;
    }

}
