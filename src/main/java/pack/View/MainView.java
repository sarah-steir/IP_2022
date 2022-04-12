package pack.View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import pack.Controller.MainController;

import static pack.View.Customs.Custom.p;

public class MainView extends Pane {

    Pane currentPane = new Pane();      // GridPane that keeps track of the view the user will be on
    MainController controller = new MainController(this);
    Pane gif = new Pane();
    public Pane view1 = new View1(), view2 = new View2(), view3 = new View3();

    public MainView() {
        this.setPrefSize(1050, 750);
        this.setCurrentPane(new WelcomeView(this));
        this.getChildren().addAll(currentPane, gif, setMenuBar());
        currentPane.setLayoutY(25);
        gif.setMouseTransparent(true);
    }

    /***
     * Function that switches the current Pane accordingly
     * It first clears it so there is no stacking and offers better efficiency (I think)
     */
    public void setCurrentPane(Pane pane) {
        this.currentPane.getChildren().clear();
        this.currentPane.getChildren().add(pane);
    }

    /***
     * Function that sets up the whole MenuBar
     * It uses an AnchorPane so the elements are stuck to the top
     * @return AnchorPane
     */
    public AnchorPane setMenuBar() {
        MenuBar menuBar = new MenuBar();
        AnchorPane menuPane = new AnchorPane();
        AnchorPane.setTopAnchor(menuBar, 0.0);

        Menu about = new Menu("About");
        MenuItem ourTeam = new MenuItem("Our team :)");
        MenuItem credits = new MenuItem("Credits");
        about.getItems().addAll(ourTeam, credits);
        ourTeam.setOnAction(e -> controller.handleOurTeam());
        credits.setOnAction(e -> controller.handleCredits());

        Menu help = new Menu("Help");
        MenuItem lse = new MenuItem("How to use the Systems of Linear Equations Calculator");
        MenuItem eigen = new MenuItem("How to use the EigenValues & EigenVectors Calculator");
        MenuItem lines = new MenuItem("How to use the Lines and Planes Calculator");
        help.getItems().addAll(lse, eigen, lines);
        lse.setOnAction(e -> controller.handleLSEQuestions());
        eigen.setOnAction(e -> controller.handleEigenQuestions());
        lines.setOnAction((e -> controller.handleLinesQuestions()));

        Menu views = new Menu("Go to");
        MenuItem view1 = new MenuItem("Systems of Linear Equations");
        MenuItem view2 = new MenuItem("EigenValues & EigenVectors");
        MenuItem view3 = new MenuItem("Lines and Planes");
        views.getItems().addAll(view1, view2, view3);
        view1.setOnAction(e -> this.playAnimation(this.view1));
        view2.setOnAction(e -> this.playAnimation(this.view2));
        view3.setOnAction(e -> this.playAnimation(this.view3));

        menuBar.getMenus().addAll(about, help, views);
        menuBar.setPrefSize(1050, 25);
        menuPane.getChildren().add(menuBar);

        return menuPane;
    }

    /***
     * Function that takes care of the whole animation that occurs when changing Views
     * It also checks if you try to go to the view you're already on
     * It will play the first half of the animation (timeline1),
     * then switch the view when timeline1 is setOnFinished,
     * and finally play the second part of the animation (timeline2).
     */
    public void playAnimation(Pane pane) {
        if (!(currentPane.getChildren().get(0).getClass() == (pane.getClass()))) {
            ImageView t1 = new ImageView(new Image(p + "Trans1.gif"));
            Timeline timeline1 = new Timeline(
                    new KeyFrame(Duration.ZERO, e -> gif.getChildren().add(t1)),
                    new KeyFrame(Duration.seconds(1.35), e -> gif.getChildren().remove(t1)));
            timeline1.play();
            timeline1.setOnFinished(event -> {
                setCurrentPane(pane);
                ImageView t2 = new ImageView(new Image(p + "Trans2.gif"));
                Timeline timeline2 = new Timeline(
                        new KeyFrame(Duration.ZERO, e -> gif.getChildren().add(t2)),
                        new KeyFrame(Duration.seconds(1.25), e -> gif.getChildren().remove(t2)));
                timeline2.play();
            });
        }
    }
}