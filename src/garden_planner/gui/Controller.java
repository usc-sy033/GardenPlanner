package garden_planner.gui;

import garden_planner.model.CircleBed;
import garden_planner.model.GardenBed;
import garden_planner.model.GardenPlanner;
import garden_planner.model.RectBed;
import garden_planner.textui.TextUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Controller {
    private GardenPlanner planner;

    public Controller() {
        planner = new GardenPlanner();
        planner.createBasicDesign();
    }

    @FXML
    private VBox col;

    @FXML
    private TextField wallLength;
    @FXML
    private TextField area;
    @FXML
    private TextField cost;

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
            CircleBed circ = new CircleBed();
            circ.getRadius();
            planner.recalculateTotals();

        }

        // Change statistics fields to be non-editable
        wallLength.setEditable(false);
        area.setEditable(false);
        cost.setEditable(false);

        // Print the statistics fields to the VBox TextFields
        wallLength.setText(String.format("Total wall length is: %8.2f m.\n", planner.getTotalWallLength(), "Cost: $", planner.getWallPrice()));
        area.setText(String.format("Total garden area is: %8.2f m2.\n", planner.getTotalGardenArea()));
        cost.setText(String.format("Total garden cost is: $%7.2f.\n", planner.getTotalCost()));
    }
}
