package pack.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pack.Controller.V3Controller;
import pack.Model.CustomButton;
import pack.Model.mainModel;
import javafx.scene.control.Label;
import pack.View.GraphView.Graph;
import java.util.ArrayList;
import java.util.Random;
//import org.apache.commons.lang3.math.NumberUtils;



import static pack.Model.mainModel.p;

public interface iView {
    ArrayList<TextField> a = createFields(6); //fields for 2by2
    ArrayList<TextField> b = createFields(12); //fields for 3by3
    Button btnStart = new CustomButton("Start\nthe\n MAGIK");

    //All these are just UI it sets the panes and nodes on the right place
    default HBox setButtons() {
        btnStart.setMinSize(115, 105);
        Button btnReset = new CustomButton("Reset\nthe\nMAGIK");
        btnReset.setMinSize(115, 105);
        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10);
        hbButtons.getChildren().addAll(btnStart, btnReset);
        btnStart.setOnAction(e -> System.out.println("Patate"));
        btnReset.setOnAction(e -> System.out.println("Reset patate"));
        return hbButtons;
    }

    default ImageView setLogo() {
        ImageView iv = new ImageView(new Image(p + "Logo.png"));
        iv.setFitWidth(225);
        iv.setFitHeight(105);
        return iv;
    }

    default VBox setLeft() {
        VBox vbLeft = new VBox();
        vbLeft.setSpacing(10);
        vbLeft.setPrefSize(500, 695);
        vbLeft.setLayoutX(10);
        vbLeft.setLayoutY(14);

        // User Input Box
        VBox vbUi = new VBox();
        vbUi.setSpacing(15);
        vbUi.setPrefSize(500, 160);
        vbUi.setStyle("-fx-background-color: #333335"); // Grey
        return vbLeft;
    }

    public static VBox setLeft(RadioButton r1, RadioButton r2, GridPane g1, GridPane g2, Node setR) {
        VBox vbLeft = new VBox();
        vbLeft.setSpacing(10);
        vbLeft.setPrefSize(500, 695);
        vbLeft.setLayoutX(10);
        vbLeft.setLayoutY(14);

        // User Input Box
        VBox vbUi = new VBox();
        vbUi.setSpacing(15);
        vbUi.setPrefSize(500, 160);
        vbUi.setStyle("-fx-background-color: #333335"); // Grey
        vbUi.getChildren().addAll(setR);

        r1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (r1.isSelected()) {
                    vbUi.getChildren().clear();
                    vbUi.getChildren().addAll(setR);
                    vbUi.getChildren().add(g1);
                }
            }
        });

        r2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (r2.isSelected()) {
                    vbUi.getChildren().clear();
                    vbUi.getChildren().addAll(setR);
                    vbUi.getChildren().add(g2);
                }
            }
        });

        // Graph Box
        Graph actualGraph = new Graph();
        Pane graph = new Pane();
        graph.getChildren().add(actualGraph);
        //actualGraph.addLine(new Point3D(12, 42, 65), new Point3D(9, 45, -15));
        graph.setPrefSize(500, 525);
        graph.setStyle("-fx-background-color: #333335");
        vbLeft.getChildren().addAll(vbUi, graph);
        return vbLeft;
    }

    default VBox setRight(String title) {
        VBox vbRight = new VBox();
        vbRight.setPrefSize(500, 695);
        vbRight.setLayoutX(520);
        vbRight.setLayoutY(14);
        vbRight.setSpacing(10);

        // Program Output Box
        VBox vbPo = new VBox();
        vbPo.setPrefSize(500, 595);
        vbPo.setSpacing(15);
        vbPo.setAlignment(Pos.TOP_CENTER);
        vbPo.setStyle("-fx-background-color: #333335");
        vbPo.getChildren().add(mainModel.setTitle(title));

        // HBox to hold buttons and logo
        HBox hbBottom = new HBox();
        hbBottom.setMaxSize(500, 105);
        hbBottom.setSpacing(55);
        hbBottom.getChildren().addAll(setButtons(), setLogo());

        vbRight.getChildren().addAll(vbPo, hbBottom);
        return vbRight;
    }

    default Pane setView(String title, Pane p) {
        Pane pane = new Pane();
        pane.setPrefSize(1050, 750);
        pane.setStyle("-fx-background-color: #6F6F77;");    // Blue Grey
        pane.getChildren().add(p);
        pane.getChildren().add(setRight(title));
        return pane;
    }

    //Creates the 2x2 and 3x3 radiobutton and connects them to a ToggleGroup so only one can be selected at a time
    default Node setRadios(RadioButton r1, RadioButton r2) {
        ToggleGroup size = new ToggleGroup();
        r1.setStyle("-fx-text-fill: E7EBEE;");
        r1.setToggleGroup(size);
        r2.setStyle("-fx-text-fill: E7EBEE;");
        r2.setToggleGroup(size);
        HBox radios = new HBox();
        radios.setSpacing(20);
        radios.getChildren().addAll(r1, r2);
        return radios;
    }


    //set2Fields and 3Fields just arranges the textfield and the signs on the gridpane
    public static GridPane set2Fields() {
        Label l = new Label("=");
        Label l2 = new Label("=");
        l.setStyle("-fx-text-fill: E7EBEE;");
        l2.setStyle("-fx-text-fill: E7EBEE;");
        GridPane twoByTwo = new GridPane();
        twoByTwo.setVgap(10);
        twoByTwo.setHgap(10);
        twoByTwo.setAlignment(Pos.BOTTOM_CENTER);
        ArrayList sL = createSigns(2);

        int acounter = 0; //max 5
        int sLcounter = 0; //max 3
        int row = 0; //max 2
        int column = 0; //max
        int n = 0;

        while (row != 2) {
            while (column != 5) {
                if (column % 2 == 0) {
                    if (acounter <= 2 + 3 * n) {
                        twoByTwo.add((Node) a.get(acounter), column, row);
                        acounter++;
                        column++;
                    }
                }

                if (column % 2 == 1) {
                    if (sLcounter <= 1 + 2 * n) {
                        twoByTwo.add((Node) sL.get(sLcounter), column, row);
                        sLcounter++;
                        column++;
                    }
                }
            }
            n++;
            row++;
            column = 0;
            checkFields(a);
        }
        return twoByTwo;
    }


    default GridPane set3Fields(int maxrow, int maxcolumn, ArrayList rep) {
        GridPane threebyThree = new GridPane();
        threebyThree.setAlignment(Pos.BOTTOM_CENTER);
        threebyThree.setVgap(10);
        threebyThree.setHgap(10);

        int acounter = 0; //max 11
        int sLcounter = 0; //max 8
        int row = 0; //max 2
        int column = 0; //max 6
        int n = 0;

        ArrayList sL = createSigns(3);

        while (row != maxrow) {
            while (column != maxcolumn) {
                if (column % 2 == 0) {
                    if (acounter <= 3 + 4 * n) {
                        threebyThree.add((Node) rep.get(acounter), column, row);
                        acounter++;
                        column++;
                    }
                }

                if (column % 2 == 1) {
                    if (sLcounter <= 2 + 3 * n) {
                        threebyThree.add((Node) sL.get(sLcounter), column, row);
                        sLcounter++;
                        column++;
                    }
                }
            }
            n++;
            row++;
            column = 0;
            checkFields(rep);
        }

        return threebyThree;
    }

    //This function creates the fields which is 6 fields for the 2by2 and 12 for the 3by3
    public static ArrayList<TextField> createFields(int tf) {
        //  int field=tf*(tf+1);
        ArrayList<TextField> fieldList = new ArrayList<TextField>(tf - 1); //For 2 is 6 and for 3 is 12
        int counter = 1;
        while (counter <= tf) {
            TextField t = new TextField();
            t.setPrefSize(80, 25);
            t.setOnAction(e -> {
                checkFields(fieldList);
            });
            fieldList.add(t);
            counter++;
        }
        return fieldList;
    }

    //Create signs x,y, z and = to add to the pane (UI related)
    public static ArrayList createSigns(int i) {
        ArrayList<Label> signs = new ArrayList<Label>();

        if (i == 2) {
            for (int j = 0; j < i; j++) {
                Label x = new Label("X +");
                Label y = new Label("Y =");
                signs.add(x);
                signs.add(y);
            }
        }

        if (i == 3) {
            for (int j = 0; j < i; j++) {
                Label x = new Label("X +");
                Label y = new Label("Y +");
                Label z = new Label("Z =");
                signs.add(x);
                signs.add(y);
                signs.add(z);
            }
        }

        int c = 0;
        while (c != signs.size()) {
            signs.get(c).setStyle("-fx-text-fill: E7EBEE;");
            c++;
        }
        return signs;
    }

    public static void checkFields(ArrayList<TextField> a) {
        ArrayList<Boolean> booleans = new ArrayList<Boolean>();

        int i = 0;
        int counter = 0;

        while (i != a.size()) {
            TextField t = a.get(i);
            if (t.getText().isEmpty()) {
                t.setStyle(" -fx-control-inner-background:#A0A0A0;");
            }

            if (!t.getText().isEmpty()) {
                if (isNumeric(t.getText())) {
                    t.setStyle(" -fx-control-inner-background:#A0A0A0;");
                    booleans.add(true);
                }

                if (!isNumeric(t.getText())) {
                    t.setStyle(" -fx-control-inner-background: red;");
                    counter--;
                    booleans.add(false);

                }

            }
            i++;
        }

        if (booleans.contains(false)) {
            btnStart.setDisable(true);
        }

        if (!booleans.contains(false)) {
            btnStart.setDisable(false);
        }

    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static void handleButton(int i) {
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
        switch (i) {
            case 1:

            case 2:

            case 3:

                if(View3.r1.isSelected()) {
                System.out.println( "ffjifjkafok"   );
                }



                if(View3.r2.isSelected()) {
                Random rn = new Random();
                 int answer = rn.nextInt(10) + 1;
                System.out.println("View3 math is set on action babyyyy");
                V3Controller.transform(View3.c);
                V3Controller.crossProduct();
                V3Controller.point(answer);
                break; }



        }



        }
            });


    }
}






