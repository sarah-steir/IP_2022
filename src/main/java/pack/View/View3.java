package pack.View;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class View3 extends Pane implements iView {
   public static RadioButton r1 = new RadioButton("Lines");
   public static RadioButton r2 = new RadioButton("Planes");
    Node n = setRadios(r1, r2);
    public static ArrayList<TextField> arplanes = iView.createFields(8,80);
    public static ArrayList<TextField> arlines = iView.createFields(12,30);
    public static ArrayList<Label> labeling=iView.createSigns(signs("s+ ", "t+ "));



    public View3() {
        iView.createSigns(3);
        Pane p = iView.setLeft(r1, r2, lines(), set3Fields(2, 7, arplanes), setRadios(r1, r2));
        this.getChildren().add(setView("Lines And THe PLanes shit", p));
        iView.handleButton(3);

    }

    public  static ArrayList<String> signs (String l,String l2){
        String s1= new String("x: ");
        String s2= new String(" y: ");
        String s3= new String(" z: ");
        String[]pr={s1,s2,s3};
        int j=0;
        ArrayList<String> arr= new ArrayList<String>();



        while (j!=2) {
            for (int i=0; i<=2;i++) {
                arr.add(pr[i]);
                arr.add(l);} j++;

            if(j==1) {
                for (int i=0; i<=2;i++) {
                    arr.add(pr[i]);
                    arr.add(l2);}} j++;}


        return arr;}

    public GridPane lines () {
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
        int j=0;
        int row = 0; //max 11

        int n = 0;


        while(n!=4) {

            if (n % 2 == 1) {
                System.out.print(n + " " + row + " " + i + " " + j);
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


}
