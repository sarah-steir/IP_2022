package pack.View;

import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pack.View.Customs.Custom;
import pack.View.Customs.CustomButton;
import pack.View.Customs.CustomRadioButton;
import pack.View.Customs.CustomTextField;
import pack.View.GraphView.Graph;

public class View3 extends Pane implements iView {
//    public static RadioButton r1 = new RadioButton("Lines");
//    public static RadioButton r2 = new RadioButton("Planes");
//    Node n = setRadios(r1, r2);
//    ArrayList<TextField> arplanes = createFields(8, 80);
//    ArrayList<TextField> arlines = createFields(12, 30);
//    public static ArrayList<Label> labeling = iView.createSigns(signs("s+ ", "t+ "));
//    public static Graph graph3 = new Graph();

    private CustomRadioButton rb1, rb2;
    private CustomButton btnStart, btnReset;
    private ToggleGroup group = new ToggleGroup();
    private Graph graph = new Graph();
    private String[] signsRb1 = {"X: ", "S + ", "Y: ", "S + ", "Z: ", "S + "};
    private String[] signsRb2 = {"X +", "Y +", "Z ="};

    public View3() {

        rb1 = new CustomRadioButton("Lines");
        rb2 = new CustomRadioButton("Planes");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);

        btnStart = new CustomButton("START\nTHE\nMAGIK");
        btnReset = new CustomButton("RESET\nTHE\nMAGIK");
        this.getChildren().addAll(setView(rb1, rb2, btnStart, btnReset, signsRb1, signsRb2, "Lines and planes", graph));
        graph.addPlane(12, 43, 43, "");
    }

    @Override
    public VBox setRadios(CustomRadioButton rb1, CustomRadioButton rb2, CustomButton btnStart, String[] signsRb1, String[] signsRb2) {

        VBox vbRadioBox = new VBox();
        vbRadioBox.setPrefSize(500, 160);

        HBox hbRadios = new HBox();
        hbRadios.setSpacing(20);
        hbRadios.setPrefWidth(115);
        hbRadios.getChildren().addAll(rb1, rb2);
        vbRadioBox.getChildren().add(hbRadios);

        rb1.setOnAction(event -> {
            vbRadioBox.getChildren().clear();
            vbRadioBox.getChildren().addAll(hbRadios, setFields(2, 6, btnStart, signsRb1));
        });

        rb2.setOnAction(event -> {
            vbRadioBox.getChildren().clear();
            vbRadioBox.getChildren().addAll(hbRadios, setFields(2, 4, btnStart, signsRb2));
        });
        return vbRadioBox;
    }

    @Override
    public GridPane setFields(int rows, int cols, CustomButton btnStart, String[] signs) {
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
                lblVariable.setFont(Custom.font);

                if (j == cols - 1) {
                    if (cols == 6) {
                        lblVariable.setText("T + ");
                    } else {
                        lblVariable.setText("");
                    }
                } else if (signs[j].equals("S + ") && i == 1) {
                    lblVariable.setText("T + ");
                } else {
                    lblVariable.setText(signs[j]);
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

                fieldList[i][j].textProperty().addListener((observable, oldValue, newValue) ->
                        btnStart.setDisable(fieldList[finalI][finalJ].checkField()));

                if (cols == 6) {
                    fieldList[i][j].setMaxWidth(35);
                    hbTextField.getChildren().addAll(lblVariable, fieldList[i][j]);
                } else {
                    hbTextField.getChildren().addAll(fieldList[i][j], lblVariable);
                }
                gridPane.add(hbTextField, j, i);
            }
        }
        return gridPane;
    }
}