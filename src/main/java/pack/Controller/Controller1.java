package pack.Controller;

import pack.Model.Model1;
import pack.View.Customs.CustomTextField;
import pack.View.View1;

import java.util.ArrayList;

public class Controller1 {

    View1 view;
    private ArrayList<CustomTextField> fieldList;
    private ArrayList<Double> matrixCoefficients;
    private double[] reduced2x2matrix, reduced3x3matrix;
    private boolean is2by2;

    public Controller1() {
        fieldList = new ArrayList<>();
        matrixCoefficients = new ArrayList<>();
    }
    public Controller1 (View1 view) {
        this.view = view;
    }

    public void setFieldList(ArrayList<CustomTextField> copyArray) {
        this.fieldList = copyArray;
    }

    public void printFields() {
        for (CustomTextField tf: this.fieldList) {
            System.out.println(tf.getText());
        }
    }

    // Include 2x2 as well later
    public void transform() {
        if (this.fieldList.size() == 6) {
            transform2x2();
            is2by2 = true;
        } else {
            transform3x3();
            is2by2 = false;
        }
    }

    public void transform2x2() {

    }

    public void transform3x3() {
        for (int i = 0; i < this.fieldList.size(); i++) {
            if (i == 3 || i == 7 || i == 11) {
                continue;
            }
            this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(i).getText()));
        }
        this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(3).getText()));
        this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(7).getText()));
        this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(11).getText()));
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