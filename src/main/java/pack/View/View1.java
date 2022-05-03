package pack.View;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import pack.Controller.Controller1;
import pack.View.Customs.*;
import pack.View.GraphView.Graph;

import java.util.ArrayList;

import static pack.View.Customs.Custom.p;

public class View1 extends Pane implements iView {

    private final CustomTextField[][] fieldListRb1, fieldListRb2;
    private GridPane fieldsPane;
    private final CustomRadioButton rb1, rb2;
    private final CustomButton btnStart, btnReset;
    private ToggleGroup group = new ToggleGroup();
    public Graph graph = new Graph();
    private String[] signsRb1 = {"X +", "Y ="};
    private String[] signsRb2 = {"X +", "Y +", "Z ="};

    private VBox vbUi;
    private VBox vbPo;

    private VBox vbLeft;
    private VBox vbRight;

    private Pane backgroundPane;

    public View1() {
        rb1 = new CustomRadioButton("2 x 2");
        rb2 = new CustomRadioButton("3 x 3");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        btnStart = new CustomButton("START\nTHE\nMAGIK");
        btnStart.setDisable(true);
        btnReset = new CustomButton("RESET\nTHE\nMAGIK");

        fieldListRb1 = new CustomTextField[2][3];
        fieldListRb2 = new CustomTextField[3][4];
        fieldsPane = new GridPane();

        vbUi = new VBox();
        vbPo = new VBox();
        vbLeft = new VBox();
        vbRight = new VBox();
        backgroundPane = new Pane();

        setVbUi(setHbRadios(this.rb1, this.rb2));
        setVbPo("Systems of linear equations");

        setVbLeft(setLeft(this.vbUi, setGraphPane(graph)));
        setVbRight(setRight(this.vbPo, setHbBottom(this.btnStart, this.btnReset)));

        setView1();
        setActions();
    }

    public void setView1() {
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
        BackgroundImage myBI = new BackgroundImage(new Image(p + "View1.png", 520, 580, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.backgroundPane.setBackground(new Background(myBI));

        this.vbPo.setPrefSize(500, 595);
        this.vbPo.setSpacing(15);
        this.vbPo.setAlignment(Pos.TOP_CENTER);
        this.vbPo.setStyle("-fx-background-color: #333335");
        this.vbPo.getChildren().addAll(Custom.setTitle(title), this.backgroundPane);
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
        this.btnStart.setOnAction(event -> {
            handleStart(rb1.isSelected());
        });
        this.btnReset.setOnAction(event -> {
            handleReset();
        });
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

                if (j == cols - 1) {
                    lblVariable.setText("");
                } else {
                    lblVariable.setText(signs[j]);
                }


                textFields[i][j].textProperty().addListener((observable, oldValue, newValue) -> btnStart.setDisable(checkFields(getFieldListRb1(), getFieldListRb2(), rb1.isSelected())));

                hbTextField.getChildren().addAll(textFields[i][j], lblVariable);
                gridPane.add(hbTextField, j, i);
            }
        }
        return gridPane;
    }

    // doesn't set text in text field to 0, also only works for rb1?
    public void handleStart(boolean isRb1Selected) {

        if (isRb1Selected) {
            for (int i = 0; i < fieldListRb1.length; i++) {
                for (int j = 0; j < fieldListRb1[0].length; j++) {
                    if (fieldListRb1[i][j].getText().equals("")) {
                        fieldListRb1[i][j].setText("0");
                    }
                }
            }
        } else {
            for (int i = 0; i < fieldListRb2.length; i++) {
                for (int j = 0; j < fieldListRb2[0].length; j++) {
                    if (fieldListRb2[i][j].getText().equals("")) {
                        fieldListRb2[i][j].setText("0");
                    }
                }
            }
        }
        Controller1 controller = new Controller1(this);
        this.graph.reset();
        controller.graphPlane();
        addOutput(controller);
    }

    public void handleReset() {
        this.getChildren().clear();
        btnStart.setDisable(false);
        rb1.setSelected(false);
        rb2.setSelected(false);
        this.vbUi.getChildren().remove(fieldsPane);
        this.backgroundPane.getChildren().clear();
        this.getChildren().addAll(this.vbLeft, this.vbRight);
        this.graph.reset();
    }

