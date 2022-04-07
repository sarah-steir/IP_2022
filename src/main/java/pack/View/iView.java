package pack.View;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pack.Controller.Controller1;
import pack.View.Customs.*;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;

import static pack.View.Customs.Custom.p;

public interface iView {

    ArrayList<CustomTextField> copyArray = new ArrayList<>();

    default ImageView setLogo() {
        ImageView iv = new ImageView(new Image(p + "Logo.png"));
        iv.setFitWidth(225);
        iv.setFitHeight(105);
        return iv;
    }

    default VBox setLeft(CustomRadioButton rb1, CustomRadioButton rb2, CustomButton btnStart, String[] signsRb1, String[] signsRb2) {
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
        vbUi.getChildren().add(setRadios(rb1, rb2, btnStart, signsRb1, signsRb2));

        // Graph Box
        Pane graph = new Pane();
        graph.setPrefSize(500, 525);
        graph.setStyle("-fx-background-color: #333335");    // Grey

        vbLeft.getChildren().addAll(vbUi, graph);

        return vbLeft;
    }

    default VBox setRadios(CustomRadioButton rb1, CustomRadioButton rb2, CustomButton btnStart, String[] signsRb1, String[] signsRb2) {

        VBox vbRadioBox = new VBox();
        vbRadioBox.setPrefSize(500, 160);

        HBox hbRadios = new HBox();
        hbRadios.setSpacing(20);
        hbRadios.setPrefWidth(115);
        hbRadios.getChildren().addAll(rb1, rb2);
        vbRadioBox.getChildren().add(hbRadios);

        rb1.setOnAction(event -> {
            vbRadioBox.getChildren().clear();
            vbRadioBox.getChildren().addAll(hbRadios, setFields(2, 3, btnStart, signsRb1));
        });

        rb2.setOnAction(event -> {
            vbRadioBox.getChildren().clear();
            vbRadioBox.getChildren().addAll(hbRadios, setFields(3, 4, btnStart, signsRb2));
        });
        return vbRadioBox;
    }

    /*public static VBox setLeft(RadioButton r1, RadioButton r2, GridPane g1, GridPane g2, Node setR, Graph hraph) {
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

        Pane graph = new Pane();
        graph.getChildren().add(hraph);
        // hraph.addLine(new Point3D(12, 42, 65), new Point3D(9, 45, -15));
        graph.setPrefSize(500, 525);
        graph.setStyle("-fx-background-color: #333335");
        vbLeft.getChildren().addAll(vbUi, graph);
        return vbLeft;
    }*/

    default HBox setButtons(CustomButton btnStart, CustomButton btnReset) {
        btnStart.setMinSize(115, 105);
        btnReset.setMinSize(115, 105);

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10);
        hbButtons.getChildren().addAll(btnStart, btnReset);
        return hbButtons;
    }

    default VBox setRight(String title, CustomButton btnStart, CustomButton btnReset) {
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
        vbPo.getChildren().add(Custom.setTitle(title));

        // HBox to hold buttons and logo
        HBox hbBottom = new HBox();
        hbBottom.setMaxSize(500, 105);
        hbBottom.setSpacing(55);
        hbBottom.getChildren().addAll(setButtons(btnStart, btnReset), setLogo());

        vbRight.getChildren().addAll(vbPo, hbBottom);
        return vbRight;
    }

    default Pane setView(CustomRadioButton rb1, CustomRadioButton rb2, CustomButton btnStart, CustomButton btnReset,
                         String[] signsRb1, String[] signsRb2, String title) {
        Pane pane = new Pane();
        pane.setPrefSize(1050, 750);
        pane.setStyle("-fx-background-color: #6F6F77;");    // Blue Grey
        pane.getChildren().addAll(setLeft(rb1, rb2, btnStart, signsRb1, signsRb2), setRight(title, btnStart, btnReset));

        btnReset.setOnAction(event -> {
            pane.getChildren().clear();
            btnStart.setDisable(false);
            rb1.setSelected(false);
            rb2.setSelected(false);
            pane.getChildren().addAll(setLeft(rb1, rb2, btnStart, signsRb1, signsRb2), setRight(title, btnStart, btnReset));
        });

        return pane;
    }

    default GridPane setFields(int rows, int cols, CustomButton btnStart, String[] signs) {

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        CustomTextField[][] fieldList = new CustomTextField[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols;  j++) {
                HBox hbTextField = new HBox();
                hbTextField.setSpacing(10);

                fieldList[i][j] = new CustomTextField();
                Label lblVariable = new Label();
                lblVariable.setStyle("-fx-text-fill: E7EBEE;");
                lblVariable.setFont(Custom.font);
                if (j == cols - 1) {
                    lblVariable.setText("");
                } else {
                    lblVariable.setText(signs[j]);
                }

                int finalI = i;
                int finalJ = j;

                btnStart.setOnAction(event -> {
                    boolean isInvalid = false;
                    for (CustomTextField[] tfArray: fieldList) {
                        for (CustomTextField tf: tfArray) {
                            if (tf.checkField()) {
                                btnStart.setDisable(true);
                                isInvalid = true;
                            } else {
                                copyArray.add(tf);
                                isInvalid = false;
                            }
                        }
                    }
                    handleStart(getWhichView(signs), isInvalid);
                });

                fieldList[i][j].textProperty().addListener((observable, oldValue, newValue) ->
                        btnStart.setDisable(fieldList[finalI][finalJ].checkField()));

                hbTextField.getChildren().addAll(fieldList[i][j], lblVariable);
                gridPane.add(hbTextField, j, i);
            }
        }
        return gridPane;
    }

    default int getWhichView(String[] signs) {
        if (Arrays.equals(signs, new String[]{"X +", "Y ="}) || Arrays.equals(signs, new String[]{"X +", "Y +", "Z ="})) {
            return 1;
        } else {
            return 3;
        }
    }

    default ArrayList<CustomTextField> getCopyArray() {
        return copyArray;
    }

    default void handleStart (int i, boolean isInvalid) {
        switch (i) {
            case 1:
                if (!isInvalid) {
                    Controller1 controller1 = new Controller1();
                    controller1.setFieldList(getCopyArray());
                    controller1.transform();
                    //cits into Tayba from master ontroller1.getOutput();
                }
                break;
            case 2:

            case 3:

//                if (View3.r1.isSelected()) {
//                    //  System.out.println(V3Controller.checkzeros(View3.arlines));
//                    //  View3.transform();
//                    //View3.toMatrix();
//
//                }
//
//
//                if (View3.r2.isSelected()) {
//                    Random rn = new Random();
//                    int answer = rn.nextInt(10) + 1;
//                    //V3Controller.transform(View3.arplanes);
//                    V3Controller.crossProduct();
//                    V3Controller.point(answer);
//                    //View3.graph3.addLine(V3Controller.point(95),V3Controller.point(-10));
//                    View3.graph3.addPoint(new Point3D(5, 5, 5));
//
//                    break;
//                }
        }
    }
}
