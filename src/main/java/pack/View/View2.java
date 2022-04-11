package pack.View;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pack.Model.ModelForJSON;
import pack.View.Customs.Custom;
import pack.View.Customs.CustomButton;
import pack.View.Customs.CustomRadioButton;
import pack.View.Customs.CustomTextField;
import pack.View.GraphView.Graph;

public class View2 extends Pane implements iView {
    private CustomRadioButton rb1, rb2;
    private ComboBox cb = new ComboBox();
    private CustomButton btnStart, btnReset;
    private ToggleGroup group = new ToggleGroup();
    private String[] signsRb1 = {"", "|"};
    private String[] signsRb2 = {"", "", "|"};
    private double a1;
    private double a2;
    private double a3;
    private double b1;
    private double b2;
    private double b3;
    private double c1;
    private double c2;
    private double c3;
    String gh = "fddd";

    public View2() {
        rb1 = new CustomRadioButton("2 x 2");
        rb2 = new CustomRadioButton("3 x 3");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        btnStart = new CustomButton("START\nTHE\nMAGIK");
        btnReset = new CustomButton("RESET\nTHE\nMAGIK");
        cb.getItems().addAll(
                "Diagonal",
                "identity",
                "Null",
                "Lower Triangle",
                "Symmetric",
                "Upper Triangle"
        );
        Button button = new Button();
        button.setOnAction(event -> {
            //Call a method to determine which item in the list the user has selected
            doAction(cb.getValue().toString()); //Send the selected item to the method
        });
        cb.setPromptText("Saved Matrices");
        this.getChildren().addAll(setView(rb1, rb2, btnStart, btnReset, signsRb1, signsRb2, "EigenValues and EigenVectors", null, cb));
    }

    private void doAction(String listItem) {
        switch (listItem) {
            case "Lower Triangle":
                a1=ModelForJSON.getLowerTriangle().get(0);
                a2=ModelForJSON.getLowerTriangle().get(1);
                a3=ModelForJSON.getLowerTriangle().get(2);
                b1=ModelForJSON.getLowerTriangle().get(3);
                b2=ModelForJSON.getLowerTriangle().get(4);
                b3=ModelForJSON.getLowerTriangle().get(5);
                c1=ModelForJSON.getLowerTriangle().get(6);
                c2=ModelForJSON.getLowerTriangle().get(7);
                c3=ModelForJSON.getLowerTriangle().get(8);

                //Action for this item
                break;
            case "Upper Triangle":
                a1=ModelForJSON.getUpperTriangle().get(0);
                a2=ModelForJSON.getUpperTriangle().get(1);
                a3=ModelForJSON.getUpperTriangle().get(2);
                b1=ModelForJSON.getUpperTriangle().get(3);
                b2=ModelForJSON.getUpperTriangle().get(4);
                b3=ModelForJSON.getUpperTriangle().get(5);
                c1=ModelForJSON.getUpperTriangle().get(6);
                c2=ModelForJSON.getUpperTriangle().get(7);
                c3=ModelForJSON.getUpperTriangle().get(8);
                break;
            case "Diagonal":
                a1=ModelForJSON.getDiagonal().get(0);
                a2=ModelForJSON.getDiagonal().get(1);
                a3=ModelForJSON.getDiagonal().get(2);
                b1=ModelForJSON.getDiagonal().get(3);
                b2=ModelForJSON.getDiagonal().get(4);
                b3=ModelForJSON.getDiagonal().get(5);
                c1=ModelForJSON.getDiagonal().get(6);
                c2=ModelForJSON.getDiagonal().get(7);
                c3=ModelForJSON.getDiagonal().get(8);
                break;
            case "identity":
                a1=ModelForJSON.getIdentity().get(0);
                a2=ModelForJSON.getIdentity().get(1);
                a3=ModelForJSON.getIdentity().get(2);
                b1=ModelForJSON.getIdentity().get(3);
                b2=ModelForJSON.getIdentity().get(4);
                b3=ModelForJSON.getIdentity().get(5);
                c1=ModelForJSON.getIdentity().get(6);
                c2=ModelForJSON.getIdentity().get(7);
                c3=ModelForJSON.getIdentity().get(8);
                break;
            case "Null":
                a1=ModelForJSON.getNul().get(0);
                a2=ModelForJSON.getNul().get(1);
                a3=ModelForJSON.getNul().get(2);
                b1=ModelForJSON.getNul().get(4);
                b3=ModelForJSON.getNul().get(5);
                c1=ModelForJSON.getNul().get(6);
                c2=ModelForJSON.getNul().get(7);
                c3=ModelForJSON.getNul().get(8);
                break;
            case "Symmetric":
                a1=ModelForJSON.getSymmetric().get(0);
                a2=ModelForJSON.getSymmetric().get(1);
                a3=ModelForJSON.getSymmetric().get(2);
                b1=ModelForJSON.getSymmetric().get(3);
                b2=ModelForJSON.getSymmetric().get(4);
                b3=ModelForJSON.getSymmetric().get(5);
                c1=ModelForJSON.getSymmetric().get(6);
                c2=ModelForJSON.getSymmetric().get(7);
                c3=ModelForJSON.getSymmetric().get(8);
                break;
            default: //Default action
                break;
        }
    }
        @Override
        public VBox setLeft (CustomRadioButton rb1, CustomRadioButton rb2, CustomButton btnStart, String[]
        signsRb1, String[] signsRb2, Graph graph, ComboBox comboBox){
            VBox vbLeft = new VBox();
            ImageView imL = new ImageView(new Image(Custom.p + "brack left.png"));
            vbLeft.setSpacing(10);
            vbLeft.setPrefSize(500, 695);
            vbLeft.setLayoutX(10);
            vbLeft.setLayoutY(14);
            vbLeft.setStyle("-fx-background-color: #333335"); // Grey
            vbLeft.getChildren().add(setRadios(rb1, rb2, btnStart, signsRb1, signsRb2, comboBox));
            vbLeft.getChildren().add(cb);
            vbLeft.getChildren().add(imL);
            return vbLeft;
        }

