package pack.Controller;

import pack.Model.Model2for2x2;
import pack.Model.Model2for3x3;
import pack.View.Customs.CustomTextField;
import pack.View.View2;
import java.util.ArrayList;


/**
 * This class is the Controller between View2 and Model2for2x2/Model2for3x3. It fetches the data from View1 and sends it
 * to the Model for the mathematical process. It then fetches the data from Model1 and gives it back to the View.
 */
public class Controller2 {

    private final View2 view;                           // The view object
    private ArrayList<CustomTextField> fieldList;       // The list of text fields
    private final ArrayList<Double> matrixCoefficients; // the list of text fields in the form of an array
    private final boolean is2by2;                       // boolean value to check if a matrix is 2x2 or 3x3

    //  Model objects to access the math functions
    private Model2for2x2 model2for2x2;
    private Model2for3x3 model2for3x3;

    public Controller2(View2 view) {
        this.view = view;
        is2by2 = view.getRb1().isSelected();
        matrixCoefficients = new ArrayList<>();
        fieldList = new ArrayList<>();
        transform();
    }

    /**
     * This method transforms the list of text fields that are in the form of an array into a list of the text fields
     * in the form of an ArrayList (it does this accordingly whether it's 2x2 or 3x3)
     */
    private void transform() {
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

    /**
     * This method copies the inputs written into the text fields and adds it into an ArrayList named matrixCoefficients
     * which will be used later for the math.
     */
    private void copyArray() {
        for (CustomTextField tf : fieldList) {
            matrixCoefficients.add(Double.parseDouble(tf.getText()));
        }
    }

    /**
     * This method is used by the View to access the Eigen values that were calculated by the Model classes.
     * @return the eigenvalues
     */
    public double[] getEigenValues() {
        if (is2by2) {
            return model2for2x2.getEigenValues();
        } else {
            return model2for3x3.getEigenValues();
        }
    }

    /**
     * This method is used by the View class to access the Eigen Vectors that were calculated by the Model classes.
     * @return an ArrayList of all the eigenvectors
     */
    public ArrayList<Double>[] getEigenVectors() {
        if (is2by2) {
            return model2for2x2.getEigenVectors();
        } else {
            return model2for3x3.getEigenVectors();
        }
    }
}
