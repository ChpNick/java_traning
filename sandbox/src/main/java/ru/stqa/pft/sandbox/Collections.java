package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nikolay Pechenin on 22.05.2017.
 */
public class Collections {

    public static void main(String[] args) {
        String[] langs = {"Java", "C#", "PHP", "Python"};

        List<String> languages = Arrays.asList("Java", "C#", "PHP", "Python");

        for (String i : languages) {
            System.out.println("Неплохо было бы выучить: " + i);
        }
    }
}
