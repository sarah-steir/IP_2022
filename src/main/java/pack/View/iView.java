package pack.View;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
//import pack.Controller.Controller1;
import pack.Model.Model2for2x2;
import pack.Model.Model2for3x3;
import pack.Model.ModelForJSON;
import pack.View.Customs.*;
import javafx.scene.control.Label;
import pack.View.GraphView.Graph;
import java.util.ArrayList;
import java.util.Arrays;

import static pack.View.Customs.Custom.p;

public interface iView {

    // Tayba is testing shiz rn so pls dont delete these extra functions down here
    default GridPane setFields (CustomTextField[][] textFields, String[] signs, CustomButton btnStart) {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        int rows = textFields.length;
        int cols = textFields[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols;  j++) {
                HBox hbTextField = new HBox();
                hbTextField.setSpacing(10);

                textFields[i][j] = new CustomTextField();
                Label lblVariable = new Label();
                lblVariable.setStyle("-fx-text-fill: E7EBEE;");
                lblVariable.setFont(Custom.font);
                lblVariable.setText(signs[j]);

                if (j == cols - 1) {
                    lblVariable.setText("");
                } else {
                    lblVariable.setText(signs[j]);
                }

                int finalI = i;
                int finalJ = j;

                textFields[i][j].textProperty().addListener((observable, oldValue, newValue) ->
                        btnStart.setDisable(textFields[finalI][finalJ].checkField()));

                hbTextField.getChildren().addAll(textFields[i][j], lblVariable);
                gridPane.add(hbTextField, j, i);
            }
        }
        return gridPane;
    }

    default HBox setHbRadios(CustomRadioButton rb1, CustomRadioButton rb2) {
        HBox hbRadios = new HBox();
        hbRadios.setSpacing(20);
        hbRadios.setPrefWidth(115);
        hbRadios.getChildren().addAll(rb1, rb2);

        return hbRadios;
    }

    default VBox setLeft(VBox vbUi, Pane graphPane) {
        VBox vbLeft = new VBox();
        vbLeft.setSpacing(10);
        vbLeft.setPrefSize(500, 695);
        vbLeft.setLayoutX(10);
        vbLeft.setLayoutY(14);
        vbLeft.getChildren().addAll(vbUi, graphPane);

        return  vbLeft;
    }

    default VBox setRight(VBox vbPo, HBox hbButtons) {
        VBox vbRight = new VBox();
        vbRight.setPrefSize(500, 695);
        vbRight.setLayoutX(520);
        vbRight.setLayoutY(14);
        vbRight.setSpacing(10);

        vbRight.getChildren().addAll(vbPo, hbButtons);

        return vbRight;
    }

    default HBox setHbBottom(CustomButton btnStart, CustomButton btnReset) {
        HBox hbBottom = new HBox();
        hbBottom.setMaxSize(500, 105);
        hbBottom.setSpacing(55);
        hbBottom.getChildren().addAll(setButtons(btnStart, btnReset), setLogo());

        return hbBottom;
    }

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

}