package pack.View;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pack.Controller.Controller1;
import pack.Model.Model2for2x2;
import pack.Model.Model2for3x3;
import pack.Model.ModelForJSON;
import pack.View.Customs.*;
import javafx.scene.control.Label;
import pack.View.GraphView.Graph;
import java.util.ArrayList;
import java.util.Arrays;

import static pack.View.Customs.Custom.p;

public interface iView {

    ArrayList<CustomTextField> copyArray = new ArrayList<>();

    /**
     * This method is the one that is called by View1, View2 and View3. It sets both the left and right pane and keeps
     * track of all the needed parameters for that specific view. This method is the one that sets off the chain reaction
     * of setting up the rest of the User Interface for a certain View.
     * @param rb1 the first RadioButton
     * @param rb2 the second RadioButton
     * @param btnStart the start Button
     * @param btnReset the reset Button
     * @param signsRb1 an array of labels to add with the TextFields for rb1
     * @param signsRb2 an array of labels to add with the TextFields for rb2
     * @param title the title of the right pane
     * @param graph the graph object
     * @return a Pane object containing the whole UI of that view
     */
    default Pane setView(CustomRadioButton rb1, CustomRadioButton rb2, CustomButton btnStart, CustomButton btnReset,
                         String[] signsRb1, String[] signsRb2, String title, Graph graph, ComboBox comboBox) {
        Pane pane = new Pane();
        pane.setPrefSize(1050, 750);
        pane.setStyle("-fx-background-color: #6F6F77;");    // Blue Grey
        pane.getChildren().addAll(setLeft(rb1, rb2, btnStart, signsRb1, signsRb2, graph, comboBox), setRight(title, btnStart, btnReset));

        btnReset.setOnAction(event -> {
            pane.getChildren().clear();
            btnStart.setDisable(false);
            rb1.setSelected(false);
            rb2.setSelected(false);
            pane.getChildren().addAll(setLeft(rb1, rb2, btnStart, signsRb1, signsRb2, graph, comboBox), setRight(title, btnStart, btnReset));
        });

        return pane;
    }

    default ImageView setLogo() {
        ImageView iv = new ImageView(new Image(p + "Logo.png"));
        iv.setFitWidth(225);
        iv.setFitHeight(105);
        return iv;
    }

    default VBox setLeft(CustomRadioButton rb1, CustomRadioButton rb2, CustomButton btnStart, String[] signsRb1, String[] signsRb2,
                         Graph graph, ComboBox comboBox) {
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
        vbUi.getChildren().add(setRadios(rb1, rb2, btnStart, signsRb1, signsRb2, comboBox));

        // Graph Box
        Pane graphPane = new Pane();
        graphPane.getChildren().add(graph);
        graphPane.setPrefSize(500, 525);
        graphPane.setStyle("-fx-background-color: #333335");    // Grey

        vbLeft.getChildren().addAll(vbUi, graphPane);

        return vbLeft;
    }

    default VBox setRadios(CustomRadioButton rb1, CustomRadioButton rb2, CustomButton btnStart, String[] signsRb1, String[] signsRb2, ComboBox comboBox) {

        VBox vbRadioBox = new VBox();
        vbRadioBox.setPrefSize(500, 160);

        HBox hbRadios = new HBox();
        hbRadios.setSpacing(20);
        hbRadios.setPrefWidth(115);
        hbRadios.getChildren().addAll(rb1, rb2);
        vbRadioBox.getChildren().addAll(hbRadios, comboBox);

        if (!rb1.isSelected() || !rb2.isSelected()) {
            comboBox.setDisable(true);
        }

        rb1.setOnAction(event -> {
            vbRadioBox.getChildren().clear();
            vbRadioBox.getChildren().addAll(hbRadios, setFields(2, 3, btnStart, signsRb1, comboBox));
            comboBox.setDisable(false);
        });

        rb2.setOnAction(event -> {
            vbRadioBox.getChildren().clear();
            vbRadioBox.getChildren().addAll(hbRadios, setFields(3, 4, btnStart, signsRb2, comboBox));
            comboBox.setDisable(false);
        });
        return vbRadioBox;
    }

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

    default GridPane setFields(int rows, int cols, CustomButton btnStart, String[] signs, ComboBox comboBox) {

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

                comboBox.setOnAction(event -> {
                    if (comboBox.getValue().equals("Identity")) {
                        System.out.println("YOOO this is the first item in the komobokobox");
                    }
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
                    controller1.printOutput();
                }
                break;
            case 2:

                /*
                if(View2.getR3().isSelected()) {
                    Model2for3x3 et = new Model2for3x3(Double.parseDouble(View2.getT1().getText()), Double.parseDouble(View2.getT2().getText()), Double.parseDouble(View2.getT3().getText()), Double.parseDouble(View2.getT4().getText()), Double.parseDouble(View2.getT5().getText()), Double.parseDouble(View2.getT6().getText()), Double.parseDouble(View2.getT7().getText()), Double.parseDouble(View2.getT8().getText()), Double.parseDouble(View2.getT9().getText()));
                    System.out.println("This is 3x3");
                    System.out.println("Vector of the eigen value " + et.getX1() + ": " + Arrays.toString(et.getS1()));
                    System.out.println("Vector of the eigen value " + et.getX2() + ": " + Arrays.toString(et.getS2()));
                    System.out.println("Vector of the eigen value " + et.getX3() + ": " + Arrays.toString(et.getS3()));
                    for (Double d : et.getS1()) {
                        View2.getStr().add(d.toString());
                    }
                    for (Double d : et.getS2()) {
                        View2.getStr2().add(d.toString());
                    }
                    for (Double d : et.getS3()) {
                        View2.getStr3().add(d.toString());
                    }
                }

                if(View2.getR2().isSelected()) {

                    Model2for2x2 et1 = new Model2for2x2(Double.parseDouble(View2.getT1().getText()), Double.parseDouble(View2.getT2().getText()), Double.parseDouble(View2.getT4().getText()), Double.parseDouble(View2.getT5().getText()));
                    System.out.println("This is 2x2");
                    System.out.println("Vector of the eigen value " + et1.getX1() + ": " + Arrays.toString(et1.getS1()));
                    System.out.println("Vector of the eigen value " + et1.getX2() + ": " + Arrays.toString(et1.getS2()));
                    for (Double d : et1.getS1()) {
                        View2.getStr().add(d.toString());
                    }
                    for (Double d : et1.getS2()) {
                        View2.getStr2().add(d.toString());
                    }
                }

                 */

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
