package pack.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pack.View.Customs.Custom;
import pack.View.Customs.CustomButton;

public class WelcomeView extends Pane {

    MainView main;

    public WelcomeView(MainView main) {
        this.main = main;
        this.setPrefSize(1050, 750);
        this.setStyle("-fx-background-color: #6F6F77;");
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().add(this.setButtons());
        this.getChildren().add(this.setLogo());
    }

    /***
     * This function takes care of the box containing the buttons
     *
     * @return the box that will be used for the buttons
     */
    public VBox setButtons() {

        VBox boxyy = new VBox();

        Button button1 = new CustomButton("Systems of Linear Equations");
        button1.setOnAction(e -> main.playAnimation(new View1()));
        Button button2 = new CustomButton("EigenValues & EigenVectors");
        button2.setOnAction(e -> main.playAnimation(new View2()));
        Button button3 = new CustomButton("Lines and Planes");
        button3.setOnAction(e -> main.playAnimation(new View3()));

        boxyy.setSpacing(20);
        boxyy.getChildren().addAll(button1, button2, button3);
        boxyy.setLayoutX(390);
        boxyy.setLayoutY(200);
        boxyy.setAlignment(Pos.CENTER);
        return boxyy;
    }

    public ImageView setLogo() {
        ImageView iv = new ImageView(new Image(Custom.p + "Logo.png"));
        iv.setFitWidth(225);
        iv.setFitHeight(105);
        iv.setLayoutX(805);
        iv.setLayoutY(610);
        return iv;
    }
}