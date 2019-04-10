package garden_planner.model;

public class CircleBed extends GardenBed {
    private double radius;

    public CircleBed() {
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius * 2;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double r) {
        this.radius = r;
    }

    @Override
    public String toString() {
        return String.format("Circle %.2f %.2f %.2f", left, top, radius);
    }
}
