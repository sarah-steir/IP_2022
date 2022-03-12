package pack.View;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class View1 extends Pane implements iView {
    static RadioButton twoo= new RadioButton("2by2");
    static RadioButton threee= new RadioButton("3by3");
    ArrayList<TextField> a=iView.createFields(6); //fields for 2by2
    ArrayList<TextField> b=iView.createFields(12); //fields for 3by3

    public View1() {
        Pane p=iView.setLeft(twoo,threee,iView.set2Fields(),set3Fields(3,7,b),setRadios(twoo,threee));
        this.getChildren().add(setView("System of Linear Equations",p));
    }
}