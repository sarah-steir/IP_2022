package pack.Model;

import java.util.ArrayList;


public class Model2for2x2 {

    private static double x1, x2; // EigenValues
    private double first, second, third;//square function

    private final double a1Initial, a2Initial;// hold initial value first row
    private final double b1Initial, b2Initial;// hold initial value second row

    double[] m1;// reduced matrix
    double[] m2;
    static ArrayList<Double> s1;//eigenvectors
    static ArrayList<Double> s2;

    /**
     * will find the eigenvector and eigenvalue for a 3x3 matrix
     *
     * @param matrixCoefficients CONTAINS THE NUMBERS FROM THE TEXT FIELDS
     */
    public Model2for2x2(ArrayList<Double> matrixCoefficients) {
        double a1 = matrixCoefficients.get(0);
        //first line
        double a2 = matrixCoefficients.get(1);
        double b1 = matrixCoefficients.get(2);
        //second line
        double b2 = matrixCoefficients.get(3);
        this.a1Initial = a1;
        this.a2Initial = a2;
        this.b1Initial = b1;
        this.b2Initial = b2;
        findTheCubicEquation2x2(a1, a2, b1, b2);
        roots2x2(first, second, third);
        answers2x2(x1, x2);
    }

    /**
     * get the polynomial equation
     *
     * @param a1 first row and first column
     * @param a2 second row first column
     * @param b1 first row second column
     * @param b2 second row second column
     */
    private void findTheCubicEquation2x2(double a1, double a2, double b1, double b2) {
        //addiction
        double a1b2 = a1 * b2;

        //subtraction
        double a2b1 = -a2 * b1;

        third = a1b2 + a2b1; // ^1
        second = -a1 - b2; // ^2
        first = 1; // ^3
    }

    /**
     * get the root for the polynomial ROOTS ARE THE EIGENVALUES
     *
     * @param a number which is before x^2
     * @param b umber which is before x
     * @param c last number
     */
    private void roots2x2(double a, double b, double c) {

        double squareRoot = (b * b) - (4 * a * c); // number in the square root
        if (squareRoot > 0) { // if # in the square root than it exist
            x1 = (-b + Math.sqrt(squareRoot)) / (2 * a);
            x2 = (-b - Math.sqrt(squareRoot)) / (2 * a);
        } else { // square root no exist
            x1 = (-b) / (2 * a);
            x2 = (-b) / (2 * a);
        }
        x1 = Double.parseDouble(String.valueOf(x1)); // format
        x2 = Double.parseDouble(String.valueOf(x2));
    }

    /**
     * this is where u get all the answers
     *
     * @param x1 first eigenvalue
     * @param x2 second eigenvalue
     */
    private void answers2x2(double x1, double x2) {
        double aa1 = a1Initial - x1;//WITH FIRST LAMBDA
        double bb2 = b2Initial - x1;//WITH FIRST LAMBDA
        m1 = reduceMatrix2x2(aa1, a2Initial, b1Initial, bb2); // reduce matrix
        s1 = findEigenVectors2x2(m1); // vector
        aa1 = a1Initial - x2;//WITH FIRST LAMBDA
        bb2 = b2Initial - x2;//WITH FIRST LAMBDA
        m2 = reduceMatrix2x2(aa1, a2Initial, b1Initial, bb2);
        s2 = findEigenVectors2x2(m2);
    }

    /**
     * reduce the matrix the best as possible
     *
     * @param a1 first row first column
     * @param a2 second row first column
     * @param b1 first row second column
     * @param b2 second row second column
     * @return the reduced matrix
     */
    private double[] reduceMatrix2x2(double a1, double a2, double b1, double b2) {
        if (a1 == 0) { // if a equal to zero (switch rows) unless all is zero (a,b,c)
            if (b1 != 0) { //switch a with b if b not zero
                double e1 = Double.parseDouble(String.valueOf(a1));// E holds the number
                // to hold values in the reduced matrix
                double e2 = Double.parseDouble(String.valueOf(a2));
                a1 = Double.parseDouble(String.valueOf(b1));
                a2 = Double.parseDouble(String.valueOf(b2));
                b1 = Double.parseDouble(String.valueOf(e1));
                b2 = Double.parseDouble(String.valueOf(e2));
            }
        }

        if (a1 != 1 && a1 != 0) { //if a not equal to one or zero
            a2 = Double.parseDouble(String.valueOf(a2 / a1));//MAKE THE FIRST NUMBER 1 AND THEN DIVIDE ALL THE NUMBERS IN THAT row
            a1 = Double.parseDouble(String.valueOf(1.0));
        }

        if (b1 != 0) { // reduce the second row to 0 in the first #
            b2 = Double.parseDouble(String.valueOf(b2 - (a2 * b1)));//MAKE THE FIRST NUMBER 0 AND THEN subtract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            b1 = Double.parseDouble(String.valueOf(b1 - (a1 * b1)));
        }

        //COLUMN ONE DONE
        if (b2 != 1 && b2 != 0) { //if a not equal to one or zero
            b2 = Double.parseDouble(String.valueOf(1.0));//MAKE THE FIRST NUMBER 1
        }

        if (b2 != 0 && a2 != 0) { // reduce the second row to 0 in the first #
            a2 = Double.parseDouble(String.valueOf(a2 - (a2 * b2)));//MAKE THE FIRST NUMBER 0
        }
        return new double[]{a1, a2, b1, b2};
    }

    /**
     * get the eigenvectors
     *
     * @param v1 the reduced matrix
     * @return the eigenvectors
     */
    private ArrayList<Double> findEigenVectors2x2(double[] v1) {
        ArrayList<Double> y1 = new ArrayList<>(); // vector can max have 2 positions
        y1.add(0, 0.0);
        y1.add(1, 0.0);

        if (v1[0] == 0 && v1[1] == 0 && v1[2] == 0 && v1[3] == 0) {
            y1.set(0, 1.0);
            y1.set(1, 0.0);
            y1.add(2, 0.0);
            y1.add(3, 1.0);}
        if (v1[0] != 0 && v1[1] == 0 && v1[2] == 0 && v1[3] == 0) {
            y1.set(0, 0.0);
            y1.set(1, 1.0);}
        if (v1[0] == 0 && v1[1] == 0 && v1[2] == 0 && v1[3] != 0) {
            y1.set(0, 1.0);
            y1.set(1, 0.0);}
        if (v1[0] != 0 && v1[1] != 0 && v1[2] == 0 && v1[3] == 0) {
            y1.set(0, -v1[1]);
            y1.set(1, 1.0);

        }
        if (v1[0] == 1 && v1[1] == 0 && v1[2] == 0 && v1[3] == 1) {
            y1.set(0, 0.0);
            y1.set(1, 0.0);

        }
        return y1;
    }

    /**
     * get the final eigenvalues
     *
     * @return the final eigenvalues
     */
    public double[] getEigenValues() {
        double[] eigenValues = new double[2];
        eigenValues[0] = x1;
        eigenValues[1] = x2;
        return eigenValues;
    }

    /**
     * get the final eigenvectors
     *
     * @return the final eigen vectors
     */
    public ArrayList<Double>[] getEigenVectors() {
        ArrayList[] eigenVectors = new ArrayList[2];
        eigenVectors[0] = s1;
        eigenVectors[1] = s2;
        return eigenVectors;
    }
}
