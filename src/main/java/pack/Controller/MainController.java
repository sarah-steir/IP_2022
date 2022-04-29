package pack.Controller;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pack.View.MainView;

import static pack.View.Customs.Custom.p;

public class MainController {

    MainView view;

    public MainController(MainView view) {
        this.view = view;
    }

    public void handleOurTeam() {
        ImageView iv = new ImageView(new Image(p + "OurTeam.png"));
        iv.setFitWidth(683);
        iv.setFitHeight(712);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(new Pane(iv)));
        stage.setTitle("Usssss");
        stage.show();
    }

    public void handleCredits() {
        ImageView iv = new ImageView(new Image(p + "Credits.png"));
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(new Pane(iv)));
        stage.setTitle("Creditsssss");
        stage.show();
    }

    public void handleLSEQuestions() {
        ImageView iv = new ImageView(new Image(p + "LSEQuestions.png"));
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(new Pane(iv)));
        stage.setTitle("Linear System of Equations 101");
        stage.show();
    }

    public void handleEigenQuestions() {
        ImageView iv = new ImageView(new Image(p + "EigenQuestions.png"));
        iv.setFitWidth(683);
        iv.setFitHeight(595);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(new Pane(iv)));
        stage.setTitle("EigenValues & EigenVectors 101");
        stage.show();
    }

    public void handleLinesQuestions() {
        ImageView iv = new ImageView(new Image(p + "LinesQuestions.png"));
        iv.setFitWidth(683);
        iv.setFitHeight(712);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(new Pane(iv)));
        stage.setTitle("Lines & Planes 101");
        stage.show();
    }
}
