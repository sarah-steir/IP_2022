package pack.View;

import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;

public class View1 extends Pane implements iView {
    static RadioButton twoo= new RadioButton("2by2");
    static RadioButton threee= new RadioButton("3by3");

    public View1() {
        this.getChildren().add(setView("System of Linear Equations"));
    }
}