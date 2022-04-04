package pack.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pack.Controller.V2Controller;
import pack.Model.CustomButton;
import pack.Model.CustomRadioButton;
import pack.Model.CustomTextField;
import pack.Model.mainModel;
import javafx.scene.control.Label;

import java.lang.reflect.Array;
import java.util.ArrayList;
//import org.apache.commons.lang3.math.NumberUtils;

import static pack.Model.mainModel.p;

public interface iView {

    CustomButton btnStart = new CustomButton("Start\nthe\nMAGIK");
    CustomButton btnReset = new CustomButton("Reset\nthe\nMAGIK");

    default Pane setView(String title, CustomRadioButton rb1, CustomRadioButton rb2) {
        Pane pane = new Pane();
        pane.setPrefSize(1050, 750);
        pane.setStyle("-fx-background-color: #6F6F77;");    // Blue Grey
        pane.getChildren().addAll(setLeft(rb1, rb2), setRight(title));

        btnReset.setOnAction(event -> {
            pane.getChildren().clear();
            rb1.setSelected(false);
            rb2.setSelected(false);
            pane.getChildren().addAll(setLeft(rb1, rb2), setRight(title));
        });

        return pane;
    }

    // All these are just UI it sets the panes and nodes on the right place
    default HBox setButtons() {
        btnStart.setMinSize(115, 105);
        btnReset.setMinSize(115, 105);

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10);
        hbButtons.getChildren().addAll(btnStart, btnReset);
        return hbButtons;
    }


    default ImageView setLogo() {
        ImageView iv = new ImageView(new Image(p + "Logo.png"));
        iv.setFitWidth(225);
        iv.setFitHeight(105);
        return iv;
    }

    default VBox setLeft(CustomRadioButton rb1, CustomRadioButton rb2) {
        VBox vbLeft = new VBox();
        vbLeft.setSpacing(10);
        vbLeft.setPrefSize(500, 695);
        vbLeft.setLayoutX(10);
        vbLeft.setLayoutY(14);

        // User Input Box
        VBox vbUi = new VBox();
        vbUi.setPrefSize(500, 160);
        vbUi.setStyle("-fx-background-color: #333335"); // Grey

        ToggleGroup toggleGroup = new ToggleGroup();
        rb1.setToggleGroup(toggleGroup);
        rb2.setToggleGroup(toggleGroup);
        vbUi.getChildren().add(setRadios(rb1, rb2));

        // Graph Box
        Pane graph = new Pane();
        graph.setPrefSize(500, 525);
        graph.setStyle("-fx-background-color: #333335");    // Grey

        vbLeft.getChildren().addAll(vbUi, graph);
        return vbLeft;
    }

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

    // Creates the 2x2 and 3x3 radiobutton and connects them to a ToggleGroup so only one can be selected at a time
    default VBox setRadios(CustomRadioButton rb1, CustomRadioButton rb2) {

        VBox vbRadioBox = new VBox();
        vbRadioBox.setPrefSize(500, 160);

        HBox hbRadios = new HBox();
        hbRadios.setSpacing(20);
        hbRadios.setPrefWidth(115);
        hbRadios.getChildren().addAll(rb1, rb2);
        vbRadioBox.getChildren().add(hbRadios);

        rb1.setOnAction(event -> {
            vbRadioBox.getChildren().clear();
            vbRadioBox.getChildren().addAll(hbRadios, setFields(2, 3));
        });

        rb2.setOnAction(event -> {
            vbRadioBox.getChildren().clear();
            vbRadioBox.getChildren().addAll(hbRadios, setFields(3, 4));
        });
        return vbRadioBox;
    }

    default GridPane setFields(int rows, int cols) {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        CustomTextField[][] fieldList = new CustomTextField[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols;  j++) {
                HBox hbTextField = new HBox();
                hbTextField.setSpacing(10);

                fieldList[i][j] = new CustomTextField();
                Label lblVariable = new Label();
                lblVariable.setStyle("-fx-text-fill: E7EBEE;");
                lblVariable.setFont(mainModel.font);

                // Rearranges the signs for the RadioButtons
                if (j == 0) {
                    lblVariable.setText("X +");
                } else if (j == 1 && rows == 2) {
                    lblVariable.setText("Y =");
                } else if (j == 1 && rows == 3) {
                    lblVariable.setText("Y +");
                } else if (j == 2 && rows == 3) {
                    lblVariable.setText("Z =");
                } else {
                    lblVariable.setText("");
                }

                int finalI = i;
                int finalJ = j;
                btnStart.setOnAction(event -> {
                    for (CustomTextField[] tfArray: fieldList) {
                        for (CustomTextField tf: tfArray) {
                            btnStart.setDisable(tf.checkField());
                        }
                    }
                });

                fieldList[i][j].textProperty().addListener((observable, oldValue, newValue) -> btnStart.setDisable(fieldList[finalI][finalJ].checkField()));

                hbTextField.getChildren().addAll(fieldList[i][j], lblVariable);
                gridPane.add(hbTextField, j, i);
            }
        }
        return gridPane;
    }


}






