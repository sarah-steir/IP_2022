package pack.View.GraphView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class Graph extends Group {

    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;

    private static final double CAMERA_INITIAL_DISTANCE = -300;
    private static final double CAMERA_INITIAL_X_ANGLE = 0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 1000.0;

    final Xform root = new Xform();
    final Xform scalable = new Xform();

    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();

    Color white = Color.web("#E7EBEE");
    Color grey = Color.web("#333234");
    Color red = Color.web("#DF5C58");
    Color yellow = Color.web("#F2C15F");
    Color blue = Color.web("#1985A1");

    public Graph() {

        ObservableList<Node> targetList = getTargetList();
        scalable.getChildren().addAll(targetList);

        scalable.getChildren().addAll(addMyStuff());

//		Affine mirror = new Affine(1,0,0,0,-1,0);
        Scale mirror = new Scale(1, -1, -1);
        scalable.getTransforms().add(mirror);

        root.getChildren().add(scalable);

        buildCamera();

//		setCameraFromViewPoint(600,600,600);
//
//		SubScene subScene = new SubScene(root, 800, 600, true, null);
//		subScene.setCamera(camera);
//		Group subWrap = new Group();
//		subWrap.getChildren().add(subScene);
//		Scene scene = new Scene(subWrap);

        SubScene scene = new SubScene(root, 800, 600);
        scene.setCamera(camera);
        this.getChildren().add(scene);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case Z:
                        scalable.setScale(1);
                        camera.setTranslateX(0);
                        camera.setTranslateY(0);
                        cameraXform2.t.setX(0.0);
                        cameraXform2.t.setY(0.0);
                        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
                        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
                        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
                        break;
                    case V:
                        scalable.setVisible(!scalable.isVisible());
                        break;
                    case UP:
                        System.out.println("Up pressed");
                        System.out.println("Old rxAngle Angle: " + cameraXform.rx.getAngle());
                        cameraXform.rx.setAngle(cameraXform.rx.getAngle() - 1);
                        System.out.println("New rxAngle Angle: " + cameraXform.rx.getAngle());
                        break;
                    case DOWN:
                        System.out.println("Down pressed");
                        System.out.println("Old rxAngle Angle: " + cameraXform.rx.getAngle());
                        cameraXform.rx.setAngle(cameraXform.rx.getAngle() + 1);
                        System.out.println("New rxAngle Angle: " + cameraXform.rx.getAngle());
                        break;
                    case LEFT:
                        System.out.println("Left pressed");
                        System.out.println("Old ryAngle Angle: " + cameraXform.ry.getAngle());
                        cameraXform.ry.setAngle(cameraXform.ry.getAngle() + 1);
                        System.out.println("New ryAngle Angle: " + cameraXform.ry.getAngle());
                        break;
                    case RIGHT:
                        System.out.println("Right pressed");
                        System.out.println("Old ryAngle Angle: " + cameraXform.ry.getAngle());
                        cameraXform.ry.setAngle(cameraXform.ry.getAngle() - 1);
                        System.out.println("New ryAngle Angle: " + cameraXform.ry.getAngle());
                        break;
                    default:
                        break;
                }
            }
        });

        scene.setOnScroll((e) -> {

            double zoomRatio = 1;
            if(e.getDeltaY() > 0) {
                zoomRatio = 1.05;
            } else {
                zoomRatio = 0.95;
            }

            System.out.println("Old Scale: " + scalable.getScale());
            double scale = scalable.getScale() * zoomRatio;
            scalable.setScale(scale);
            System.out.println("New Scale: " + scalable.getScale());

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

                    System.out.println("Old rxAngle Angle: " + cameraXform.rx.getAngle());
                    System.out.println("Old ryAngle Angle: " + cameraXform.ry.getAngle());

                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() + mouseDeltaX);
                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() - mouseDeltaY);

                    System.out.println("New rxAngle Angle: " + cameraXform.rx.getAngle());
                    System.out.println("New ryAngle Angle: " + cameraXform.ry.getAngle());
                }
                else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    System.out.println("Old z: " + z);
                    double newZ = z + mouseDeltaX;
                    camera.setTranslateZ(newZ);
                    System.out.println("New z: " + newZ);

                }
                else if (me.isMiddleButtonDown()) {
                    cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX);
                    cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY);
                }
            }
        });

    }

    public ObservableList<Node> addMyStuff() {
        ObservableList<Node> myStuff = FXCollections.observableArrayList();

        Line line = new Line(-150, -150, 150, 150);
        line.setFill(Color.CORNFLOWERBLUE);
        line.setStroke(Color.CORNFLOWERBLUE);
        myStuff.add(line);

        Sphere boxxy = new Sphere(2);
        boxxy.setMaterial(new PhongMaterial(Color.PALEGREEN));
        boxxy.setTranslateX(30);
        boxxy.setTranslateY(-90);
        boxxy.setTranslateZ(150);
        myStuff.add(boxxy);

        Rectangle rec = new Rectangle(400, 400);
        rec.setFill(Color.YELLOW);
        rec.setOpacity(0.25);
        rec.setTranslateY(-50);
        rec.setTranslateZ(100);
        rec.getTransforms().add(new Rotate(40, Rotate.Y_AXIS));
        myStuff.add(rec);

        return myStuff;
    }

    public Line getLine(Point3D start, Point3D end) {

        Line line = new Line();

        double dist = start.distance(end);

        if(dist > 0) {
            line = new Line(0, 0, dist, 0);

            Point3D vector = end.subtract(start);

            double x = vector.getX() + 0.0;
            double y = vector.getY() + 0.0;
            double z = vector.getZ() + 0.0;

            double ry = Math.toDegrees(Math.atan2(-z, x));
            Rotate yRotate = new Rotate(ry, Rotate.Y_AXIS);

            double rx = Math.toDegrees(Math.asin(y / dist));
            Rotate xRotate = new Rotate(rx, Rotate.Z_AXIS);

            Translate tt = new Translate(start.getX(),
                    start.getY(), start.getZ());

            line.getTransforms().addAll(tt,yRotate, xRotate);

        }

        return line;

    }

    private ObservableList<Node> getTargetList() {

        ObservableList<Node> targetList = FXCollections.observableArrayList();

        Line xAxis = getLine(new Point3D(0, 0, 0), new Point3D(200, 0, 0));
        xAxis.setStroke(white);
        xAxis.setStrokeWidth(2);
        Line yAxis = getLine(new Point3D(0, 0, 0), new Point3D(0, 200, 0));
        yAxis.setStroke(white);
        yAxis.setStrokeWidth(2);
        Line zAxis = getLine(new Point3D(0, 0, 0), new Point3D(0, 0, 200));
        zAxis.setStroke(white);
        zAxis.setStrokeWidth(2);

        Xform axes = new Xform();
        axes.getChildren().addAll(xAxis, yAxis, zAxis);
        targetList.add(axes);

        for(int x = -200; x <=200; x += 200) {
            for(int y = -200; y <=200; y += 200) {
                for(int z = -200; z <=200; z += 200) {

//					Sphere target = new Sphere(10);
                    Box target = new Box(20, 20, 20);
                    target.setTranslateX(x+20);
                    target.setTranslateY(y+20);
                    target.setTranslateZ(z+20);

                    if(y > 0) {
                        target.setMaterial(new PhongMaterial(red));
                    } else if (y < 0) {
                        target.setMaterial(new PhongMaterial(yellow));
                    } else {
                        target.setMaterial(new PhongMaterial(blue));
                    }

                    targetList.add(target);

                    Text text = new Text(0, 0,
                            String.format("(%1$d,%2$d,%3$d)", x, y,z));
                    text.setScaleY(-1);

                    text.setTranslateX(x);
                    text.setTranslateY(y);
                    text.setTranslateZ(z);
                    targetList.add(text);


                }
            }
        }

        return targetList;
    }

    private void getViewPointFromCamera() {

        double zTranslate = Math.abs(camera.getTranslateZ());
        double rxAngle = cameraXform.rx.getAngle();
        double ryAngle = cameraXform.ry.getAngle();

        double x = - zTranslate * Math.cos(Math.toRadians(rxAngle)) * Math.sin(Math.toRadians(ryAngle));
        double y = - zTranslate * Math.sin(Math.toRadians(rxAngle));
        double z = zTranslate * Math.cos(Math.toRadians(rxAngle)) * Math.cos(Math.toRadians(ryAngle));

        System.out.println("zTranslate: " + zTranslate);
        System.out.println("rxAngle: " + rxAngle);
        System.out.println("ryAngle: " + ryAngle);
        System.out.println("Viewpoint: (" + x + ", " + y + ", " + z + ")");
        System.out.println("");
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

        System.out.println("Viewpoint: (" + x + ", " + y + ", " + z + ")");
        System.out.println("zTranslate: " + zTranslate);
        System.out.println("ryAngle: " + ryAngle);
        System.out.println("rxAngle: " + rxAngle);

        camera.setTranslateZ(-zTranslate);
        cameraXform.ry.setAngle(ryAngle);
        cameraXform.rx.setAngle(rxAngle);

    }

    private void buildCamera() {
        System.out.println("buildCamera()");
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);

        camera.setFieldOfView(90);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);

        setCameraFromViewPoint(-0, -0, 300);

//		getViewPointFromCamera();

//		camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
//		cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
//		cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
    }

}

