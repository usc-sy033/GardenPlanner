package garden_planner.gui;

import garden_planner.model.GardenBed;
import garden_planner.model.GardenPlanner;
import garden_planner.model.RectBed;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Controller {
    private GardenPlanner planner;

    public Controller() {
        planner = new GardenPlanner();
        planner.createBasicDesign();
    }

    @FXML
    private Pane inner;

    @FXML
    public void button1Handler(ActionEvent event) {
        System.out.println("Clicked!");
        for (GardenBed bed : planner.getBeds()) {
            System.out.println("found bed: " + bed);
            Rectangle rect = new Rectangle();
            rect.setWidth(bed.getWidth() * 100);
            rect.setHeight(bed.getWidth() * 100);
            rect.setX(bed.getLeft() * 100);
            rect.setY(bed.getTop()*100);
            inner.getChildren().add(rect);
        }
    }
}
