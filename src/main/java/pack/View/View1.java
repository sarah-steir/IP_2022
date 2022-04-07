package pack.View;

import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import pack.View.Customs.*;
import pack.View.GraphView.Graph;

public class View1 extends Pane implements iView {

    private CustomRadioButton rb1, rb2;
    private CustomButton btnStart, btnReset;
    private ToggleGroup group = new ToggleGroup();
    private Graph graph = new Graph();
    private String[] signsRb1 = {"X +", "Y ="};
    private String[] signsRb2 = {"X +", "Y +", "Z ="};

    public View1() {
        rb1 = new CustomRadioButton("2 x 2");
        rb2 = new CustomRadioButton("3 x 3");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        btnStart = new CustomButton("START\nTHE\nMAGIK");
        btnReset = new CustomButton("RESET\nTHE\nMAGIK");
        this.getChildren().addAll(setView(rb1, rb2, btnStart, btnReset, signsRb1, signsRb2, "Systems of linear equations", graph));
    }
}