package pack.View;

import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class View2 extends Pane implements iView {
    RadioButton ra1 = new RadioButton("2x2");
    RadioButton ra2 = new RadioButton("3x3");
    Node n = setRadios(ra1, ra2);
    public static ArrayList<TextField> a = iView.createFields(4);
    public static ArrayList<TextField> ab = iView.createFields(8);


    public View2() {
       this.getChildren().add(setView("Eigen UI",this.setLeft()));

    }

    @Override
    public VBox setLeft() {
        VBox vbLeft = new VBox();
        vbLeft.getChildren().add(n);
        vbLeft.setSpacing(10);
        vbLeft.setLayoutX(10);
        vbLeft.setLayoutY(14);
        vbLeft.setPrefSize(500, 695);
        vbLeft.setStyle("-fx-background-color: #333335"); // Grey
        return vbLeft;
    }
}
