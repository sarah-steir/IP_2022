package pack.Controller;

import pack.View.Customs.CustomTextField;
import pack.View.View3;

import java.util.ArrayList;

public class Controller3 {

    View3 view;

    public Controller3(View3 view) {
        this.view = view;
    }


    private ArrayList<CustomTextField> fieldList;
    private ArrayList<Double> matrixCoefficients;
    private boolean is2by2;

    public Controller3() {
        fieldList = new ArrayList<>();
        matrixCoefficients = new ArrayList<>();
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
        }
    }

    public void transform2x2() { // always 6
        for (int i = 0; i < this.fieldList.size(); i++) {
            if (i == 2 || i == 5 ) {
                continue;
            }
            this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(i).getText()));
        }
        this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(2).getText()));
        this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(5).getText()));

    }

}
