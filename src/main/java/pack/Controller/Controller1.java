package pack.Controller;

import pack.Model.Model1;
import pack.View.Customs.CustomTextField;
import pack.View.View1;

import java.util.ArrayList;

public class Controller1 {

    View1 view;
    private double[] reduced2x2matrix, reduced3x3matrix;
    private ArrayList<Double> matrixCoefficients;
    private boolean is2by2;

    public Controller1 (View1 view) {
        this.view = view;
        is2by2 = view.getRb1().isSelected();
        matrixCoefficients = new ArrayList<>();
        transform();
    }

    // Include 2x2 as well later
    public void transform() {
        if (is2by2) {
            transform2x2();
        } else {
            transform3x3();
        }
    }

    public void transform2x2() {
        for (CustomTextField[] tfArray: this.view.getFieldListRb1()) {
            for (CustomTextField tf: tfArray) {
                this.matrixCoefficients.add(Double.parseDouble(tf.getText()));
            }
        }
    }

    public void transform3x3() {
        for (CustomTextField[] tfArray: this.view.getFieldListRb2()) {
            for (CustomTextField tf: tfArray) {
                this.matrixCoefficients.add(Double.parseDouble(tf.getText()));
            }
        }
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