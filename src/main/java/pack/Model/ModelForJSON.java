package pack.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ModelForJSON {
    private static FileWriter file;

    public ModelForJSON(){
        writeBasics();
        readBasics();
    }
public static void writeBasics() {
    JSONObject matrix = new JSONObject();

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
    matrix.put("Identity", identity);

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
    matrix.put("UpperTriangle", upperTriangle);

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
    matrix.put("LowerTriangle", lowerTriangle);

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
    matrix.put("Diagonal", diagonal);

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
    matrix.put("Symmetric", symmetric);

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
    matrix.put("Null", Null);
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
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray companyList = (JSONArray) jsonObject.get("Identity");

            Iterator<JSONObject> iterator = companyList.iterator();
            while (iterator.hasNext()) {


        }




    }
}


