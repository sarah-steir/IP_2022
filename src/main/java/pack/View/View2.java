package pack.View;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class View2 extends Pane implements iView {

    public View2() {
        this.getChildren().add(setView("Eigen UI"));
    }

    @Override
    public VBox setLeft() {
        VBox vbLeft = new VBox();
        vbLeft.setSpacing(10);
        vbLeft.setLayoutX(10);
        vbLeft.setLayoutY(14);
        vbLeft.setPrefSize(500, 695);
        vbLeft.setStyle("-fx-background-color: #333335"); // Grey
        return vbLeft;
    }

    @Override
    public HBox setRadios() {
        return null;
    }
}
