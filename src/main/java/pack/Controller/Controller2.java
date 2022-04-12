package pack.Controller;

import pack.Model.Model1;
import pack.Model.Model2for2x2;
import pack.Model.Model2for3x3;
import pack.View.Customs.CustomTextField;
import pack.View.View1;
import pack.View.View2;

import java.sql.Array;
import java.util.ArrayList;

public class Controller2 {

    private View2 view;
    private ArrayList<CustomTextField> fieldList;
    private ArrayList<Double> matrixCoefficients;
    private boolean is2by2;

    private Model2for2x2 model2for2x2;
    private Model2for3x3 model2for3x3;

    public Controller2 (View2 view) {
        this.view = view;
        is2by2 = view.getRb1().isSelected();
        matrixCoefficients = new ArrayList<>();
        fieldList = new ArrayList<>();
        transform();
    }

    public void transform() {
        if (is2by2) {
            fieldList = view.getFieldListRb1();
            copyArray();
            model2for2x2 = new Model2for2x2(matrixCoefficients);
        } else {
            fieldList = view.getFieldListRb2();
            copyArray();
            model2for3x3 = new Model2for3x3(matrixCoefficients);
        }
    }

    public void copyArray() {
        for (CustomTextField tf: fieldList) {
            matrixCoefficients.add(Double.parseDouble(tf.getText()));
        }
    }

    public double[] getEigenValues() {
        if (is2by2) {
            return model2for2x2.getEigenValues();
        } else {
            return model2for3x3.getEigenValues();
        }
    }

    public ArrayList<Double>[] getEigenVectors() {
        if (is2by2) {
            return model2for2x2.getEigenVectors();
        } else {
            return model2for3x3.getEigenVectors();
        }
    }

    // Get the reduced matrix as well cause WHY NOT ya schmuck

}
