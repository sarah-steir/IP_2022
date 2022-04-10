package pack.View.Customs;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.File;

public class Custom {

    static File f = new File("");
    public static final String PATH = "file:" + f.getAbsolutePath() + "/Resources/";
    public static String p = PATH;

    public static Font font = Font.loadFont(p + "Font.otf", 15);
    public static Color white = Color.web("#E7EBEE");
    public static Color grey = Color.web("#333234");
    public static Color color1 = Color.web("#DF5C58"); // RED
    public static Color color2 = Color.web("#F2C15F"); // YELLOW
    public static Color color3 = Color.web("#1985A1"); // BLUE
    public static ImageView logo = new ImageView(new Image(p + "TheOriginal/Trans.gif"));
    public static ImageView trans1 = new ImageView(new Image(p + "TheOriginal/Trans.gif"));
    public static ImageView trans2 = new ImageView(new Image(p + "TheOriginal/Trans2.gif"));

    public static Label setTitle(String title){
        Label t = new Label(title);
        t.setFont(font);
        t.setStyle("-fx-text-fill: E7EBEE;");
        t.setAlignment(Pos.CENTER);
        return t;}

    public static void setTheme(int theme) {
        switch (theme) {
            case 2 :
                p  = PATH + "Spooky/";
                color1 = Color.web("#AC00FF"); // PURPLE
                color2 = Color.web("#4BFF00"); // GREEN
                color3 = Color.web("#FFA000"); // ORANGE
                break;
            case 3 :
                p = PATH + "DeepWoter/";
                color1 = Color.web("#FEBF73"); // YELLOW / SAND
                color2 = Color.web("#00A89D"); // LIGHT BLUE
                color3 = Color.web("#007162"); // DARK BLUE
                break;
            default :
                p = PATH + "TheOriginal/";
                color1 = Color.web("#DF5C58"); // RED
                color2 = Color.web("#F2C15F"); // YELLOW
                color3 = Color.web("#1985A1"); // BLUE
                break;
        }
        update();
    }

    public static void update() {
        logo.setImage(new Image(p + "Logo.png"));
        trans1 = new ImageView(new Image(p + "Trans1.gif"));
        trans2 = new ImageView(new Image(p + "Trans2.gif"));
    }

}
