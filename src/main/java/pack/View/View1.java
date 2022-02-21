package pack.View;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.scene.text.Text;
import pack.Model.CustomButton;
import pack.Model.mainModel;

import java.util.ArrayList;

import static pack.Model.mainModel.p;

public class View1 extends Pane implements iView {

    public View1() {
        this.getChildren().add(iView.setView("System of Linear Equations"));
    }

    public static Node setRadios(){
        ToggleGroup size = new ToggleGroup();
        RadioButton two= new RadioButton("2x2");
        two.setStyle("-fx-text-fill: E7EBEE;");
        two.setToggleGroup(size);
        RadioButton three= new RadioButton("3x3");
        three.setStyle("-fx-text-fill: E7EBEE;");
        three.setToggleGroup(size);

        HBox radios= new HBox();
        radios.setSpacing(10);
        radios.getChildren().addAll(two,three);
        return radios;
    }

    public static Node set2Fields(){
        return null;
    }

    public static Node set3Fields(){
        return null;
    }

    public static ArrayList createFields(int tf){
        ArrayList fieldList= new ArrayList(tf-1);
        int counter=1;

        while(counter!=tf){
            TextField t= new TextField();
            counter++;
        }

        return fieldList;
    }

}