package pack.View.graphView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class Graph extends Group {

    /***
     * Elements to include in the graph :
     *
     * Big cube so the user can use it to move around ✅
     * Axis ✅
     * Line given 2 Point3D ✅
     *      A label for the line
     * Plane given a Point3D (a, b, c) and d (ax + by + cz = d)
     *      A label for the graph
     * Point given well Point3D dumbass
     *      A label for the point
     -     */

    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;

    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 1000.0;

    final Xform root = new Xform();
    final Xform scalable = new Xform();

    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();

    Box map = bigCube();

    Color white = Color.web("#E7EBEE");
    Color grey = Color.web("#333234");
    Color red = Color.web("#DF5C58");
    Color yellow = Color.web("#F2C15F");
    Color blue = Color.web("#1985A1");

    ObservableList<Node> axisList;
    ObservableList<Node> thingsToGraphList = FXCollections.observableArrayList();
    ObservableList<Node> labelsList = FXCollections.observableArrayList();

    public Graph() {
        axisList = getAxis();
        thingsToGraphList.add(map);

        this.addLine(new Point3D(12, 42, 65), new Point3D(9, 45, -15));
        scalable.getChildren().addAll(axisList);
        scalable.getChildren().addAll(thingsToGraphList);

        Scale mirror = new Scale(1, -1, -1);
        scalable.getTransforms().add(mirror);
        root.getChildren().add(scalable);
        buildCamera();

        SubScene scene = new SubScene(root, 500, 525);
        scene.setCamera(camera);
        this.getChildren().add(scene);

        scene.setOnScroll((e) -> {
            double zoomRatio = 1;
            if(e.getDeltaY() > 0 && scalable.getScale() < 5) {
                zoomRatio = 1.05;
            } else if (e.getDeltaY() < 0 && scalable.getScale() > 0.5) {
                zoomRatio = 0.95;
            }
            map.setScaleX(map.getScaleX() / zoomRatio);
            map.setScaleY(map.getScaleY() / zoomRatio);
            map.setScaleZ(map.getScaleZ() / zoomRatio);
            double scale = scalable.getScale() * zoomRatio;
            scalable.setScale(scale);
        });

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX);
                mouseDeltaY = (mousePosY - mouseOldY);

                if (me.isControlDown()) {}
                if (me.isShiftDown()) {}

                if (me.isPrimaryButtonDown() && me.isSecondaryButtonDown()) {
                    camera.setTranslateX(camera.getTranslateX() + mouseDeltaX);
                    camera.setTranslateY(camera.getTranslateY() + mouseDeltaY);
                }
                else if (me.isPrimaryButtonDown()) {
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() + mouseDeltaX);
                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() - mouseDeltaY);
                }
                else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaX;
                    camera.setTranslateZ(newZ);
                }
                else if (me.isMiddleButtonDown()) {
                    cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX);
                    cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY);
                }
            }
        });

    }

    public Box bigCube() {
        map = new Box(400, 400, 400);
        map.setMaterial(new PhongMaterial(Color.TRANSPARENT));
        return map;
    }

    private ObservableList<Node> getAxis() {

        ObservableList<Node> axisList = FXCollections.observableArrayList();

        Cylinder xAxis = new Cylinder(1, 1000);
        xAxis.setMaterial(new PhongMaterial(white));
        xAxis.getTransforms().add(new Rotate(90, 0, 0, 0, new Point3D(1, 0, 0)));
        Cylinder yAxis = new Cylinder(1, 1000);
        yAxis.setMaterial(new PhongMaterial(white));
        yAxis.getTransforms().add(new Rotate(90, 0, 0, 0, new Point3D(0, 1, 0)));
        Cylinder zAxis = new Cylinder(1, 1000);
        zAxis.setMaterial(new PhongMaterial(white));
        zAxis.getTransforms().add(new Rotate(90, 0, 0, 0, new Point3D(0, 0, 1)));

        Xform axes = new Xform();
        axes.getChildren().addAll(xAxis, yAxis, zAxis);
        axisList.add(axes);

        return axisList;
    }

    public void addPoint(Point3D point) {

    }

    public void addGraph(Point3D point1, Point3D point2, Point3D point3) {

    }

    public void addLine(Point3D point1, Point3D point2) {

        Line line = new Line();
        double dist = point1.distance(point2);

        if(dist > 0) {
            line = new Line(0, 0, dist, 0);
            line.setFill(red);
            line.setStroke(red);

            Point3D vector = point2.subtract(point1);

            double x = vector.getX() + 0.0;
            double y = vector.getY() + 0.0;
            double z = vector.getZ() + 0.0;

            double ry = Math.toDegrees(Math.atan2(-z, x));
            Rotate yRotate = new Rotate(ry, Rotate.Y_AXIS);

            double rx = Math.toDegrees(Math.asin(y / dist));
            Rotate xRotate = new Rotate(rx, Rotate.Z_AXIS);

            Translate tt = new Translate(point1.getX(),
                    point1.getY(), point1.getZ());

            line.getTransforms().addAll(tt,yRotate, xRotate);

        }
        Text label = new Text("l(t) = (" + point1.getX() + ", " + point1.getY() + ", " + point1.getZ() + ") + t <" + point2.getX() + ", " + point2.getY() + ", " + point2.getZ() + ">");
        System.out.println("l(t) = (" + point1.getX() + ", " + point1.getY() + ", " + point1.getZ() + ") + t <" + point2.getX() + ", " + point2.getY() + ", " + point2.getZ() + ">");
        labelsList.add(label);
        thingsToGraphList.add(line);
    }

    private void setCameraFromViewPoint(double x, double y, double z) {
        double zTranslate
                = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));

        double ryAngle, rxAngle;

        if(zTranslate > 0) {
            ryAngle = - Math.toDegrees(Math.atan2(x , z));
            rxAngle
                    = - Math.toDegrees(Math.asin(y / zTranslate));
        } else {
            ryAngle = 0;
            rxAngle = 0;
        }

        camera.setTranslateZ(-zTranslate);
        cameraXform.ry.setAngle(ryAngle);
        cameraXform.rx.setAngle(rxAngle);
    }

    private void buildCamera() {
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);

        camera.setFieldOfView(90);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);

        setCameraFromViewPoint(-0, -0, 300);
    }

}

//    Text text = new Text(0, 0,
//            String.format("(%1$d,%2$d,%3$d)", x, y,z));
//                    text.setScaleY(-1);