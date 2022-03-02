package pack.View;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.scene.text.Text;
import pack.Model.CustomButton;
import pack.Model.mainModel;

import java.util.ArrayList;

import static pack.Model.mainModel.p;

public class View1 extends Pane implements iView {
     public static RadioButton twoo = new RadioButton("2x2");

    public static RadioButton threee= new RadioButton("3x3");

    public View1() {
        this.getChildren().add(setView("System of Linear Equations"));


    }

}