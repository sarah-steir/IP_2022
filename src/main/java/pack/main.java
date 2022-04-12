package pack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pack.View.MainView;

public class main extends Application {

    @Override
    public void start(Stage stage) {

        Pane grid = new MainView();
        stage.setResizable(false);
        stage.setTitle("Matrix");

        Scene scene = new Scene(grid, 1050, 750);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}