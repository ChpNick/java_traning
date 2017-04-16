package ru.stqa.pft.sandbox.task2;

/**
 * Created by Nikolay Pechenin on 16.04.2017.
 */
public class Point {
    public double x;
    public double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Вычисление расстояния между точками через статичную функцию
     * @param p1 экземпляр первой точки
     * @param p2 экземпляр второй точки
     * @return Расстояние от точки  p2 до точки p1
     */
    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

    /**
     * Вычисление расстояния между точками через метод экземпляра
     * @param p экземпляр второй точки
     * @return Расстоние от this точки до P
     */
    public double distance(Point p) {
        return Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
    }

    public String toString() {
        return "(x=" + this.x +", y=" + this.y + ")";
    }

    public static void main(String[] args) {

        Point p1 = new Point(10, 10);
        Point p2 = new Point(40, 40);

        System.out.println("1 вариант, через статичную функцию:");
        System.out.println("Расстояние между точкой " + p1 + " и точкой " + p2 + " = " + distance(p1, p2));

        System.out.println("-------------------------------------");

        System.out.println("2 вариант, через метод экземпляра:");
        System.out.println("Расстояние между точкой " + p1 + " и точкой " + p2 + " = " + p1.distance(p2));
    }
}