    @Override
    public VBox setRight(String title, CustomButton btnStart, CustomButton btnReset) {
        VBox vbRight= new VBox();
        ImageView imL = new ImageView(new Image(Custom.p + "brack left.png"));
        vbRight.setSpacing(10);
        vbRight.setPrefSize(500, 695);
        vbRight.setLayoutX(10);
        vbRight.setLayoutY(14);
        vbRight.setStyle("-fx-background-color: #333335"); // Grey
        //vbRight.getChildren().add(setRadios(rb1, rb2, btnStart, signsRb1, signsRb2));
        vbRight.getChildren().add(cb);
        vbRight.getChildren().add(imL);
        return vbRight;    }

    @Override
    public VBox setRadios(CustomRadioButton rb1, CustomRadioButton rb2, CustomButton btnStart, String[] signsRb1,
                          String[] signsRb2, ComboBox comboBox) {
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

    @Override
    public GridPane setFields(int rows, int cols, CustomButton btnStart, String[] signs, ComboBox comboBox) {
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


        public String[] getSignsRb2 () {
            return signsRb2;
        }

        public void setSignsRb2 (String[]signsRb2){
            this.signsRb2 = signsRb2;
        }

        public CustomRadioButton getRb1 () {
            return rb1;
        }

        public void setRb1 (CustomRadioButton rb1){
            this.rb1 = rb1;
        }

        public CustomRadioButton getRb2 () {
            return rb2;
        }

        public void setRb2 (CustomRadioButton rb2){
            this.rb2 = rb2;
        }

        public CustomButton getBtnStart () {
            return btnStart;
        }

        public void setBtnStart (CustomButton btnStart){
            this.btnStart = btnStart;
        }

        public CustomButton getBtnReset () {
            return btnReset;
        }

        public void setBtnReset (CustomButton btnReset){
            this.btnReset = btnReset;
        }

        public ToggleGroup getGroup () {
            return group;
        }

        public void setGroup (ToggleGroup group){
            this.group = group;
        }

        public String[] getSignsRb1 () {
            return signsRb1;
        }

        public void setSignsRb1 (String[]signsRb1){
            this.signsRb1 = signsRb1;
        }


        public double getA1 () {
            return a1;
        }

        public void setA1 ( double a1){
            this.a1 = a1;
        }

        public double getA2 () {
            return a2;
        }

        public void setA2 ( double a2){
            this.a2 = a2;
        }

        public double getA3 () {
            return a3;
        }

        public void setA3 ( double a3){
            this.a3 = a3;
        }

        public double getB1 () {
            return b1;
        }

        public void setB1 ( double b1){
            this.b1 = b1;
        }

        public double getB2 () {
            return b2;
        }

        public void setB2 ( double b2){
            this.b2 = b2;
        }

        public double getB3 () {
            return b3;
        }

        public void setB3 ( double b3){
            this.b3 = b3;
        }

        public double getC1 () {
            return c1;
        }

        public void setC1 ( double c1){
            this.c1 = c1;
        }

        public double getC2 () {
            return c2;
        }

        public void setC2 ( double c2){
            this.c2 = c2;
        }

        public double getC3 () {
            return c3;
        }

        public void setC3 ( double c3){
            this.c3 = c3;
        }
    }