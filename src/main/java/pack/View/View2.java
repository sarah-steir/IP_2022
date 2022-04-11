package pack.View;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pack.View.Customs.CustomButton;
import pack.View.Customs.CustomRadioButton;
import pack.View.GraphView.Graph;

import java.util.ArrayList;

public class View2 extends Pane implements iView {
    private CustomRadioButton rb1, rb2;
    private CustomButton btnStart, btnReset;
    private ToggleGroup group = new ToggleGroup();
    private Graph graph = new Graph();
    private String[] signsRb1 = {"", "|"};
    private String[] signsRb2 = {"", "", "|"};

    public View2() {
        rb1 = new CustomRadioButton("2 x 2");
        rb2 = new CustomRadioButton("3 x 3");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        btnStart = new CustomButton("START\nTHE\nMAGIK");
        btnReset = new CustomButton("RESET\nTHE\nMAGIK");
        this.getChildren().addAll(setView(rb1, rb2, btnStart, btnReset, signsRb1, signsRb2, "Eigen Values and EigenVectors", null, null));
    }

    @Override
    public VBox setLeft(CustomRadioButton rb1, CustomRadioButton rb2, CustomButton btnStart, String[] signsRb1, String[] signsRb2, Graph graph, ComboBox cb) {
        VBox vbLeft = new VBox();
        vbLeft.setSpacing(10);
        vbLeft.setPrefSize(500, 695);
        vbLeft.setLayoutX(10);
        vbLeft.setLayoutY(14);
        vbLeft.setStyle("-fx-background-color: #333335"); // Grey
        vbLeft.getChildren().add(setRadios(rb1, rb2, btnStart, signsRb1, signsRb2, cb));
        return vbLeft;
    }
}