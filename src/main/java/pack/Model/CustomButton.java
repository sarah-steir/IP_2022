package pack.Model;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import static pack.Model.mainModel.p;

public class CustomButton extends Button {

    public CustomButton(String message) {
        super(message);
        this.setStyle("-fx-background-color: #333335;" +
                "-fx-text-fill: E7EBEE;");
        this.setTextAlignment(TextAlignment.CENTER);
        this.setFont(mainModel.font);
    }
}
