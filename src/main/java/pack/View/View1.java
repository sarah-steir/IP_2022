package pack.View;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.scene.text.Text;
import pack.Model.CustomButton;
import pack.Model.mainModel;

import java.util.ArrayList;

import static pack.Model.mainModel.p;


public class View1 extends Pane {

    public View1() {
        this.setPrefSize(1050, 750);
        this.setStyle("-fx-background-color: #7E7F9A;");
        this.setButtons();
        this.setLogo();
        this.setLeft();
        this.setRight();

    }

    public static Node setButtons() {
        Button start = new CustomButton("Start\nthe\n MAGIK");
        Button reset= new CustomButton("Reset\nthe\nMAGIK");
        HBox buttons= new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().addAll(start,reset);
        start.setOnAction(e -> System.out.println("Patate"));
        reset.setOnAction(e -> System.out.println("Reset patate"));
        return buttons;
    }

    public static Node setLogo() {
        ImageView iv = new ImageView(new Image(p + "Logo.png"));
        iv.setFitWidth(225);
        iv.setFitHeight(105);
        // iv.setLayoutX(805);
        // iv.setLayoutY(610);

        return iv;
    }

    public void setLeft(){
        VBox left= new VBox();
        left.setSpacing(10);
        left.setPrefSize(500, 695);
        left.setLayoutX(10);
        left.setLayoutY(14);

        //User input
        VBox ui= new VBox(); //1
        ui.setPrefSize(500, 160);
        ui.setStyle("-fx-background-color:#333335;"); //grey
        ui.getChildren().addAll(setRadios());

        //Graph
        Pane graph= new Pane();//3
        graph.setPrefSize(500, 525);
        graph.setStyle("-fx-background-color:#EAD062;"); //yellow

        left.getChildren().addAll(ui,graph);
        this.getChildren().add(left);
    }

    public void setRight(){
        VBox right= new VBox();
        right.setPrefSize(500, 695);
        right.setLayoutX(520);
        right.setLayoutY(14);
        right.setSpacing(10);

        //Program output
        VBox po= new VBox();//2
        po.setPrefSize(500, 595);
        po.setSpacing(15);
        po.setAlignment(Pos.TOP_CENTER);
        po.setStyle("-fx-background-color:#333335;");
        po.getChildren().addAll(mainModel.setTitle("System of linear equations"));





        //Bottom that holds button and logo
        HBox bottom= new HBox();//4
        bottom.setMaxSize(500, 105);
        bottom.setSpacing(55);

        bottom.getChildren().addAll(setButtons(),setLogo());

        right.getChildren().addAll(po,bottom);
        this.getChildren().add(right);

    }

    public static Node setRadios(){
        ToggleGroup size = new ToggleGroup();
        RadioButton two= new RadioButton("2x2");
        two.setStyle("-fx-text-fill: E7EBEE;");
        two.setToggleGroup(size);
        RadioButton three= new RadioButton("3x3");
        three.setStyle("-fx-text-fill: E7EBEE;");
        three.setToggleGroup(size);

        HBox radios= new HBox();
        radios.setSpacing(10);
        radios.getChildren().addAll(two,three);
        return radios;
    }

    public static Node set2Fields(){
        return null;
    }

    public static Node set3Fields(){
        return null;
    }

    public static ArrayList createFields(int tf){
        ArrayList fieldList= new ArrayList(tf-1);
        int counter=1;

        while(counter!=tf){
            TextField t= new TextField();
            counter++;
        }

        return fieldList;
    }





}
