package pack.View.Customs;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static pack.View.Customs.Custom.p;

public class CustomText extends Text {

    public CustomText(String message) {
        super (message);
        this.setFont(Custom.font);
    }

    public void changeSize(int size) {
        this.setFont(Font.loadFont(p + "Font.otf", size));
    }
}