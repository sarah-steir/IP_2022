package pack.View.GraphView;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

public class CustomGroup extends Group {

    Color red = Color.web("#DF5C58");

    Rectangle plane = new Rectangle(1000, 1000);
    Sphere center = new Sphere(2);
    Point3D centerPoint;

    public CustomGroup() {
        super();

        this.plane.setOpacity(0.5);
        this.plane.setFill(red);

        this.centerPoint = new Point3D(500, 500, 0);

        this.center.setTranslateX(500);
        this.center.setTranslateY(500);
        this.center.setTranslateZ(0);
        //this.center.setMaterial(new PhongMaterial(Color.TRANSPARENT));

        this.getChildren().addAll(plane, center);
    }

    public void addTransforms(Rotate x, Rotate y, Rotate z) {
        this.getTransforms().addAll(x, y, z);
    }
}
