package garden_planner.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircleBedTest {
    private CircleBed circle = new CircleBed();

    @Test
    public void testCircle() {
        circle.setRadius(4.0);

        assertEquals(circle.getRadius(), 4.0);
        assertEquals(circle.getWidth(), 8.0);
        assertEquals(circle.getHeight(), 8.0);
        assertEquals(circle.getArea(), 50.26548245743669);
        assertEquals(circle.getPerimeter(), 25.132741228718345);
    }
}
