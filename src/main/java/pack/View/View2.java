package pack.View;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pack.Model.CustomRadioButton;

public class View2 extends Pane implements iView {

    public View2() {
    }

    @Override
    public VBox setLeft(CustomRadioButton rb1, CustomRadioButton rb2) {
        VBox vbLeft = new VBox();
        vbLeft.setSpacing(10);
        vbLeft.setLayoutX(10);
        vbLeft.setLayoutY(14);
        vbLeft.setPrefSize(500, 695);
        vbLeft.setStyle("-fx-background-color: #333335"); // Grey
        return vbLeft;
    }
}
