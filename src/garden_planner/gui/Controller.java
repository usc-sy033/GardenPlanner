package garden_planner.gui;

import garden_planner.model.CircleBed;
import garden_planner.model.GardenBed;
import garden_planner.model.GardenPlanner;
import garden_planner.model.RectBed;
import garden_planner.textui.TextUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
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
    Rectangle selectedRectangle;
    GardenBed selectedBed;
    Circle selectedCircle;


    private static final double metre = 50;

    public Controller() {
        planner = new GardenPlanner();
        //planner.createBasicDesign();
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
   public void button2Handler(ActionEvent event) {


       RectBed rect = new RectBed();
       System.out.println("found bed: ");

       rect.setHeight(2.0);
       rect.setWidth(2.0);
       rect.setLeft(5);
       rect.setTop(5);
       planner.getBeds().add(rect);


       refresh();
    }
   public void button3Handler(ActionEvent event) {

       CircleBed circle = new CircleBed();

       circle.setRadius(2);
       circle.setLeft(5);
       circle.setTop(5);
       planner.getBeds().add(circle);


       refresh();
    }

    public void button4Handler(ActionEvent event) {


                    System.out.println("delete bed");
                    if (selectedBed != null ) {
                        if (selectedBed instanceof RectBed) {
                            planner.getBeds().remove(selectedBed);
                            inner.getChildren().remove(selectedRectangle);
                        }
                        if (selectedBed instanceof CircleBed) {
                            planner.getBeds().remove(selectedBed);
                            inner.getChildren().remove(selectedCircle);
                        }
                    }

          // //
           //         rect.setX(ev.getX());
          //          rect.setY(ev.getY());
          //          bed.setLeft(ev.getX() / metre);
           //         bed.setTop(ev.getY() / metre);


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
                rect.setWidth(bed.getWidth() * metre);
                rect.setHeight(bed.getHeight() * metre);
                rect.setX(bed.getLeft() * metre);
                rect.setY(bed.getTop() * metre);
                inner.getChildren().add(rect);
                rect.setOnMouseClicked(ev -> {
                    selectedRectangle = rect;
                    selectedBed = bed;

                });

                rect.setOnMouseDragged(ev -> {
                    System.out.println("x=" + ev.getX() + " r=" + bed);
                    if (ev.getX() > rect.getX() + rect.getWidth() / 2 && ev.getY() > rect.getY() + rect.getHeight() / 2){
                        rect.setWidth(ev.getX() - rect.getX());
                        rect.setHeight(ev.getY() - rect.getY());
                    }
                    else{
                        rect.setX(ev.getX());
                        rect.setY(ev.getY());
                        bed.setLeft(ev.getX() / metre);
                        bed.setTop(ev.getY() / metre);
                    }


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
                circle.setOnMouseClicked(ev -> {
                    selectedCircle = circle;
                    selectedBed = bed;

                });
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
        //Todo fix Values of statistics
        wallLength.setText(String.format("Total wall length: %8.2f m.", planner.getTotalWallLength()));
        wallCost.setText("" + planner.getWallPrice());

        area.setText(String.format("Total garden area: %8.2f m2.", planner.getTotalGardenArea()));
        areaCost.setText(String.format("Cost: $ %8.2f", planner.getSoilPrice()));

        totalCost.setText(String.format("Total garden cost: $ %7.2f", planner.getTotalCost()));
    }
  /**  public class DragResizer {

        /**
         * The margin around the control that a user can click in to start resizing
         * the region.
         */
     /**   private static final int RESIZE_MARGIN = 5;

        private final Rectangle region;

        private double y;

        private boolean initMinHeight;

        private boolean dragging;

        private DragResizer(Region aRegion) {
            region = aRegion;
        }

        public static void makeResizable(Region region) {
            final DragResizer resizer = new DragResizer(region);

            region.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    resizer.mousePressed(event);
                }});
            region.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    resizer.mouseDragged(event);
                }});
            region.setOnMouseMoved(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    resizer.mouseOver(event);
                }});
            region.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    resizer.mouseReleased(event);
                }});
        }

        protected void mouseReleased(MouseEvent event) {
            dragging = false;
            region.setCursor(Cursor.DEFAULT);
        }

        protected void mouseOver(MouseEvent event) {
            if(isInDraggableZone(event) || dragging) {
                region.setCursor(Cursor.S_RESIZE);
            }
            else {
                region.setCursor(Cursor.DEFAULT);
            }
        }

        protected boolean isInDraggableZone(MouseEvent event) {
            return event.getY() > (region.getHeight() - RESIZE_MARGIN);
        }

        protected void mouseDragged(MouseEvent event) {
            if(!dragging) {
                return;
            }

            double mousey = event.getY();

            double newHeight = region.getMinHeight() + (mousey - y);

            region.setMinHeight(newHeight);

            y = mousey;
        }

        protected void mousePressed(MouseEvent event) {

            // ignore clicks outside of the draggable margin
            if(!isInDraggableZone(event)) {
                return;
            }

            dragging = true;

            // make sure that the minimum height is set to the current height once,
            // setting a min height that is smaller than the current height will
            // have no effect
            if (!initMinHeight) {
                region.setMinHeight(region.getHeight());
                initMinHeight = true;
            }

            y = event.getY();
        }
    }*/
}
