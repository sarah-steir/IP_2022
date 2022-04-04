package pack.View;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pack.Model.CustomRadioButton;
import pack.View.GraphView.Graph;

import java.util.ArrayList;
public class View1 extends Pane implements iView {
    public static RadioButton twoo= new CustomRadioButton("2 x 2");
    public static RadioButton threee= new CustomRadioButton("3 x 3");
    ArrayList<TextField> a=iView.createFields(6,80); //fields for 2by2
    ArrayList<TextField> b=iView.createFields(12,80); //fields for 3by3
    Graph graph = new Graph();

    public View1() {
        Pane p=iView.setLeft(twoo,threee,set2Fields(),set3Fields(3,7,b),setRadios(twoo,threee), graph);
        this.getChildren().add(setView("System of Linear Equations",p));
    }
}