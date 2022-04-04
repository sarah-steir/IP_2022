package pack.View;

import javafx.scene.layout.Pane;
import pack.View.Customs.CustomRadioButton;
import pack.View.GraphView.Graph;

public class View3 extends Pane implements iView{

    Graph graph = new Graph();

    public View3() {
        CustomRadioButton rb1 = new CustomRadioButton("Lines");
        CustomRadioButton rb2 = new CustomRadioButton("Planes");
        this.getChildren().add(setView("Lines and Planes", rb1, rb2));
    }
}
