package com.example.quadtree;

import java.util.ArrayList;
import java.util.List;

public class QuadTreeNode {
    private static final int CAPACITY = 4;

    private final Boundary boundary;
    private final List<Point> points;
    private QuadTreeNode[] children;

    public QuadTreeNode(Boundary boundary) {
        this.boundary = boundary;
        this.points = new ArrayList<>();
        this.children = null;
    }

    public boolean insert(Point point) {
        if (!boundary.contains(point)) {
            return false;
        }

        if (points.size() < CAPACITY && children == null) {
            points.add(point);
            return true;
        }

        if (children == null) {
            split();
        }

        for (QuadTreeNode child : children) {
            if (child.insert(point)) {
                return true;
            }
        }

        return false;
    }

    private void split() {
        double x = boundary.getX();
        double y = boundary.getY();
        double hw = boundary.getHalfWidth() / 2;
        double hh = boundary.getHalfHeight() / 2;

        children = new QuadTreeNode[4];
        children[0] = new QuadTreeNode(new Boundary(x - hw, y - hh, hw, hh));
        children[1] = new QuadTreeNode(new Boundary(x + hw, y - hh, hw, hh));
        children[2] = new QuadTreeNode(new Boundary(x - hw, y + hh, hw, hh));
        children[3] = new QuadTreeNode(new Boundary(x + hw, y + hh, hw, hh));

        for (Point point : points) {
            for (QuadTreeNode child : children) {
                if (child.insert(point)) {
                    break;
                }
            }
        }
        points.clear();
    }

    public List<Point> query(Boundary range) {
        List<Point> found = new ArrayList<>();

        if (!boundary.intersects(range)) {
            return found;
        }

        for (Point point : points) {
            if (range.contains(point)) {
                found.add(point);
            }
        }

        if (children != null) {
            for (QuadTreeNode child : children) {
                found.addAll(child.query(range));
            }
        }

        return found;
    }

    public List<Point> getAllPoints() {
        return query(new Boundary(boundary.getX(), boundary.getY(),
                boundary.getHalfWidth(), boundary.getHalfHeight()));
    }


    public Boundary getBoundary() { return boundary; }
    public List<Point> getPoints() { return points; }
    public QuadTreeNode[] getChildren() { return children; }
}