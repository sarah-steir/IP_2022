package pack.Model;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

import static pack.Model.mainModel.p;

public class CustomButton extends Button {

    Font font = new Font(p + "Font.ttf", 15);

    public CustomButton(String message) {
        super(message);
        this.setStyle("-fx-background-color: #333335;" +
                "-fx-text-fill: E7EBEE;");
        this.setFont(font);
        this.setMinSize(115, 30);
    }

}
