package pack.Model;

import javafx.scene.control.Menu;
import javafx.scene.text.TextAlignment;

import static pack.Model.mainModel.font;
import static pack.Model.mainModel.p;

public class CustomMenu extends Menu {

    public CustomMenu(String message) {
        super(message);
        this.setStyle("");
        //"-fx-font-size: 15px;");
    }

}
