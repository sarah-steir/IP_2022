package pack.Model;
import java.text.DecimalFormat;

public class Model2for3x3 {

    // idk if i need all of this
    private static double x1;
    private static double x2;
    private static double x3; // eigenvalues
    private double first, second, third, fourth; //cubic function
    private double e1, e2, e3; // to hold values in the reduce matrix
    private double a1, a2, a3; // first line of matrix
    private double b1, b2, b3;//second line
    private double c1, c2, c3;// third line
    private double a1Initial, a2Initial, a3Initial; // hold initiql value first row
    private double b1Initial, b2Initial, b3Initial;// hold initial value second row
    private double c1Initial, c2Initial, c3Initial;// hold initial value third row
    double m1[], m2[], m3[]; // reduced matrix
    static double[] s1,s2, s3;//eigenvectors

    static final DecimalFormat formatting = new DecimalFormat("0.0000"); // format the number to 4 decimals

    public Model2for3x3(double a1, double a2, double a3, double b1, double b2, double b3, double c1, double c2, double c3) {
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.a1Initial = a1;
        this.a2Initial = a2;
        this.a3Initial = a3;
        this.b1Initial = b1;
        this.b2Initial = b2;
        this.b3Initial = b3;
        this.c1Initial = c1;
        this.c2Initial = c2;
        this.c3Initial = c3;
        findTheCubicEquation3x3(a1, a2, a3, b1, b2, b3, c1, c2, c3);
        roots3x3(first, second, third, fourth);
        answers3x3(a1, a2, a3, b1, b2, b3, c1, c2, c3, x1, x2, x3);

    }

