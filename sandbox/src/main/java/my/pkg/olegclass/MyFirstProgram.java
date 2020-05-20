package my.pkg.olegclass;

public class MyFirstProgram {
    public static double distance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    public static void main(String[] args) {
        System.out.println("Hello, world");
        Point p1 = new Point(1, 2);
        Point p2 = new Point(5, 5);
        double dist1 = p1.distance(p2);
        System.out.println("Расстояние = "+ dist1);

    }

}