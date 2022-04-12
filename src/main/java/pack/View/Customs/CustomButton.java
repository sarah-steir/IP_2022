package pack.View.Customs;

import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

public class CustomButton extends Button {

    public CustomButton(String message) {
        super(message);
        this.setStyle("-fx-background-color: #F15152;" +
                "-fx-text-fill: E7EBEE;");
        this.setTextAlignment(TextAlignment.CENTER);
        this.setFont(Custom.font);
    }
}


