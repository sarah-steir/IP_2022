package pack.Controller;

import javafx.geometry.Point3D;
import pack.Model.Model1;
import pack.View.Customs.CustomTextField;
import pack.View.View1;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Controller1 {

    View1 view;
    private ArrayList<CustomTextField> fieldList;
    private ArrayList<Double> matrixCoefficients;
    private boolean is2by2;
    public Model1 model;
    static final DecimalFormat formatting = new DecimalFormat("0.000");

    public Controller1(View1 view) {
        this.view = view;
        is2by2 = view.getRb1().isSelected();
        fieldList = new ArrayList<>();
        matrixCoefficients = new ArrayList<>();
        transform();
    }


    public void graphPlane() {
        model = new Model1(this.matrixCoefficients, is2by2);

        if (is2by2) {
            double[][] A = Model1.getMatrixA_2x2();
            double[] b = Model1.getMatrixB_2x2();

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


    private String rank;

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

    public String[][] getCoefficients() {
        double[][] A;
        if (is2by2) {
            A = Model1.getMatrixA_2x2();
        } else {
            A = Model1.getMatrixA_3x3();
        }
        return model.getCoefficients(A);
    }

    public String getRank() {
        return rank;
    }

    public int getArraySize() {
        model = new Model1(this.matrixCoefficients, is2by2);
        if (is2by2) {
            return 2;
        } else {
            return 3;
        }
    }

    public void printSol(String[] sol) {
        for (String solution: sol) {
            System.out.println("YA: " + solution);
        }
    }
}
