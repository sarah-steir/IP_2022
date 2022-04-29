package pack.View.Customs;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;

public class Custom {

    public static Color white = Color.web("#E7EBEE");
    public static Color grey = Color.web("#333234");
    public static Color red = Color.web("#DF5C58");
    public static Color yellow = Color.web("#F2C15F");
    public static Color blue = Color.web("#1985A1");

    static File f = new File("");
    public static String p = "file:" + f.getAbsolutePath() + "/Resources/";

    public static Font font = Font.loadFont(p + "Font.otf", 15);

    public static Label setTitle(String title) {
        Label t = new Label(title);
        t.setFont(font);
        t.setStyle("-fx-text-fill: E7EBEE;");
        t.setAlignment(Pos.CENTER);

        return t;
    }
}
