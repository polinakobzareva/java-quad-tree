package com.example.quadtree;


public class Boundary {
    private final double x;
    private final double y;
    private final double halfWidth;
    private final double halfHeight;

    public Boundary(double x, double y, double halfWidth, double halfHeight) {
        this.x = x;
        this.y = y;
        this.halfWidth = halfWidth;
        this.halfHeight = halfHeight;
    }

    public boolean contains(Point point) {
        return point.getX() >= x - halfWidth &&
                point.getX() <= x + halfWidth &&
                point.getY() >= y - halfHeight &&
                point.getY() <= y + halfHeight;
    }

    public boolean intersects(Boundary other) {
        return !(other.x - other.halfWidth > this.x + this.halfWidth ||
                other.x + other.halfWidth < this.x - this.halfWidth ||
                other.y - other.halfHeight > this.y + this.halfHeight ||
                other.y + other.halfHeight < this.y - this.halfHeight);
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getHalfWidth() { return halfWidth; }
    public double getHalfHeight() { return halfHeight; }

    @Override
    public String toString() {
        return String.format("Boundary(center=(%.2f, %.2f), size=%.2fx%.2f)",
                x, y, halfWidth * 2, halfHeight * 2);
    }
}