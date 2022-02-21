package pack.View;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import pack.Model.CustomButton;
import static pack.Model.mainModel.p;


public class View3 extends Pane implements iView{

    public View3() {
        this.getChildren().add(iView.setView("Lines And THe PLanes shit"));
    }
}
