package pack.View;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
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
    private CustomTextField t1,t2,t3,t4,t5,t6,t7,t8,t9;



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
   public GridPane RIGHT3X3(){
        GridPane gpt = new GridPane();
       gpt.setPadding(new Insets(10, 10, 10, 10));
       Text text = new Text("FOR THE EIGENVALUE " + Model2for3x3.getX1() + " THE EIGEN VECTOR IS");
       Text text1 = new Text("FOR THE EIGENVALUE " + Model2for3x3.getX2() + " THE EIGEN VECTOR IS");
       Text text2 = new Text("FOR THE EIGENVALUE " + Model2for3x3.getX3() + " THE EIGEN VECTOR IS");
       gpt.setPrefSize(500, 695);
       gpt.setLayoutX(520);
       gpt.setLayoutY(14);
        ImageView imL = new ImageView(new Image(Custom.p + "brack left.png"));
       imL.setFitWidth(15);
       imL.setFitHeight(75);
       ImageView imL1 = new ImageView(new Image(Custom.p + "brack left.png"));
       imL1.setFitWidth(15);
       imL1.setFitHeight(75);
       ImageView imL2 = new ImageView(new Image(Custom.p + "brack left.png"));
       imL2.setFitWidth(15);
       imL2.setFitHeight(75);
       ImageView imR = new ImageView(new Image(Custom.p + "brack right.png"));
       imR.setFitWidth(14);
       imR.setFitHeight(75);
       ImageView imR1 = new ImageView(new Image(Custom.p + "brack right.png"));
       imR1.setFitWidth(14);
       imR1.setFitHeight(75);
       ImageView imR2 = new ImageView(new Image(Custom.p + "brack right.png"));
       imR2.setFitWidth(14);
       imR2.setFitHeight(75);
       gpt.add(text,1,10);
       gpt.add(imL,2,15);
       gpt.add(imR,5,15);
       gpt.add(text1,1,20);
       gpt.add(imL1,2,25);
       gpt.add(imR1,5,25);
       gpt.add(text2,1,30);
       gpt.add(imL2,2,35);
       gpt.add(imR2,5,35);


        return gpt;
    }
    public GridPane RIGHT2X2(){
        GridPane gpt = new GridPane();
        gpt.setPadding(new Insets(10, 10, 10, 10));
        Text text = new Text("FOR THE EIGENVALUE " + Model2for2x2.getX1() + " THE EIGEN VECTOR IS");
        Text text1 = new Text("FOR THE EIGENVALUE " + Model2for2x2.getX2() + " THE EIGEN VECTOR IS");

        gpt.setPrefSize(500, 695);
        gpt.setLayoutX(520);
        gpt.setLayoutY(14);

        ImageView imL = new ImageView(new Image(Custom.p + "brack left.png"));
        imL.setFitWidth(15);
        imL.setFitHeight(75);

        ImageView imL1 = new ImageView(new Image(Custom.p + "brack left.png"));
        imL1.setFitWidth(15);
        imL1.setFitHeight(75);

        ImageView imR = new ImageView(new Image(Custom.p + "brack right.png"));
        imR.setFitWidth(14);
        imR.setFitHeight(75);

        ImageView imR1 = new ImageView(new Image(Custom.p + "brack right.png"));
        imR1.setFitWidth(14);
        imR1.setFitHeight(75);

        VBox vbx =  new VBox();
        vbx.getChildren().add(new Text(Double. toString(Model2for2x2.getS1().get(0))));
        vbx.getChildren().add(new Text(Double. toString(Model2for2x2.getS1().get(1))));


        gpt.add(text,1,10);
        gpt.add(imL,2,15);
        gpt.add(vbx,3,15);
        gpt.add(imR,5,15);
        if(Model2for2x2.getS1().size()==4){
            ImageView imR2 = new ImageView(new Image(Custom.p + "brack right.png"));
            imR.setFitWidth(14);
            imR.setFitHeight(75);

            ImageView imL2 = new ImageView(new Image(Custom.p + "brack left.png"));
            imL1.setFitWidth(15);
            imL1.setFitHeight(75);
            gpt.add(imL2,6,15);
            gpt.add(imR2,8,15);
        }

        gpt.add(text1,1,20);
        gpt.add(imL1,2,25);
        gpt.add(imR1,5,25);
        if(Model2for2x2.getS2().size()==4){
            ImageView imR3 = new ImageView(new Image(Custom.p + "brack right.png"));
            imR.setFitWidth(14);
            imR.setFitHeight(75);

            ImageView imL3 = new ImageView(new Image(Custom.p + "brack left.png"));
            imL1.setFitWidth(15);
            imL1.setFitHeight(75);
            gpt.add(imL3,6,25);
            gpt.add(imR3,8,25);
        }


        return gpt;
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
        /*if(rb2.isSelected()) {
            btnStart.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    GridPane gpjCoaching = RIGHT3X3();
                    vbPo.getChildren().add(gpjCoaching);
                }
            });
      //  }
*/          // if(rb1.isSelected()) {
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        GridPane gpjCoaching = RIGHT2X2();
                        vbPo.getChildren().add(gpjCoaching);
                    }
                });
        //    }


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