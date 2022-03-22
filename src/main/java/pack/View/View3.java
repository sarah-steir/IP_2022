package pack.View;

import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import javafx.scene.layout.Pane;
import pack.Model.CustomRadioButton;

import java.util.ArrayList;

public class View3 extends Pane implements iView{
//    RadioButton r1=new RadioButton("Lines");
//    RadioButton r2=new RadioButton("Planes");
//    Node n=setRadios(r1,r2);
//    public static ArrayList<TextField> c= iView.createFields(8);

    public View3() {
//        iView.createSigns(3);
//        Pane p = iView.setLeft(r1, r2, iView.set2Fields(), set3Fields(2, 7, c), setRadios(r1, r2));
//        this.getChildren().add(setView("Lines And THe PLanes shit", p));
//        iView.handleButton(3);
        CustomRadioButton rb1 = new CustomRadioButton("Lines");
        CustomRadioButton rb2 = new CustomRadioButton("Planes");
        this.getChildren().add(setView("Lines and Planes", rb1, rb2));
    }
}
