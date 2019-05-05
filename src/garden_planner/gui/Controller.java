package garden_planner.gui;

import garden_planner.model.CircleBed;
import garden_planner.model.GardenBed;
import garden_planner.model.GardenPlanner;
import garden_planner.model.RectBed;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static final double METRE = 50;

    private GardenPlanner planner;

    private GardenBed selectedBed;
    private Rectangle selectedRectangle;
    private Circle selectedCircle;

    private ArrayList<GardenBed> drawnBeds = new ArrayList<>();

    public Controller() {
        planner = new GardenPlanner();
    }

    @FXML
    private TextField wallLength;
    @FXML
    private TextField totalWallLength;
    @FXML
    private TextField area;
    @FXML
    private TextField totalArea;
    @FXML
    private TextField totalCost;
    @FXML
    private TextField totalCostDollars;

    @FXML
    private Pane inner;

    public void button1Handler(ActionEvent event) {
        refresh();
    }

    public void button2Handler(ActionEvent event) {
        RectBed rect = new RectBed();

        rect.setHeight(2.0);
        rect.setWidth(2.0);
        rect.setLeft(5);
        rect.setTop(5);
        planner.getBeds().add(rect);

        refresh();
        drawnBeds.add(rect);
    }

    public void button3Handler(ActionEvent event) {
        CircleBed circle = new CircleBed();

        circle.setRadius(2);
        circle.setLeft(5);
        circle.setTop(5);
        planner.getBeds().add(circle);

        refresh();
        drawnBeds.add(circle);
    }

    public void button4Handler(ActionEvent event) {
        if (selectedBed != null) {
            if (selectedBed instanceof RectBed) {
                planner.getBeds().remove(selectedBed);
                inner.getChildren().remove(selectedRectangle);
            }
            if (selectedBed instanceof CircleBed) {
                planner.getBeds().remove(selectedBed);
                inner.getChildren().remove(selectedCircle);
            }
        }
        refresh();
    }

    private void refresh() {
        for (GardenBed bed : planner.getBeds()) {
            if (bed instanceof RectBed && !drawnBeds.contains(bed)) {
                Rectangle rect = new Rectangle();

                rect.getStyleClass().add("rectangle");
                Image rectImg = new Image("garden_planner/gui/rectbed.png");
                rect.setFill(new ImagePattern(rectImg));

                rect.setWidth(bed.getWidth() * METRE);
                rect.setHeight(bed.getHeight() * METRE);
                rect.setX(bed.getLeft() * METRE);
                rect.setY(bed.getTop() * METRE);
                inner.getChildren().add(rect);

                rect.setOnMouseClicked(ev -> {
                    selectedRectangle = rect;
                    selectedBed = bed;
                });

                rect.setOnMouseDragged(ev -> {
                    if (ev.getX() > rect.getX() + rect.getWidth() / 2 && ev.getY() > rect.getY() + rect.getHeight() / 2) {
                        rect.setWidth(ev.getX() - rect.getX());
                        rect.setHeight(ev.getY() - rect.getY());
                        ((RectBed) bed).setWidth(rect.getWidth() / METRE);
                        ((RectBed) bed).setHeight(rect.getHeight() / METRE);
                    } else {
                        rect.setX(ev.getX());
                        rect.setY(ev.getY());
                        bed.setLeft(ev.getX() / METRE);
                        bed.setTop(ev.getY() / METRE);
                    }
                });

            } else if (bed instanceof CircleBed && !drawnBeds.contains(bed)) {
                Circle circle = new Circle();

                circle.getStyleClass().add("rectangle");
                Image rectImg = new Image("garden_planner/gui/circlebed.png");
                circle.setFill(new ImagePattern(rectImg));

                circle.getStyleClass().add("circle");
                circle.setRadius((((CircleBed) bed).getRadius() * METRE));
                circle.setCenterX(bed.getLeft() * METRE);
                circle.setCenterY(bed.getTop() * METRE);
                inner.getChildren().add(circle);

                circle.setOnMouseClicked(ev -> {
                    selectedCircle = circle;
                    selectedBed = bed;
                });

                circle.setOnMouseDragged(ev -> {
                    if (ev.getX() > circle.getCenterX() + circle.getRadius() / 2 && ev.getY() > circle.getCenterY() + circle.getRadius() / 2) {
                        circle.setRadius(ev.getX() - circle.getCenterX());
                        circle.setRadius(ev.getY() - circle.getCenterY());
                        ((CircleBed) bed).setRadius(circle.getRadius() / METRE);
                    } else {
                        circle.setCenterX(ev.getX());
                        circle.setCenterY(ev.getY());
                        bed.setLeft(ev.getX() / METRE);
                        bed.setTop(ev.getY() / METRE);
                    }
                });
            }

            planner.recalculateTotals();
        }

        // Change statistics fields to be non-editable
        wallLength.setEditable(false);
        totalWallLength.setEditable(false);
        area.setEditable(false);
        totalArea.setEditable(false);
        totalCost.setEditable(false);

        //Todo fix Values of statistics
        wallLength.setText("Wall cost:");
        totalWallLength.setText("$" + Math.round(planner.getTotalWallLength() * planner.getWallPrice()));

        area.setText("Total area:");
        totalArea.setText("" + Math.round(planner.getTotalGardenArea()) + "m2");

        totalCost.setText("Total cost:");
        totalCostDollars.setText("$" + Math.round(planner.getTotalCost()));
    }

}
