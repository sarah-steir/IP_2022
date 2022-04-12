package pack.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import pack.Model.Model2for2x2;
import pack.Model.Model2for3x3;
import pack.Model.ModelForJSON;
import pack.View.Customs.Custom;
import pack.View.Customs.CustomButton;
import pack.View.Customs.CustomRadioButton;
import pack.View.Customs.CustomTextField;
import pack.View.GraphView.Graph;

public class View2<textField> extends Pane implements iView {
    private static CustomRadioButton rb1;
    private static CustomRadioButton rb2;
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
    private static CustomTextField t1;
    private static CustomTextField t2;
    private static CustomTextField t3;
    private static CustomTextField t4;
    private static CustomTextField t5;
    private static CustomTextField t6;
    private static CustomTextField t7;
    private static CustomTextField t8;
    private static CustomTextField t9;



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
        this.getChildren().addAll(setView(rb1, rb2, btnStart, btnReset, signsRb1, signsRb2, "EigenValues and EigenVectors", null));
        //iView.handleStart.(2,false);
    }
    private void getTextfields(){

    }

    private void doAction(String listItem) {
        switch (listItem) {
            case "Lower Triangle":
                a1 = ModelForJSON.getLowerTriangle().get(0);
                a2 = ModelForJSON.getLowerTriangle().get(1);
                a3 = ModelForJSON.getLowerTriangle().get(2);
                b1 = ModelForJSON.getLowerTriangle().get(3);
                b2 = ModelForJSON.getLowerTriangle().get(4);
                b3 = ModelForJSON.getLowerTriangle().get(5);
                c1 = ModelForJSON.getLowerTriangle().get(6);
                c2 = ModelForJSON.getLowerTriangle().get(7);
                c3 = ModelForJSON.getLowerTriangle().get(8);

                //Action for this item
                break;
            case "Upper Triangle":
                a1 = ModelForJSON.getUpperTriangle().get(0);
                a2 = ModelForJSON.getUpperTriangle().get(1);
                a3 = ModelForJSON.getUpperTriangle().get(2);
                b1 = ModelForJSON.getUpperTriangle().get(3);
                b2 = ModelForJSON.getUpperTriangle().get(4);
                b3 = ModelForJSON.getUpperTriangle().get(5);
                c1 = ModelForJSON.getUpperTriangle().get(6);
                c2 = ModelForJSON.getUpperTriangle().get(7);
                c3 = ModelForJSON.getUpperTriangle().get(8);
                break;
            case "Diagonal":
                a1 = ModelForJSON.getDiagonal().get(0);
                a2 = ModelForJSON.getDiagonal().get(1);
                a3 = ModelForJSON.getDiagonal().get(2);
                b1 = ModelForJSON.getDiagonal().get(3);
                b2 = ModelForJSON.getDiagonal().get(4);
                b3 = ModelForJSON.getDiagonal().get(5);
                c1 = ModelForJSON.getDiagonal().get(6);
                c2 = ModelForJSON.getDiagonal().get(7);
                c3 = ModelForJSON.getDiagonal().get(8);
                break;
            case "identity":
                a1 = ModelForJSON.getIdentity().get(0);
                a2 = ModelForJSON.getIdentity().get(1);
                a3 = ModelForJSON.getIdentity().get(2);
                b1 = ModelForJSON.getIdentity().get(3);
                b2 = ModelForJSON.getIdentity().get(4);
                b3 = ModelForJSON.getIdentity().get(5);
                c1 = ModelForJSON.getIdentity().get(6);
                c2 = ModelForJSON.getIdentity().get(7);
                c3 = ModelForJSON.getIdentity().get(8);
                break;
            case "Null":
                a1 = ModelForJSON.getNul().get(0);
                a2 = ModelForJSON.getNul().get(1);
                a3 = ModelForJSON.getNul().get(2);
                b1 = ModelForJSON.getNul().get(4);
                b3 = ModelForJSON.getNul().get(5);
                c1 = ModelForJSON.getNul().get(6);
                c2 = ModelForJSON.getNul().get(7);
                c3 = ModelForJSON.getNul().get(8);
                break;
            case "Symmetric":
                a1 = ModelForJSON.getSymmetric().get(0);
                a2 = ModelForJSON.getSymmetric().get(1);
                a3 = ModelForJSON.getSymmetric().get(2);
                b1 = ModelForJSON.getSymmetric().get(3);
                b2 = ModelForJSON.getSymmetric().get(4);
                b3 = ModelForJSON.getSymmetric().get(5);
                c1 = ModelForJSON.getSymmetric().get(6);
                c2 = ModelForJSON.getSymmetric().get(7);
                c3 = ModelForJSON.getSymmetric().get(8);
                break;
            default: //Default action
                break;
        }
        if (rb1.isSelected()) {
            t1= iView.getCopyArray().get(0);
            t2= iView.getCopyArray().get(1);
            t3= iView.getCopyArray().get(2);
            t4= iView.getCopyArray().get(3);
            t1.setText(String.valueOf(a1));
            t2.setText(String.valueOf(a2));
            t4.setText(String.valueOf(b1));
            t5.setText(String.valueOf(b2));
        }
        if (rb2.isSelected()) {
            t1= iView.getCopyArray().get(0);
            t2= iView.getCopyArray().get(1);
            t3= iView.getCopyArray().get(2);
            t4= iView.getCopyArray().get(3);
            t5= iView.getCopyArray().get(4);
            t6= iView.getCopyArray().get(5);
            t7= iView.getCopyArray().get(6);
            t8= iView.getCopyArray().get(7);
            t9= iView.getCopyArray().get(8);
            t1.setText(String.valueOf(a1));
            t2.setText(String.valueOf(a2));
            t3.setText(String.valueOf(a3));
            t4.setText(String.valueOf(b1));
            t5.setText(String.valueOf(b2));
            t6.setText(String.valueOf(63));
            t7.setText(String.valueOf(c1));
            t8.setText(String.valueOf(c2));
            t9.setText(String.valueOf(c3));
        }


    }
        @Override
        public VBox setLeft (CustomRadioButton rb1, CustomRadioButton rb2, CustomButton btnStart, String[]
        signsRb1, String[]signsRb2, Graph graph){
            VBox vbLeft = new VBox();
            vbLeft.setSpacing(10);
            vbLeft.setPrefSize(500, 695);
            vbLeft.setLayoutX(10);
            vbLeft.setLayoutY(14);
            vbLeft.setStyle("-fx-background-color: #333335"); // Grey
            vbLeft.getChildren().add(setRadios(rb1, rb2, btnStart, signsRb1, signsRb2));
            vbLeft.getChildren().add(cb);
            return vbLeft;
        }
   public GridPane showDaRight() {
       GridPane gpt = new GridPane();
       gpt.setPadding(new Insets(10, 10, 10, 10));
       gpt.setPrefSize(500, 695);
       gpt.setLayoutX(520);
       gpt.setLayoutY(14);
       if (rb2.isSelected()) {
           Text text = new Text("FOR THE EIGENVALUE " + Model2for3x3.getX1() + " THE EIGEN VECTOR IS");
           Text text1 = new Text("FOR THE EIGENVALUE " + Model2for3x3.getX2() + " THE EIGEN VECTOR IS");
           Text text2 = new Text("FOR THE EIGENVALUE " + Model2for3x3.getX3() + " THE EIGEN VECTOR IS");
           gpt.add(text, 1, 10);
           if (Model2for3x3.getS1().size() == 3) {
               gpt.add(newVector(1,3), 2, 15);
           } else if (Model2for3x3.getS1().size() == 6) {
               gpt.add(newVector(1,3), 2, 15);
               gpt.add(newVector(2,3), 3, 15);
           } else {
               gpt.add(newVector(1,3), 2, 15);
               gpt.add(newVector(2,3), 3, 15);
               gpt.add(newVector(3,3), 4, 15);
           }
           gpt.add(text1, 1, 10);
           if (Model2for3x3.getS2().size() == 3) {
               gpt.add(newVector(1,3), 2, 15);
           } else if (Model2for3x3.getS2().size() == 6) {
               gpt.add(newVector(1,3), 2, 15);
               gpt.add(newVector(2,3), 3, 15);
           } else {
               gpt.add(newVector(1,3), 2, 15);
               gpt.add(newVector(2,3), 3, 15);
               gpt.add(newVector(3,3), 4, 15);
           }
           gpt.add(text2, 1, 10);
           if (Model2for3x3.getS3().size() == 3) {
               gpt.add(newVector(1,3), 2, 15);
           } else if (Model2for3x3.getS3().size() == 6) {
               gpt.add(newVector(1,3), 2, 15);
               gpt.add(newVector(2,3), 3, 15);
           } else {
               gpt.add(newVector(1,3), 2, 15);
               gpt.add(newVector(2,3), 3, 15);
               gpt.add(newVector(3,3), 4, 15);
           }
       }
       if (rb1.isSelected()) {
           Text text = new Text("FOR THE EIGENVALUE " + Model2for2x2.getX1() + " THE EIGEN VECTOR IS");
           Text text1 = new Text("FOR THE EIGENVALUE " + Model2for2x2.getX2() + " THE EIGEN VECTOR IS");
           gpt.add(text, 1, 10);
           if (Model2for2x2.getS1().size() == 2) {
               gpt.add(newVector(1,2), 2, 15);
           }
           else{
               gpt.add(newVector(1,2), 2, 15);
               gpt.add(newVector(2,2), 3, 15);
           }
           gpt.add(text1, 1, 10);
           if (Model2for2x2.getS2().size() == 2) {
               gpt.add(newVector(1,2), 2, 15);
           }
           else{
               gpt.add(newVector(1,2), 2, 15);
               gpt.add(newVector(2,2), 3, 15);
           }

       }
           return gpt;
       }

    public HBox newVector(int counter, int whatSize){ // counter is the vector if size=6 there is counter 1 and 2 possible
        HBox hbx = new HBox();
        VBox vbx1 = new VBox();
        ImageView imL = new ImageView(new Image(Custom.p + "brack left.png"));
        imL.setFitWidth(15);
        imL.setFitHeight(75);
        ImageView imR = new ImageView(new Image(Custom.p + "brack right.png"));
        imR.setFitWidth(14);
        imR.setFitHeight(75);
        if(whatSize == 2){
            vbx1 = putVertical2x2(counter);
         vbx1.setPrefHeight(75);
        }
        if(whatSize == 3){
            vbx1 = putVertical3x3(counter);
            vbx1.setPrefHeight(75);
        }
        hbx.getChildren().addAll(imL,vbx1,imR);

        return hbx;
    }
    public VBox putVertical3x3(int counter){
        VBox vbx1 = new VBox();
        Double numba1 = null;
        Double numba2 = null;
        Double numba3 = null;
        if(counter==1){
             numba1 = Model2for3x3.getS1().get(0);
             numba2 = Model2for3x3.getS1().get(1);
             numba3 = Model2for3x3.getS1().get(2);
        }
        else if(counter==2){
             numba1 = Model2for3x3.getS1().get(3);
             numba2 = Model2for3x3.getS1().get(4);
             numba3 = Model2for3x3.getS1().get(5);
        }
        else{
             numba1 = Model2for3x3.getS1().get(6);
             numba2 = Model2for3x3.getS1().get(7);
             numba3 = Model2for3x3.getS1().get(8);
        }
        Text nb1 = new Text(Double.toString(numba1));
        Text nb2 = new Text(Double.toString(numba2));
        Text nb3 = new Text(Double.toString(numba3));
        vbx1.getChildren().add(nb1);
        vbx1.getChildren().add(nb2);
        vbx1.getChildren().add(nb3);


        return vbx1;
    }

    public VBox putVertical2x2(int counter){
        VBox vbx1 = new VBox();
        Double numba1 = null;
        Double numba2= null;
        if(counter==1){
             numba1 = Model2for3x3.getS1().get(0);
             numba2 = Model2for3x3.getS1().get(1);
        }
        if(counter==2){
             numba1 = Model2for3x3.getS1().get(2);
             numba2 = Model2for3x3.getS1().get(3);
        }
        Text nb1 = new Text(Double.toString(numba1));
        Text nb2 = new Text(Double.toString(numba2));
        vbx1.getChildren().add(nb1);
        vbx1.getChildren().add(nb2);
        return vbx1;
    }

    @Override
    public VBox setRight(String title, CustomButton btnStart, CustomButton btnReset) {
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
        if(rb2.isSelected()) { //THIS DONT WORK
            btnStart.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    GridPane gpjCoaching = showDaRight();
                    vbPo.getChildren().add(gpjCoaching);
                }
            });
        }
           if(rb1.isSelected()) {
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        GridPane gpjCoaching = showDaRight();
                        vbPo.getChildren().add(gpjCoaching);
                    }
                });
            }


        // HBox to hold buttons and logo
        HBox hbBottom = new HBox();
        hbBottom.setMaxSize(500, 105);
        hbBottom.setSpacing(55);
        hbBottom.getChildren().addAll(setButtons(btnStart, btnReset), setLogo());

        vbRight.getChildren().addAll(vbPo, hbBottom);
        return vbRight;
    }


        public String[] getSignsRb2 () {
            return signsRb2;
        }

        public void setSignsRb2 (String[]signsRb2){
            this.signsRb2 = signsRb2;
        }

        public static CustomRadioButton getRb1() {
            return rb1;
        }

        public void setRb1 (CustomRadioButton rb1){
            this.rb1 = rb1;
        }

        public static CustomRadioButton getRb2() {
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

    public static ComboBox getCb() {
        return cb;
    }

    public void setCb(ComboBox cb) {
        this.cb = cb;
    }

    public static CustomTextField getT1() {
        return t1;
    }

    public void setT1(CustomTextField t1) {
        this.t1 = t1;
    }

    public static CustomTextField getT2() {
        return t2;
    }

    public void setT2(CustomTextField t2) {
        this.t2 = t2;
    }

    public static CustomTextField getT3() {
        return t3;
    }

    public void setT3(CustomTextField t3) {
        this.t3 = t3;
    }

    public static CustomTextField getT4() {
        return t4;
    }

    public void setT4(CustomTextField t4) {
        this.t4 = t4;
    }

    public static CustomTextField getT5() {
        return t5;
    }

    public void setT5(CustomTextField t5) {
        this.t5 = t5;
    }

    public static CustomTextField getT6() {
        return t6;
    }

    public void setT6(CustomTextField t6) {
        this.t6 = t6;
    }

    public static CustomTextField getT7() {
        return t7;
    }

    public void setT7(CustomTextField t7) {
        this.t7 = t7;
    }

    public static CustomTextField getT8() {
        return t8;
    }

    public void setT8(CustomTextField t8) {
        this.t8 = t8;
    }

    public static CustomTextField getT9() {
        return t9;
    }

    public void setT9(CustomTextField t9) {
        this.t9 = t9;
    }
}