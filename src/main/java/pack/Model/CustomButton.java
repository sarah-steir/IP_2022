package pack.Model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import pack.Controller.Controller1;
import pack.Controller.V2Controller;
import pack.View.View1;
import pack.View.View3;
import pack.View.iView;

import java.util.ArrayList;

import static pack.Model.mainModel.p;

public class CustomButton extends Button {

    public CustomButton(String message) {
        super(message);
        this.setStyle("-fx-background-color: #333335;" +
                "-fx-text-fill: E7EBEE;");
        this.setTextAlignment(TextAlignment.CENTER);
        this.setFont(mainModel.font);
    }

    public static void handleSButton(int i){

        iView.btnStart.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
        switch (i) {
            case 1:
                System.out.println("isgijfsejifejief");

                break;


            case 2:
                System.out.println("Case 2");
                break;


            case 3:
                if(View3.r1.isSelected() ) {
                    System.out.println("fjisdofjifsdofdsjidf");
                    break;
                }

                 if(View3.r2.isSelected()) {
                   V2Controller.transform(View3.c);
                   V2Controller.crossProduct();
                   V2Controller.point();
                 break;}

        } }}); }

    public static void handleRButton(ArrayList<TextField> f){
        iView.btnReset.setOnAction(new EventHandler<ActionEvent>() {public void handle(ActionEvent e) {iView.clearing(f);}});}


}

