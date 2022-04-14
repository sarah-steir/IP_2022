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
import pack.View.Customs.CustomText;

import static pack.View.Customs.Custom.*;

public class Graph extends Group {

    double mousePosX, mousePosY;
    double mouseOldX, mouseOldY;
    double mouseDeltaX, mouseDeltaY;

    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 1000.0;

    final Xform root = new Xform();
    final Xform scalable = new Xform();

    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();

    Box map = bigCube();

    private int a = 0; // Serves as a counter to know which color should the added element be

    ObservableList<Node> axisList;
    ObservableList<Node> thingsToGraphList = FXCollections.observableArrayList();
    ObservableList<Node> labelsList = FXCollections.observableArrayList();

    public Graph() {
        axisList = getAxis();
        this.update();

      //  this.addPoint(new Point3D(-15.82, -19.82, 59.55));
     //   this.addPoint(new Point3D(-95.82, -99.82, 299.55));

        Scale mirror = new Scale(1, -1, -1);
        scalable.getTransforms().add(mirror);
        root.getChildren().add(scalable);
        buildCamera();

        SubScene scene = new SubScene(root, 500, 525);
        scene.setCamera(camera);
        this.getChildren().add(scene);

        scene.setOnScroll((e) -> {
            double zoomRatio = 1;
            if (e.getDeltaY() > 0 && scalable.getScale() < 5) {
                zoomRatio = 1.05;
            } else if (e.getDeltaY() < 0 && scalable.getScale() > 0.3) {
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

    /**
     * This function is used so that the user doesn't have to click on a specific element to rotate/move the graph
     * @return a big, transparent box that will serve as a "grip" so the user can click anywhere on the graph and rotate it
     */
    public Box bigCube() {
        map = new Box(400, 400, 400);
        map.setMaterial(new PhongMaterial(Color.TRANSPARENT));
        return map;
    }

    /**
     * Well it's a function to create the axis duh
     * @return an ArrayList of the three axis, to be added in the scene
     */
    private ObservableList<Node> getAxis() {
        ObservableList<Node> axisList = FXCollections.observableArrayList();

        Cylinder xAxis = new Cylinder(2, 3000);
        xAxis.setMaterial(new PhongMaterial(Color.RED));
        xAxis.getTransforms().add(new Rotate(90, 0, 0, 0, new Point3D(0, 0, 1)));
        Cylinder yAxis = new Cylinder(2, 3000);
        yAxis.setMaterial(new PhongMaterial(Color.GREEN));
        yAxis.getTransforms().add(new Rotate(90, 0, 0, 0, new Point3D(0, 1, 0)));
        Cylinder zAxis = new Cylinder(2, 3000);
        zAxis.setMaterial(new PhongMaterial(Color.BLUE));
        zAxis.getTransforms().add(new Rotate(90, 0, 0, 0, new Point3D(1, 0, 0)));

        Xform axes = new Xform();
        axes.getChildren().addAll(xAxis, yAxis, zAxis);
        axisList.add(axes);

        return axisList;
    }

    /**
     * This function takes three coordinates (a Point3D), and then translates a sphere to these coordinates in order to "create a point"
     * @param point with the three coordinates to be added
     */
    public void addPoint(Point3D point) {
        Sphere sphere = new Sphere(3);
        sphere.setTranslateX(point.getX());
        sphere.setTranslateY(point.getY());
        sphere.setTranslateZ(point.getZ());
//        createPointLabel(point);
        addPointToList(sphere);
    }

    /**
     * This function finds two Lines that will form one Line.
     * The first one, Line line1, goes from point1, passes through point2 and extends to 1000 pixels
     * The second one, Line line2, goes from point2, passes through point1 and extends to 1000 pixels
     * line1 is like line2 but in the opposite direction, so that it goes beyond each point
     * @param point1 the first point the line passes through
     * @param point2 the second point the line passes through
     */
    public void addLine(Point3D point1, Point3D point2, double[]direction) {

        this.addPoint(point1);
        this.addPoint(point2);

        Line line1 = this.FindOneLine(point1, point2);
        Line line2 = this.FindOneLine(point2, point1);

        createLineLabel(point1, direction);
        addLineToList(line1, line2);
    }

    /**
     * This function finds the first "half" of the line (so that it is veryyyyy long instead of being smol)
     * @param point1
     * @param point2
     * @return the "first half" of the line
     */
    private Line FindOneLine(Point3D point1, Point3D point2) {
        double dist = point1.distance(point2);

        Line line = new Line(0, 0, 1000, 0);

        Point3D vector = point2.subtract(point1);

        double ry = Math.toDegrees(Math.atan2(-vector.getZ(), vector.getX()));
        Rotate yRotate = new Rotate(ry, Rotate.Y_AXIS);

        double rx = Math.toDegrees(Math.asin(vector.getY() / dist));
        Rotate xRotate = new Rotate(rx, Rotate.Z_AXIS);

        Translate tt = new Translate(point1.getX(), point1.getY(), point1.getZ());
        line.getTransforms().addAll(tt, yRotate, xRotate);

        return line;
    }

    /**
     *
     * Boy oh boy was this function a pain in the ass to program... Anyways here we go
     * It creates the plane (a circle because it is too hard to take a rectangle and transform it since the pivot point is in the corner, not in the center)
     * When the circle is added, it goes automatically through x and y, so we just to rotate along this vector (x -> y) to make it pass through z
     * It finds the midpoint m between the x and y
     * It finds the angle it needs to rotate the circle in order to pass through z
     * It rotates the circle
     *
     * @param x the x-intercept
     * @param y the y-intercept
     * @param z the z-intercept
     * @param equation
     */
    public void addPlane(double x, double y, double z, String equation) {

        System.out.println("x = " + x + ", y = " + y + ", z = " + z);
        Rectangle plane = new Rectangle(-500, -500, 1000, 1000);

        Point3D m = new Point3D(x/2, y/2, 0); // Midpoint between x and y
        double d = Math.sqrt(Math.pow(m.getX(), 2) + Math.pow(m.getY(), 2) + Math.pow(z, 2)); // Distance between m and z
        double angle = Math.toDegrees(Math.asin(z / d)); // Angle it needs to rotate
        Point3D vector = new Point3D(-x, y, 0); // "Axis" of rotation, aka vector from x to y

        if (x * y < 0) { // If the coordinates are a bit weird we need to do this
            angle = 360 - angle;
        }

        Rotate rotate = new Rotate(angle, m.getX(), m.getY(), 0, vector);
        plane.getTransforms().addAll(rotate);

        createPlaneLabel(equation);
        addPlaneToList(plane);
    }

    /**
     * Chooses the right color depending on how many elements are already in the graph
     * and adds the Point/Sphere
     * @param sphere
     */
    private void addPointToList(Sphere sphere) {
        a++;
        thingsToGraphList.add(sphere);
        switch (a) {
            case 1:
                sphere.setMaterial(new PhongMaterial(red));
                break;
            case 2:
                sphere.setMaterial(new PhongMaterial(yellow));
                break;
            case 3:
                sphere.setMaterial(new PhongMaterial(blue));
                break;
            default:
                sphere.setMaterial(new PhongMaterial(yellow));
                break;
        }
        this.update();
    }

    /**
     * Chooses the right color depending on how many elements are already in the graph
     * and adds the Line
     *
     * @param line1
     * @param line2
     */
    private void addLineToList(Line line1, Line line2) {
        a++;
        thingsToGraphList.add(line1);
        thingsToGraphList.add(line2);
        switch (a) {
            case 1:
                line1.setStroke(red);
                line2.setStroke(red);
                break;
            case 2:
                line1.setStroke(yellow);
                line2.setStroke(yellow);
                break;
            case 3:
                line1.setStroke(blue);
                line2.setStroke(blue);
                break;
            default:
                line1.setStroke(white);
                line2.setStroke(white);
                break;
        }
        this.update();
    }

    /**
     * Chooses the right color depending on how many elements are already in the graph
     * and adds the Rectangle/Plane
     * @param rectangle
     */
    public void addPlaneToList(Rectangle rectangle) {
        a++;
        rectangle.setOpacity(0.5);
        thingsToGraphList.add(rectangle);
        switch (a) {
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
        this.update();
    }

    /**
     * Creates the label and places it next to the Point/Sphere
     * @param point
     */
    private void createPointLabel(Point3D point) {
        Text label = new CustomText("(" + point.getX() + ", " + point.getY() + ", " + point.getZ() + ")");
        label.setScaleY(-1);
        label.setTranslateX(point.getX());
        label.setTranslateY(point.getY());
        label.setTranslateZ(point.getZ());
        labelsList.add(label);
    }

    /**
     * Creates the label and places it next to the Line
     * @param point1
     * @param direction
     */
    private void createLineLabel(Point3D point1,  double[]direction) {
        Text label = new CustomText("l(t) = (" + point1.getX() + ", " + point1.getY() + ", " + point1.getZ() + ") +\nt <" + direction[0] + ", " +direction[1] + ", " +direction[2] + ">");
        label.setScaleY(-1);
        label.setTranslateX(point1.getX());
        label.setTranslateY(point1.getY());
        label.setTranslateZ(point1.getZ());
        labelsList.add(label);
    }

    /**
     * Creates the label and places it at the origin cause I don't know where else
     * @param equation
     */
    private void createPlaneLabel(String equation) {
        Text label = new Text(equation);
        label.setScaleY(-1);
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
        setCameraFromViewPoint(100, 100, 300);
    }

    /**
     * Needed to update everything when we add an element
     */
    public void update() {
        scalable.getChildren().clear();
        scalable.getChildren().add(new AmbientLight());
        scalable.getChildren().addAll(axisList);
        scalable.getChildren().add(map);
        scalable.getChildren().addAll(thingsToGraphList);
        scalable.getChildren().addAll(labelsList);
    }

    public void reset() {
        thingsToGraphList.clear();
        labelsList.clear();
        this.update();
        a = 0;
    }
}