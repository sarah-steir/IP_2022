package pack.View;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pack.Controller.Controller3;
import pack.Model.Model3;
import pack.View.Customs.Custom;
import pack.View.Customs.CustomButton;
import pack.View.Customs.CustomRadioButton;
import pack.View.Customs.CustomTextField;
import pack.View.GraphView.Graph;

import java.util.ArrayList;

public class View3 extends Pane implements iView {

    private CustomTextField[][] fieldListRb1, fieldListRb2;
    private GridPane fieldsPane;
    private CustomRadioButton rb1, rb2;
    private CustomButton btnStart, btnReset;
    private ToggleGroup group = new ToggleGroup();
    private Graph graph = new Graph();
    private String[] signsRb1 = {"X: ", "S + ", "Y: ", "S + ", "Z: ", "S + "};
    private String[] signsRb2 = {"X +", "Y +", "Z +", "= 0"};

    private VBox vbUi;
    private VBox vbPo;

    private VBox vbLeft;
    private VBox vbRight;

    public View3() {

        rb1 = new CustomRadioButton("Lines");
        rb2 = new CustomRadioButton("Planes");
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

        setVbUi(setHbRadios(this.rb1, this.rb2));
        setVbPo("Planes and lines ");

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

    private void setVbPo(String title) {
        this.vbPo.setPrefSize(500, 595);
        this.vbPo.setSpacing(15);
        this.vbPo.setAlignment(Pos.TOP_CENTER);
        this.vbPo.setStyle("-fx-background-color: #333335");
        this.vbPo.getChildren().add(Custom.setTitle(title));
    }

    public void handleStart(boolean isRb1Selected) {
        if (isRb1Selected) {
            for (int i = 0; i < fieldListRb1.length; i++) {
                for (int j = 0; j < fieldListRb1[0].length; j++) {
                    if (fieldListRb1[i][j].getText().equals("")) {
                        fieldListRb1[i][j].setText("0");
                    }

                }
            }

            Model3 model3 = new Model3(input(),constants());

            double [][]A = Model3.getMatrixA_2x2();
            double[] b = Model3.getMatrixB_2x2();
            double[] x = model3.SLESolve(A, b);

            System.out.println("S = "+ x[0]);
            System.out.println("T = "+ x[1]);
            graph.addPoint(model3.intersectionLines(getFieldListRb1()));

           //First line
            graph.addLine(  model3.linesPoints(1,1,getFieldListRb1()), model3.linesPoints(1,2,getFieldListRb1()));

            //Second line
            graph.addLine(model3.linesPoints(2,1,getFieldListRb1()),model3.linesPoints(2,2,getFieldListRb1()));



        } else {
            for (int i = 0; i < fieldListRb2.length; i++) {
                for (int j = 0; j < fieldListRb2[0].length; j++) {
                    if (fieldListRb2[i][j].getText().equals("")) {
                        fieldListRb2[i][j].setText("0");

                    }

                }
            }
            Model3 c3=new Model3();
            c3.transform(getFieldListRb2());
            c3.crossProduct();
            c3.solutionPoints(5);
            graph.addPlane(c3.n1.get(0)/-c3.n1.get(3),c3.n1.get(1)/-c3.n1.get(3),c3.n1.get(2)/-c3.n1.get(3),"Plane1");
            graph.addPlane(c3.n2.get(0)/-c3.n2.get(3),c3.n2.get(1)/-c3.n2.get(3),c3.n2.get(2)/-c3.n2.get(3),"Plane2");
            graph.addLine(c3.solutionPoints(95), c3.solutionPoints(-10));
            Controller3 controller3 = new Controller3(this);

            //addOutput(controller3);
        }

    }

    public void handleReset() {
        this.graph.reset();
        this.getChildren().clear();
        btnStart.setDisable(false);
        rb1.setSelected(false);
        rb2.setSelected(false);
        this.vbUi.getChildren().remove(fieldsPane);
        this.getChildren().addAll(this.vbLeft, this.vbRight);
    }
    public void setActions() {
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
        this.btnStart.setOnAction(event -> { handleStart(rb1.isSelected()); });
        this.btnReset.setOnAction(event -> { handleReset(); });
    }

    public void addOutput() {

    }

    @Override
    public GridPane setFields(CustomTextField[][] textFields, String[] signs, CustomButton btnStart) {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        int rows = textFields.length;
        int cols = textFields[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols;  j++) {
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

                int finalI = i;
                int finalJ = j;

                textFields[i][j].textProperty().addListener((observable, oldValue, newValue) ->
                        btnStart.setDisable(textFields[finalI][finalJ].checkField()));

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
        for (CustomTextField[] tfArray: this.fieldListRb1) {
            for (CustomTextField tf: tfArray) {
                fieldList.add(tf);
            }
        }
        return fieldList;
    }

    public ArrayList<CustomTextField> getFieldListRb2() {
        ArrayList<CustomTextField> fieldList = new ArrayList<>();
        for (CustomTextField[] tfArray: this.fieldListRb2) {
            for (CustomTextField tf: tfArray) {
                fieldList.add(tf);
            }
        }
        return fieldList;
    }


    public  ArrayList<Double> constants() {
        int n = 1;
      ArrayList<Double> constant = new ArrayList<>();

        double d1 = Double.parseDouble(getFieldListRb1().get(1).getText());
        double d2 = Double.parseDouble(getFieldListRb1().get(7).getText());
        double d3 = Double.parseDouble(getFieldListRb1().get(3).getText());
        double d4 = Double.parseDouble(getFieldListRb1().get(9).getText());
        constant.add( d1-d2);
        constant.add(d3-d4);

        return constant;
    }

    public   ArrayList<Double> input() {
        ArrayList<Double> arr = new ArrayList<>();
        double d1 = Double.parseDouble(getFieldListRb1().get(0).getText());
        double d2 = Double.parseDouble(getFieldListRb1().get(2).getText());
        double d3 = Double.parseDouble(getFieldListRb1().get(6).getText());
        double d4 = Double.parseDouble(getFieldListRb1().get(8).getText());

        arr.add(-d1);
        arr.add(d3);
        arr.add(-d2);
        arr.add(d4);
        return arr;}

}

