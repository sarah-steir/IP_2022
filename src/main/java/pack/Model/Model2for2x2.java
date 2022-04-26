package pack.Model;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Model2for2x2 {

    private static double x1;
    private static double x2;// eigenvalues
    private double first, second, third;//square function
    private double e1, e2;// to hold values in the reduce matrix
    private double a1, a2;//first line
    private double b1, b2;//second line

    private double a1Initial;// hold initiql value first row
    private double a2Initial;
    private double b1Initial;// hold initial value second row
    private double b2Initial;

    double m1[];// reduced matrix
    double m2[];
    static ArrayList<Double> s1;//eigenvectors
    static ArrayList<Double> s2;

    static final DecimalFormat formatting = new DecimalFormat("0.000");// format the number to 3 decimals

    public Model2for2x2(ArrayList<Double> matrixCoefficients) {
        this.a1 = matrixCoefficients.get(0);
        this.a2 = matrixCoefficients.get(1);
        this.b1 = matrixCoefficients.get(2);
        this.b2 = matrixCoefficients.get(3);
        this.a1Initial = a1;
        this.a2Initial = a2;
        this.b1Initial = b1;
        this.b2Initial = b2;
        findTheCubicEquation2x2(a1, a2, b1, b2);
        roots2x2(first, second, third);
        answers2x2(a1, a2, b1, b2, x1, x2);
    }

    private void findTheCubicEquation2x2(double a1, double a2, double b1, double b2) {
        //addiction
        double a1b2 = a1 * b2;

        //subtraction
        double a2b1 = -a2 * b1;

        third = a1b2 + a2b1; // ^1
        second = -a1 + -b2; // ^2
        first = 1; // ^3
    }

    private void roots2x2(double a, double b, double c) {

        double squareRoot = (b * b) - (4 * a * c); // numnber in the square root
        if (squareRoot > 0) { // if # in the square root than it exist
            x1 = (-b + Math.sqrt(squareRoot)) / (2 * a);
            x2 = (-b - Math.sqrt(squareRoot)) / (2 * a);
        } else { // square root no exist
            x1 = (-b) / (2 * a);
            x2 = (-b) / (2 * a);
        }
        x1 = Double.parseDouble(formatting.format(x1)); // format
        x2 = Double.parseDouble(formatting.format(x2));
    }
//SEND THIS TO DE CONTROLLA
    private void answers2x2(double a1, double a2, double b1, double b2, double x1, double x2) {
        double aa1 = a1Initial - x1;//WITH FIRST LAMBA
        double bb2 = b2Initial - x1;//WITH FIRST LAMBA
        m1 = reduceMatrix2x2(aa1, a2Initial, b1Initial, bb2); // reduce matrix
        s1 = findEigenVectors2x2(m1); // vector
        aa1 = a1Initial - x2;//WITH FIRST LAMBA
        bb2 = b2Initial - x2;//WITH FIRST LAMBA
        m2 = reduceMatrix2x2(aa1, a2Initial, b1Initial, bb2);
        s2 = findEigenVectors2x2(m2);
    }

    private double[] reduceMatrix2x2(double a1, double a2, double b1, double b2) {
        if (a1 == 0) { // if a equal to zero (switch rows) unless all is zero (a,b,c)
            if (b1 != 0) { //switch a with b if b not zero
                e1 = Double.parseDouble(String.valueOf(a1));// E holds the number
                e2 = Double.parseDouble(String.valueOf(a2));
                a1 = Double.parseDouble(String.valueOf(b1));
                a2 = Double.parseDouble(String.valueOf(b2));
                b1 = Double.parseDouble(String.valueOf(e1));
                b2 = Double.parseDouble(String.valueOf(e2));
            }
        }

        if (a1 != 1 && a1 != 0) { //if a not equal to one or zero
            a2 = Double.parseDouble(formatting.format(a2 / a1));//MAKE THE FIRST NUMBER 1 AND THEN DIVIDE ALL THE NUMBERS IN THAT row
            a1 = Double.parseDouble(formatting.format(a1 / a1));
        }

        if (b1 != 0) { // reduce the second row to 0 in the first #
            b2 = Double.parseDouble(formatting.format(b2 - (a2 * b1)));//MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            b1 = Double.parseDouble(formatting.format(b1 - (a1 * b1)));
        }

        //COLUMN ONE DONE
        if (b2 != 1 && b2 != 0) { //if a not equal to one or zero
            b2 = Double.parseDouble(formatting.format(b2 / b2));//MAKE THE FIRST NUMBER 1
        }

        if (b2 != 0 && a2 != 0) { // reduce the second row to 0 in the first #
            a2 = Double.parseDouble(formatting.format(a2 - (a2 * b2)));//MAKE THE FIRST NUMBER 0
        }
        double arr[] = {a1, a2, b1, b2}; // reduced matrix
        return arr;
    }

    private ArrayList<Double> findEigenVectors2x2(double[] v1) {
        ArrayList<Double> y1= new ArrayList<>(); // vector can max have 2 positions
        y1.add(0,0.0);
        y1.add(1,0.0);

        if(v1[0]==0 && v1[1]==0 && v1[2]==0 && v1[3]==0 ){
            y1.set(0,1.0);
            y1.set(1,0.0);
            y1.add(2,0.0);
            y1.add(3,1.0);
        }
        if(v1[0]!=0 && v1[1]==0 && v1[2]==0 && v1[3]==0 ){
            y1.set(0,0.0);
            y1.set(1,1.0);

        }
        if(v1[0]==0 && v1[1]==0 && v1[2]==0 && v1[3]!=0 ){
            y1.set(0,1.0);
            y1.set(1,0.0);

        }
        if(v1[0]!=0 && v1[1]!=0 && v1[2]==0 && v1[3]==0 ){
            y1.set(0,-v1[1]);
            y1.set(1,1.0);

        }
        if(v1[0]==1 && v1[1]==0 && v1[2]==0 && v1[3]==1 ){
            y1.set(0,0.0);
            y1.set(1,0.0);

        }
        return y1;
    }

    public double[] getEigenValues() {
        double[] eigenValues = new double[2];
        eigenValues[0] = x1;
        eigenValues[1] = x2;
        return eigenValues;
    }

    public ArrayList<Double>[] getEigenVectors() {
        ArrayList<Double>[] eigenVectors = new ArrayList[2];
        eigenVectors[0] = s1;
        eigenVectors[1] = s2;
        return eigenVectors;
    }
}
