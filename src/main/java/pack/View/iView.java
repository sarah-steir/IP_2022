package pack.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pack.Model.CustomButton;
import pack.Model.mainModel;
import javafx.scene.control.Label;
import java.util.ArrayList;

import static pack.Model.mainModel.p;

public interface iView {
    ArrayList a=createFields(2); //fields for 2by2
    ArrayList b=createFields(3); //fields for 3by3

    default HBox setButtons() {
        Button btnStart = new CustomButton("Start\nthe\n MAGIK");
        btnStart.setMinSize(115, 105);
        Button btnReset= new CustomButton("Reset\nthe\nMAGIK");
        btnReset.setMinSize(115, 105);
        HBox hbButtons= new HBox();
        hbButtons.setSpacing(10);
        hbButtons.getChildren().addAll(btnStart,btnReset);
        btnStart.setOnAction(e -> System.out.println("Patate"));
        btnReset.setOnAction(e -> System.out.println("Reset patate"));
        return hbButtons;
    }

    default ImageView setLogo() {
        ImageView iv = new ImageView(new Image(p + "Logo.png"));
        iv.setFitWidth(225);
        iv.setFitHeight(105);
        return iv;
    }

    default VBox setLeft() {
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
        vbUi.getChildren().addAll(setRadios());

        View1.twoo.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                if(View1.twoo.isSelected()) {
                    vbUi.getChildren().clear();
                    vbUi.getChildren().addAll(setRadios());
                    vbUi.getChildren().add(set2Fields());
                }
            }});

        View1.threee.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                if(View1.threee.isSelected()) {
                    vbUi.getChildren().clear();
                    vbUi.getChildren().addAll(setRadios());
                    vbUi.getChildren().add(set3Fields());
                }
            }});



        // Graph Box
        Pane graph = new Pane();
        graph.setPrefSize(500, 525);
        graph.setStyle("-fx-background-color: #333335");    // Grey

        vbLeft.getChildren().addAll(vbUi, graph);
        return vbLeft;
    }

    default VBox setRight(String title) {
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
        vbPo.getChildren().add(mainModel.setTitle(title));

        // HBox to hold buttons and logo
        HBox hbBottom = new HBox();
        hbBottom.setMaxSize(500, 105);
        hbBottom.setSpacing(55);
        hbBottom.getChildren().addAll(setButtons(), setLogo());

        vbRight.getChildren().addAll(vbPo, hbBottom);
        return vbRight;
    }

    default Pane setView(String title) {
        Pane pane = new Pane();
        pane.setPrefSize(1050, 750);
        pane.setStyle("-fx-background-color: #6F6F77;");    // Blue Grey
        pane.getChildren().add(setLeft());
        pane.getChildren().add(setRight(title));
        return pane;
    }

    public static Node setRadios(){
        ToggleGroup size = new ToggleGroup();
        RadioButton two= new RadioButton("2x2");
        two.setLayoutX(80);
        two.setLayoutY(150);
        View1.twoo.setStyle("-fx-text-fill: E7EBEE;");
        View1.twoo.setToggleGroup(size);
        RadioButton three= new RadioButton("3x3");
        View1.threee.setStyle("-fx-text-fill: E7EBEE;");
        View1.threee.setToggleGroup(size);
        HBox radios= new HBox();
        radios.setSpacing(20);

        radios.getChildren().addAll( View1.twoo, View1.threee);

        return radios;
    }

    public static GridPane set2Fields(){
        Label l= new Label("=");
        Label l2= new Label("=");
        l.setStyle("-fx-text-fill: E7EBEE;");
        l2.setStyle("-fx-text-fill: E7EBEE;");
        GridPane twoByTwo= new GridPane();
        twoByTwo.setVgap(10);
        twoByTwo.setHgap(10);
        twoByTwo.setAlignment(Pos.BOTTOM_CENTER);
        ArrayList sL= createSigns(2);

        int acounter=0; //max 5
        int sLcounter=0; //max 3
        int row=0; //max 2
        int column=0;
        int n=0;

        while(row!=2) {
           while(column!=5) {
                if(column%2==0){
                    if (acounter<=2+3*n){
                        twoByTwo.add((Node) a.get(acounter), column, row);
                        acounter++;
                        column++ ;} }

                    if(column%2==1){
                        if (sLcounter<=1+2*n){
                            twoByTwo.add((Node) sL.get(sLcounter), column, row);
                            sLcounter++;
                            column++ ;}}}
           n++;
           row++;
           column=0; }
        return twoByTwo;
    }

    public static GridPane set3Fields(){
        GridPane threebyThree= new GridPane();
        threebyThree.setAlignment(Pos.BOTTOM_CENTER);
        threebyThree.setVgap(10);
        threebyThree.setHgap(10);

        int acounter=0; //max 11
        int sLcounter=0; //max 8
        int row=0; //max 2
        int column=0; //max 6
        int n=0;

        ArrayList sL= createSigns(3);

        while(row!=3) {
            while(column!=7) {
                if(column%2==0){
                    if (acounter<=3+4*n){
                        threebyThree.add((Node) b.get(acounter), column, row);
                        acounter++;
                        column++ ;} }

                if(column%2==1){
                    if (sLcounter<=2+3*n){
                        threebyThree.add((Node) sL.get(sLcounter), column, row);
                        sLcounter++;
                        column++ ;}}}
            n++;
            row++;
            column=0; }

        return threebyThree;
    }

    public static ArrayList createFields(int tf){
        int field=tf*(tf+1);
        ArrayList fieldList= new ArrayList(field-1); //For 2 is 6 and for 3 is 12
        int counter=1;
        while(counter<=field){
            TextField t= new TextField();
            t.setPrefSize(80,25);
            fieldList.add(t);
            counter++;}

        return fieldList;}

    public static ArrayList createSigns(int i) {
        ArrayList<Label> signs= new ArrayList<Label>();
        
        if (i==2){
        for(int j=0;j<i;j++){
            Label x= new Label("X +");
            Label y= new Label("Y =");
            signs.add(x);
            signs.add(y);} }

        if (i==3){
            for(int j=0;j<i;j++){
                Label x= new Label("X +");
                Label y= new Label("Y +");
                Label z= new Label("Z =");
                signs.add(x);
                signs.add(y);
                signs.add(z);} }

        int c=0;
        while (c!=signs.size()){
            signs.get(c).setStyle("-fx-text-fill: E7EBEE;");
            c++;}
        return signs; }}
