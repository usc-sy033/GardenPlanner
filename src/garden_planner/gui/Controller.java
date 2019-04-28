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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Controller {
    private GardenPlanner planner;
    double originalMouseX;
    double originalMouseY;


    public static final double metre = 50;

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

        refresh();

        // Change statistics fields to be non-editable
        wallLength.setEditable(false);
        wallCost.setEditable(false);
        area.setEditable(false);
        areaCost.setEditable(false);
        totalCost.setEditable(false);

        // Print the statistics fields to the VBox TextFields

    }


    //public void dragHandler(ActionEvent event){


    //}

    //public void mousePressedHandler(MouseEvent mouseEvent){
    //    originalMouseX = mouseEvent.getX();
    //    originalMouseY = mouseEvent.getY();
    //}

    //public void mouseDraggedHandler(double x, double y, GardenBed bed, Rectangle rect) {
    //    double offsetX = x - originalMouseX;
    //    double offsetY = y - originalMouseY;

    //    double newLeft = bed.getLeft() + offsetX / 50;
    //    double newTop = bed.getTop() + offsetY / 50;
    //   System.out.println(String.format("moving rect from %f,%f to %f,%f", bed.getLeft(), bed.getTop(), newLeft, newTop ));
    //  bed.setLeft(newLeft);
    //  bed.setTop(newTop);
    //   rect.setX(newLeft);
    //   rect.setY(newTop);
    // }

    private void refresh() {
        for (GardenBed bed : planner.getBeds()) {
            if (bed instanceof RectBed) {
                System.out.println("found bed: " + bed);
                Rectangle rect = new Rectangle();
                rect.getStyleClass().add("rectangle");
                rect.setWidth(bed.getWidth() * 50);
                rect.setHeight(bed.getHeight() * 50);
                rect.setX(bed.getLeft() * 50);
                rect.setY(bed.getTop() * 50);
                inner.getChildren().add(rect);
                rect.setOnMouseDragged(ev -> {
                    System.out.println("x=" + ev.getX() + " r=" + bed);
                    rect.setX(ev.getX());
                    rect.setY(ev.getY());
                    bed.setLeft(ev.getX() / metre);
                    bed.setTop(ev.getY() / metre);
                });

                //rect.setOnMousePressed(this::mousePressedHandler);
                //rect.setOnMouseDragged(ev -> mouseDraggedHandler(ev.getX(), ev.getY(), bed, rect));
            } else if (bed instanceof CircleBed) {
                System.out.println("found bed: " + bed);
                Circle circle = new Circle();
                circle.getStyleClass().add("circle");
                circle.setRadius((((CircleBed) bed).getRadius() * 50));
                circle.setCenterX(bed.getLeft() * 50);
                circle.setCenterY(bed.getTop() * 50);
                inner.getChildren().add(circle);
                circle.setOnMouseDragged(ev -> {
                    System.out.println("x=" + ev.getX() + " r=" + bed);
                    circle.setCenterX(ev.getX());
                    circle.setCenterY(ev.getY());
                    bed.setLeft(ev.getX() / metre);
                    bed.setTop(ev.getY() / metre);
                });
            }

            planner.recalculateTotals();
        }

        wallLength.setText(String.format("Total wall length: %8.2f m.", planner.getTotalWallLength()));
        wallCost.setText("" + planner.getWallPrice());

        area.setText(String.format("Total garden area: %8.2f m2.", planner.getTotalGardenArea()));
        areaCost.setText(String.format("Cost: $ %8.2f", planner.getSoilPrice()));

        totalCost.setText(String.format("Total garden cost: $ %7.2f", planner.getTotalCost()));
    }
}
