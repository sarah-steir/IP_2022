package pack.View;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import pack.Model.CustomButton;
import static pack.Model.mainModel.p;


public class View1 extends Pane {

    public View1() {
        this.setPrefSize(1050, 750);
        this.setStyle("-fx-background-color: #7F8753;");
        this.setButton();
        this.setLogo();
    }

    public void setButton() {
        Button button = new CustomButton("Start the MAGIK");
        this.getChildren().add(button);
        button.setLayoutX(50);
        button.setLayoutY(50);

        button.setOnAction(e -> System.out.println("Patate"));
    }

    public void setLogo() {
        ImageView iv = new ImageView(new Image(p + "Logo.png"));
        this.getChildren().add(iv);
        iv.setFitWidth(225);
        iv.setFitHeight(100);
        iv.setLayoutX(805);
        iv.setLayoutY(610);
    }

}
