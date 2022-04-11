package pack.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import pack.View.Customs.*;
import pack.View.GraphView.Graph;

import java.util.ArrayList;

public class View1 extends Pane implements iView {

    private CustomTextField[][] fieldListRb1, fieldListRb2;
    private GridPane fieldsPane;
    private CustomRadioButton rb1, rb2;
    private CustomButton btnStart, btnReset;
    private ToggleGroup group = new ToggleGroup();
    private Graph graph = new Graph();
    private String[] signsRb1 = {"X +", "Y ="};
    private String[] signsRb2 = {"X +", "Y +", "Z ="};

    private VBox vbUi;
    private VBox vbPo;

    private VBox vbLeft;
    private VBox vbRight;

    public View1() {
        rb1 = new CustomRadioButton("2 x 2");
        rb2 = new CustomRadioButton("3 x 3");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        btnStart = new CustomButton("START\nTHE\nMAGIK");
        btnReset = new CustomButton("RESET\nTHE\nMAGIK");

        fieldListRb1 = new CustomTextField[2][3];
        fieldListRb2 = new CustomTextField[3][4];
        fieldsPane = new GridPane();

        vbUi = new VBox();
        vbPo = new VBox();
        vbLeft = new VBox();
        vbRight = new VBox();

        setVbUi(setHbRadios(this.rb1, this.rb2));   // FIX THIS
        setVbPo("Systems of linear equations");

        setVbLeft(setLeft(this.vbUi, setGraphPane(graph)));
        setVbRight(setRight(this.vbPo, setHbBottom(this.btnStart, this.btnReset)));

        setView1();
        setActions();
    }

    public void setView1() {
        this.setPrefSize(1050, 750);
        this.setStyle("-fx-background-color: #6F6F77;");    // Blue Grey
        this.getChildren().addAll(this.vbLeft, this.vbRight);
    }

    private void setVbLeft(VBox vbLeft) {
        this.vbLeft = vbLeft;
    }

    private void setVbRight(VBox vbRight) {
        this.vbRight = vbRight;
    }

    private void setVbUi(HBox hbRadios) {
        this.vbUi.setSpacing(5);
        this.vbUi.setPrefSize(500, 160);
        this.vbUi.setStyle("-fx-background-color: #333335");
        this.vbUi.getChildren().add(hbRadios);
    }

    private void setVbPo(String title) {
        this.vbPo.setPrefSize(500, 595);
        this.vbPo.setSpacing(15);
        this.vbPo.setAlignment(Pos.TOP_CENTER);
        this.vbPo.setStyle("-fx-background-color: #333335");
        this.vbPo.getChildren().add(Custom.setTitle(title));
    }

    public void setActions() {
        rb1.setOnAction(event -> {
            fieldsPane = setFields(fieldListRb1, signsRb1, this.btnStart);
            this.vbUi.getChildren().clear();
            this.vbUi.getChildren().addAll(setHbRadios(rb1, rb2), fieldsPane);
        });

        rb2.setOnAction(event -> {
            fieldsPane = setFields(fieldListRb2, signsRb2, this.btnStart);
            this.vbUi.getChildren().clear();
            this.vbUi.getChildren().addAll(setHbRadios(rb1, rb2), fieldsPane);
        });

        // when none are selected (doesn't work)
        if (rb1.isSelected() && rb2.isSelected()) {
            btnStart.setDisable(true);
        }

        this.btnStart.setOnAction(event -> { handleStart(this); });
        this.btnReset.setOnAction(event -> { handleReset(); });
    }

    // null problem when start button is clicked without rb being clicked
    public void handleStart(View1 view1) {
        for (CustomTextField[] tfArray: fieldListRb1) {
            for (CustomTextField tf: tfArray) {
                if (tf.getText().equals("")) {  // find out why tf is sometimes null
                    tf.setText("0");
                }
                System.out.println("YO: " + tf.getText());
            }
        }
    }

    public void handleReset() {
        this.getChildren().clear();
        btnStart.setDisable(false);
        rb1.setSelected(false);
        rb2.setSelected(false);
        this.vbUi.getChildren().remove(fieldsPane);
        this.getChildren().addAll(this.vbLeft, this.vbRight);
    }

    // Use to add elements in vbPo after calculations
    public void addVbPo() {
        this.vbPo.getChildren().clear();
        setVbPo("Systems of linear equations");
        //this.vbPo.getChildren().add(new Graph());
    }
}