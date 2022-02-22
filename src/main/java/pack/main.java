package pack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pack.View.MainView;
import pack.View.View1;

public class main extends Application {

    @Override
    public void start(Stage stage) {

        Pane grid = new View1();
        stage.setResizable(false);
        stage.setTitle("Matrix");

        Scene scene = new Scene(grid, 1050, 750);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /***
     * The color palette that we will be using for most of the project :
     *
     * Grey-black : 333335
     * White : E7EBEE
     * Red : F15152
     * Yellow : FCBF49
     * Grey-blue : 6F6F77
     */

    /***
     * Size of the Stage :
     *
     * Height : 750 pixels
     * Width : 1050 pixels
     */

    /***
     * Information about the logo :
     *
     * Height : 100 pixels
     * Width : 225 pixels
     * LayoutX : 805 pixels
     * LayoutY : 610 pixels
     */

}