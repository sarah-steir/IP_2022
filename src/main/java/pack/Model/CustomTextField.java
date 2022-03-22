package pack.Model;

import javafx.scene.control.TextField;

public class CustomTextField extends TextField {

    public CustomTextField(String message) {
        super(message);
        this.setPrefSize(80, 25);
        this.setStyle("-fx-background-color: #A0A0A0"); // Light Grey
    }
    public CustomTextField() {
        this.setPrefSize(80, 25);
        this.setStyle("-fx-background-color: #A0A0A0"); // Light Grey
    }
}
