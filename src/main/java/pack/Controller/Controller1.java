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

    public void getOutput() {
        Model1 model1 = new Model1(this.matrixCoefficients, is2by2);
        if (is2by2) {
            reduced2x2matrix = model1.getReduced2x2matrix();
        } else {
            reduced3x3matrix = model1.getReduced3x3matrix();
        }
    }

    public void printOutput() {
        if (is2by2) {
            for (Double coefficient: reduced2x2matrix) {
                System.out.println(coefficient);
            }
        } else {
            for (Double coefficient: reduced3x3matrix) {
                System.out.println(coefficient);
            }
        }
    }
}