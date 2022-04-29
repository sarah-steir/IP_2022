package pack.Model;

import javafx.geometry.Point3D;
import javafx.scene.text.Text;
import pack.View.Customs.CustomText;
import pack.View.Customs.CustomTextField;

import java.util.ArrayList;

public class Model3 {

    //Planes
    public double[] n1 = new double[4];
    public double[] n2 = new double[4];
    double crossProduct[] = new double[3];
    public double[] x = new double[2];
    public Point3D[] solutions = new Point3D[2];
    private ArrayList<Double> numbers = new ArrayList<>();
    public ArrayList<CustomText> labels = new ArrayList<>();

    /**
     * Empty constructor for the planes
     */
    public Model3() {
    }

    /**
     * The function divides the textfields into 2 groups (for plane 1 and for plane 2) and stores the double value of the text on
     * their respective double arrayList
     *
     * @param f The ArrayList that contains the Textfields on the plane section
     */
    public void transform(ArrayList<CustomTextField> f) {
        n1 = new double[]{0, 0, 0, 0};
        n2 = new double[]{0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            Double d = Double.parseDouble(f.get(i).getText());
            n1[i] = d;
        }
        for (int j = 4; j < f.size(); j++) {
            Double d2 = Double.parseDouble(f.get(j).getText());
            n2[j - 4] = d2;
        }
    }

    //TODO GCD of the elements of the cross product

    /**
     * Calculates the direction vector of the string
     *
     * @return an array with the direction vector (not simplified)
     */

    public double[] crossProduct(double[] n1, double[] n2) {
        crossProduct[0] = n1[1] * n2[2] - n1[2] * n2[1];
        crossProduct[1] = n1[2] * n2[0] - n1[0] * n2[2];
        crossProduct[2] = n1[0] * n2[1] - n1[1] * n2[0];
        return crossProduct;
    }

    public void solutionPoint() {
        double determinantWithZis0 = n1[0] * n2[1] - n2[0] * n1[1];
        double determinantXwithZis0 = -n1[3] * n2[1] - -n2[3] * n1[1];
        double determinantYwithZis0 = n1[0] * -n2[3] - n2[0] * -n1[3];
        double x1 = determinantXwithZis0 / determinantWithZis0;
        double y1 = determinantYwithZis0 / determinantWithZis0;
        double z1 = 0;
        solutions[0] = new Point3D(x1, y1, z1);
        double determinantWithYis0 = n1[0] * n2[2] - n2[0] * n1[2];
        double determinantXwithYis0 = -n1[3] * n2[2] - -n2[3] * n1[2];
        double determinantZwithYis0 = n1[0] * -n2[3] - n2[0] * -n1[3];
        double x2 = determinantXwithYis0 / determinantWithYis0;
        double y2 = 0;
        double z2 = determinantZwithYis0 / determinantWithYis0;
        solutions[1] = new Point3D(x2, y2, z2);
    }

    /**
     * @param i wether one or two
     * @return the equation of the plane given by i (1 or 2)
     */

