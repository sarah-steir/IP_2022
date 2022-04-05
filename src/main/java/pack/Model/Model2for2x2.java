package pack.Model;
import java.text.DecimalFormat;

public class Model2for2x2 {

    private double x1, x2;// eigenvalues
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
    static double[] s1;//eigenvectors
    static double[] s2;

    static final DecimalFormat formatting = new DecimalFormat("0.000");// format the number to 3 decimals

    public Model2for2x2(double a1, double a2, double b1, double b2) {
        this.a1 = a1;
        this.a2 = a2;
        this.b1 = b1;
        this.b2 = b2;
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
        //substraction
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
                e1 = Double.parseDouble(formatting.format(a1));// E holds the number
                e2 = Double.parseDouble(formatting.format(a2));
                a1 = Double.parseDouble(formatting.format(b1));
                a2 = Double.parseDouble(formatting.format(b2));
                b1 = Double.parseDouble(formatting.format(e1));
                b2 = Double.parseDouble(formatting.format(e2));
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

    private double[] findEigenVectors2x2(double[] v1) {
        double[] y1 = new double[2]; // vector can max have 2 positions
        int counterUp = 0;
        for (int i = 0; i < v1.length; i++) {// loop until we went through all the numbers in the matrix  SHOULD GO THROUGH 2 TIME CUZ 2 ROWS
            double t11 = Double.parseDouble(formatting.format(v1[i]));// T11 = FIRST NUMBER IN ROW T22= SECOND NUMBER IN ROW
            double t22 = Double.parseDouble(formatting.format(v1[i + 1]));
            if (t11 != 0 && t22 != 0) {// if the whole row has numbers ( which means other row is zero
                y1[0] = -t22;
                y1[1] = 1; // second number is 1 cuz its was the free number
                return y1;
            } else {
                if ((t11 == 0 || t22 == 0) && (t11 != 0 || t22 != 0)) { //one 0 & one 1
                    if (t11 == 0) {
                        y1[counterUp] = 0; // will put zero on the first number in the vector
                    }
                    if (t22 == 0) {
                        y1[counterUp] = 0;  // will put zero in the second number in the vector
                    }
                }
                if (t11 == 0 && t22 == 0) { // if they both zero then one of em is free if first row firsgt number is free if second row second number in vector is free
                    y1[counterUp] = 1;
                }
            }
            counterUp++;
            i++;
        }
        return y1;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getFirst() {
        return first;
    }

    public void setFirst(double first) {
        this.first = first;
    }

    public double getSecond() {
        return second;
    }

    public void setSecond(double second) {
        this.second = second;
    }

    public double getThird() {
        return third;
    }

    public void setThird(double third) {
        this.third = third;
    }

    public double getE1() {
        return e1;
    }

    public void setE1(double e1) {
        this.e1 = e1;
    }

    public double getE2() {
        return e2;
    }

    public void setE2(double e2) {
        this.e2 = e2;
    }

    public double getA1() {
        return a1;
    }

    public void setA1(double a1) {
        this.a1 = a1;
    }

    public double getA2() {
        return a2;
    }

    public void setA2(double a2) {
        this.a2 = a2;
    }

    public double getB1() {
        return b1;
    }

    public void setB1(double b1) {
        this.b1 = b1;
    }

    public double getB2() {
        return b2;
    }

    public void setB2(double b2) {
        this.b2 = b2;
    }

    public double getA1Initial() {
        return a1Initial;
    }

    public void setA1Initial(double a1Initial) {
        this.a1Initial = a1Initial;
    }

    public double getA2Initial() {
        return a2Initial;
    }

    public void setA2Initial(double a2Initial) {
        this.a2Initial = a2Initial;
    }

    public double getB1Initial() {
        return b1Initial;
    }

    public void setB1Initial(double b1Initial) {
        this.b1Initial = b1Initial;
    }

    public double getB2Initial() {
        return b2Initial;
    }

    public void setB2Initial(double b2Initial) {
        this.b2Initial = b2Initial;
    }

    public double[] getM1() {
        return m1;
    }

    public void setM1(double[] m1) {
        this.m1 = m1;
    }

    public double[] getM2() {
        return m2;
    }

    public void setM2(double[] m2) {
        this.m2 = m2;
    }

    public static double[] getS1() {
        return s1;
    }

    public static void setS1(double[] s1) {
        Eigen2x2.s1 = s1;
    }

    public static double[] getS2() {
        return s2;
    }

    public static void setS2(double[] s2) {
        Eigen2x2.s2 = s2;
    }
}