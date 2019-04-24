package garden_planner.model;

public abstract class GardenBed {
    protected double left = 0.0;
    protected double top = 0.0;

    /**
     * Position of left edge of this shape.
     *
     * @return left edge, in metres
     */
    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    /**
     * Position of top edge of this shape.
     *
     * @return top edge, in metres
     */
    public double getTop() {
        return top;
    }

    public void setTop(double top) {
        this.top = top;
    }

    public abstract double getWidth();

    public abstract double getHeight();

    public abstract double getArea();

    public abstract double getPerimeter();


}