    public void addOutput(Controller1 controller) {
        this.backgroundPane.getChildren().clear();
        //setVbPo("Systems of linear equations");

//        VBox vbOutput = new VBox();
//        vbOutput.setSpacing(15);
//        vbOutput.setPadding(new Insets(15));
        ArrayList<String[]> output = controller.getOutput();
        String[] sol = output.get(1);

        System.out.println("YA: " + sol[0]);

        VBox vbSolutions = new VBox();
        CustomText textX = new CustomText("X = " + output.get(1)[0]);
        textX.changeSize(20);
        vbSolutions.getChildren().add(textX);
        CustomText textY = new CustomText("Y = " + output.get(1)[1]);
        textY.changeSize(20);
        vbSolutions.getChildren().add(textY);
        vbSolutions.setSpacing(15);
        vbSolutions.setLayoutX(10);
        vbSolutions.setLayoutY(430);
        if (output.get(1).length > 3) {
            CustomText textZ = new CustomText("Z = " + output.get(1)[2]);
            textZ.changeSize(20);
            vbSolutions.getChildren().add(textZ);
            vbSolutions.setLayoutY(410);
        }

        // Anything
        HBox hbReducedMatrix = new HBox(10);
        ImageView iv1 = new ImageView(new Image(p + "Right.png"));
        iv1.setFitWidth(10);
        iv1.setFitHeight(75);
        ImageView iv2 = new ImageView(new Image(p + "Left.png"));
        iv2.setFitWidth(10);
        iv2.setFitHeight(75);
        ImageView iv3 = new ImageView(new Image(p + "Bar.png"));
        iv3.setFitWidth(10);
        iv3.setFitHeight(75);

        GridPane gp = new GridPane();
        gp.setVgap(10);
        gp.setHgap(10);
        for (int i = 0; i < controller.getCoefficients().length; i++) {
            for (int j = 0; j < controller.getCoefficients()[0].length; j++) {
                CustomText coefficientMatrix = new CustomText(controller.getCoefficients()[i][j]);
                coefficientMatrix.changeSize(20);
                gp.add(coefficientMatrix, j, i);
            }
        }
        VBox vbConstants = new VBox(10);

        for (int i = 0; i < output.get(0).length; i++) {
            CustomText constantMatrix = new CustomText(output.get(0)[i]);
            constantMatrix.changeSize(20);
            vbConstants.getChildren().add(constantMatrix);
        }

        hbReducedMatrix.getChildren().addAll(iv1, gp, iv3, vbConstants, iv2);
        hbReducedMatrix.setLayoutX(20);
        hbReducedMatrix.setLayoutY(150);

        if (rb2.isSelected()) {
            iv1.setFitHeight(105);
            iv1.setFitWidth(15);
            iv2.setFitHeight(105);
            iv2.setFitWidth(15);
            iv3.setFitHeight(105);

            hbReducedMatrix.setLayoutY(130);
        }

        HBox hbRank = new HBox();
        CustomText rankText = new CustomText(controller.getRank());
        rankText.changeSize(60);
        rankText.setStyle("-fx-fill: E7EBEE");
        hbRank.getChildren().add(rankText);
        hbRank.setLayoutX(380);
        hbRank.setLayoutY(315);

        this.backgroundPane.getChildren().addAll(hbRank, vbSolutions, hbReducedMatrix);
    }

    public CustomRadioButton getRb1() {
        return rb1;
    }

    public ArrayList<CustomTextField> getFieldListRb1() {
        ArrayList<CustomTextField> fieldList = new ArrayList<>();
        for (CustomTextField[] tfArray : this.fieldListRb1) {
            for (CustomTextField tf : tfArray) {
                fieldList.add(tf);
            }
        }
        return fieldList;
    }

    public ArrayList<CustomTextField> getFieldListRb2() {
        ArrayList<CustomTextField> fieldList = new ArrayList<>();
        for (CustomTextField[] tfArray : this.fieldListRb2) {
            for (CustomTextField tf : tfArray) {
                fieldList.add(tf);
            }
        }
        return fieldList;
    }


    public boolean checkFields(boolean b) {
        ArrayList<Boolean> booleans = new ArrayList<>();
        int i = 0;
        ArrayList<CustomTextField> a;
        if (b) {
            a = getFieldListRb1();
        } else {
            a = getFieldListRb2();
        }

        while (i != a.size()) {
            CustomTextField t = a.get(i);

            if (!t.getText().isEmpty()) {
                if (isNumeric(t.getText())) {
                    t.setStyle(" -fx-control-inner-background:#A0A0A0;");
                    booleans.add(true);
                }

                if (!isNumeric(t.getText())) {
                    t.setStyle(" -fx-control-inner-background: red;");
                    booleans.add(false);
                }

            }
            i++;
        }

        if (booleans.contains(false)) {
            return true;
        } else {
            return false;
        }
    }


}
