package pack.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import pack.View.View2;

public class ModelForJSON {
    private static FileWriter file;
    private static ArrayList<Integer> Diagonal = new ArrayList<>();
    private static ArrayList<Integer> Identity = new ArrayList<>();
    private static ArrayList<Integer> UpperTriangle = new ArrayList<>();
    private static ArrayList<Integer> LowerTriangle = new ArrayList<>();
    private static ArrayList<Integer> Symmetric = new ArrayList<>();
    private static ArrayList<Integer> Nul = new ArrayList<>();

    private static JSONObject matrix = new JSONObject();

    public ModelForJSON() {
        //writeBasics();
        readBasics();
    }

public static void writeBasics() {
    //JSONObject matrix = new JSONObject();

    JSONArray identity = new JSONArray();
    identity.add("1");
    identity.add("0");
    identity.add("0");
    identity.add("0");
    identity.add("1");
    identity.add("0");
    identity.add("0");
    identity.add("0");
    identity.add("1");
    matrix.put("identity", identity);

    JSONArray upperTriangle = new JSONArray();
    upperTriangle.add("2");
    upperTriangle.add("4");
    upperTriangle.add("8");
    upperTriangle.add("0");
    upperTriangle.add("1");
    upperTriangle.add("6");
    upperTriangle.add("0");
    upperTriangle.add("0");
    upperTriangle.add("2");
    matrix.put("upper triangle", upperTriangle);

    JSONArray lowerTriangle = new JSONArray();
    lowerTriangle.add("1");
    lowerTriangle.add("0");
    lowerTriangle.add("0");
    lowerTriangle.add("-4");
    lowerTriangle.add("2");
    lowerTriangle.add("0");
    lowerTriangle.add("6");
    lowerTriangle.add("-1");
    lowerTriangle.add("8");
    matrix.put("lower triangle", lowerTriangle);

    JSONArray diagonal = new JSONArray();
    diagonal.add("-9");
    diagonal.add("0");
    diagonal.add("0");
    diagonal.add("0");
    diagonal.add("-6");
    diagonal.add("0");
    diagonal.add("0");
    diagonal.add("0");
    diagonal.add("3");
    matrix.put("diagonal", diagonal);

    JSONArray symmetric = new JSONArray();
    symmetric.add("1");
    symmetric.add("-3");
    symmetric.add("2");
    symmetric.add("-3");
    symmetric.add("4");
    symmetric.add("-1");
    symmetric.add("2");
    symmetric.add("-1");
    symmetric.add("1");
    matrix.put("symmetric", symmetric);

    JSONArray Null = new JSONArray();
    Null.add("0");
    Null.add("0");
    Null.add("0");
    Null.add("0");
    Null.add("0");
    Null.add("0");
    Null.add("0");
    Null.add("0");
    Null.add("0");
    matrix.put("null", Null);
//CREATE NEW JSON FILE
    File newFile = new File("Resources/JsonFile.json");

    try {
        // Constructs a FileWriter given a file name, using the platform's default charset
        file = new FileWriter("Resources/JsonFile.json");
        file.write(matrix.toJSONString());
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        }
    public static void writeBasics1() {
        JSONObject matrix = new JSONObject();



        JSONArray Null = new JSONArray();
        Null.add("0");
        Null.add("0");
        Null.add("0");
        Null.add("0");
        Null.add("0");
        Null.add("0");
        Null.add("0");
        Null.add("0");
        Null.add("0");
        matrix.put("null", Null);
//CREATE NEW JSON FILE
        File newFile = new File("Resources/JsonFile.json");

        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("Resources/JsonFile.json");
            file.write(matrix.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
        public static void readBasics(){
//Read
            JSONParser parser = new JSONParser();

            Object obj = null;
            try {
                obj = parser.parse(new FileReader("Resources/JsonFile.json"));
                JSONObject jsonObject = (JSONObject) obj;


                Identity = makeTheArrayList((JSONArray) jsonObject.get("identity"));
                UpperTriangle = makeTheArrayList((JSONArray) jsonObject.get("upper triangle"));
                LowerTriangle = makeTheArrayList((JSONArray) jsonObject.get("lower triangle"));
                Symmetric = makeTheArrayList((JSONArray) jsonObject.get("symmetric"));
                Diagonal = makeTheArrayList((JSONArray) jsonObject.get("diagonal"));
                Nul = makeTheArrayList((JSONArray) jsonObject.get("null"));

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
    public static ArrayList<Integer> makeTheArrayList(JSONArray js){
        ArrayList<String> objs = new ArrayList<>();
        ArrayList<Integer> ints = new ArrayList<>();
        JSONArray list = js;
        Iterator<JSONObject> iterator = js.iterator();
        while (iterator.hasNext()) {
            objs.add(String.valueOf(iterator.next()));
        }

        for(int i = 0; i< objs.size();i++){
            ints.add(Integer.valueOf(objs.get(i)));
        }
        return ints;
    }


    public static ArrayList<Integer> getDiagonal() {
        return Diagonal;
    }

    public static void setDiagonal(ArrayList<Integer> diagonal) {
        Diagonal = diagonal;
    }

    public static ArrayList<Integer> getIdentity() {
        return Identity;
    }

    public static void setIdentity(ArrayList<Integer> identity) {
        Identity = identity;
    }

    public static ArrayList<Integer> getUpperTriangle() {
        return UpperTriangle;
    }

    public static void setUpperTriangle(ArrayList<Integer> upperTriangle) {
        UpperTriangle = upperTriangle;
    }

    public static ArrayList<Integer> getLowerTriangle() {
        return LowerTriangle;
    }

    public static void setLowerTriangle(ArrayList<Integer> lowerTriangle) {
        LowerTriangle = lowerTriangle;
    }

    public static ArrayList<Integer> getSymmetric() {
        return Symmetric;
    }

    public static void setSymmetric(ArrayList<Integer> symmetric) {
        Symmetric = symmetric;
    }

    public static ArrayList<Integer> getNul() {
        return Nul;
    }

    public static void setNul(ArrayList<Integer> nul) {
        Nul = nul;
    }

    public static JSONObject getMatrix() {
        return matrix;
    }

    public static void setMatrix(JSONObject matrix) {
        ModelForJSON.matrix = matrix;
    }
}


