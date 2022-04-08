package pack.View.Customs;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.File;

public class Custom {

    static File f = new File("");
    public static String p = "file:" + f.getAbsolutePath() + "/Resources/";

    public static Font font = Font.loadFont(p + "Font.otf", 15);

    public static Label setTitle(String title){
        Label t = new Label(title);
        t.setFont(font);
        t.setStyle("-fx-text-fill: E7EBEE;");
        t.setAlignment(Pos.CENTER);

        return t;}
}