    public String planeEq(int i) {
        switch (i) {
            case 1:
                String st = n1[0] + "x +" + n1[1] + "y +" + n1[2] + "z = -" + n1[3];
                return st;
            case 2:
                String st2 = n2[0] + "x +" + n2[1] + "y +" + n2[2] + "z = -" + n2[3];
                return st2;
        }
        return null;
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


    public void setThingies() {
        n = 2;
        //Row 1
        this.a1 = inputs().get(0);
        matrixA_2x2[0][0] = this.a1;
        this.a2 = inputs().get(1);
        matrixA_2x2[0][1] = this.a2;

        //Row2
        this.b1 = inputs().get(2);
        matrixA_2x2[1][0] = this.b1;
        this.b2 = inputs().get(3);
        matrixA_2x2[1][1] = this.b2;

        //B Matrix (Constants)
        this.d1 = constants().get(0);
        matrixB_2x2[0] = this.d1;
        this.d2 = constants().get(1);
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


    /**
     * @return the point of intersection between the two lines (if they intersect)
     */
    public Point3D intersectionLines() {
        double[][] A = Model3.getMatrixA_2x2();
        double[] b = Model3.getMatrixB_2x2();
        x = SLESolve(A, b);
        double xpoint = numbers.get(0) * x[0] + numbers.get(1);
        double ypoint = numbers.get(2) * x[0] + numbers.get(3);
        double zpoint = numbers.get(4) * x[0] + numbers.get(5);

        Point3D solution = new Point3D(xpoint, ypoint, zpoint);

        return solution;
    }

    /**
     * @param i wether 1 or 2, for line 1 or line 2
     * @param t The value  that the direction vector will be multiplied by to find a point in the line i (x=1+3*t)
     * @return a point in the line i
     */
    public Point3D linesPoints(int i, int t) {
        double xpoint = 0;
        double ypoint = 0;
        double zpoint = 0;

        switch (i) {
            case 1:
                xpoint = numbers.get(0) * t + numbers.get(1);
                ypoint = numbers.get(2) * t + numbers.get(3);
                zpoint = numbers.get(4) * t + numbers.get(5);
                return new Point3D(xpoint, ypoint, zpoint);

            case 2:
                xpoint = numbers.get(6) * t + numbers.get(7);
                ypoint = numbers.get(8) * t + numbers.get(9);
                zpoint = numbers.get(10) * t + numbers.get(11);
                return new Point3D(xpoint, ypoint, zpoint);
        }

        return new Point3D(xpoint, ypoint, zpoint);
    }


    public double[] dirVector(int i) {
        double[] direction = new double[3];
        switch (i) {
            case 1:
                direction[0] = numbers.get(0);
                direction[1] = numbers.get(2);
                direction[2] = numbers.get(4);
                return direction;

            case 2:
                direction[0] = numbers.get(6);
                direction[1] = numbers.get(8);
                direction[2] = numbers.get(10);
                return direction;
        }
        return null;
    }


    public boolean det() {
        double[][] d = {{numbers.get(7) - numbers.get(1), numbers.get(9) - numbers.get(3), numbers.get(11) - numbers.get(5)},
                {numbers.get(0), numbers.get(2), numbers.get(4)},
                {numbers.get(6), numbers.get(8), numbers.get(10)}};
        double sum = d[0][0] * ((d[1][1] * d[2][2]) - (d[1][2] * d[2][1])) -
                d[0][1] * ((d[1][0] * d[2][2]) - (d[1][2] * d[2][0])) +
                d[0][2] * ((d[1][0] * d[2][1]) - (d[2][0] * d[1][1]));

        if (sum == 0) {
            return true;
        } else {
            return false;
        }
    }


    public void bringT(ArrayList<CustomTextField> tf) {
        numbers.clear();
        for (int i = 0; i < 12; i++) {
            Double d = Double.parseDouble(tf.get(i).getText());
            numbers.add(d);
        }
    }


    public boolean parallel() {
        if (numbers.get(0) / numbers.get(6) == numbers.get(2) / numbers.get(8)) {
            if (numbers.get(4) / numbers.get(10) == numbers.get(0) / numbers.get(6)) {
                return true;
            }
        }
        return false;
    }


    public ArrayList<Double> constants() {
        int n = 1;
        ArrayList<Double> constant = new ArrayList<>();

        double d1 = numbers.get(1);
        double d2 = numbers.get(7);
        double d3 = numbers.get(3);
        double d4 = numbers.get(9);
        constant.add(d1 - d2);
        constant.add(d3 - d4);

        return constant;
    }

    public ArrayList<Double> inputs() {
        ArrayList<Double> arr = new ArrayList<>();
        double d1 = numbers.get(0);
        double d2 = numbers.get(2);
        double d3 = numbers.get(6);
        double d4 = numbers.get(8);

        arr.add(-d1);
        arr.add(d3);
        arr.add(-d2);
        arr.add(d4);
        return arr;
    }


    public String distanceSkew() {
        double[] v = new double[3];
        v[0] = numbers.get(7) - numbers.get(1);
        v[1] = numbers.get(9) - numbers.get(3);
        v[2] = numbers.get(11) - numbers.get(5);
        double[] n1 = new double[3];
        n1[0] = numbers.get(0);
        n1[1] = numbers.get(2);
        n1[2] = numbers.get(4);

        double[] n2 = new double[3];
        n2[0] = numbers.get(6);
        n2[1] = numbers.get(8);
        n2[2] = numbers.get(10);

        double[] d = crossProduct(n1, n2);
        double upper = Math.abs(v[0] * d[0] + v[1] * d[1] + v[2] * d[2]);
        ;
        double lower = Math.sqrt(d[0] * d[0] + d[1] * d[1] + d[2] * d[2]);

        double distance = upper / lower;
        return String.valueOf(distance);

    }

    public double[] getCrossProduct() {
        return crossProduct;
    }
}