    // find the cubic equation
    private void findTheCubicEquation3x3(double a1, double a2, double a3, double b1, double b2, double b3, double c1, double c2, double c3) {
        //additions
        //SEPARATED A1*B2*C3 according to the exponent of lambda
        double a1b2c3 = (a1 * b2 * c3);
        double a1b2 = -(a1 * b2);// ^1
        double a1b2Cal1 = -(a1 + b2);// needed in further steps
        double a1b2Cal2 = (a1b2Cal1 * c3);
        double a1b2Cal3 = -(a1b2Cal1); // ^2
        double c3Cal = c3; // ^2
        double toTheCube = -1; // the lambda ^3 always neg
        // the 2 that dont have lambda in them
        double a2b3c1 = a2 * b3 * c1;
        double a3b1c2 = a3 * b1 * c2;
        //substraction
        // the ones with lambda ^1 in them
        double a1b3c2 = -(a1 * b3 * c2);
        double b3c2 = (b3 * c2);// ^1
        double b2a3c1 = -(b2 * a3 * c1);
        double a3c1 = (a3 * c1);// ^1
        double c3a2b1 = -(c3 * a2 * b1);
        double a2b1 = (a2 * b1);// ^1
        //cubic equation = ax^3+bx^2+cx^1+d -> a=first, b=second, c=third, d= fourth
        fourth = a1b2c3 + a2b3c1 + a3b1c2 + a1b3c2 + b2a3c1 + c3a2b1;
        third = a2b1 + a3c1 + b3c2 + a1b2 + a1b2Cal2; //x
        second = a1b2Cal3 + c3Cal; // x^2
        first = toTheCube; // x^3
    }

// get the roots so the eigenvalues
    private void roots3x3(double a, double b, double c, double d) {

        if (a == 0.0){
            double squareRoot = (b * b) - (4 * a * c); // numnber in the square root
            if (squareRoot > 0) { // if # in the square root than it exist
                x1 = (-b + Math.sqrt(squareRoot)) / (2 * a);
                x2 = (-b - Math.sqrt(squareRoot)) / (2 * a);
                x3 = 0.0; // because there is no cube
            } else { // square root no exist
                x1 = (-b) / (2 * a);
                x2 = (-b) / (2 * a);
                x3= 0.0;// because there is no cube
            }
        }

        double r = a;
        a = b/r;
        b = c/r;
        c = d/r;

        double SmallMath = (3 * b - a * a) / 9.0;
        double v = SmallMath * SmallMath * SmallMath;
        double bigMath = (9 * a * b - 27 * c - 2 * a * a * a) / 54.0;
        double D = v + (bigMath * bigMath);

        if (D < 0.0)// 3 different roots
        {
            double theta = Math.acos (bigMath / Math.sqrt (-v));
            double SQRT_Q = Math.sqrt (-SmallMath);
            x1 = 2.0 * SQRT_Q * Math.cos (theta/3.0) - (a/ 3.0);
            x2 = 2.0 * SQRT_Q * Math.cos ((theta+(2.0 * Math.PI))/3.0) - (a/ 3.0);
            x3 = 2.0 * SQRT_Q * Math.cos ((theta+(4.0 * Math.PI))/3.0) - (a/ 3.0);
        }
        else if (D > 0.0) // one root only
        {
            double sqrt = Math.sqrt (D);
            double S = Math.cbrt (bigMath + sqrt);
            double T = Math.cbrt (bigMath - sqrt);
            x1 = (S + T) - (a/ 3.0);
            x2 = Double.NaN;
            x3 = Double.NaN;
        }

        else // 3 roots where 2 are the same
        {
            double cubeRoot = Math.cbrt (bigMath);
            x1 = 2*cubeRoot - (a/ 3.0);
            x2 = x3 = cubeRoot - (a/ 3.0);
        }
        x1 = Double.parseDouble(formatting.format(x1)); // FORMAT TO 0.0000
        x2 = Double.parseDouble(formatting.format(x2));
        x3 = Double.parseDouble(formatting.format(x3));
    }
// find the eigenvectors and finds reduce matrix PUTS EVERYTHING TOGETHER
    private void answers3x3(double a1, double a2, double a3, double b1, double b2, double b3, double c1, double c2, double c3, double x1, double x2, double x3) {
        double aa1 = a1Initial - x1;//WITH FIRST LAMBA
        double bb2 = b2Initial - x1;//WITH FIRST LAMBA
        double cc3 = c3Initial - x1;//WITH FIRST LAMBA
        m1 = reduceMatrix3x3(aa1, a2Initial, a3Initial, b1Initial, bb2, b3Initial, c1Initial, c2Initial, cc3); // reduced matrix
        s1 = findEigenVectors3x3(m1); // find the vector

        aa1 = a1Initial - x2;//WITH FIRST LAMBA
        bb2 = b2Initial - x2;//WITH FIRST LAMBA
        cc3 = c3Initial - x2;//WITH FIRST LAMBA
        m2 = reduceMatrix3x3(aa1, a2Initial, a3Initial, b1Initial, bb2, b3Initial, c1Initial, c2Initial, cc3);
        s2 = findEigenVectors3x3(m2);

        aa1 = a1Initial - x3;//WITH FIRST LAMBA
        bb2 = b2Initial - x3;//WITH FIRST LAMBA
        cc3 = c3Initial - x3;//WITH FIRST LAMBA
        m3 = reduceMatrix3x3(aa1, a2Initial, a3Initial, b1Initial, bb2, b3Initial, c1Initial, c2Initial, cc3);
        s3 = findEigenVectors3x3(m3);

    }
// reduces the matrix
    private double[] reduceMatrix3x3(double a1, double a2, double a3, double b1, double b2, double b3, double c1, double c2, double c3) {
        a1 = Double.parseDouble(formatting.format(a1)); // format everything
        a2 = Double.parseDouble(formatting.format(a2));
        a3 = Double.parseDouble(formatting.format(a3));
        b1 = Double.parseDouble(formatting.format(b1));
        b2 = Double.parseDouble(formatting.format(b2));
        b3 = Double.parseDouble(formatting.format(b3));
        c1 = Double.parseDouble(formatting.format(c1));
        c2 = Double.parseDouble(formatting.format(c2));
        c3 = Double.parseDouble(formatting.format(c3));
        if (a1 == 0) { // if a equal to zero (switch rows) unless all is zero (a,b,c)
            if (b1 == 0) { // if second row also has zero as the first number
                if (c1 != 0) { // then switch a with c if c not equal zero
                    e1 = Double.parseDouble(formatting.format(a1)); // E holds the number
                    e2 = Double.parseDouble(formatting.format(a2));
                    e3 = Double.parseDouble(formatting.format(a3));
                    a1 = Double.parseDouble(formatting.format(c1));
                    a2 = Double.parseDouble(formatting.format(c2));
                    a3 = Double.parseDouble(formatting.format(c3));
                    c1 = Double.parseDouble(formatting.format(e1));
                    c2 = Double.parseDouble(formatting.format(e2));
                    c3 = Double.parseDouble(formatting.format(e3));
                }
            }
            if (b1 != 0) { // switch a with b if b not equal zero

                e1 = Double.parseDouble(formatting.format(a1));
                e2 = Double.parseDouble(formatting.format(a2));
                e3 = Double.parseDouble(formatting.format(a3));
                a1 = Double.parseDouble(formatting.format(b1));
                a2 = Double.parseDouble(formatting.format(b2));
                a3 = Double.parseDouble(formatting.format(b3));
                b1 = Double.parseDouble(formatting.format(e1));
                b2 = Double.parseDouble(formatting.format(e2));
                b3 = Double.parseDouble(formatting.format(e3));
            }
        }
        if (a1 != 1 && a1 != 0) { //if a not equal to one or zero

            a2 = Double.parseDouble(formatting.format(a2 / a1)); //MAKE THE FIRST NUMBER 1 AND THEN DIVIDE ALL THE NUMBERS IN THAT row
            a3 = Double.parseDouble(formatting.format(a3 / a1));
            a1 = Double.parseDouble(formatting.format(a1 / a1));
        }
        if (b1 != 0) { // reduce the second row to 0 in the first #

            b2 = Double.parseDouble(formatting.format(b2 - (a2 * b1)));//MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            b3 = Double.parseDouble(formatting.format(b3 - (a3 * b1)));
            b1 = Double.parseDouble(formatting.format(b1 - (a1 * b1)));
        }
        if (c1 != 0) { // reduce the third row to 0 in the first #

            c2 = Double.parseDouble(formatting.format(c2 - (a2 * c1)));//MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            c3 = Double.parseDouble(formatting.format(c3 - (a3 * c1)));
            c1 = Double.parseDouble(formatting.format(c1 - (a1 * c1)));
        }
        //COLUMN ONE DONE
        if (b2 == 0) { // if a equal to zero (switch rows) unless all is zero (a,b,c)
            if (c2 != 0) {
                e2 = Double.parseDouble(formatting.format(b2)); // replace b with c if c not zero
                e3 = Double.parseDouble(formatting.format(b3));
                b2 = Double.parseDouble(formatting.format(c2));
                b3 = Double.parseDouble(formatting.format(c3));
                c2 = Double.parseDouble(formatting.format(e2));
                c3 = Double.parseDouble(formatting.format(e3));
            }
            if (b2 == 0 && c2 == 0 && a1 == 0 && a2 != 0) { // if A has 0 in the first position and not zero in second position a switch with b
                e2 = Double.parseDouble(formatting.format(b2));
                e3 = Double.parseDouble(formatting.format(b3));
                b2 = Double.parseDouble(formatting.format(a2));
                b3 = Double.parseDouble(formatting.format(a3));
                a2 = Double.parseDouble(formatting.format(e2));
                a3 = Double.parseDouble(formatting.format(e3));
            }
        }
        if (b2 != 1 && b2 != 0) { //if a not equal to one or zero
            b3 = Double.parseDouble(formatting.format(b3 / b2));//MAKE THE FIRST NUMBER 1 AND THEN DIVIDE ALL THE NUMBERS IN THAT row
            b2 = Double.parseDouble(formatting.format(b2 / b2));
        }

        if (a2 != 0) { // reduce the second row to 0 in the first #
            a3 = Double.parseDouble(formatting.format(a3 - (a2 * b3))); //MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            a2 = Double.parseDouble(formatting.format(a2 - (a2 * b2)));
        }

        if (c2 != 0) { // reduce the third row to 0 in the first #
            c3 = Double.parseDouble(formatting.format(c3 - (b3 * c2))); //MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            c2 = Double.parseDouble(formatting.format(c2 - (b2 * c2)));
        }
        if(c3<0.005){
            c3=0;
        }
        // second row done
        if (c3 != 1 && c3 != 0) { //if a not equal to one or zero
            c3 = Double.parseDouble(formatting.format(c3 / c3));//MAKE THE FIRST NUMBER 1 AND THEN DIVIDE ALL THE NUMBERS IN THAT row
        }

        if (c3 != 0 && b3 != 0) { // reduce the second row to 0 in the first #
            b3 = Double.parseDouble(formatting.format(b3 - (b3 * c3)));
        }

        if (c3 != 0 && a3 != 0) { // reduce the third row to 0 in the first #
            a3 = Double.parseDouble(formatting.format(a3 - (a3 * c3)));
        }
        double arr[] = {a1, a2, a3, b1, b2, b3, c1, c2, c3}; // array to hold the matrix

        return arr;
    }
// find eigenvectors
    private double[] findEigenVectors3x3(double[] v1) {
        double[] y1 = new double[3]; // if only one vector for that one eigen value
        double[] y2 = new double[6];// if two vectors for that one eigen value
        int counterUp = 0;
        for (int i = 0; i < v1.length; i++) { // loop until we went through all the numbers in the matrix  SHOULD GO THROUGH 3 TIME CUZ 3 ROWS
            double t11 = Double.parseDouble(formatting.format(v1[i])); // T11 = FIRST NUMBER IN ROW T22= SECOND NUMBER IN ROW T33 THRID NUMBER IN ROW
            double t22 = Double.parseDouble(formatting.format(v1[i + 1]));
            double t33 = Double.parseDouble(formatting.format(v1[i + 2]));
            if (t11 != 0 && t22 != 0 && t33 != 0) { // if the whole row has numbers ( which means other 2 rows are all zero
                y2[0] = -t22; // x2
                y2[1] = 1; //  vector for second number (x2) free number
                y2[2] = 0;
                y2[3] = -t33; // x3
                y2[4] = 0;
                y2[5] = 1;// vector for third number (x3) free number
                return y2;
            } else {
                if ((t11 == 0 || t22 == 0 || t33 == 0) && (t11 == 1 || t22 == 1 || t33 == 1)) { //one 0 & one 1
                    if (t11 == 1) { // FIND WHERE IS THE ONE
                        if (t22 == 0) { // FIND WHERE IS THE ZERO
                            if (t33 == 0) { // IF THERE IS ANOTHER ZERO THEN 0 IN THE VECTOR FOR THAT ROW (COUNTER =1 THEN ROW ONE AND THE TOP POSITION IN VECTOR)
                                y1[counterUp] = 0;
                            } else {
                                y1[counterUp] = -t33; // else that position in the vector is the value of which ever number is not zero and not the first 1 free number
                            }
                        }
                        if (t33 == 0) {
                            if (t22 == 0) {
                                y1[counterUp] = 0;
                            } else {
                                y1[counterUp] = -t22;
                            }
                        }
                    }
                    if (t22 == 1) {
                        if (t33 == 0) {
                            if (t11 == 0) {
                                y1[counterUp] = 0;
                            } else {

                                y1[counterUp] = -t11;
                            }
                        }
                        if (t11 == 0) {
                            if (t33 == 0) {
                                y1[counterUp] = 0;
                            } else {
                                y1[counterUp] = -t33;
                            }
                        }
                    }
                    if (t33 == 1) {
                        if (t11 == 0) {
                            if (t22 == 0) {
                                y1[counterUp] = 0;
                            } else {
                                y1[counterUp] = -t22;
                            }
                        }
                        if (t22 == 0) {
                            if (t11 == 0) {
                                y1[counterUp] = 0;
                            } else {
                                y1[counterUp] = -t11;
                            }
                        }
                    }
                }
                if (t11 == 0 && t22 == 0 && t33 == 0) { // if the whole row is zero then that position in the vector is 1
                    y1[counterUp] = 1;
                }
            }
            counterUp++;
            i++; // up by two to switch row
            i++;
        }
        return y1;
    }

