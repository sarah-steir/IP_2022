package pack.View;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import pack.Controller.Controller1;
import pack.Controller.Controller3;
import pack.Model.Model3;
import pack.View.Customs.Custom;
import pack.View.Customs.CustomButton;
import pack.View.Customs.CustomRadioButton;
import pack.View.Customs.CustomTextField;
import pack.View.GraphView.Graph;

import java.util.ArrayList;

import static pack.View.Customs.Custom.p;

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

    private Pane backgroundPane;

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

        this.backgroundPane = new Pane();

        setVbUi(setHbRadios(this.rb1, this.rb2));
        setVbPo("Planes and lines");

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
        this.backgroundPane.setPrefSize(500, 580);
        BackgroundImage myBI= new BackgroundImage(new Image(p + "View3.png",520,580,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.backgroundPane.setBackground(new Background(myBI));
        this.vbPo.setPrefSize(500, 595);
        this.vbPo.setSpacing(15);
        this.vbPo.setAlignment(Pos.TOP_CENTER);
        this.vbPo.setStyle("-fx-background-color: #333335");
        this.vbPo.getChildren().addAll(Custom.setTitle(title), this.backgroundPane);

    }

    //TODO method for filling empty textfields
    public void handleStart(boolean isRb1Selected) {
        if (isRb1Selected) {
            for (int i = 0; i < fieldListRb1.length; i++) {
                for (int j = 0; j < fieldListRb1[0].length; j++) {
                    if (fieldListRb1[i][j].getText().equals("")) {
                        fieldListRb1[i][j].setText("0");
                    }

                }
            }

            Model3 model3 = new Model3();
            model3.bringT(getFieldListRb1());
            model3.setThingies();

            if(model3.det()==true) {
                double [][]A = Model3.getMatrixA_2x2();
                double[] b = Model3.getMatrixB_2x2();
                double[] x = model3.SLESolve(A, b);
                System.out.println("S = "+ x[0]);
                System.out.println("T = "+ x[1]);

                graph.addPoint(model3.intersectionLines());}
            if(model3.det()==false) {  model3.distanceSkew(); System.out.println("Skew lines hehe");}

            if(model3.parallel()==true) {System.out.println("Parallel lines babe");}


           //First line
            graph.addLine(  model3.linesPoints(1,0), model3.linesPoints(1,2),model3.dirVector(1));
            //Second line
            graph.addLine(model3.linesPoints(2,0),model3.linesPoints(2,2),model3.dirVector(2));
            Controller3 controller = new Controller3(this);
            addOutput(controller);
        }

        // for rb2
        else {
            for (int i = 0; i < fieldListRb2.length; i++) {
                for (int j = 0; j < fieldListRb2[0].length; j++) {
                    if (fieldListRb2[i][j].getText().equals("")) {
                        fieldListRb2[i][j].setText("0");}}}

            Model3 c3 = new Model3();
            c3.transform(getFieldListRb2());
            c3.crossProduct(c3.n1,c3.n2);
            c3.solutionPoints(5);
            graph.addPlane(c3.n1[0]/-c3.n1[3],c3.n1[1]/-c3.n1[3],c3.n1[2]/-c3.n1[3],"Plane1");
            graph.addPlane(c3.n2[0]/-c3.n2[3],c3.n2[1]/-c3.n2[3],c3.n2[2]/-c3.n2[3],"Plane1");
            graph.addLine(c3.solutionPoints(95), c3.solutionPoints(-10),c3.crossProduct(c3.n1,c3.n2));

          /*  Label l= new Label("Direction vector: <"+c3.crossProduct()[0]+", "+c3.crossProduct()[1]+", "+c3.crossProduct()[2]+">");
            this.vbPo.getChildren().clear();
            this.vbPo.getChildren().add(l);*/
            Controller3 controller3 = new Controller3(this);
            addOutput(controller3);
        }}

    public void addOutput(Controller3 controller) {
        this.backgroundPane.getChildren().clear();


        // Hbox bla bla
        this.backgroundPane.getChildren().addAll();
    }

    public void handleReset() {
        this.graph.reset();
        this.getChildren().clear();
        btnStart.setDisable(false);
        rb1.setSelected(false);
        rb2.setSelected(false);
        this.vbUi.getChildren().remove(fieldsPane);
        this.getChildren().addAll(this.vbLeft, this.vbRight);}

    public void setActions() {
        rb1.setOnAction(event -> {
            this.btnStart.setDisable(false);
            fieldsPane = setFields(fieldListRb1, signsRb1, this.btnStart);
            this.vbUi.getChildren().clear();
            this.vbUi.getChildren().addAll(setHbRadios(rb1, rb2), fieldsPane);});

        rb2.setOnAction(event -> {
            this.btnStart.setDisable(false);
            fieldsPane = setFields(fieldListRb2, signsRb2, this.btnStart);
            this.vbUi.getChildren().clear();
            this.vbUi.getChildren().addAll(setHbRadios(rb1, rb2), fieldsPane);});

        this.btnStart.setOnAction(event -> { handleStart(rb1.isSelected()); });
        this.btnReset.setOnAction(event -> { handleReset(); });}


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
                    lblVariable.setText("T + ");}

                else {
                    lblVariable.setText(signs[j]);}

                int finalI = i;
                int finalJ = j;

                textFields[i][j].textProperty().addListener((observable, oldValue, newValue) ->
                        btnStart.setDisable(textFields[finalI][finalJ].checkField()));

                if (rb1.isSelected()) {
                    textFields[i][j].setPrefSize(40, 30);
                    hbTextField.getChildren().addAll(lblVariable, textFields[i][j]);}

                else {
                    hbTextField.getChildren().addAll(textFields[i][j], lblVariable);}

                gridPane.add(hbTextField, j, i);
            }
        }
        return gridPane;
    }


    public ArrayList<CustomTextField> getFieldListRb1() {
        ArrayList<CustomTextField> fieldList = new ArrayList<>();
        for (CustomTextField[] tfArray: this.fieldListRb1) {
            for (CustomTextField tf: tfArray) {
                fieldList.add(tf);}}
        return fieldList;}

    public ArrayList<CustomTextField> getFieldListRb2() {
        ArrayList<CustomTextField> fieldList = new ArrayList<>();
        for (CustomTextField[] tfArray: this.fieldListRb2) {
            for (CustomTextField tf: tfArray) {
                fieldList.add(tf);}}
        return fieldList;}


}

