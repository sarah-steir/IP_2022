package pack.Model;

import javafx.scene.text.Font;

import java.io.File;

public class mainModel {

    static File f = new File("");
    public static String p = "file:" + f.getAbsolutePath() + "/";

    public static Font font = Font.loadFont(p + "Font.otf", 15);

}
