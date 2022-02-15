package pack.View;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import pack.Model.CustomButton;
import static pack.Model.mainModel.p;


public class View3 extends Pane {

    public View3() {
        this.setPrefSize(1050, 750);
        this.setStyle("-fx-background-color: #7F8753;");
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
        //left.setStyle("-fx-background-color:#EEBBF8;"); //pink

        //User input
        VBox ui= new VBox(); //1
        ui.setPrefSize(600, 160);
        //ui.setMaxSize(725, 250);
        //ui.setPadding(new Insets(10,10,10,10));
        ui.setStyle("-fx-background-color:#860DD6;"); //purple

        //Graph
        Pane graph= new Pane();//3
        graph.setPrefSize(725, 525);
        graph.setStyle("-fx-background-color:#EAD062;"); //yellow

        left.getChildren().addAll(ui,graph);
        this.getChildren().add(left);
    }

    public void setRight(){
        VBox right= new VBox();
        right.setPrefSize(500, 695);
        right.setLayoutX(530);
        right.setLayoutY(14);
        right.setSpacing(10);
        // right.setStyle("-fx-background-color:#57B5FE;"); //blue

        //Program output
        VBox po= new VBox();//2
        po.setPrefSize(500, 595);
        po.setStyle("-fx-background-color:#AE4944;"); //red



        //Bottom that holds button and logo
        HBox bottom= new HBox();//4
        bottom.setMaxSize(500, 105);
        bottom.setSpacing(55);

        bottom.getChildren().addAll(setButtons(),setLogo());

        right.getChildren().addAll(po,bottom);
        this.getChildren().add(right);

    }


}

