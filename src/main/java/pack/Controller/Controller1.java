package pack.Controller;

import javafx.geometry.Point3D;
import pack.Model.Model1;
import pack.View.Customs.CustomTextField;
import pack.View.View1;
import java.util.ArrayList;

/**
 * This class is the Controller between View1 and Model1. It fetches the data from View1 and sends it to the Model for
 * the mathematical process. It then fetches the data from Model1 and gives it back to the View.
 */
public class Controller1 {

    private View1 view;                             // The view object that is getting updated
    private ArrayList<CustomTextField> fieldList;   // The list of text fields that are the input
    private ArrayList<Double> matrixCoefficients;   // The list of inputs (text fields) in the form of double
    private boolean is2by2;                         // A boolean variable to determine if the matrix is 2x2 or 3x3
    private Model1 model;                           // The model object that does the math
    private String rank;                            // The rank of the final matrix

    public Controller1(View1 view) {
        this.view = view;
        is2by2 = view.getRb1().isSelected();
        fieldList = new ArrayList<>();
        matrixCoefficients = new ArrayList<>();
        transform();
    }

    /**
     * This method sends to 2 different methods to transform the given data.
     */
    private void transform() {
        if (is2by2) {
            fieldList = view.getFieldListRb1();
            transform2x2();
        } else {
            fieldList = view.getFieldListRb2();
            transform3x3();
        }
    }

    /**
     * This method transforms the data given for a 2x2 matrix. It adds all the inputs of the text fields into the
     * matrixCoefficients variable to store it as Doubles.
     */
    public void transform2x2() { // always 6
        for (int i = 0; i < this.fieldList.size(); i++) {
            if (i == 2 || i == 5) {
                continue;
            }
            this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(i).getText()));
        }
        this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(2).getText()));
        this.matrixCoefficients.add(Double.parseDouble(this.fieldList.get(5).getText()));
    }

    /**
     * This method transforms the data given for a 3x3 matrix. It adds all the inputs of the text fields into the
     * matrixCoefficients variable to store it as Doubles.
     */
    public void transform3x3() {   //always 12d
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

    /**
     * This method graphs the output for View 1 which is either two lines or two planes.
     */
    public void graphPlane() {
        model = new Model1(this.matrixCoefficients, is2by2);

        if (is2by2) {
            view.graph.addLine(model.solutionPointsLineOne(69), model.solutionPointsLineOne(420), new double[]{0, 0, 0});
            view.graph.addLine(model.solutionPointsLineTwo(69), model.solutionPointsLineTwo(420), new double[]{0, 0, 0});

        } else {
            double[][] A = Model1.getMatrixA_3x3();
            double[] b = Model1.getMatrixB_3x3();
            view.graph.addPlane(b[0] / A[0][0], b[0] / A[0][1], b[0] / A[0][2]);
            view.graph.addPlane(b[1] / A[1][0], b[1] / A[1][1], b[1] / A[1][2]);
            view.graph.addPlane(b[2] / A[2][0], b[2] / A[2][1], b[2] / A[2][2]);
        }
    }

    /**
     * This method retrieves the result from model and puts all the output into an ArrayList of String[]. This ArrayList
     * is then sent to View to put on display on the application.
     * @return the String[] of outputs containing all the constants of the reduced matrix and the solutions
     */
    public ArrayList<String[]> getOutput() {
        model = new Model1(this.matrixCoefficients, is2by2);
        if (is2by2) {
            double[][] A = Model1.getMatrixA_2x2();
            double[] b = Model1.getMatrixB_2x2();
            double[] x = model.SLEsolve(A, b);

            String[] sol;

            ArrayList<String[]> output = new ArrayList<>();
            output.add(model.getConstants(b));
            sol = model.round(x);

            //no solutions
            if (x[2] == 0) {
                for (int i = 0; i < x.length - 1; i++) {
                    sol[i] = "No solution";
                }
                rank = "0";
                output.add(sol);
                return output;
            }
            //unique solution
            else if (x[2] == 1) {
                for (int i = 0; i < x.length - 1; i++) {
                    sol[i] = " " + String.format("%.2f", x[i]);
                }
                view.graph.addPoint(new Point3D(Double.parseDouble(sol[0]), Double.parseDouble(sol[1]), 0));
                rank = "" + b.length;
            }
            //if there is 1 free variable
            else if (x[2] == 2) {
                sol[0] = " " + String.format("%.2f", x[0]) + " + (" + String.format("%.2f", x[1]) + ")t";
                sol[1] = " t";
                rank = "1";
            }

            output.add(sol);
            return output;
        } else {
            double[][] A = Model1.getMatrixA_3x3();
            double[] b = Model1.getMatrixB_3x3();
            double[] x = model.SLEsolve(A, b);

            String[] sol = new String[x.length];

            ArrayList<String[]> output = new ArrayList<>();
            output.add(model.getConstants(b));

            if (x[4] == 0) { // No Solution
                for (int i = 0; i < x.length - 1; i++)
                    sol[i] = " No solution";
                rank = "0";

                output.add(sol);
                return output;
            } // 1 Solution
            else if (x[4] == 1) {
                for (int i = 0; i < x.length - 1; i++) {
                    sol[i] = " " + String.format("%.2f", x[i]);
                }
                view.graph.addPoint(new Point3D(Double.parseDouble(sol[0]), Double.parseDouble(sol[1]), Double.parseDouble(sol[2])));
                rank = "" + b.length;
            } // 1 Free Variable
            else if (x[4] == 2) {
                sol[0] = " " + String.format("%.2f", x[0]) + " + (" + String.format("%.2f", x[1]) + ")t";
                sol[1] = " " + String.format("%.2f", x[2]) + " + (" + String.format("%.2f", x[3]) + ")t";
                sol[2] = "t";

                rank = "2";
            } else { // 2 Free Variables
                sol[0] = " " + String.format("%.2f", x[0]) + " + (" + String.format("%.2f", x[1]) + ")s" + " + (" + String.format("%.2f", x[2]) + ")t";
                sol[1] = "s";
                sol[2] = "t";

                rank = "1";
            }
            output.add(sol);
            return output;
        }
    }

    /**
     * This method gets the coefficient reduced matrix (A) and returns it as a String[][] so that the View can use it
     * to print the output.
     * @return the 2D array of the matrix coefficients
     */
    public String[][] getCoefficients() {
        double[][] A;
        if (is2by2) {
            A = Model1.getMatrixA_2x2();
        } else {
            A = Model1.getMatrixA_3x3();
        }
        return model.getCoefficients(A);
    }

    /**
     * This method returns the rank of the reduced matrix to be used in the View.
     * @return the rank of the matrix
     */
    public String getRank() {
        return rank;
    }
}
