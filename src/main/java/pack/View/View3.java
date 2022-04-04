package pack.View;

import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import javafx.scene.layout.Pane;
import pack.Model.CustomRadioButton;

import java.util.ArrayList;

public class View3 extends Pane implements iView{

    public View3() {

        CustomRadioButton rb1 = new CustomRadioButton("Lines");
        CustomRadioButton rb2 = new CustomRadioButton("Planes");
        this.getChildren().add(setView("Lines and Planes", rb1, rb2));
    }
}
