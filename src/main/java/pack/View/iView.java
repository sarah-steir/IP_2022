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
import pack.Model.CustomRadioButton;
import pack.Model.mainModel;

import java.util.ArrayList;

import static pack.Model.mainModel.p;

public interface iView {

    default Pane setView(String title) {
        Pane pane = new Pane();
        pane.setPrefSize(1050, 750);
        pane.setStyle("-fx-background-color: #6F6F77;");    // Blue Grey
        pane.getChildren().add(setLeft());
        pane.getChildren().add(setRight(title));
        return pane;
    }

    default HBox setButtons() {
        Button btnStart = new CustomButton("Start\nthe\n MAGIK");
        btnStart.setMinSize(115, 105);
        Button btnReset = new CustomButton("Reset\nthe\nMAGIK");
        btnReset.setMinSize(115, 105);
        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10);
        hbButtons.getChildren().addAll(btnStart, btnReset);
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
        vbUi.setPrefSize(500, 160);
        vbUi.setStyle("-fx-background-color: #333335"); // Grey
        vbUi.getChildren().add(setRadios());

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

    default HBox setRadios() {
        ToggleGroup group = new ToggleGroup();
        CustomRadioButton two = new CustomRadioButton("2x2", group);
        CustomRadioButton three = new CustomRadioButton("3x3", group);

        HBox radioBox = new HBox(10);
        radioBox.setPrefSize(500, 160);
        VBox radios = new VBox();
        radios.setSpacing(20);
        radios.setPrefWidth(115);
        radios.getChildren().addAll(two, three);
        radioBox.getChildren().add(radios);

        two.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                radioBox.getChildren().clear();
                radioBox.getChildren().addAll(radios, setTwoFields());
            }
        });

        three.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                radioBox.getChildren().clear();
                radioBox.getChildren().addAll(radios, setThreeFields());
            }
        });
        return radioBox;
    }

    default GridPane setTwoFields() {
        GridPane twoByTwo = new GridPane();
        twoByTwo.setVgap(10);
        twoByTwo.setHgap(10);
        twoByTwo.setAlignment(Pos.CENTER);
        ArrayList a = createFields(6);
        int counter = 0; // max 5
        int row = 0; // max 2
        int column = 0; // max 1

        twoByTwo.add((Node) a.get(0), 0, 0);
        twoByTwo.add((Node) a.get(1), 1, 0);
        twoByTwo.add((Node) a.get(2), 2, 0);
        twoByTwo.add((Node) a.get(3), 0, 1);
        twoByTwo.add((Node) a.get(4), 1, 1);
        twoByTwo.add((Node) a.get(5), 2, 1);

        return twoByTwo;
    }

    default GridPane setThreeFields() {
        GridPane threebyThree = new GridPane();
        threebyThree.setAlignment(Pos.CENTER);
        threebyThree.setVgap(10);
        threebyThree.setHgap(10);
        ArrayList a = createFields(9);

        threebyThree.add((Node) a.get(0), 0, 0);
        threebyThree.add((Node) a.get(1), 1, 0);
        threebyThree.add((Node) a.get(2), 2, 0);
        threebyThree.add((Node) a.get(3), 0, 1);
        threebyThree.add((Node) a.get(4), 1, 1);
        threebyThree.add((Node) a.get(5), 2, 1);
        threebyThree.add((Node) a.get(6), 0, 2);
        threebyThree.add((Node) a.get(7), 1, 2);
        threebyThree.add((Node) a.get(8), 2, 2);

        return threebyThree;
    }

    default ArrayList createFields(int tf) {
        ArrayList fieldList = new ArrayList(tf - 1); //6-1
        int counter = 1;
        while (counter <= tf) {
            TextField t = new TextField();
            t.setPrefSize(80, 25);
            fieldList.add(t);
            counter++;
        }
        return fieldList;
    }
}