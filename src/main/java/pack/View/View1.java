package pack.View;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import pack.Model.CustomRadioButton;

import java.util.ArrayList;

public class View1 extends Pane implements iView {
    //ArrayList<TextField> a=iView.createFields(6); //fields for 2by2
    //ArrayList<TextField> b=iView.createFields(12); //fields for 3by3

    public View1() {
        //Pane p=iView.setLeft(twoo,threee,iView.set2Fields(),set3Fields(3,7,b),setRadios(twoo,threee));
        CustomRadioButton rb1 = new CustomRadioButton("2x2");
        CustomRadioButton rb2 = new CustomRadioButton("3x3");
        this.getChildren().add(setView("System of Linear Equations", rb1, rb2));
    }
}