package pack.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import pack.Model.CustomButton;

import java.util.ArrayList;

import static pack.Model.mainModel.p;


public class View3 extends Pane implements iView{
    RadioButton r1=new RadioButton("Lines");
    RadioButton r2=new RadioButton("Planes");
    Node n=setRadios(r1,r2);
    public static ArrayList<TextField> c= iView.createFields(8);

    public View3() {
        iView.createSigns(3);
        Pane p=iView.setLeft(r1,r2,iView.set2Fields(),set3Fields(2,7,c),setRadios(r1,r2));
        this.getChildren().add(setView("Lines And THe PLanes shit",p));
        iView.handleButton(3);
    }




}
