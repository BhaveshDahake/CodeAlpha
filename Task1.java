package com.CodeAlpha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
    Develop a program that allows a teacher to enter
    students' grades and compute their average,
    highest, and lowest scores. You can use arrays or
    ArrayLists to store the student data.
 */
public class Task1 {
    public static void main(String[] args) {
        ArrayList<Double> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        double grade;

        while (true) {
            grade = sc.nextDouble();
            if (grade == -1) {
                break;
            }
            list.add(grade);
        }

        if (list.isEmpty()) {
            System.out.println("No grades entered");
        }

        double sum = 0.0;
        for (double marks : list) {
            sum += marks;
        }

        double average = sum / list.size();
        double highest = Collections.max(list);
        double lowest = Collections.min(list);

        System.out.println("The Average of students: " + average);
        System.out.println("Lowest marks: " + lowest);
        System.out.println("Highest marks: " + highest);
    }
}