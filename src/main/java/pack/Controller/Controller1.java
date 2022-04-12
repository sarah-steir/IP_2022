package pack.Controller;

import pack.Model.Model1;
import pack.View.Customs.CustomTextField;
import pack.View.View1;

import java.util.ArrayList;

public class Controller1 {

    View1 view;
    private ArrayList<Double> matrixCoefficients;
    private ArrayList<CustomTextField> fieldList;
    private boolean is2by2;

    public Controller1 (View1 view) {
        this.view = view;
        is2by2 = view.getRb1().isSelected();
        matrixCoefficients = new ArrayList<>();
        fieldList = new ArrayList<>();
        transform();
    }

    // Include 2x2 as well later
    public void transform() {
        if (is2by2) {
            fieldList = view.getFieldListRb1();
            transform2x2();
        } else {
            fieldList = view.getFieldListRb2();
            transform3x3();
        }
    }

    public void transform2x2() {
        for (int i = 0; i < fieldList.size(); i++) {
            if (i == 2 || i == 5 ) {
                continue;
            }
            this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(i).getText()));
        }
        this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(2).getText()));
        this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(5).getText()));
    }

    public void transform3x3() {
        for (int i = 0; i < fieldList.size(); i++) {
            if (i == 3 || i == 7 || i == 11) {
                continue;
            }
            this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(i).getText()));
        }
        this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(3).getText()));
        this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(7).getText()));
        this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(11).getText()));
    }

    public double[] getOutput() {
        Model1 model1 = new Model1(this.matrixCoefficients, is2by2);
        if (is2by2) {
            double [][] A = Model1.getMatrixA_2x2();
            double[] b = Model1.getMatrixB_2x2();
            double[] x = model1.SLESolve(A, b);
            return x;
        } else {
            double [][]A = Model1.getMatrixA_3x3();
            double[] b = Model1.getMatrixB_3x3();
            double[] x = model1.SLESolve(A, b);
            return x;
        }
    }
}