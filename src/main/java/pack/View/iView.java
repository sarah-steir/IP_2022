package pack.View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pack.Model.CustomButton;
import pack.Model.mainModel;

import static pack.Model.mainModel.p;

/**
 * This interface sets the general view for all three different scenes in the application.
 * It first sets the buttons, the logo as well as the different general panes.
 */

public interface iView {

    /**
     * This method calls all the different methods and sets the general view.
     * @param title is the title of the right pane
     * @return the pane that is the final general view
     */
    default Pane setView(String title) {
        Pane pane = new Pane();
        pane.setPrefSize(1050, 750);
        pane.setStyle("-fx-background-color: #6F6F77;");    // Blue Grey
        pane.getChildren().add(setLeft());
        pane.getChildren().add(setRight(title));
        return pane;
    }

    /**
     * This method sets the right pane of the general view. The method is defaulted to include the buttons and
     * the logo as well as a top-right pane called that serves as the program output.
     * @param title is the title of the right pane
     * @return the VBox of the right pane
     */
    default VBox setRight(String title) {
        VBox vbRight = new VBox();
        vbRight.setPrefSize(500, 695);
        vbRight.setLayoutX(520);
        vbRight.setLayoutY(14);
        vbRight.setSpacing(10);

        // Program Output Box
        VBox vbPo = new VBox();
        vbPo.setPrefSize(500, 595);
        vbPo.setSpacing(15);
        vbPo.setAlignment(Pos.TOP_CENTER);
        vbPo.setStyle("-fx-background-color: #333335");
        vbPo.getChildren().add(mainModel.setTitle(title));

        // HBox to hold buttons and logo
        HBox hbBottom = new HBox();
        hbBottom.setMaxSize(500, 105);
        hbBottom.setSpacing(55);
        hbBottom.getChildren().addAll(setButtons(), setLogo());

        vbRight.getChildren().addAll(vbPo, hbBottom);
        return vbRight;
    }

    /**
     * This method sets the buttons of the HBox for the general views. These two buttons are the
     * "Start the MAGIK" button and the "Reset the MAGIK" button which all have the same functions. These two
     * buttons are present in all the three panes.
     * @return the HBox containing the buttons
     */
    default HBox setButtons() {
        Button btnStart = new CustomButton("Start\nthe\n MAGIK");
        btnStart.setMinSize(115, 105);
        Button btnReset= new CustomButton("Reset\nthe\nMAGIK");
        btnReset.setMinSize(115, 105);
        HBox hbButtons= new HBox();
        hbButtons.setSpacing(10);
        hbButtons.getChildren().addAll(btnStart,btnReset);
        btnStart.setOnAction(e -> System.out.println("Patate"));
        btnReset.setOnAction(e -> System.out.println("Reset patate"));
        return hbButtons;
    }

    /**
     * This method sets the logo at the bottom-right of the pane.
     * @return the ImageView that contains the logo
     */
    default ImageView setLogo() {
        ImageView iv = new ImageView(new Image(p + "Logo.png"));
        iv.setFitWidth(225);
        iv.setFitHeight(105);
        return iv;
    }

    /**
     * This method sets the left pane of the general view. The default version of this method includes
     * a user input box at the top followed by a space then a graph pane underneath it. This method however is overriden in
     * View2.
     * @return the VBox that is the left pane
     */
    default VBox setLeft() {
        VBox vbLeft = new VBox();
        vbLeft.setSpacing(10);
        vbLeft.setPrefSize(500, 695);
        vbLeft.setLayoutX(10);
        vbLeft.setLayoutY(14);

        // User Input Box
        VBox vbUi = new VBox();
        vbUi.setPrefSize(500, 160);
        vbUi.setStyle("-fx-background-color: #333335"); // Grey

        // Graph Box
        Pane graph = new Pane();
        graph.setPrefSize(500, 525);
        graph.setStyle("-fx-background-color: #333335");    // Grey

        vbLeft.getChildren().addAll(vbUi, graph);
        return vbLeft;
    }
}
