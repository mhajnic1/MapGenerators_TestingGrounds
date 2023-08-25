package MapGenerators;

import java.awt.Point;
import java.util.ArrayList;

public class generateBlobIsland {

	generateBlobIsland(MapGeneratorGUI map) {
        int numPoints = map.rand.nextInt(5) + 5; // Random number of points (between 5 and 9)

        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < numPoints; i++) {
            int x = map.rand.nextInt(map.mapWidth);
            int y = map.rand.nextInt(map.mapHeight);
            points.add(new Point(x, y));
        }

        for (Point point : points) {
        	map.map[point.y][point.x] = ' '; // Set point as land
        }

        for (Point point : points) {
            int numConnections = map.rand.nextInt(3) + 1; // Random number of connections (between 1 and 3)
            for (int i = 0; i < numConnections; i++) {
                int targetIndex = map.rand.nextInt(points.size());
                Point target = points.get(targetIndex);
                connectPoints(point, target, map);
            }
        }
    }
    private void connectPoints(Point p1, Point p2, MapGeneratorGUI map) {
        int x1 = p1.x;
        int y1 = p1.y;
        int x2 = p2.x;
        int y2 = p2.y;

        while (x1 != x2 || y1 != y2) {
            if (x1 < x2) {
                x1++;
            } else if (x1 > x2) {
                x1--;
            }
            if (y1 < y2) {
                y1++;
            } else if (y1 > y2) {
                y1--;
            }
            map.map[y1][x1] = ' '; // Set path as land
        }
    }
    
}
