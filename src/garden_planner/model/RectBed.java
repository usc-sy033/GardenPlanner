package garden_planner.model;

/**
 * Represents a rectangular shape garden.
 *
 * @author Mark Utting
 */
public class RectBed extends GardenBed {
	private double width = 1.0;
	private double height = 1.0;

	public RectBed() {
	}

	/**
	 * Total width of this shape.
	 *
	 * @return width in metres.
	 */
	@Override
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * Total height of this shape.
	 *
	 * @return height in metres.
	 */
	@Override
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Get the area of this shape.
	 *
	 * @return the total internal area of the shape.
	 */
	@Override
	public double getArea() {
		return width * height;
	}

	/**
	 * Get the perimeter of this shape.
	 *
	 * @return the total length of the edges of the shape.
	 */
	@Override
	public double getPerimeter() {
		return 2 * (width + height);
	}

	@Override
	public String toString() {
		return String.format("Rectangle %.2f %.2f %.2f %.2f", left, top, width, height);
	}
}

