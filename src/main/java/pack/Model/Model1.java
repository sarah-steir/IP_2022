

package pack.Model;

import javafx.geometry.Point3D;

import java.text.DecimalFormat;
import java.util.ArrayList;
public class Model1 {
    private static final double EPSILON = 1e-10;

    final DecimalFormat formatting = new DecimalFormat("0.000");// format the number to 3 decimals
    double a1, a2, a3; // Row 1
    double b1, b2, b3; // Row 2
    double c1, c2, c3; // Row 3
    double d1, d2, d3; // Last Column
    int n;

//Ax=B

    // storing arraylist variables for the SLE calculation 2x2
    //A matrix storage
    public static double[][] matrixA_2x2 = {{1, 1}, {1, 1}};
    //B matrix storage
    public static double[] matrixB_2x2 = {1, 1};

    // storing arraylist variables for the SLE calculation 3x3
    //A matrix storage
    public static double[][] matrixA_3x3 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    //B matrix storage
    public static double[] matrixB_3x3 = {1, 1, 1};

    //Storing A matrix variables
    public Model1(ArrayList<Double> matrixOfCoefficients, boolean is2by2) {
        if (is2by2) {
            n =2;
            // A matrix for 2x2
            //Row 1
            this.a1 = matrixOfCoefficients.get(0);
            matrixA_2x2[0][0] = this.a1;
            this.a2 = matrixOfCoefficients.get(1);
            matrixA_2x2[0][1] = this.a2;

            //Row2
            this.b1 = matrixOfCoefficients.get(2);
            matrixA_2x2[1][0] = this.b1;
            this.b2 = matrixOfCoefficients.get(3);
            matrixA_2x2[1][1] = this.b2;

            //B Matrix
            this.d1 = matrixOfCoefficients.get(4);
            matrixB_2x2[0] = this.d1;
            this.d2 = matrixOfCoefficients.get(5);
            matrixB_2x2[1] = this.d2;

//            //making sure im getting the right numbers for A lmao 2x2
//            for (int i = 0; i < 2; i++)
//                for (int j = 0; j < 2; j++)
//                    System.out.println("arr2A[" + i + "][" + j + "] = "
//                            + matrixA_2x2[i][j]);
//            //making sure im getting the right numbers for B lmao 2x2
//            for (int i = 0; i < 2; i++)
//                for (int j = 0; j < 1; j++)
//                    System.out.println("arr2B[" + i + "][" + j + "] = "
//                            + matrixB_2x2[i]);

            System.out.println(matrixOfCoefficients);

        } else {

            n = 3;
            System.out.println(matrixOfCoefficients.toString());
            // A matrix for 3x3
            //Row 1
            this.a1 = matrixOfCoefficients.get(0);
            matrixA_3x3[0][0] = this.a1;
            this.a2 = matrixOfCoefficients.get(1);
            matrixA_3x3[0][1] = this.a2;
            this.a3 = matrixOfCoefficients.get(2);
            matrixA_3x3[0][2] = this.a3;

            //Row 2
            this.b1 = matrixOfCoefficients.get(3);
            matrixA_3x3[1][0] = this.b1;
            this.b2 = matrixOfCoefficients.get(4);
            matrixA_3x3[1][1] = this.b2;
            this.b3 = matrixOfCoefficients.get(5);
            matrixA_3x3[1][2] = this.b3;

            //Row 3
            this.c1 = matrixOfCoefficients.get(6);
            matrixA_3x3[2][0] = this.c1;
            this.c2 = matrixOfCoefficients.get(7);
            matrixA_3x3[2][1] = this.c2;
            this.c3 = matrixOfCoefficients.get(8);
            matrixA_3x3[2][2] = this.c3;


            //B Matrix
            this.d1 = matrixOfCoefficients.get(9);
            matrixB_3x3[0] = this.d1;
            this.d2 = matrixOfCoefficients.get(10);
            matrixB_3x3[1] = this.d2;
            this.d3 = matrixOfCoefficients.get(11);
            matrixB_3x3[2] = this.d3;


//            //making sure im getting the right numbers for A lmao 3x3
//            for (int i = 0; i < 3; i++)
//                for (int j = 0; j < 3; j++)
//                    System.out.println("arr3A[" + i + "][" + j + "] = "
//                            + matrixA_3x3[i][j]);
//            //making sure im getting the right numbers for B lmao 3x3
//            for (int i = 0; i < 3; i++)
//                for (int j = 0; j < 1; j++)
//                    System.out.println("arr3B[" + i + "][" + j + "] = "
//                            + matrixB_3x3[i]);


        }

    }

    public static double[][] getMatrixA_2x2() {
        return matrixA_2x2;
    }

    public static double[] getMatrixB_2x2() {
        return matrixB_2x2;
    }

    public static double[][] getMatrixA_3x3() {
        return matrixA_3x3;
    }

    public static double[] getMatrixB_3x3() {
        return matrixB_3x3;
    }



// SLE SOLVER

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

//
//    public Point3D solutionPoints(int i) {
//
//        double x = i;
//        double z = ((n2.get(1) / n1.get(1)) * (n1.get(0) * x + n1.get(3)) - n2.get(0) * x - n2.get(3)) / (n2.get(2) - n1.get(2) * n2.get(1) / n1.get(1));
//        double y = (-n1.get(2) * z - n1.get(0) * x - n1.get(3)) / n1.get(1);
//        //double z= ((-n2.get(1)/n1.get(1))*(n1.get(0)*x+n1.get(3))-n2.get(0)*x- -n2.get(3)) /(n2.get(2)-n1.get(2)*n2.get(1)/n1.get(1));
//        //double z=((-n2.get(1/n1.get(1)))*n1.get(3)-n2.get(3))/((n2.get(2)*n2.get(1)/n1.get(1))*n1.get(2));
//        //double y=(-n1.get(3)-n1.get(2)*z)/n1.get(1);
//        System.out.println("x: " + x + " y: " + y + " z: " + z);
//        Point3D point1 = new Point3D(x, y, z);
//        return point1;
//    }

    /**
     * This function returns a
     * @param i random number to plug as x-value
     * @return
     */
    public Point3D solutionPoints(int i) {
        double y = (d1 - a1*i)/a2;
        return new Point3D(i, y, 0);
    }
}
