package pack.Controller;

import javafx.geometry.Point3D;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class V3Controller {
    static int n = 3;
    public static ArrayList<Double> n1 = new ArrayList<Double>();
    public static ArrayList<Double> n2 = new ArrayList<Double>();
    static double crossProduct[] = new double[3];

    public static void transform(ArrayList<TextField> f) {
        int b=0;





        for (int i = 0; i < 4; i++) {
            Double d = Double.parseDouble(f.get(i).getText());
            n1.add(d);

        }
        for (int j = 4; j < f.size(); j++) {
            Double d2 = Double.parseDouble(f.get(j).getText());
            n2.add(d2);

        }
    }

    public static void crossProduct() {
        crossProduct[0] = n1.get(1) * n2.get(2) - n1.get(2) * n2.get(1);
        crossProduct[1] = n1.get(2) * n2.get(0) - n1.get(0) * n2.get(2);
        crossProduct[2] = n1.get(0) * n2.get(1) - n1.get(1) * n2.get(0);

        for (int i = 0; i < 3; i++) {
            System.out.print(crossProduct[i] + " ");
        }
    }

    public static Point3D point(int i) {

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

  /*  public static String st(int i) {
        switch (i) {
            case 1:
                String st = n1.get(0).toString() + "x +" + n1.get(1).toString() + "y +" + n1.get(3).toString() + "z =" + n1.get(4).toString();
                return st;
            case 2:
                String st2 = n2.get(0).toString() + "x +" + n2.get(1).toString() + "y +" + n2.get(3).toString() + "z =" + n2.get(4).toString();
                return st2;
        }
        return null;
    }*/


  /*  public static double checkzeros(ArrayList<TextField> f) {
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
    }*/



    public static void linesMath( double[][] m, double[][] c) {
        double[][] mat = m;
        double[][] constants = c;

        double inverted_mat[][] = invertMatrix(mat);
        for (int i = 0; i < 1; ++i) {
            for (int j = 0; j < 2; ++j) {
            }
        }
        //Multiplying inverse and constants
        double result[][] = new double[2][1];
        for (int i = 0; i < 2; i++) {

                for (int k = 0; k < 2; k++) {
                    result[i][0] = result[i][0] + inverted_mat[i][k] * constants[k][0];

                }

        }


        System.out.println("result" +result[0][0]);
        System.out.println("result" +result[1][0]);
    }


    public static double[][] invertMatrix(double a[][]) {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;
        // Transform the matrix into an upper triangle
        gaussianElim(a, index);
        // Update the matrix b[i][j] with the ratios stored
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i] * b[index[i]][k];
        // Perform backward substitutions
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }


    // Method to carry out the partial-pivoting Gaussian elimination.  Here index[] stores the order in which the rows of the matrix are reduced
    //eliminates methodically based on pivoting order
    public static void gaussianElim(double a[][], int index[]) {

        int n = index.length;
        double c[] = new double[n];


        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;


        // Find the rescaling factors, one from each row( factoring out (4 6 8) is equal to 2(2 3 4) )
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
        // Search the pivoting element from each column(making the elements below or above a leading 1 a 0)
        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }
            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];
                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;
                // Modify other elements accordingly
                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }



}