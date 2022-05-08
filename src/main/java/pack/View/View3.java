package pack.View;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import pack.Controller.Controller3;
import pack.View.Customs.*;
import pack.View.GraphView.Graph;

import java.util.ArrayList;
import java.util.Arrays;

import static pack.View.Customs.Custom.p;

public class View3 extends Pane implements iView {

    private final CustomTextField[][] fieldListRb1, fieldListRb2;

    public CustomRadioButton getRb1() {
        return rb1;
    }

    private GridPane fieldsPane;
    private final CustomRadioButton rb1, rb2;
    private final CustomButton btnStart, btnReset;
    private final Graph graph = new Graph();
    private final String[] signsRb1 = {"X: ", "S + ", "Y: ", "S + ", "Z: ", "S + "};
    private final String[] signsRb2 = {"X +", "Y +", "Z +", "= 0"};
    private final VBox vbUi;
    private final VBox vbPo;
    private VBox vbLeft;
    private VBox vbRight;

    private final Pane backgroundPane;
    Controller3 controller = new Controller3(this);

    public View3() {

        rb1 = new CustomRadioButton("Lines");
        rb2 = new CustomRadioButton("Planes");
        ToggleGroup group = new ToggleGroup();
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        btnStart = new CustomButton("START\nTHE\nMAGIK");
        btnStart.setDisable(true);
        btnReset = new CustomButton("RESET\nTHE\nMAGIK");

        fieldListRb1 = new CustomTextField[2][6];
        fieldListRb2 = new CustomTextField[2][4];
        fieldsPane = new GridPane();

        vbUi = new VBox();
        vbPo = new VBox();
        vbLeft = new VBox();
        vbRight = new VBox();


        this.backgroundPane = new Pane();


        setVbUi(setHbRadios(this.rb1, this.rb2));
        setVbPo();

        setVbLeft(setLeft(this.vbUi, setGraphPane(graph)));
        setVbRight(setRight(this.vbPo, setHbBottom(this.btnStart, this.btnReset)));

        setView3();
        setActions();
    }

    public void setView3() {
        this.setPrefSize(1050, 750);
        this.setStyle("-fx-background-color: #6F6F77;");    // Blue Grey
        this.getChildren().addAll(this.vbLeft, this.vbRight);
    }

    private void setVbLeft(VBox vbLeft) {
        this.vbLeft = vbLeft;
    }

    private void setVbRight(VBox vbRight) {
        this.vbRight = vbRight;
    }

    private void setVbUi(HBox hbRadios) {
        this.vbUi.setSpacing(5);
        this.vbUi.setPrefSize(500, 160);
        this.vbUi.setStyle("-fx-background-color: #333335");
        this.vbUi.getChildren().add(hbRadios);
    }

