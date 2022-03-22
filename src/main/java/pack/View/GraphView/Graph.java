package pack.View.GraphView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import pack.Model.CustomText;

public class Graph extends Group {

    //TODO Make the line longer
    //TODO Make plane more rectanglish

    /***
     * Elements to include in the graph :
     *
     * Big cube so the user can use it to move around ✅
     * Axis ✅
     * Line given 2 Point3D ✅
     *      A label for the line ✅
     * Plane given 3 Point3D (a, b, c) ✅
     *      A label for the graph
     * Point given well Point3D dumbass ✅
     *      A label for the point ✅
     *
     *      Fix the line
     *      Fix the labels
     *      Fix the plane ?
     *          Make it make sense (rectangle)
     *          Render ?
     */

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

        Point3D point1 = new Point3D(120, -24, -136);
        Point3D point2 = new Point3D(150, -45, 190);
        Point3D point3 = new Point3D(-65, 38, -50);

        this.addPlane(point1, point2, point3, "zefghjkl");

        this.addPoint(point1);
        this.addPoint(point2);
        this.addPoint(point3);

        this.addLine(point1, point2);

//        this.addLine(new Point3D(21, -64, 4), new Point3D(9, 45, -15));
//        this.addLine(new Point3D(65, -97, 3), new Point3D(67, 2, 9));
        scalable.getChildren().addAll(axisList);
        scalable.getChildren().addAll(thingsToGraphList);
        scalable.getChildren().addAll(labelsList);
        scalable.getChildren().add(map);

        Scale mirror = new Scale(1, -1, -1);
        scalable.getTransforms().add(mirror);
        root.getChildren().add(scalable);
        buildCamera();

        SubScene scene = new SubScene(root, 500, 525);
        scene.setCamera(camera);
        this.getChildren().add(scene);

