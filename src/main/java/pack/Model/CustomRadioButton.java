package pack.Model;

import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.TextAlignment;

public class CustomRadioButton extends RadioButton {

    public CustomRadioButton(String message) {
        super(message);
        this.setStyle("-fx-text-fill: E7EBEE;");
        this.setTextAlignment(TextAlignment.CENTER);
        this.setPadding(new Insets(15));
        this.setFont(mainModel.font);
    }
}