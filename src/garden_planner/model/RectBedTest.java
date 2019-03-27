package garden_planner.model;

import org.junit.jupiter.api.Test;
import org.w3c.dom.css.Rect;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectBedTest {
    private RectBed rect;

    public void createTestRectBed() {
        rect = new RectBed();
        rect.setLeft(1);
        rect.setTop(2);
        rect.setWidth(12);
        rect.setHeight(12);
    }

    @Test
    public void testGetArea() {
       createTestRectBed();
       assertEquals(rect.getArea(), 144);
    }

    @Test
    public void testGetPerimeter() {
        createTestRectBed();
        assertEquals(rect.getPerimeter(), 2 * (rect.getWidth() + rect.getHeight()));
    }

    @Test
    public void testToString() {
        createTestRectBed();
        assertEquals(rect.toString(), (String.format("Rectangle %.2f %.2f %.2f %.2f", rect.getLeft(), rect.getTop(), rect.getWidth(), rect.getHeight())));
    }
}
