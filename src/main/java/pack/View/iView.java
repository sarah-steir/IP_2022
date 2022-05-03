package pack.View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pack.View.Customs.Custom;
import pack.View.Customs.CustomButton;
import pack.View.Customs.CustomRadioButton;
import pack.View.Customs.CustomTextField;
import pack.View.GraphView.Graph;

import java.util.ArrayList;

import static pack.View.Customs.Custom.p;

public interface iView {

    /**
     * Set radio buttons of the corresponding view
     *
     * @param rb1 first radio button (one of the left)
     * @param rb2 second radio button (one of the right)
     * @return HBox formed by the two radio buttons
     */
    default HBox setHbRadios(CustomRadioButton rb1, CustomRadioButton rb2) {
        HBox hbRadios = new HBox();
        hbRadios.setSpacing(20);
        hbRadios.setPrefWidth(115);
        hbRadios.getChildren().addAll(rb1, rb2);
        return hbRadios;
    }

    /**
     * @param btnStart Start button, one button for each class
     * @param btnReset Reset button, one button for each class
     * @return HBox with the buttons and logo
     */
    default HBox setHbBottom(CustomButton btnStart, CustomButton btnReset) {
        HBox hbBottom = new HBox();
        hbBottom.setMaxSize(500, 105);
        hbBottom.setSpacing(55);
        hbBottom.getChildren().addAll(setButtons(btnStart, btnReset), setLogo());
        return hbBottom;
    }

    /**
     * @param btnStart Start button, one button for each class
     * @param btnReset Reset button, one button for each class
     * @return HBox with the buttons
     */
    default HBox setButtons(CustomButton btnStart, CustomButton btnReset) {
        btnStart.setMinSize(115, 105);
        btnReset.setMinSize(115, 105);
        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10);
        hbButtons.getChildren().addAll(btnStart, btnReset);
        return hbButtons;
    }


    default Pane setGraphPane(Graph graph) {
        Pane graphPane = new Pane();
        graphPane.getChildren().add(graph);
        graphPane.setPrefSize(500, 525);
        graphPane.setStyle("-fx-background-color: #333335");
        return graphPane;
    }

    default ImageView setLogo() {
        ImageView iv = new ImageView(new Image(p + "Logo.png"));
        iv.setFitWidth(225);
        iv.setFitHeight(105);
        return iv;
    }

    /**
     * @param vbUi      VBox for the user input ( formed of text fields and the signs)
     * @param graphPane Pane that contains the graph
     * @return a VBox that puts both panes together on the left side of the screen
     */
    default VBox setLeft(VBox vbUi, Pane graphPane) {
        VBox vbLeft = new VBox();
        vbLeft.setSpacing(10);
        vbLeft.setPrefSize(500, 695);
        vbLeft.setLayoutX(10);
        vbLeft.setLayoutY(14);
        vbLeft.getChildren().addAll(vbUi, graphPane);
        return vbLeft;
    }

    /**
     * @param vbPo      VBox for the program output (labels with solutions)
     * @param hbButtons HBox with buttons
     * @return a VBox that puts both panes together on the right side of the screen
     */
    default VBox setRight(VBox vbPo, HBox hbButtons) {
        VBox vbRight = new VBox();
        vbRight.setPrefSize(500, 695);
        vbRight.setLayoutX(520);
        vbRight.setLayoutY(14);
        vbRight.setSpacing(10);
        vbRight.getChildren().addAll(vbPo, hbButtons);
        return vbRight;
    }


    default boolean checkFields(ArrayList<CustomTextField> rb1, ArrayList<CustomTextField> rb2, boolean b) {
        ArrayList<Boolean> booleans = new ArrayList<>();
        int i = 0;
        ArrayList<CustomTextField> a;
        if (b) {
            a = rb1;
        } else {
            a = rb2;
        }

        while (i != a.size()) {
            CustomTextField t = a.get(i);
            t.setFont(Custom.smallerFont);

            if (!t.getText().isEmpty()) {
                if (isNumeric(t.getText())) {
                    t.setStyle(" -fx-control-inner-background:#A0A0A0;");
                    booleans.add(true);
                }

                if (!isNumeric(t.getText())) {
                    t.setStyle(" -fx-control-inner-background: #DC5C58;");
                    booleans.add(false);
                }
            }
            i++;
        }
        return booleans.contains(false);
    }

    default boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
