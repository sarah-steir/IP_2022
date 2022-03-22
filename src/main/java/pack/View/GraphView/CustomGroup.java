package pack.View.GraphView;

import javafx.animation.PathTransition;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class CustomGroup extends Group {

    Color red = Color.web("#DF5C58");

    Rectangle plane;
    Sphere center;

    public CustomGroup() {
        super();

        this.plane = new Rectangle(1000, 1000);
        this.plane.setOpacity(0.5);
        this.plane.setFill(red);

        this.center = new Sphere(2);
        this.center.setTranslateX(500);
        this.center.setTranslateY(500);
        this.center.setTranslateZ(0);
        //this.center.setMaterial(new PhongMaterial(Color.TRANSPARENT));

        this.getChildren().addAll(plane, center);
    }

    public void rotate(Rotate x, Rotate y, Rotate z) {
        this.plane.getTransforms().addAll(x, y, z);
        this.center.getTransforms().addAll(x, y, z);
    }

    public void translate(Point3D point) {
    }
}
