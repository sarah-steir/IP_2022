
package pack.View;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class View2 extends Pane implements iView {
    private static RadioButton r2 = new RadioButton("2x2");
    static RadioButton r3 = new RadioButton("3x3");
    Node n = setRadios(r2, r3);
    public static ArrayList<TextField> a = iView.createFields(9, 80);
    private static TextField t1 = a.get(0);
    private static TextField t2 = a.get(1);
    private static TextField t3 = a.get(2);
    private static TextField t4 = a.get(3);
    private static TextField t5 = a.get(4);
    private static TextField t6 = a.get(5);
    private static TextField t7 = a.get(6);
    private static TextField t8 = a.get(7);
    private static TextField t9 = a.get(8);
    static double x1;
    static double x2;
    static double x3;
    GridPane grid = new GridPane();

    static ArrayList<String> str = new ArrayList<>();
    static ArrayList<String> str2 = new ArrayList<>();
    static ArrayList<String> str3 = new ArrayList<>();

    public View2() {
        this.getChildren().add(setView("Eigen UI",this.setLeft()));
        Button1EventHandler3x3 rdobut3 = new Button1EventHandler3x3();
        Button1EventHandler2x2 rdobut2 = new Button1EventHandler2x2();
        r3.setOnAction(rdobut3);
        r2.setOnAction(rdobut2);
        iView.handleButton(2);

    }

    class Button1EventHandler3x3 implements EventHandler {

        @Override
        public void handle(Event event) {
            Node t11 = t1;
            if(grid.getRowIndex(t11) == null){

                grid.add(t1, 10, 5);
                grid.add(t2, 13, 5);
                grid.add(t3, 16, 5);
                grid.add(t4, 10, 8);
                grid.add(t5, 13, 8);
                grid.add(t6, 16, 8);
                grid.add(t7, 10, 11);
                grid.add(t8, 13, 11);
                grid.add(t9, 16, 11);

            }
            else{
                t3.setVisible(true);
                t6.setVisible(true);
                t7.setVisible(true);
                t8.setVisible(true);
                t9.setVisible(true);
            }



        }


    }


    class Button1EventHandler2x2 implements EventHandler {

        @Override
        public void handle(Event event) {
            Node t11 = t1;
            if (grid.getRowIndex(t11) == null) {

                grid.add(t1, 10, 5);
                grid.add(t2, 13, 5);
                grid.add(t4, 10, 8);
                grid.add(t5, 13, 8);
            }
            else{
                t3.setVisible(false);
                t6.setVisible(false);
                t7.setVisible(false);
                t8.setVisible(false);
                t9.setVisible(false);
            }
        }
    }


//CHECK TEXTFIELDS
    /*
    public boolean validateTextField(TextField rr){
        if(rr.getText().isEmpty() || rr.getText().isEmpty()){
            rr.setText("0.0");
        }
        try{
            Double.parseDouble(rr.getText());
        }catch (NumberFormatException e) {
            System.out.println("Letters or symbols detected please fix your input and press ok");
            return false;
        }
        return true;
    }
*/

    @Override
    public VBox setLeft() {
        VBox vbLeft = new VBox();
        vbLeft.getChildren().add(n);
        vbLeft.getChildren().add(grid);
        vbLeft.setSpacing(10);
        vbLeft.setLayoutX(10);
        vbLeft.setLayoutY(14);
        vbLeft.setPrefSize(500, 695);
        vbLeft.setStyle("-fx-background-color: #333335"); // Grey
        return vbLeft;
    }

    public static RadioButton getR2() {
        return r2;
    }

    public static void setR2(RadioButton r2) {
        View2.r2 = r2;
    }

    public static RadioButton getR3() {
        return r3;
    }

    public static void setR3(RadioButton r3) {
        View2.r3 = r3;
    }

    public Node getN() {
        return n;
    }

    public void setN(Node n) {
        this.n = n;
    }

    public static ArrayList<TextField> getA() {
        return a;
    }

    public static void setA(ArrayList<TextField> a) {
        View2.a = a;
    }

    public static TextField getT1() {
        return t1;
    }

    public static void setT1(TextField t1) {
        View2.t1 = t1;
    }

    public static TextField getT2() {
        return t2;
    }

    public static void setT2(TextField t2) {
        View2.t2 = t2;
    }

    public static TextField getT3() {
        return t3;
    }

    public static void setT3(TextField t3) {
        View2.t3 = t3;
    }

    public static TextField getT4() {
        return t4;
    }

    public static void setT4(TextField t4) {
        View2.t4 = t4;
    }

    public static TextField getT5() {
        return t5;
    }

    public static void setT5(TextField t5) {
        View2.t5 = t5;
    }

    public static TextField getT6() {
        return t6;
    }

    public static void setT6(TextField t6) {
        View2.t6 = t6;
    }

    public static TextField getT7() {
        return t7;
    }

    public static void setT7(TextField t7) {
        View2.t7 = t7;
    }

    public static TextField getT8() {
        return t8;
    }

    public static void setT8(TextField t8) {
        View2.t8 = t8;
    }

    public static TextField getT9() {
        return t9;
    }

    public static void setT9(TextField t9) {
        View2.t9 = t9;
    }

    public static double getX1() {
        return x1;
    }

    public static void setX1(double x1) {
        View2.x1 = x1;
    }

    public static double getX2() {
        return x2;
    }

    public static void setX2(double x2) {
        View2.x2 = x2;
    }

    public static double getX3() {
        return x3;
    }

    public static void setX3(double x3) {
        View2.x3 = x3;
    }

    public GridPane getGrid() {
        return grid;
    }

    public void setGrid(GridPane grid) {
        this.grid = grid;
    }

    public static ArrayList<String> getStr() {
        return str;
    }

    public void setStr(ArrayList<String> str) {
        View2.str = str;
    }

    public static ArrayList<String> getStr2() {
        return str2;
    }

    public void setStr2(ArrayList<String> str2) {
        View2.str2 = str2;
    }

    public static ArrayList<String> getStr3() {
        return str3;
    }

    public void setStr3(ArrayList<String> str3) {
        View2.str3 = str3;
    }
}