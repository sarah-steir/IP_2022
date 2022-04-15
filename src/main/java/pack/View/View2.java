package pack.View;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pack.View.iView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import pack.Controller.Controller2;
import pack.Model.Model2for2x2;
import pack.Model.Model2for3x3;
import pack.Model.ModelForJSON;
import pack.View.Customs.Custom;
import pack.View.Customs.CustomButton;
import pack.View.Customs.CustomRadioButton;
import pack.View.Customs.CustomTextField;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class View2 extends Pane implements iView {

    private CustomTextField[][] fieldListRb1, fieldListRb2;
    private GridPane fieldsPane;
    private CustomRadioButton rb1;
    private CustomRadioButton rb2;
    private CustomButton btnStart, btnReset, btnSave;
    private ToggleGroup group = new ToggleGroup();
    private ComboBox cb;
    private CustomButton butt;
    //public static Font font = Font.loadFont(p + "Font.otf", 15);
    private  Label ll;
    private JSONObject jsonObject ;
private BufferedWriter bufferedWriter;
    private VBox vbUi;
    private VBox vbPo;
    private ModelForJSON JASONDERULO;
    private VBox vbLeft;
    private VBox vbRight;
    private CustomTextField t1, t2, t3, t4, t5, t6, t7, t8, t9;
    private double a1,a2,a3,b1,b2,b3,c1,c2,c3;
    static FileWriter file;
    private ArrayList<Integer> other = new ArrayList<>();

    public View2() {
        jsonObject = ModelForJSON.getMatrix();
        this.rb1 = new CustomRadioButton("2 x 2");
        this.rb2 = new CustomRadioButton("3 x 3");
        this.rb1.setToggleGroup(group);
        this.rb2.setToggleGroup(group);
        this.btnStart = new CustomButton("START\nTHE\nMAGIK");
        this.btnStart.setDisable(true);
        this.btnReset = new CustomButton("RESET\nTHE\nMAGIK");
        this.cb = new ComboBox();
        this.btnSave = new CustomButton("Save Matrix");
        this.btnSave.setPrefSize(200, 20);
       JASONDERULO = new ModelForJSON();
       cb.getItems().addAll(jsonObject);
       cb.setOnAction(event -> {
            //Call a method to determine which item in the list the user has selected
            ///doAction(cb.getValue().toString()); //Send the selected item to the method
           setMatrix(YesImAGummyBear());
        });
        btnSave.setOnAction(event -> {
            //Call a method to determine which item in the list the user has selected
            DaVoid(); //Send the selected item to the method
        });

        fieldListRb1 = new CustomTextField[2][2];
        fieldListRb2 = new CustomTextField[3][3];
        fieldsPane = new GridPane();

        vbUi = new VBox();  // user input
        vbPo = new VBox();  // program output
        vbLeft = new VBox();
        vbRight = new VBox();

        setVbUi(setHbRadios(this.rb1, this.rb2), setHbComboBox());
        setVbPo("Eigenvalues and Eigenvectors");

        setVbLeft(setLeft(this.vbUi, null));
        setVbRight(setRight(this.vbPo, setHbBottom(this.btnStart, this.btnReset)));

        setView2();
        setActions();
//        ArrayList<String> stuff = YesImAGummyBear();
//        for(int i =0; i<stuff.size(); i++) {
//            System.out.println(stuff.get(i).toString());
//            cb.getItems().add(stuff.get(i));
//        }
        cb.setPromptText("Saved Matrices");
    }
    private ArrayList<Integer> YesImAGummyBear(){ // cmbox moves


        ArrayList<String> objs = new ArrayList<>();
        ArrayList<Integer> ints = new ArrayList<>();
        JSONArray js = (JSONArray) jsonObject.get(cb.getValue().toString());
        JSONArray list = js;
        Iterator<JSONObject> iterator = js.iterator();
        while (iterator.hasNext()) {
            objs.add(String.valueOf(iterator.next()));
        }

        for(int i = 0; i< objs.size();i++){
            ints.add(Integer.valueOf(objs.get(i)));
        }


//        ArrayList<String> objs = new ArrayList<>();
//
//        Iterator<String> keys = jsonObject.values().iterator();
//        while (keys.hasNext()) {
//            objs.add(String.valueOf(keys.next()));
//        }
//        System.out.println(objs.get(3));
        return ints;
//        while(keys.hasNext()) {
//            String key = keys.next();
//            System.out.println(key);
//            if(jsonObject.get(key) instanceof JSONArray) {
//                JSONArray array = (JSONArray) jsonObject.get(key);
//                JSONObject object = (JSONObject) array.get(0);
//                Iterator<String> innerKeys = object.keys();
//                while(innerKeys.hasNext()) {
//                    String innerKey = innerKeys.next();
//                    System.out.println(innerKey);
//                }
    }
    private void setMatrix(ArrayList<Integer> ints){
        a1 = ints.get(0);
        a2 = ints.get(1);
        a3 = ints.get(2);
        b1 = ints.get(3);
        b2 = ints.get(4);
        b3 = ints.get(5);
        c1 = ints.get(6);
        c2 = ints.get(7);
        c3 = ints.get(8);

        if (rb1.isSelected()) {
            fieldListRb1[0][0].setText(String.valueOf(a1));
            fieldListRb1[0][1].setText(String.valueOf(a2));
            fieldListRb1[1][0].setText(String.valueOf(b1));
            fieldListRb1[1][1].setText(String.valueOf(b2));
        }
        if (rb2.isSelected()) {
            fieldListRb2[0][0].setText(String.valueOf(a1));
            fieldListRb2[0][1].setText(String.valueOf(a2));
            fieldListRb2[0][2].setText(String.valueOf(a3));
            fieldListRb2[1][0].setText(String.valueOf(b1));
            fieldListRb2[1][1].setText(String.valueOf(b2));
            fieldListRb2[1][2].setText(String.valueOf(b3));
            fieldListRb2[2][0].setText(String.valueOf(c1));
            fieldListRb2[2][1].setText(String.valueOf(c2));
            fieldListRb2[2][2].setText(String.valueOf(c3));
        }

    }

    private void DaVoid(){


        StackPane secondaryLayout = new StackPane();

        secondaryLayout.getChildren().add(PANCAKES());
        Scene secondScene = new Scene(secondaryLayout, 300, 300);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
//        newWindow.setX(primaryStage.getX() + 200);
//        newWindow.setY(primaryStage.getY() + 100);

        newWindow.show();
    } //open new scene
    private VBox PANCAKES(){
        VBox payne = new VBox();
        payne.setPadding(new Insets(10));
        this.ll = new Label("Choose a name for your matrix");
        ll.setTextFill(Color.BLUE);
        Text txt = new Text("YO SWITCH IT UP");
        //ll.setFont(font);
        //ll.setStyle("-fx-text-fill: E7EBEE;");

        TextField ctf = new CustomTextField();
        butt = new CustomButton("SEND IT");
        payne.getChildren().add(ll);
        payne.getChildren().add(ctf);
        payne.getChildren().add(butt);
        butt.setOnAction(event -> {
//            System.out.println("juhegfcufeefehf");
//            System.out.println(ctf.getText());
//             ArrayList<Integer> Nul = new ArrayList<>();
//
//            Nul = ModelForJSON.makeTheArrayList((JSONArray) jsonObject.get("null"));
//            System.out.println(Nul.get(0));
//            System.out.println(jsonObject.get("null"));
           // boolean exist = false;
            try {
                if (jsonObject.containsKey(ctf.getText()) == true) {
                    if (!payne.getChildren().contains(txt)) {
                        payne.getChildren().add(txt);
                        System.out.println("THICK BITCHES ONLY");

                    }
                }else{
                    System.out.println("BAD BITCHES ONLY");

                    humptyDumptyFellOffAWall(ctf.getText());
                    payne.getChildren().remove(txt);
                }
            }catch(NullPointerException e) {
                System.out.println("THIS AINT GOOD");

            }
            System.out.println("3jhuyruefhbwkmef");
        });

        return payne;
    } //vbox for scene
    private void humptyDumptyFellOffAWall(String name) { //CONNECT TO BUTTON HANDL
        JSONArray newMatrix = new JSONArray();
        if (this.getRb1().isSelected()) {
            newMatrix.add(this.getFieldListRb1().get(0));//
            newMatrix.add(this.getFieldListRb1().get(1));//
            newMatrix.add("0");
            newMatrix.add(this.getFieldListRb1().get(2));//
            newMatrix.add(this.getFieldListRb1().get(3));//
            newMatrix.add("0");
            newMatrix.add("0");
            newMatrix.add("0");
            newMatrix.add("0");
        }
        if (this.getRb2().isSelected()) {
            for(int i=0; i<9; i++) {
                newMatrix.add(this.getFieldListRb2().get(i).getText());
            }
        }
        jsonObject.put(name, newMatrix);
        //File newFile = new File("Resources/JsonFile.json");

        try {
            //PrintWriter pw = new PrintWriter(new FileWriter("Resources/JsonFile.json", true));
            file = new FileWriter("Resources/JsonFile.json");
            //FileWriter fileWriter = new FileWriter(log, true);

            // bufferedWriter = new BufferedWriter(file);
            //BufferedWriter out = new BufferedWriter(file);
            file.write(jsonObject.toJSONString()); // read before right
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
                //file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<Integer> humptyDumptyCameBackToLife () {
        JSONParser parser = new JSONParser();

        Object obj = null;
        try {
            obj = parser.parse(new FileReader("Resources/JsonFile.json"));
            this.jsonObject = (JSONObject) obj;


            try {
                this.other = ModelForJSON.makeTheArrayList((JSONArray) jsonObject.get(this.getCb().getValue()));
                a1 = getOther().get(0);
                a2 = getOther().get(1);
                a3 = getOther().get(2);
                b1 = getOther().get(3);
                b2 = getOther().get(4);
                b3 = getOther().get(5);
                c1 = getOther().get(6);
                c2 = getOther().get(7);
                c3 = getOther().get(8);
                if (rb1.isSelected()) {
                    fieldListRb1[0][0].setText(String.valueOf(a1));
                    fieldListRb1[0][1].setText(String.valueOf(a2));
                    fieldListRb1[1][0].setText(String.valueOf(b1));
                    fieldListRb1[1][1].setText(String.valueOf(b2));
                }
                if (rb2.isSelected()) {
                    fieldListRb2[0][0].setText(String.valueOf(a1));
                    fieldListRb2[0][1].setText(String.valueOf(a2));
                    fieldListRb2[0][2].setText(String.valueOf(a3));
                    fieldListRb2[1][0].setText(String.valueOf(b1));
                    fieldListRb2[1][1].setText(String.valueOf(b2));
                    fieldListRb2[1][2].setText(String.valueOf(b3));
                    fieldListRb2[2][0].setText(String.valueOf(c1));
                    fieldListRb2[2][1].setText(String.valueOf(c2));
                    fieldListRb2[2][2].setText(String.valueOf(c3));
                }
            }catch(Exception e){
                e.printStackTrace();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return other;
    }
    private HBox setHbComboBox() {
        HBox hbComboBox = new HBox(100);
        hbComboBox.setPadding(new Insets(15));
        hbComboBox.getChildren().addAll(this.cb, this.btnSave);
        return hbComboBox;
    }

    @Override
    public VBox setLeft(VBox vbUi, Pane graphPane) {
        VBox vbLeft = new VBox();
        vbLeft.setSpacing(10);
        vbLeft.setPrefSize(500, 695);
        vbLeft.setLayoutX(10);
        vbLeft.setLayoutY(14);
        vbLeft.getChildren().addAll(vbUi);

        return  vbLeft;
    }


    public GridPane showDaRight(Controller2 controller2) {
        GridPane gpt = new GridPane();
        gpt.setPadding(new Insets(10, 10, 10, 10));
        gpt.setPrefSize(500, 695);
        gpt.setLayoutX(520);
        gpt.setLayoutY(14);

        if (rb2.isSelected()) {
            String text ="FOR THE EIGENVALUE " + controller2.getEigenValues()[0] + " THE EIGEN VECTOR IS";
            String text1 = "FOR THE EIGENVALUE " + controller2.getEigenValues()[1] + " THE EIGEN VECTOR IS";
            String text2 = "FOR THE EIGENVALUE " + controller2.getEigenValues()[2] + " THE EIGEN VECTOR IS";
            //String text3 = "FOR THE EIGENVALUE " + controller2.getEigenValues()[0] + " THE EIGEN VECTOR IS";
            gpt.add(Custom.setTitle(text), 2, 10);
            if (Model2for3x3.getS1().size() == 3) {
                gpt.add(newVector(1,3, controller2,0), 2, 20);
            } else if (Model2for3x3.getS1().size() == 6) {
                gpt.add(newVector(1,3, controller2,0), 2, 20);
                gpt.add(newVector(2,3, controller2,0), 3, 20);
            } else {
                gpt.add(newVector(1,3, controller2,0), 2, 20);
                gpt.add(newVector(2,3, controller2,0), 3, 20);
                gpt.add(newVector(3,3, controller2,0), 4, 20);
            }
            gpt.add(Custom.setTitle(text1), 2, 30);
            if (Model2for3x3.getS2().size() == 3) {
                gpt.add(newVector(1,3, controller2,1), 2, 40);
            } else if (Model2for3x3.getS2().size() == 6) {
                gpt.add(newVector(1,3, controller2,1), 2, 40);
                gpt.add(newVector(2,3, controller2,1), 4, 40);
            } else {
                gpt.add(newVector(1,3, controller2,1), 2, 40);
                gpt.add(newVector(2,3, controller2,1), 4, 40);
                gpt.add(newVector(3,3,  controller2,1), 6, 40);
            }
            gpt.add(Custom.setTitle(text2), 1, 23);
            if (Model2for3x3.getS3().size() == 3) {
                gpt.add(newVector(1,3, controller2,2), 2, 27);
            } else if (Model2for3x3.getS3().size() == 6) {
                gpt.add(newVector(1,3,  controller2,2), 2, 27);
                gpt.add(newVector(2,3,  controller2,2), 4, 27);
            } else {
                gpt.add(newVector(1,3,  controller2,2), 2, 27);
                gpt.add(newVector(2,3,  controller2,2), 4, 27);
                gpt.add(newVector(3,3, controller2,2), 6, 27);
            }
        }
        if (rb1.isSelected()) {
            Text text = new Text("FOR THE EIGENVALUE " + controller2.getEigenValues()[0] + " THE EIGEN VECTOR IS");
            Text text1 = new Text("FOR THE EIGENVALUE " + controller2.getEigenValues()[1] + " THE EIGEN VECTOR IS");
            gpt.add(text, 1, 10);
            if (Model2for2x2.getS1().size() == 2) {
                gpt.add(newVector(1,2,  controller2,0), 2, 15);
            }
            else{
                gpt.add(newVector(1,2, controller2,0), 2, 15);
                gpt.add(newVector(2,2,  controller2,0), 4, 15);
            }
            gpt.add(text1, 1, 17);
            if (Model2for2x2.getS2().size() == 2) {
                gpt.add(newVector(1,2,  controller2,1), 2, 23);
            }
            else{
                gpt.add(newVector(1,2,  controller2,1), 2, 23);
                gpt.add(newVector(2,2,  controller2,1), 4, 23);
            }

        }
        gpt.setStyle("-fx-border-width: 2px;\n" +
                "    -fx-border-color: red;\n" +
                "    -fx-border-insets: -2px;");
        return gpt;
    }

    public HBox newVector(int counter, int whatSize, Controller2 controller2, int i ){ // counter is the vector if size=6 there is counter 1 and 2 possible
        HBox hbx = new HBox();
        VBox vbx1 = new VBox();
        ImageView imL = new ImageView(new Image(Custom.p + "Right.png"));
        imL.setFitWidth(10);
        imL.setFitHeight(75);
        ImageView imR = new ImageView(new Image(Custom.p + "Left.png"));
        imR.setFitWidth(10);
        imR.setFitHeight(75);
        if(whatSize == 2){
            vbx1 = putVertical2x2(counter,  controller2, i );
            vbx1.setPrefHeight(75);
        }
        if(whatSize == 3){
            vbx1 = putVertical3x3(counter,  controller2, i);
            vbx1.setPrefHeight(75);
        }
        hbx.getChildren().addAll(imL,vbx1,imR);
        hbx.setStyle("-fx-border-width: 2px;\n" +
                "    -fx-border-color: red;\n" +
                "    -fx-border-insets: -2px;");

        return hbx;
    }
    public VBox putVertical3x3(int counter,  Controller2 controller2, int i){
        VBox vbx1 = new VBox();
        Double numba1 = null;
        Double numba2 = null;
        Double numba3 = null;

        if(counter==1){
            numba1 = controller2.getEigenVectors()[i].get(0);
            numba2 = controller2.getEigenVectors()[i].get(1);
            numba3 = controller2.getEigenVectors()[i].get(2);
        }
        else if(counter==2){
            numba1 = controller2.getEigenVectors()[i].get(3);
            numba2 = controller2.getEigenVectors()[i].get(4);
            numba3 = controller2.getEigenVectors()[i].get(5);
        }
        else{
            numba1 = controller2.getEigenVectors()[i].get(6);
            numba2 = controller2.getEigenVectors()[i].get(7);
            numba3 = controller2.getEigenVectors()[i].get(8);
        }

        Text nb1 = new Text(Double.toString(numba1));
        Text nb2 = new Text(Double.toString(numba2));
        Text nb3 = new Text(Double.toString(numba3));
        vbx1.getChildren().add(nb1);
        vbx1.getChildren().add(nb2);
        vbx1.getChildren().add(nb3);


        return vbx1;
    }
    public VBox putVertical2x2(int counter, Controller2 controller2, int i){
        VBox vbx1 = new VBox();
        Double numba1 = null;
        Double numba2= null;
        if(counter==1){
            numba1 = controller2.getEigenVectors()[i].get(0);
            numba2 = controller2.getEigenVectors()[i].get(1);
        }
        if(counter==2){
            numba1 = controller2.getEigenVectors()[1].get(2);
            numba2 = controller2.getEigenVectors()[1].get(3);
        }
        Text nb1 = new Text(Double.toString(numba1));
        Text nb2 = new Text(Double.toString(numba2));
        vbx1.getChildren().add(nb1);
        vbx1.getChildren().add(nb2);
        return vbx1;
    }

    private void setVbUi(HBox hbRadios, HBox hbComboBox) {
        this.vbUi.setSpacing(5);
        this.vbUi.setPrefSize(500, 695);
        this.vbUi.setStyle("-fx-background-color: #333335");
        this.vbUi.getChildren().addAll(hbRadios, hbComboBox);
    }

    private void setVbPo(String title) {
        this.vbPo.setPrefSize(500, 595);
        this.vbPo.setSpacing(15);
        this.vbPo.setAlignment(Pos.TOP_CENTER);
        this.vbPo.setStyle("-fx-background-color: #333335");
        this.vbPo.getChildren().add(Custom.setTitle(title));
    }

    public void setView2() {
        this.setPrefSize(1050, 750);
        this.setStyle("-fx-background-color: #6F6F77;");    // Blue Grey
        this.getChildren().addAll(this.vbLeft, this.vbRight);
    }

    public void setActions() {
        rb1.setOnAction(event -> {
            this.btnStart.setDisable(false);
            fieldsPane = setFields(fieldListRb1);
            this.vbUi.getChildren().clear();
            this.vbUi.getChildren().addAll(setHbRadios(rb1, rb2), setHbComboBox(), fieldsPane);
        });

        rb2.setOnAction(event -> {
            this.btnStart.setDisable(false);
            fieldsPane = setFields(fieldListRb2);
            this.vbUi.getChildren().clear();
            this.vbUi.getChildren().addAll(setHbRadios(rb1, rb2), setHbComboBox(), fieldsPane);
        });
        this.btnStart.setOnAction(event -> { handleStart(rb1.isSelected()); });
        this.btnReset.setOnAction(event -> { handleReset(); });

    }

    public GridPane setFields (CustomTextField[][] textFields) {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        int rows = textFields.length;
        int cols = textFields[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols;  j++) {
                textFields[i][j] = new CustomTextField();
                int finalI = i;
                int finalJ = j;

                textFields[i][j].textProperty().addListener((observable, oldValue, newValue) ->
                        btnStart.setDisable(textFields[finalI][finalJ].checkField()));

                gridPane.add(textFields[i][j], j, i);
            }
        }
        return gridPane;
    }

    public void handleStart(boolean isRb1Selected) {
        if (isRb1Selected) {
            for (int i = 0; i < fieldListRb1.length; i++) {
                for (int j = 0; j < fieldListRb1[0].length; j++) {
                    if (fieldListRb1[i][j].getText().equals("")) {
                        fieldListRb1[i][j].setText("0");
                    }
                    System.out.println("YO: " + fieldListRb1[i][j].getText());
                }
            }
        } else {
            for (int i = 0; i < fieldListRb2.length; i++) {
                for (int j = 0; j < fieldListRb2[0].length; j++) {
                    if (fieldListRb2[i][j].getText().equals("")) {
                        fieldListRb2[i][j].setText("0");
                    }
                    System.out.println("YO FROM 3x3: " + fieldListRb2[i][j].getText());
                }
            }
        }
        Controller2 controller2 = new Controller2(this);
        addOutput(controller2);
    }

    public void handleReset() {
        this.getChildren().clear();
        btnStart.setDisable(false);
        rb1.setSelected(false);
        rb2.setSelected(false);
        this.vbUi.getChildren().remove(fieldsPane);
        this.getChildren().addAll(this.vbLeft, this.vbRight);
    }

    public void addOutput(Controller2 controller2) {
        this.vbPo.getChildren().clear();
        setVbPo("Eigenvalues and eigenvectors");

        VBox vbOutput = new VBox();
        vbOutput.setSpacing(15);
        vbOutput.setPadding(new Insets(15));

        // Print eigenvalues


        /*for (int i = 0; i < controller1.getOutput().length; i++) {
            if (i == 0) {
                vbOutput.getChildren().add(Custom.setTitle("X = " + controller1.getOutput()[i]));
            } else if (i == 1) {
                vbOutput.getChildren().add(Custom.setTitle("Y = " + controller1.getOutput()[i]));
            } else if (i == 2) {
                vbOutput.getChildren().add(Custom.setTitle("Z = " + controller1.getOutput()[i]));
            }
        }*/
        this.vbPo.getChildren().add(vbOutput);
        this.vbPo.getChildren().add(showDaRight(controller2));
    }

    public ArrayList<CustomTextField> getFieldListRb1() {
        ArrayList<CustomTextField> fieldList = new ArrayList<>();
        for (CustomTextField[] tfArray: this.fieldListRb1) {
            for (CustomTextField tf: tfArray) {
                fieldList.add(tf);
            }
        }
        return fieldList;
    }

    public ArrayList<CustomTextField> getFieldListRb2() {
        ArrayList<CustomTextField> fieldList = new ArrayList<>();
        for (CustomTextField[] tfArray: this.fieldListRb2) {
            for (CustomTextField tf: tfArray) {
                fieldList.add(tf);
            }
        }
        return fieldList;
    }

    private void doAction(String listItem) {
        System.out.println("pepepepe");
        switch (listItem) {
            case "Lower Triangle" -> {
                this.a1 = ModelForJSON.getLowerTriangle().get(0);
                a2 = ModelForJSON.getLowerTriangle().get(1);
                a3 = ModelForJSON.getLowerTriangle().get(2);
                b1 = ModelForJSON.getLowerTriangle().get(3);
                b2 = ModelForJSON.getLowerTriangle().get(4);
                b3 = ModelForJSON.getLowerTriangle().get(5);
                c1 = ModelForJSON.getLowerTriangle().get(6);
                c2 = ModelForJSON.getLowerTriangle().get(7);
                c3 = ModelForJSON.getLowerTriangle().get(8);
            }

            //Action for this item
            case "Upper Triangle" -> {
                a1 = ModelForJSON.getUpperTriangle().get(0);
                a2 = ModelForJSON.getUpperTriangle().get(1);
                a3 = ModelForJSON.getUpperTriangle().get(2);
                b1 = ModelForJSON.getUpperTriangle().get(3);
                b2 = ModelForJSON.getUpperTriangle().get(4);
                b3 = ModelForJSON.getUpperTriangle().get(5);
                c1 = ModelForJSON.getUpperTriangle().get(6);
                c2 = ModelForJSON.getUpperTriangle().get(7);
                c3 = ModelForJSON.getUpperTriangle().get(8);
            }
            case "Diagonal" -> {
                a1 = ModelForJSON.getDiagonal().get(0);
                a2 = ModelForJSON.getDiagonal().get(1);
                a3 = ModelForJSON.getDiagonal().get(2);
                b1 = ModelForJSON.getDiagonal().get(3);
                b2 = ModelForJSON.getDiagonal().get(4);
                b3 = ModelForJSON.getDiagonal().get(5);
                c1 = ModelForJSON.getDiagonal().get(6);
                c2 = ModelForJSON.getDiagonal().get(7);
                c3 = ModelForJSON.getDiagonal().get(8);
            }
            case "identity" -> {
                a1 = ModelForJSON.getIdentity().get(0);
                a2 = ModelForJSON.getIdentity().get(1);
                a3 = ModelForJSON.getIdentity().get(2);
                b1 = ModelForJSON.getIdentity().get(3);
                b2 = ModelForJSON.getIdentity().get(4);
                b3 = ModelForJSON.getIdentity().get(5);
                c1 = ModelForJSON.getIdentity().get(6);
                c2 = ModelForJSON.getIdentity().get(7);
                c3 = ModelForJSON.getIdentity().get(8);
            }
            case "Null" -> {
                a1 = ModelForJSON.getNul().get(0);
                a2 = ModelForJSON.getNul().get(1);
                a3 = ModelForJSON.getNul().get(2);
                b1 = ModelForJSON.getNul().get(3);
                b2 = ModelForJSON.getNul().get(4);
                b3 = ModelForJSON.getNul().get(5);
                c1 = ModelForJSON.getNul().get(6);
                c2 = ModelForJSON.getNul().get(7);
                c3 = ModelForJSON.getNul().get(8);
            }
            case "Symmetric" -> {
                a1 = ModelForJSON.getSymmetric().get(0);
                a2 = ModelForJSON.getSymmetric().get(1);
                a3 = ModelForJSON.getSymmetric().get(2);
                b1 = ModelForJSON.getSymmetric().get(3);
                b2 = ModelForJSON.getSymmetric().get(4);
                b3 = ModelForJSON.getSymmetric().get(5);
                c1 = ModelForJSON.getSymmetric().get(6);
                c2 = ModelForJSON.getSymmetric().get(7);
                c3 = ModelForJSON.getSymmetric().get(8);
            }
            default -> {
            } //Default action
        }
        if (rb1.isSelected()) {
            fieldListRb1[0][0].setText(String.valueOf(a1));
            fieldListRb1[0][1].setText(String.valueOf(a2));
            fieldListRb1[1][0].setText(String.valueOf(b1));
            fieldListRb1[1][1].setText(String.valueOf(b2));
        }
        if (rb2.isSelected()) {
            fieldListRb2[0][0].setText(String.valueOf(a1));
            fieldListRb2[0][1].setText(String.valueOf(a2));
            fieldListRb2[0][2].setText(String.valueOf(a3));
            fieldListRb2[1][0].setText(String.valueOf(b1));
            fieldListRb2[1][1].setText(String.valueOf(b2));
            fieldListRb2[1][2].setText(String.valueOf(b3));
            fieldListRb2[2][0].setText(String.valueOf(c1));
            fieldListRb2[2][1].setText(String.valueOf(c2));
            fieldListRb2[2][2].setText(String.valueOf(c3));
        }
    } //DONE DONE DONE



        public void setFieldListRb1(CustomTextField[][] fieldListRb1) {
        this.fieldListRb1 = fieldListRb1;
    }

    public void setFieldListRb2(CustomTextField[][] fieldListRb2) {
        this.fieldListRb2 = fieldListRb2;
    }

    public GridPane getFieldsPane() {
        return fieldsPane;
    }

    public void setFieldsPane(GridPane fieldsPane) {
        this.fieldsPane = fieldsPane;
    }

    public void setRb1(CustomRadioButton rb1) {
        this.rb1 = rb1;
    }

    public CustomRadioButton getRb1() {
        return rb1;
    }

    public CustomRadioButton getRb2() {
        return rb2;
    }

    public void setRb2(CustomRadioButton rb2) {
        this.rb2 = rb2;
    }

    public CustomButton getBtnStart() {
        return btnStart;
    }

    public void setBtnStart(CustomButton btnStart) {
        this.btnStart = btnStart;
    }

    public CustomButton getBtnReset() {
        return btnReset;
    }

    public void setBtnReset(CustomButton btnReset) {
        this.btnReset = btnReset;
    }

    public CustomButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CustomButton btnSave) {
        this.btnSave = btnSave;
    }

    public ToggleGroup getGroup() {
        return group;
    }

    public void setGroup(ToggleGroup group) {
        this.group = group;
    }

    public ComboBox getCb() {
        return cb;
    }

    public void setCb(ComboBox cb) {
        this.cb = cb;
    }

    public CustomButton getButt() {
        return butt;
    }

    public void setButt(CustomButton butt) {
        this.butt = butt;
    }

    public Label getLl() {
        return ll;
    }

    public void setLl(Label ll) {
        this.ll = ll;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public VBox getVbUi() {
        return vbUi;
    }

    public void setVbUi(VBox vbUi) {
        this.vbUi = vbUi;
    }

    public VBox getVbPo() {
        return vbPo;
    }

    public void setVbPo(VBox vbPo) {
        this.vbPo = vbPo;
    }

    public ModelForJSON getJASONDERULO() {
        return JASONDERULO;
    }

    public void setJASONDERULO(ModelForJSON JASONDERULO) {
        this.JASONDERULO = JASONDERULO;
    }

    public VBox getVbLeft() {
        return vbLeft;
    }

    public void setVbLeft(VBox vbLeft) {
        this.vbLeft = vbLeft;
    }

    public VBox getVbRight() {
        return vbRight;
    }

    public void setVbRight(VBox vbRight) {
        this.vbRight = vbRight;
    }

    public CustomTextField getT1() {
        return t1;
    }

    public void setT1(CustomTextField t1) {
        this.t1 = t1;
    }

    public CustomTextField getT2() {
        return t2;
    }

    public void setT2(CustomTextField t2) {
        this.t2 = t2;
    }

    public CustomTextField getT3() {
        return t3;
    }

    public void setT3(CustomTextField t3) {
        this.t3 = t3;
    }

    public CustomTextField getT4() {
        return t4;
    }

    public void setT4(CustomTextField t4) {
        this.t4 = t4;
    }

    public CustomTextField getT5() {
        return t5;
    }

    public void setT5(CustomTextField t5) {
        this.t5 = t5;
    }

    public CustomTextField getT6() {
        return t6;
    }

    public void setT6(CustomTextField t6) {
        this.t6 = t6;
    }

    public CustomTextField getT7() {
        return t7;
    }

    public void setT7(CustomTextField t7) {
        this.t7 = t7;
    }

    public CustomTextField getT8() {
        return t8;
    }

    public void setT8(CustomTextField t8) {
        this.t8 = t8;
    }

    public CustomTextField getT9() {
        return t9;
    }

    public void setT9(CustomTextField t9) {
        this.t9 = t9;
    }

    public double getA1() {
        return a1;
    }

    public void setA1(double a1) {
        this.a1 = a1;
    }

    public double getA2() {
        return a2;
    }

    public void setA2(double a2) {
        this.a2 = a2;
    }

    public double getA3() {
        return a3;
    }

    public void setA3(double a3) {
        this.a3 = a3;
    }

    public double getB1() {
        return b1;
    }

    public void setB1(double b1) {
        this.b1 = b1;
    }

    public double getB2() {
        return b2;
    }

    public void setB2(double b2) {
        this.b2 = b2;
    }

    public double getB3() {
        return b3;
    }

    public void setB3(double b3) {
        this.b3 = b3;
    }

    public double getC1() {
        return c1;
    }

    public void setC1(double c1) {
        this.c1 = c1;
    }

    public double getC2() {
        return c2;
    }

    public void setC2(double c2) {
        this.c2 = c2;
    }

    public double getC3() {
        return c3;
    }

    public void setC3(double c3) {
        this.c3 = c3;
    }

    public static FileWriter getFile() {
        return file;
    }

    public static void setFile(FileWriter file) {
        View2.file = file;
    }

    public ArrayList<Integer> getOther() {
        return other;
    }

    public void setOther(ArrayList<Integer> other) {
        this.other = other;
    }
}