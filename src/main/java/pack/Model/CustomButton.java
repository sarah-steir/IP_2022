package pack.Model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import pack.Controller.V2Controller;
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
        switch (i) {
            case 1:

            case 2:

            case 3:
                iView.btnStart.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        System.out.println("View3 math is set on action babyyyy");
                        V2Controller.transform(View3.c);
                        V2Controller.crossProduct();
                        V2Controller.point();
                    }});

        } }

    public static void handleRButton(ArrayList<TextField> f){
        iView.btnReset.setOnAction(new EventHandler<ActionEvent>() {public void handle(ActionEvent e) {iView.clearing(f);}});}


}

