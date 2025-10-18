package com.example.demo;

import com.example.quadtree.*;
import java.util.List;
import java.util.Scanner;

public class QuadTreeDemo {
    private static QuadTree quadTree;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        quadTree = new QuadTree(new Boundary(50, 50, 50, 50));
        showMenu();
    }

    private static void showMenu() {
        while (true) {
            System.out.println("1. Добавить точку");
            System.out.println("2. Добавить случайные точки");
            System.out.println("3. Найти точки в области");
            System.out.println("4. Показать все точки");
            System.out.println("5. Очистить дерево");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addPoint();
                    break;
                case 2:
                    addRandomPoints();
                    break;
                case 3:
                    queryPoints();
                    break;
                case 4:
                    showAllPoints();
                    break;
                case 5:
                    clearTree();
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    private static void addPoint() {
        System.out.print("Введите координату X: ");
        double x = scanner.nextDouble();
        System.out.print("Введите координату Y: ");
        double y = scanner.nextDouble();

        if (quadTree.insert(x, y)) {
            System.out.printf("Точка (%.2f, %.2f) добавлена%n", x, y);
        } else {
            System.out.println("Ошибка: точка вне границ дерева");
        }
    }

    private static void addRandomPoints() {
        System.out.print("Сколько точек добавить?: ");
        int count = scanner.nextInt();

        for (int i = 0; i < count; i++) {
            double x = Math.random() * 100;
            double y = Math.random() * 100;
            quadTree.insert(x, y);
            System.out.printf("Добавлена точка: (%.2f, %.2f)%n", x, y);
        }
        System.out.println("Все точки добавлены");
    }

    private static void queryPoints() {
        System.out.println("Введите параметры области поиска:");
        System.out.print("Центр X: ");
        double centerX = scanner.nextDouble();
        System.out.print("Центр Y: ");
        double centerY = scanner.nextDouble();
        System.out.print("Половина ширины: ");
        double halfWidth = scanner.nextDouble();
        System.out.print("Половина высоты: ");
        double halfHeight = scanner.nextDouble();

        Boundary searchRange = new Boundary(centerX, centerY, halfWidth, halfHeight);
        List<Point> foundPoints = quadTree.query(searchRange);

        System.out.printf("\nНайдено точек: %d%n", foundPoints.size());
        for (Point point : foundPoints) {
            System.out.printf("  %s%n", point);
        }
    }

    private static void showAllPoints() {
        List<Point> allPoints = quadTree.getAllPoints();
        System.out.printf("\nВсего точек в дереве: %d%n", allPoints.size());
        for (Point point : allPoints) {
            System.out.printf("  %s%n", point);
        }
    }

    private static void clearTree() {
        quadTree.clear();
        System.out.println("Дерево очищено");
    }
}