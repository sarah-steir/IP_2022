package pack.Model;

import javafx.geometry.Point3D;
import javafx.scene.control.TextField;
import pack.View.Customs.CustomTextField;

import java.util.ArrayList;

public class Model3 {
    //Planes
    public  ArrayList<Double> n1 = new ArrayList<Double>();
    public  ArrayList<Double> n2 = new ArrayList<Double>();
     double crossProduct[] = new double[3];

    public Model3(){}

    public  void transform(ArrayList<CustomTextField> f) {
        for (int i = 0; i < 4; i++) {
            Double d = Double.parseDouble(f.get(i).getText());
            n1.add(d);
        }
        for (int j = 4; j < f.size(); j++) {
            Double d2 = Double.parseDouble(f.get(j).getText());
            n2.add(d2);
        }
    }

    public  void crossProduct() {
        crossProduct[0] = n1.get(1) * n2.get(2) - n1.get(2) * n2.get(1);
        crossProduct[1] = n1.get(2) * n2.get(0) - n1.get(0) * n2.get(2);
        crossProduct[2] = n1.get(0) * n2.get(1) - n1.get(1) * n2.get(0);

        for (int i = 0; i < 3; i++) {
            System.out.print(crossProduct[i] + " ");
        }
    }

    public Point3D solutionPoints(int i) {

        double x = i;
        double z = ((n2.get(1) / n1.get(1)) * (n1.get(0) * x + n1.get(3)) - n2.get(0) * x - n2.get(3)) / (n2.get(2) - n1.get(2) * n2.get(1) / n1.get(1));
        double y = (-n1.get(2) * z - n1.get(0) * x - n1.get(3)) / n1.get(1);
        //double z= ((-n2.get(1)/n1.get(1))*(n1.get(0)*x+n1.get(3))-n2.get(0)*x- -n2.get(3)) /(n2.get(2)-n1.get(2)*n2.get(1)/n1.get(1));
        //double z=((-n2.get(1/n1.get(1)))*n1.get(3)-n2.get(3))/((n2.get(2)*n2.get(1)/n1.get(1))*n1.get(2));
        //double y=(-n1.get(3)-n1.get(2)*z)/n1.get(1);
        System.out.println("x: " + x + " y: " + y + " z: " + z);
        Point3D point1 = new Point3D(x, y, z);
        return point1;
    }

    public  String st(int i) {
        switch (i) {
            case 1:
                String st = n1.get(0).toString() + "x +" + n1.get(1).toString() + "y +" + n1.get(3).toString() + "z =" + n1.get(4).toString();
                return st;
            case 2:
                String st2 = n2.get(0).toString() + "x +" + n2.get(1).toString() + "y +" + n2.get(3).toString() + "z =" + n2.get(4).toString();
                return st2;
        }
        return null;
    }

    public static double checkzeros(ArrayList<TextField> f) {
        int n = 0;

        while (n != 6) {
            int j = 2 * n;
            if (j < f.size()) {
                if (f.get(j).getText().toString().equals("0")) {

                    double x=Double.parseDouble(f.get(j + 1).getText().toString());
                    if(n==0||n==3 ) {

                    }

                    if(n==1||n==4 ) {

                    }
                    if(n==2||n==5 ) {

                    }

                    return x;
                } else {
                    n++;
                }
            }
        }
        return 0;
    }


    //Lines

    private static final double EPSILON = 1e-10;
    double a1, a2; // Row 1
    double b1, b2; // Row 2
    double d1, d2; // Last Column
    int n;

    // storing arraylist variables for the SLE calculation 2x2
    //A matrix storage
    public static double[][] matrixA_2x2 = {{1, 1}, {1, 1}};
    //B matrix storage
    public static double[] matrixB_2x2 = {1, 1};

    public Model3(ArrayList<Double> input,ArrayList<Double>constant) {
            n = 2;
            // A matrix for 2x2
            //Row 1
            this.a1 = input.get(0);
            matrixA_2x2[0][0] = this.a1;
            this.a2 = input.get(1);
            matrixA_2x2[0][1] = this.a2;

            //Row2
            this.b1 = input.get(2);
            matrixA_2x2[1][0] = this.b1;
            this.b2 = input.get(3);
            matrixA_2x2[1][1] = this.b2;

            //B Matrix (Constants)
            this.d1 = constant.get(0);
            matrixB_2x2[0] = this.d1;
            this.d2 = constant.get(1);
            matrixB_2x2[1] = this.d2;

        }

    public static double[][] getMatrixA_2x2() {
        return matrixA_2x2;
    }

    public static double[] getMatrixB_2x2() {
        return matrixB_2x2;
    }

    // Gaussian elimination with partial pivoting
    public double[] SLESolve(double[][] A, double[] b) {
        n = b.length;

        for (int p = 0; p < n; p++) {

            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p];
            A[p] = A[max];
            A[max] = temp;
            double t = b[p];
            b[p] = b[max];
            b[max] = t;

            // singular or almost singular
            if (Math.abs(A[p][p]) <= EPSILON) {
                System.out.println("Matrix is singular or super close to being singular, try again :) ");
            }

            // pivot within A and b
            for (int i = p + 1; i < n; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        // back substitution
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }








}