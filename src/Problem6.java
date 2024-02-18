import java.util.ArrayList;
import java.util.List;

public class Problem6 {

    // Define a nested class Point to represent coordinates
    static class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // Method to generate the shape recursively based on the input level n
    public static List<Point> generateShape(int n) {
        List<Point> result;
        // Base case: when n is 0, return the vertices of the initial triangle
        if (n == 0) {
            result = new ArrayList<>();
            result.add(new Point(0, 0));
            result.add(new Point(1, 0));
            result.add(new Point(0.5, Math.sqrt(3) / 2));
            return result;
        }
        // Recursive case: generate the shape based on the previous level (n-1)
        List<Point> prevShape = generateShape(n - 1);
        result = new ArrayList<>();
        // Calculate midpoints between consecutive points and add them to the result list
        for (int i = 0; i < prevShape.size(); i++) {
            Point firstPoint = prevShape.get(i);
            Point nextPoint = prevShape.get((i + 1) % prevShape.size());
            double firstMidPointX = (2 * firstPoint.x / 3) + (nextPoint.x / 3);
            double firstMidPointY = (2 * firstPoint.y / 3) + (nextPoint.y / 3);
            double secondMidPointX = (firstPoint.x / 3) + (2 * nextPoint.x / 3);
            double secondMidPointY = (firstPoint.y / 3) + (2 * nextPoint.y / 3);
            result.add(new Point(firstMidPointX, firstMidPointY));
            result.add(new Point(secondMidPointX, secondMidPointY));
        }
        return result;
    }

    // Method to generate a string representing the shape in Desmos format
    public static String desmosString(int n) {
        List<Point> shape = generateShape(n);
        StringBuilder sb = new StringBuilder("polygon(");
        // Append the coordinates of each point to the string
        for (Point point : shape) {
            sb.append("(").append(point.x).append(",").append(point.y).append("),");
        }
        sb.deleteCharAt(sb.length() - 1); // Remove the trailing comma
        sb.append(")");
        return sb.toString();
    }

    // Method to calculate the area of a triangle formed by the main point and its adjacent midpoints
    public static double cornerAreas(int n) {
        List<Point> shape = generateShape(n);
        Point point = shape.get(0);
        Point prevPoint = shape.get(shape.size() - 1);
        Point nextPoint = shape.get(1);
        double prevMidPointX = (2 * point.x / 3) + (prevPoint.x / 3);
        double prevMidPointY = (2 * point.y / 3) + (prevPoint.y / 3);
        double nextMidPointX = (2 * point.x / 3) + (nextPoint.x / 3);
        double nextMidPointY = (2 * point.y / 3) + (nextPoint.y / 3);
        double a1 = point.x, b1 = point.y;
        double a2 = prevMidPointX, b2 = prevMidPointY;
        double a3 = nextMidPointX, b3 = nextMidPointY;
        // Calculate the area of the triangle using the Shoelace formula
        return Math.abs((a1 * b2 + a2 * b3 + a3 * b1 - a1 * b3 - a3 * b2 - a2 * b1) / 2);
    }

    public static void main(String[] args) {
        List<Double> areas = new ArrayList<>();
        // Calculate and store the area of triangles for the first 20 levels
        for (int i = 0; i < 20; i++) {
            areas.add(cornerAreas(i));
        }
        // Calculate the ratio of areas between consecutive levels and print them
        for (int i = 0; i < areas.size() - 1; i++) {
            System.out.println(areas.get(i) / areas.get(i + 1));
        }
    }
}