        scene.setOnScroll((e) -> {

//            double delta = 1.05;
//
//            double scale = scalable.getScale(); // currently we only use Y, same value is used for X
//            double oldScale = scale;
//
//            if (e.getDeltaY() < 0) {
//                scale /= delta;
//                map.setScaleX(map.getScaleX() * delta);
//                map.setScaleY(map.getScaleY() * delta);
//                map.setScaleZ(map.getScaleZ() * delta);
//            } else {
//                scale *= delta;
//                map.setScaleX(map.getScaleX() / delta);
//                map.setScaleY(map.getScaleY() / delta);
//                map.setScaleZ(map.getScaleZ() / delta);
//            }
//
//            scale = clamp(scale, 0.3, 3);
//
//            double f = (scale / oldScale)-1;
//
//            double dx = (e.getSceneX() - (scalable.getBoundsInParent().getWidth()/2 + scalable.getBoundsInParent().getMinX()));
//            double dy = (e.getSceneY() - (scalable.getBoundsInParent().getHeight()/2 + scalable.getBoundsInParent().getMinY()));
//
//
//            // note: pivot value must be untransformed, i. e. without scaling
//            scalable.setPivot(f*dx, f*dy);
//
//            e.consume();

            double zoomRatio = 1;
            if (e.getDeltaY() > 0 && scalable.getScale() < 5) {
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
            @Override
            public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX);
                mouseDeltaY = (mousePosY - mouseOldY);

                if (me.isControlDown()) {
                }
                if (me.isShiftDown()) {
                }

                if (me.isPrimaryButtonDown() && me.isSecondaryButtonDown()) {
                    camera.setTranslateX(camera.getTranslateX() + mouseDeltaX);
                    camera.setTranslateY(camera.getTranslateY() + mouseDeltaY);
                } else if (me.isPrimaryButtonDown()) {
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() + mouseDeltaX);
                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() - mouseDeltaY);
                } else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaX;
                    camera.setTranslateZ(newZ);
                } else if (me.isMiddleButtonDown()) {
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

        Cylinder xAxis = new Cylinder(1.5, 1000);
        xAxis.setMaterial(new PhongMaterial(white));
        xAxis.getTransforms().add(new Rotate(90, 0, 0, 0, new Point3D(1, 0, 0)));
        Cylinder yAxis = new Cylinder(1.5, 1000);
        yAxis.setMaterial(new PhongMaterial(white));
        yAxis.getTransforms().add(new Rotate(90, 0, 0, 0, new Point3D(0, 1, 0)));
        Cylinder zAxis = new Cylinder(1.5, 1000);
        zAxis.setMaterial(new PhongMaterial(white));
        zAxis.getTransforms().add(new Rotate(90, 0, 0, 0, new Point3D(0, 0, 1)));

        Xform axes = new Xform();
        axes.getChildren().addAll(xAxis, yAxis, zAxis);
        axisList.add(axes);

        return axisList;
    }

    public void addPoint(Point3D point) {
        Sphere sphere = new Sphere(2);
        sphere.setTranslateX(point.getX());
        sphere.setTranslateY(point.getY());
        sphere.setTranslateZ(point.getZ());
        createPointLabel(point);
        addPointToList(sphere);
    }

    public void addLine(Point3D point1, Point3D point2) {

        Line line = new Line();
        double dist = point1.distance(point2);

        line = new Line(0, 0, dist, 0);

        Point3D vector = point2.subtract(point1);

        double x = vector.getX() + 0.0;
        double y = vector.getY() + 0.0;
        double z = vector.getZ() + 0.0;

        double ry = Math.toDegrees(Math.atan2(-z, x));
        Rotate yRotate = new Rotate(ry, Rotate.Y_AXIS);

        double rx = Math.toDegrees(Math.asin(y / dist));
        Rotate xRotate = new Rotate(rx, Rotate.Z_AXIS);

        Translate tt = new Translate(point1.getX(), point1.getY(), point1.getZ());
        line.getTransforms().addAll(tt, yRotate, xRotate);

        //        System.out.println("l(t) = (" + (int) point1.getX() + ", " + (int) point1.getY() + ", " + (int) point1.getZ() + ") + t <" + (int) point2.getX() + ", " + (int) point2.getY() + ", " + (int) point2.getZ() + ">");
        createLineLabel(point1, point2);
        addLineToList(line);
    }

    public void addPlane(Point3D point1, Point3D point2, Point3D point3, String equation) {

        Circle rectangle = new Circle(500);

        Point3D triangleCenter = this.getCenter(point1, point2, point3);
        double newX = triangleCenter.getX();
        double newY = triangleCenter.getY();
        double newZ = triangleCenter.getZ();

        double angleX = Math.toDegrees(Math.atan(point2.getZ() / point1.getY()));
        Rotate rotateX = new Rotate(angleX, Rotate.X_AXIS);

        double angleY = Math.toDegrees(Math.atan(point3.getX() / point1.getZ()));
        Rotate rotateY = new Rotate(angleY, Rotate.Y_AXIS);

        double angleZ = Math.toDegrees(Math.atan(point1.getY() / point1.getX()));
        Rotate rotateZ = new Rotate(angleZ, Rotate.Z_AXIS);

        rectangle.getTransforms().add(new Translate(newX, newY, newZ));
        rectangle.getTransforms().addAll(rotateX, rotateY, rotateZ);
//        rectangle.setTranslateX(500);
//        rectangle.setTranslateY(500);

        this.addPlaneToList(rectangle);
        this.createPlaneLabel(triangleCenter, equation);
    }

    public Point3D getCenter(Point3D point1, Point3D point2, Point3D point3) {
        double x = (point1.getX() + point2.getX() + point3.getX()) / 3;
        double y = (point1.getY() + point2.getY() + point3.getY()) / 3;
        double z = (point1.getZ() + point2.getZ() + point3.getZ()) / 3;
        Sphere sphere = new Sphere(5);
        sphere.setMaterial(new PhongMaterial(yellow));
        sphere.setTranslateX(x);
        sphere.setTranslateY(y);
        sphere.setTranslateZ(z);
        scalable.getChildren().add(sphere);
        return new Point3D(x, y, z);
    }

    public void addPointToList(Sphere sphere) {
        thingsToGraphList.add(sphere);
        switch (thingsToGraphList.size()) {
            case 1:
                System.out.println("hehe the liane should be  red");
                sphere.setMaterial(new PhongMaterial(red));
                break;
            case 2:
                sphere.setMaterial(new PhongMaterial(yellow));
                break;
            case 3:
                sphere.setMaterial(new PhongMaterial(blue));
                break;
            default:
                sphere.setMaterial(new PhongMaterial(white));
                break;
        }
    }

    public void addLineToList(Line line) {
        thingsToGraphList.add(line);
        switch (thingsToGraphList.size()) {
            case 1:
                line.setStroke(red);
                break;
            case 2:
                line.setStroke(yellow);
                break;
            case 3:
                line.setStroke(blue);
                break;
            default:
                line.setStroke(white);
                break;
        }
    }

    public void addPlaneToList(Circle rectangle) {
        rectangle.setOpacity(0.5);
        thingsToGraphList.add(rectangle);
        switch (thingsToGraphList.size()) {
            case 1:
                rectangle.setFill(red);
                break;
            case 2:
                rectangle.setFill(yellow);
                break;
            case 3:
                rectangle.setFill(blue);
                break;
            default:
                rectangle.setFill(white);
                break;
        }
    }

    private void createPointLabel(Point3D point) {
        Text label = new CustomText("(" + point.getX() + ", " + point.getY() + ", " + point.getZ() + ")");
        label.setScaleY(-1);
        label.setTranslateX(point.getX());
        label.setTranslateY(point.getY());
        label.setTranslateZ(point.getZ());
        labelsList.add(label);
    }

    private void createLineLabel(Point3D point1, Point3D point2) {
        Text label = new CustomText("l(t) = (" + (int) point1.getX() + ", " + (int) point1.getY() + ", " + (int) point1.getZ() + ") +\nt <" + (int) point2.getX() + ", " + (int) point2.getY() + ", " + (int) point2.getZ() + ">");
        label.setScaleY(-1);
        label.setTranslateX(point1.getX());
        label.setTranslateY(point1.getY());
        label.setTranslateZ(point1.getZ());
        labelsList.add(label);
    }

    private void createPlaneLabel(Point3D point, String equation) {
        Text label = new CustomText(equation);
        label.setScaleY(-1);
        label.setTranslateX(point.getX());
        label.setTranslateY(point.getY());
        label.setTranslateZ(point.getZ());
        labelsList.add(label);
    }

    private void setCameraFromViewPoint(double x, double y, double z) {
        double zTranslate
                = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));

        double ryAngle, rxAngle;

        if (zTranslate > 0) {
            ryAngle = -Math.toDegrees(Math.atan2(x, z));
            rxAngle
                    = -Math.toDegrees(Math.asin(y / zTranslate));
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

    public static double clamp( double value, double min, double max) {
        if( Double.compare(value, min) < 0)
            return min;
        if( Double.compare(value, max) > 0)
            return max;
        return value;
    }
}

//    Text text = new Text(0, 0,
//            String.format("(%1$d,%2$d,%3$d)", x, y,z));
//                    text.setScaleY(-1);