package pack.View;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import pack.Model.CustomButton;
import static pack.Model.mainModel.p;

public class View2 extends Pane{

    public View2() {
        this.setPrefSize(1050, 750);
        this.setStyle("-fx-background-color: #6F6F77;");
        this.setLogo();
    }

    public Node setLogo() {
        ImageView iv = new ImageView(new Image(p + "Logo.png"));
        this.getChildren().add(iv);
        iv.setFitWidth(225);
        iv.setFitHeight(100);
        iv.setLayoutX(805);
        iv.setLayoutY(610);
        return iv;
    }

}
