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

/**
 * This class is the whole user interface for the first view of the program, the System of Linear Equations Calculator.
 */
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

    /**
     * This methods sets all the panes and boxes for this view
     */
    private void setView1() {
        this.setPrefSize(1050, 750);
        this.setStyle("-fx-background-color: #6F6F77;");    // Blue Grey
        this.getChildren().addAll(this.vbLeft, this.vbRight);
    }

    /**
     * This methods sets the left pane.
     * @param vbLeft the left VBox
     */
    private void setVbLeft(VBox vbLeft) {
        this.vbLeft = vbLeft;
    }

    /**
     * This method sets the right pane.
     * @param vbRight the right VBox
     */
    private void setVbRight(VBox vbRight) {
        this.vbRight = vbRight;
    }

    /**
     * This method sets the upper left pane called the UI box. It contains the radio buttons and the text fields
     * for the user's input.
     * @param hbRadios the HBox of the RadioButtons
     */
    private void setVbUi(HBox hbRadios) {
        this.vbUi.setSpacing(5);
        this.vbUi.setPrefSize(500, 160);
        this.vbUi.setStyle("-fx-background-color: #333335");
        this.vbUi.getChildren().add(hbRadios);
    }

    /**
     * This method sets the right pane called the Program Output box. This contains a background image of cloud drawings,
     * as well as the output of the view (when the start button is clicked)
     * @param title the title of the view
     */
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

    /**
     * This method sets the actions of the elements in the view such as both RadioButtons as well as both the Start Button
     * and the Reset Button.
     */
    private void setActions() {
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

    /**
     * This method sets the text fields of the view.
     * @param textFields the array of textfields
     * @param signs the signs for this specific radio button (differs for 2x2 and 3x3)
     * @param btnStart the start button
     * @return a GridPane with the properly labelled and formatted text fields
     */
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

                textFields[i][j].textProperty().addListener((observable, oldValue, newValue) ->
                        btnStart.setDisable(checkFields(getFieldListRb1(), getFieldListRb2(), rb1.isSelected())));

                hbTextField.getChildren().addAll(textFields[i][j], lblVariable);
                gridPane.add(hbTextField, j, i);
            }
        }
        return gridPane;
    }

    /**
     * This method handles what happens when the start button is clicked.
     * @param isRb1Selected the boolean to check which RadioButton is selected
     */
    private void handleStart(boolean isRb1Selected) {
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

    /**
     * This method handles what happens when the Reset Button is clicked.
     */
    private void handleReset() {
        this.getChildren().clear();
        btnStart.setDisable(false);
        rb1.setSelected(false);
        rb2.setSelected(false);
        this.vbUi.getChildren().remove(fieldsPane);
        this.backgroundPane.getChildren().clear();
        this.getChildren().addAll(this.vbLeft, this.vbRight);
        this.graph.reset();
    }

    /**
     * This methods add the output (the results of the math) once the Start button is clicked.
     * @param controller the controller object used to fetch and retrieve data
     */
    private void addOutput(Controller1 controller) {
        this.backgroundPane.getChildren().clear();

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

    /**
     * Gets the first RadioButton
     * @return the RadioButton
     */
    public CustomRadioButton getRb1() {
        return rb1;
    }

    /**
     * This methods gets the list (of the first RadioButton) of text fields in the form of an arrayList instead of an
     * array since that's how the controller and model classes need it.
     * @return the arraylist of text fields for rb1
     */
    public ArrayList<CustomTextField> getFieldListRb1() {
        ArrayList<CustomTextField> fieldList = new ArrayList<>();
        for (CustomTextField[] tfArray : this.fieldListRb1) {
            for (CustomTextField tf : tfArray) {
                fieldList.add(tf);
            }
        }
        return fieldList;
    }

    /**
     * This methods gets the list (of the second RadioButton) of text fields in the form of an arrayList instead of an
     * array since that's how the controller and model classes need it.
     * @return the arraylist of textfields for rb2
     */
    public ArrayList<CustomTextField> getFieldListRb2() {
        ArrayList<CustomTextField> fieldList = new ArrayList<>();
        for (CustomTextField[] tfArray : this.fieldListRb2) {
            for (CustomTextField tf : tfArray) {
                fieldList.add(tf);
            }
        }
        return fieldList;
    }
}
