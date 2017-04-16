package ru.stqa.pft.sandbox;

/**
 * Created by Nikolay Pechenin on 16.04.2017.
 */
public class Square {

    public double l;

    public Square(double l) {
        this.l = l;
    }

    public double area() {
        return this.l * this.l;
    }
}
