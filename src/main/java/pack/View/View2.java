package pack.View;

import javafx.collections.ObservableArray;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pack.View.Customs.CustomButton;
import pack.View.Customs.CustomRadioButton;
import pack.View.GraphView.Graph;

public class View2 extends Pane implements iView {
    private CustomRadioButton rb1, rb2;
    private CustomButton btnStart, btnReset;
    private ToggleGroup group = new ToggleGroup();
    private String[] signsRb1 = {"", "|"};
    private String[] signsRb2 = {"", "", "|"};
    private ComboBox comboBox = new ComboBox();

    public View2() {
        rb1 = new CustomRadioButton("2 x 2");
        rb2 = new CustomRadioButton("3 x 3");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        comboBox.getItems().addAll("Identity", "Upper Triangular", "Lower Triangular", "Diagonal", "Symmetrical", "Null");
        btnStart = new CustomButton("START\nTHE\nMAGIK");
        btnReset = new CustomButton("RESET\nTHE\nMAGIK");
        this.getChildren().addAll(setView(rb1, rb2, btnStart, btnReset, signsRb1, signsRb2, "EigenValues and EigenVectors", null, comboBox));
    }

    @Override
    public VBox setLeft(CustomRadioButton rb1, CustomRadioButton rb2, CustomButton btnStart, String[] signsRb1, String[] signsRb2, Graph graph,
                        ComboBox comboBox) {
        VBox vbLeft = new VBox();
        vbLeft.setSpacing(10);
        vbLeft.setPrefSize(500, 695);
        vbLeft.setLayoutX(10);
        vbLeft.setLayoutY(14);
        vbLeft.setStyle("-fx-background-color: #333335"); // Grey
        vbLeft.getChildren().addAll(setRadios(rb1, rb2, btnStart, signsRb1, signsRb2, comboBox));
        return vbLeft;
    }

    public String[] getSignsRb2() {
        return signsRb2;
    }

    public void setSignsRb2(String[] signsRb2) {
        this.signsRb2 = signsRb2;
    }

    public CustomRadioButton getRb1() {
        return rb1;
    }

    public void setRb1(CustomRadioButton rb1) {
        this.rb1 = rb1;
    }

    public CustomRadioButton getRb2() {
        return rb2;
    }

    public void setRb2(CustomRadioButton rb2) {
        this.rb2 = rb2;
    }

    public CustomButton getBtnStart() {
        return btnStart;
    }

    public void setBtnStart(CustomButton btnStart) {
        this.btnStart = btnStart;
    }

    public CustomButton getBtnReset() {
        return btnReset;
    }

    public void setBtnReset(CustomButton btnReset) {
        this.btnReset = btnReset;
    }

    public ToggleGroup getGroup() {
        return group;
    }

    public void setGroup(ToggleGroup group) {
        this.group = group;
    }

    public String[] getSignsRb1() {
        return signsRb1;
    }

    public void setSignsRb1(String[] signsRb1) {
        this.signsRb1 = signsRb1;
    }
}