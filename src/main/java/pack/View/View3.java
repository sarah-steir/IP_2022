package pack.View;

import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import pack.View.GraphView.Graph;

import java.util.ArrayList;

public class View3 extends Pane implements iView {
    public static RadioButton r1 = new RadioButton("Lines");
    public static RadioButton r2 = new RadioButton("Planes");
    Node n = setRadios(r1, r2);
    public static ArrayList<TextField> arplanes = iView.createFields(8, 80);
    public static ArrayList<TextField> arlines = iView.createFields(12, 30);
    public static ArrayList<Label> labeling = iView.createSigns(signs("s+ ", "t+ "));
    public static Graph graph3 = new Graph();

    public View3() {
        iView.createSigns(3);
        Pane p = iView.setLeft(r1, r2, lines(), set3Fields(2, 7, arplanes), setRadios(r1, r2), graph3);
        this.getChildren().add(setView("Lines And THe PLanes shit", p));
        handleButton(3);
        //graph3.addPlane(new Point3D(0,0,20),new Point3D(0,50,0),new Point3D(30,10,10),"fjdkskfkjsfjk");
        graph3.addLine(new Point3D(0, 0, 20), new Point3D(0, 50, 0));
    }

    public static ArrayList<String> signs(String l, String l2) {
        String s1 = new String("x: ");
        String s2 = new String(" y: ");
        String s3 = new String(" z: ");
        String[] pr = {s1, s2, s3};
        int j = 0;
        ArrayList<String> arr = new ArrayList<String>();

        while (j != 2) {
            for (int i = 0; i <= 2; i++) {
                arr.add(pr[i]);
                arr.add(l);
            }
            j++;
            if (j == 1) {
                for (int i = 0; i <= 2; i++) {
                    arr.add(pr[i]);
                    arr.add(l2);
                }
            }
            j++;
        }
        return arr;
    }

    public GridPane lines() {
        Label l = new Label("Line 1:");
        Label l2 = new Label("Line 2:");
        l.setStyle("-fx-text-fill: E7EBEE;");
        l2.setStyle("-fx-text-fill: E7EBEE;");
        GridPane twoByTwo = new GridPane();
        twoByTwo.setVgap(10);
        twoByTwo.setHgap(10);
        twoByTwo.setAlignment(Pos.BOTTOM_CENTER);

        int acounter = 0; //max 5
        int i = 0; //max 11 (for textfields)
        int j = 0;
        int row = 0; //max 11
        int n = 0;

        while (n != 4) {
            if (n % 2 == 1) {
                while (row != 12) {
                    if (row % 2 == 1) {
                        if (i != 3 * n + 3) {
                            twoByTwo.add((Node) arlines.get(i), row, n);
                            i++;
                            row++;
                        }
                    }
                    if (row % 2 == 0) {
                        if (j != 3 * n + 3) {
                            twoByTwo.add((Node) labeling.get(j), row, n);
                            j++;
                            row++;
                        }
                    }
                }
                n++;
                row = 0;
            }

            if (n == 0) {
                twoByTwo.add(l, 0, n);
                n++;
            }
            if (n == 2) {
                twoByTwo.add(l2, 0, n);
                n++;
            }
        }
        iView.checkFields(arlines);
        return twoByTwo;
    }

    public static void transform() {
        int n = 1;
        Double[][] constant = new Double[2][1];
        for(int i=0; i<2;i++) {
            while (n != 4) {
                if (n % 2 == 1) {
                    Double d1 = Double.parseDouble(arlines.get(n).getText());
                    Double d2 = Double.parseDouble(arlines.get(n + 6).getText());
                    constant[i][1]=d2-d1;
                    for (int p = 0;p< constant.length; p++) {
                        System.out.print(constant[p][0] + " ");
                    }
                }
                n++;  }
        } }

    public static void toMatrix() {
        Double[][] arr = new Double[2][2];
        Double d1 = Double.parseDouble(arlines.get(0).getText());
        arr[0][0] = d1;
        Double d2 = Double.parseDouble(arlines.get(2).getText());
        arr[1][0] = d2;
        Double d3 = Double.parseDouble(arlines.get(6).getText());
        arr[0][1] = d3;
        Double d4 = Double.parseDouble(arlines.get(8).getText());
        arr[1][1] = d4;

        for (int i = 0; i < arr.length; i++) { //this equals to the row in our matrix.
            for (int j = 0; j < arr[i].length; j++) { //this equals to the column in each row.
                System.out.print(arr[i][j] + " ");
            }
        }
    }
}