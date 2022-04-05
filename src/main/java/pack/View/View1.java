package pack.View;

import javafx.scene.layout.Pane;
import pack.View.Customs.CustomRadioButton;
import pack.View.GraphView.Graph;

public class View1 extends Pane implements iView {
    Graph graph = new Graph();

    public View1() {
        CustomRadioButton rb1 = new CustomRadioButton("2x2");
        CustomRadioButton rb2 = new CustomRadioButton("3x3");
        this.getChildren().add(setView("System of Linear Equations", rb1, rb2, graph));
    }
}