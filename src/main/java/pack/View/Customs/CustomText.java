package pack.View.Customs;

import javafx.scene.text.Text;
import pack.Model.mainModel;

public class CustomText extends Text {

    public CustomText(String message) {
        super (message);
        this.setFont(Custom.font);
    }
}