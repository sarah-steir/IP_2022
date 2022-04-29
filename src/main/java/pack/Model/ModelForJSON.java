package pack.Model;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ModelForJSON {
    private static FileWriter file;
    private static JSONObject matrix = new JSONObject();
    private static JSONObject names = new JSONObject();

    //SET UP BOTH OF THE JSON FILE WHEN NEEDED OR RESET THEM
    public ModelForJSON() {
        //writeBasics();
        //writeNames();
    }

    //Write all the names of the matrices in one jsonfile
    public static void writeNames() {
        JSONArray getNames = new JSONArray();
        getNames.add("identity");
        getNames.add("diagonal");
        getNames.add("null");
        getNames.add("symmetric");
        getNames.add("upper triangle");
        getNames.add("lower triangle");

        names.put("names", getNames);

        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("Resources/JsonNames.json");
            file.write(names.toJSONString());
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

    //write all the matrices with their names in one jsonfile for one JSONObject
    public static void writeBasics() {
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
}


