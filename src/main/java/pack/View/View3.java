package pack.View;

import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class View3 extends Pane implements iView {
   public static RadioButton r1 = new RadioButton("Lines");
   public static RadioButton r2 = new RadioButton("Planes");
    Node n = setRadios(r1, r2);
    public static ArrayList<TextField> c = iView.createFields(8);

    public View3() {
        iView.createSigns(3);
        Pane p = iView.setLeft(r1, r2, null, set3Fields(2, 7, c), setRadios(r1, r2));
        this.getChildren().add(setView("Lines And THe PLanes shit", p));
        iView.handleButton(3);
    }
}
