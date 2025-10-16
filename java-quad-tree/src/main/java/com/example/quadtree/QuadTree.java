package com.example.quadtree;

import java.util.List;

public class QuadTree {
    private QuadTreeNode root;

    public QuadTree(Boundary boundary) {
        this.root = new QuadTreeNode(boundary);
    }

    public boolean insert(Point point) {
        return root.insert(point);
    }

    public boolean insert(double x, double y) {
        return insert(new Point(x, y));
    }

    public List<Point> query(Boundary range) {
        return root.query(range);
    }

    public List<Point> query(double centerX, double centerY, double halfWidth, double halfHeight) {
        return query(new Boundary(centerX, centerY, halfWidth, halfHeight));
    }

    public List<Point> getAllPoints() {
        return root.getAllPoints();
    }

    public void clear() {
        this.root = new QuadTreeNode(root.getBoundary());
    }

    public QuadTreeNode getRoot() {
        return root;
    }
}