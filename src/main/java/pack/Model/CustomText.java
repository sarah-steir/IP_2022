package pack.Model;

import javafx.scene.text.Text;

public class CustomText extends Text {

    public CustomText(String message) {
        super (message);
        this.setFont(mainModel.font);
    }
}