    private void setVbPo() {
        this.backgroundPane.setPrefSize(500, 580);
        BackgroundImage myBI = new BackgroundImage(new Image(p + "View3.png", 520, 580, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.backgroundPane.setBackground(new Background(myBI));
        this.vbPo.setPrefSize(500, 595);
        this.vbPo.setSpacing(15);
        this.vbPo.setAlignment(Pos.TOP_CENTER);
        this.vbPo.setStyle("-fx-background-color: #333335");
        this.vbPo.getChildren().addAll(Custom.setTitle("Planes and lines"), this.backgroundPane);
    }

    public Graph getGraph() {
        return graph;
    }

    public void handleStart(boolean isRb1Selected) {
        this.graph.reset();
        if (isRb1Selected) {
            for (CustomTextField[] customTextFields : fieldListRb1) {
                for (int j = 0; j < fieldListRb1[0].length; j++) {
                    if (customTextFields[j].getText().isBlank()) {
                        customTextFields[j].setText("0");
                    }
                }
            }
        }
        // for rb2
        else {
            for (CustomTextField[] customTextFields : fieldListRb2) {
                for (int j = 0; j < fieldListRb2[0].length; j++) {
                    if (customTextFields[j].getText().isBlank()) {
                        customTextFields[j].setText("0");
                    }
                }
            }
        }
        controller.getValues();
        controller.addElementsGraph();
        addOutput(controller);
    }

    public void addOutput(Controller3 controller) {
        this.backgroundPane.getChildren().clear();
        VBox vbSolutions = new VBox();
        VBox vbGeneric = new VBox();
        Circle[] legend = new Circle[] {new Circle(0), new Circle(10, Color.valueOf("#1985A1")), new Circle(10, Color.valueOf("#F2C15F"))};

        for (int i = 0; i < controller.GenericTexts().length; i++) {
            CustomText textX = new CustomText(controller.GenericTexts()[i].getText());
            textX.changeSize(15);
            HBox otherBox = new HBox(legend[i],new CustomText("   "), textX);
            vbGeneric.getChildren().addAll(otherBox);
        }
        for (int i = 0; i < controller.SolutionTexts().length; i++) {
            CustomText textX = new CustomText(controller.SolutionTexts()[i].getText());
            textX.changeSize(15);
            vbSolutions.getChildren().add(textX);
        }

        vbGeneric.setSpacing(15);
        vbGeneric.setLayoutX(10);
        vbGeneric.setLayoutY(130);

        vbSolutions.setSpacing(15);
        vbSolutions.setLayoutX(10);
        vbSolutions.setLayoutY(375);

        this.backgroundPane.getChildren().addAll(vbGeneric, vbSolutions);
    }

    public void handleReset() {
        this.graph.reset();
        this.getChildren().clear();
        btnStart.setDisable(false);
        rb1.setSelected(false);
        rb2.setSelected(false);
        this.vbUi.getChildren().remove(fieldsPane);
        this.backgroundPane.getChildren().clear();
        this.getChildren().addAll(this.vbLeft, this.vbRight);
    }

    public CustomRadioButton getRb2() {
        return rb2;
    }

    public void setActions() {
        this.graph.reset();
        rb1.setOnAction(event -> {
            this.btnStart.setDisable(false);
            fieldsPane = setFields(fieldListRb1, signsRb1, this.btnStart);
            this.vbUi.getChildren().clear();
            this.vbUi.getChildren().addAll(setHbRadios(rb1, rb2), fieldsPane);
        });
        rb2.setOnAction(event -> {
            this.btnStart.setDisable(false);
            fieldsPane = setFields(fieldListRb2, signsRb2, this.btnStart);
            this.vbUi.getChildren().clear();
            this.vbUi.getChildren().addAll(setHbRadios(rb1, rb2), fieldsPane);
        });
        this.btnStart.setOnAction(event -> handleStart(rb1.isSelected()));
        this.btnReset.setOnAction(event -> handleReset());
    }


    public GridPane setFields(CustomTextField[][] textFields, String[] signs, CustomButton btnStart) {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        int rows = textFields.length;
        int cols = textFields[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                HBox hbTextField = new HBox();
                hbTextField.setSpacing(10);

                textFields[i][j] = new CustomTextField();
                Label lblVariable = new Label();
                lblVariable.setStyle("-fx-text-fill: E7EBEE;");
                lblVariable.setFont(Custom.font);
                lblVariable.setText(signs[j]);

                if (signs[j].equals("S + ") && i == 1) {
                    lblVariable.setText("T + ");
                } else {
                    lblVariable.setText(signs[j]);
                }

                textFields[i][j].textProperty().addListener((observable, oldValue, newValue) -> btnStart.setDisable(checkFields(getFieldListRb1(), getFieldListRb2(), rb1.isSelected())));
                if (rb1.isSelected()) {
                    textFields[i][j].setPrefSize(40, 30);
                    hbTextField.getChildren().addAll(lblVariable, textFields[i][j]);
                } else {
                    hbTextField.getChildren().addAll(textFields[i][j], lblVariable);
                }
                gridPane.add(hbTextField, j, i);
            }
        }
        return gridPane;
    }

    public ArrayList<CustomTextField> getFieldListRb1() {
        ArrayList<CustomTextField> fieldList = new ArrayList<>();
        for (CustomTextField[] tfArray : this.fieldListRb1) {
            fieldList.addAll(Arrays.asList(tfArray));
        }
        return fieldList;
    }

    public ArrayList<CustomTextField> getFieldListRb2() {
        ArrayList<CustomTextField> fieldList = new ArrayList<>();
        for (CustomTextField[] tfArray : this.fieldListRb2) {
            fieldList.addAll(Arrays.asList(tfArray));
        }
        return fieldList;
    }
}