    public static double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public static double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public static double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
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

    public double getFourth() {
        return fourth;
    }

    public void setFourth(double fourth) {
        this.fourth = fourth;
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

    public double getE3() {
        return e3;
    }

    public void setE3(double e3) {
        this.e3 = e3;
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

    public double getA3() {
        return a3;
    }

    public void setA3(double a3) {
        this.a3 = a3;
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

    public double getB3() {
        return b3;
    }

    public void setB3(double b3) {
        this.b3 = b3;
    }

    public double getC1() {
        return c1;
    }

    public void setC1(double c1) {
        this.c1 = c1;
    }

    public double getC2() {
        return c2;
    }

    public void setC2(double c2) {
        this.c2 = c2;
    }

    public double getC3() {
        return c3;
    }

    public void setC3(double c3) {
        this.c3 = c3;
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

    public double getA3Initial() {
        return a3Initial;
    }

    public void setA3Initial(double a3Initial) {
        this.a3Initial = a3Initial;
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

    public double getB3Initial() {
        return b3Initial;
    }

    public void setB3Initial(double b3Initial) {
        this.b3Initial = b3Initial;
    }

    public double getC1Initial() {
        return c1Initial;
    }

    public void setC1Initial(double c1Initial) {
        this.c1Initial = c1Initial;
    }

    public double getC2Initial() {
        return c2Initial;
    }

    public void setC2Initial(double c2Initial) {
        this.c2Initial = c2Initial;
    }

    public double getC3Initial() {
        return c3Initial;
    }

    public void setC3Initial(double c3Initial) {
        this.c3Initial = c3Initial;
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

    public double[] getM3() {
        return m3;
    }

    public void setM3(double[] m3) {
        this.m3 = m3;
    }

    public static double[] getS1() {
        return s1;
    }

    public static void setS1(double[] s1) {
        Model2for3x3.s1 = s1;
    }

    public static double[] getS2() {
        return s2;
    }

    public static void setS2(double[] s2) {
        Model2for3x3.s2 = s2;
    }

    public static double[] getS3() {
        return s3;
    }

    public static void setS3(double[] s3) {
        Model2for3x3.s3 = s3;
    }
}