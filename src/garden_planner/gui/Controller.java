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
    private TextField wallCost;
    @FXML
    private TextField area;
    @FXML
    private TextField areaCost;
    @FXML
    private TextField totalCost;

    @FXML
    private Pane inner;

    @FXML
    public void button1Handler(ActionEvent event) {
        for (GardenBed bed : planner.getBeds()) {
            if (bed instanceof RectBed) {
                System.out.println("found bed: " + bed);
                Rectangle rect = new Rectangle();
                rect.setWidth(bed.getWidth() * 50);
                rect.setHeight(bed.getWidth() * 50);
                rect.setX(bed.getLeft() * 50);
                rect.setY(bed.getTop() * 50);
                inner.getChildren().add(rect);
            } else if (bed instanceof CircleBed) {
                System.out.println("found bed: " + bed);
                Circle circle = new Circle();
                circle.setRadius(((CircleBed) bed).getRadius() * 50);
                circle.setCenterX(bed.getLeft() * 50);
                circle.setCenterY(bed.getTop() * 50);
                inner.getChildren().add(circle);
            }

            planner.recalculateTotals();
        }

        // Change statistics fields to be non-editable
        wallLength.setEditable(false);
        wallCost.setEditable(false);
        area.setEditable(false);
        areaCost.setEditable(false);
        totalCost.setEditable(false);

        // Print the statistics fields to the VBox TextFields
        wallLength.setText(String.format("Total wall length: %8.2f m.", planner.getTotalWallLength() ));
        wallCost.setText("" + planner.getWallPrice());

        area.setText(String.format("Total garden area: %8.2f m2.", planner.getTotalGardenArea()));
        areaCost.setText(String.format("Cost: $ %8.2f", planner.getSoilPrice()));

        totalCost.setText(String.format("Total garden cost: $ %7.2f", planner.getTotalCost()));
    }
}
