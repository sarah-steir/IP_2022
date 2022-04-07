//package pack.Model;
//
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//
//
///**
// * Fix math. In example 1, only the constants are different. In example 2, everything is wrong. Possibly because of negative
// * values? Also wth are is there so much formatting?
// *
// * Ex 1:
// * 1 2 3 4
// * 4 5 6 7
// * 6 7 8 9
// *
// * Ex 2:
// * 1 -1 1 8
// * 2 3 -1 -2
// * 3 -2 -9 9
// */
//public class Model1 {
//    static final DecimalFormat formatting = new DecimalFormat("0.000");// format the number to 3 decimals
//    private double e1, e2, e3; // to hold values in the reduce matrix
//    private double a1, a2, a3; // first line of matrix
//    private double b1, b2, b3;//second line
//    private double c1, c2, c3;
//    private double d1, d2, d3;
//
//    public Model1(ArrayList<Double> matrixCoefficients, boolean is2by2) {
//        this.a1 = Double.parseDouble(formatting.format(matrixCoefficients.get(0)));
//        this.a2 = Double.parseDouble(formatting.format(matrixCoefficients.get(1)));
//        this.a3 = Double.parseDouble(formatting.format(matrixCoefficients.get(2)));
//        this.b1 = Double.parseDouble(formatting.format(matrixCoefficients.get(3)));
//        this.b2 = Double.parseDouble(formatting.format(matrixCoefficients.get(4)));
//        this.b3 = Double.parseDouble(formatting.format(matrixCoefficients.get(5)));
//        this.c1 = Double.parseDouble(formatting.format(matrixCoefficients.get(6)));
//        this.c2 = Double.parseDouble(formatting.format(matrixCoefficients.get(7)));
//        this.c3 = Double.parseDouble(formatting.format(matrixCoefficients.get(8)));
//        this.d1 = Double.parseDouble(formatting.format(matrixCoefficients.get(9)));
//        this.d2 = Double.parseDouble(formatting.format(matrixCoefficients.get(10)));
//        this.d3 = Double.parseDouble(formatting.format(matrixCoefficients.get(11)));
//        reduceMatrix3x3();
//    }
//
//    private void reduceMatrix3x3() {
//        if (a1 == 0) { // if a equal to zero (switch rows) unless all is zero (a,b,c)
//            if (b1 == 0) { // if second row also has zero as the first number
//                if (c1 != 0) { // then switch a with c if c not equal zero
//                    e1 = Double.parseDouble(formatting.format(a1)); // E holds the number
//                    e2 = Double.parseDouble(formatting.format(a2));
//                    e3 = Double.parseDouble(formatting.format(a3));
//                    a1 = Double.parseDouble(formatting.format(c1));
//                    a2 = Double.parseDouble(formatting.format(c2));
//                    a3 = Double.parseDouble(formatting.format(c3));
//                    c1 = Double.parseDouble(formatting.format(e1));
//                    c2 = Double.parseDouble(formatting.format(e2));
//                    c3 = Double.parseDouble(formatting.format(e3));
//                }
//            }
//            if (b1 != 0) { // switch a with b if b not equal zero
//
//                e1 = Double.parseDouble(formatting.format(a1));
//                e2 = Double.parseDouble(formatting.format(a2));
//                e3 = Double.parseDouble(formatting.format(a3));
//                a1 = Double.parseDouble(formatting.format(b1));
//                a2 = Double.parseDouble(formatting.format(b2));
//                a3 = Double.parseDouble(formatting.format(b3));
//                b1 = Double.parseDouble(formatting.format(e1));
//                b2 = Double.parseDouble(formatting.format(e2));
//                b3 = Double.parseDouble(formatting.format(e3));
//            }
//        }
//        if (a1 != 1 && a1 != 0) { //if a not equal to one or zero
//
//            a2 = Double.parseDouble(formatting.format(a2 / a1)); //MAKE THE FIRST NUMBER 1 AND THEN DIVIDE ALL THE NUMBERS IN THAT row
//            a3 = Double.parseDouble(formatting.format(a3 / a1));
//            a1 = Double.parseDouble(formatting.format(a1 / a1));
//        }
//        if (b1 != 0) { // reduce the second row to 0 in the first #
//
//            b2 = Double.parseDouble(formatting.format(b2 - (a2 * b1)));//MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
//            b3 = Double.parseDouble(formatting.format(b3 - (a3 * b1)));
//            b1 = Double.parseDouble(formatting.format(b1 - (a1 * b1)));
//        }
//        if (c1 != 0) { // reduce the third row to 0 in the first #
//
//            c2 = Double.parseDouble(formatting.format(c2 - (a2 * c1)));//MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
//            c3 = Double.parseDouble(formatting.format(c3 - (a3 * c1)));
//            c1 = Double.parseDouble(formatting.format(c1 - (a1 * c1)));
//        }
//        //COLUMN ONE DONE
//        if (b2 == 0) { // if a equal to zero (switch rows) unless all is zero (a,b,c)
//            if (c2 != 0) {
//                e2 = Double.parseDouble(formatting.format(b2)); // replace b with c if c not zero
//                e3 = Double.parseDouble(formatting.format(b3));
//                b2 = Double.parseDouble(formatting.format(c2));
//                b3 = Double.parseDouble(formatting.format(c3));
//                c2 = Double.parseDouble(formatting.format(e2));
//                c3 = Double.parseDouble(formatting.format(e3));
//            }
//            if (b2 == 0 && c2 == 0 && a1 == 0 && a2 != 0) { // if A has 0 in the first position and not zero in second position a switch with b
//                e2 = Double.parseDouble(formatting.format(b2));
//                e3 = Double.parseDouble(formatting.format(b3));
//                b2 = Double.parseDouble(formatting.format(a2));
//                b3 = Double.parseDouble(formatting.format(a3));
//                a2 = Double.parseDouble(formatting.format(e2));
//                a3 = Double.parseDouble(formatting.format(e3));
//            }
//        }
//        if (b2 != 1 && b2 != 0) { //if a not equal to one or zero
//            b3 = Double.parseDouble(formatting.format(b3 / b2));//MAKE THE FIRST NUMBER 1 AND THEN DIVIDE ALL THE NUMBERS IN THAT row
//            b2 = Double.parseDouble(formatting.format(b2 / b2));
//        }
//
//        if (a2 != 0) { // reduce the second row to 0 in the first #
//            a3 = Double.parseDouble(formatting.format(a3 - (a2 * b3))); //MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
//            a2 = Double.parseDouble(formatting.format(a2 - (a2 * b2)));
//        }
//
//        if (c2 != 0) { // reduce the third row to 0 in the first #
//            c3 = Double.parseDouble(formatting.format(c3 - (b3 * c2))); //MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
//            c2 = Double.parseDouble(formatting.format(c2 - (b2 * c2)));
//        }
//        if(c3<0.005){
//            c3=0;
//        }
//        // second row done
//        if (c3 != 1 && c3 != 0) { //if a not equal to one or zero
//            c3 = Double.parseDouble(formatting.format(c3 / c3));//MAKE THE FIRST NUMBER 1 AND THEN DIVIDE ALL THE NUMBERS IN THAT row
//        }
//
//        if (c3 != 0 && b3 != 0) { // reduce the second row to 0 in the first #
//            b3 = Double.parseDouble(formatting.format(b3 - (b3 * c3)));
//        }
//
//        if (c3 != 0 && a3 != 0) { // reduce the third row to 0 in the first #
//            a3 = Double.parseDouble(formatting.format(a3 - (a3 * c3)));
//        }
//        double arr[] = {a1, a2, a3, b1, b2, b3, c1, c2, c3}; // array to hold the matrix
//    }
//
//    public void printOutput() {
//        System.out.println("a1: " + a1);
//        System.out.println("a2: " + a2);
//        System.out.println("a3: " + a3);
//
//        System.out.println("b1: " + b1);
//        System.out.println("b2: " + b2);
//        System.out.println("b3: " + b3);
//
//        System.out.println("c1: " + c1);
//        System.out.println("c2: " + c2);
//        System.out.println("c3: " + c3);
//
//        System.out.println("d1: " + d1);
//        System.out.println("d2: " + d2);
//        System.out.println("d3: " + d3);
//
//    }
//}

package pack.Model;

import java.text.DecimalFormat;

public class Model1 {
    static final DecimalFormat formatting = new DecimalFormat("0.000");// format the number to 3 decimals
    private double e1, e2, e3, e4; // to hold values in the reduce matrix
    private double a1, a2, a3;
    private double b1, b2, b3;
    private double c1, c2, c3;
    private double d1, d2, d3;

    public Model1(double a1, double a2, double a3, double b1, double b2, double b3, double c1, double c2, double c3, double d1, double d2, double d3) {
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.d1 = d1;
        this.d2= d2;
        this.d3=d3;
        reduceMatrix3x3(a1,a2,a3,b1,b2,b3,c1,c2,c3,d1,d2,d3);
    }
    public Model1(double a1, double a2, double b1, double b2, double d1, double d2) {
        this.a1 = a1;
        this.a2 = a2;
        this.b1 = b1;
        this.b2 = b2;
        this.d1 = d1;
        this.d2= d2;
        reduceMatrix2x2(a1,a2,b1,b2,d1,d2);

    }

    private double transform(double value) {
        return Double.parseDouble(formatting.format(value));
    };
    private double[] reduceMatrix2x2(double a1, double a2, double b1, double b2, double d1, double d2) {
        if (a1 == 0) { // if a equal to zero (switch rows) unless all is zero (a,b,c)
            if (b1 != 0) { //switch a with b if b not zero
                e1 = Double.parseDouble(formatting.format(a1));
                e2 = Double.parseDouble(formatting.format(a2));
                e3 = Double.parseDouble(formatting.format(d1));
                a1 = Double.parseDouble(formatting.format(b1));
                a2 = Double.parseDouble(formatting.format(b2));
                d1 = Double.parseDouble(formatting.format(d2));
                b1 = Double.parseDouble(formatting.format(e1));
                b2 = Double.parseDouble(formatting.format(e2));
                d2 = Double.parseDouble(formatting.format(d1));
            }
        }

        if (a1 != 1 && a1 != 0) { //if a not equal to one or zero
            a2 = Double.parseDouble(formatting.format(a2 / a1));//MAKE THE FIRST NUMBER 1 AND THEN DIVIDE ALL THE NUMBERS IN THAT row
            d1 = Double.parseDouble(formatting.format(d1 / a1));
            a1 = Double.parseDouble(formatting.format(a1 / a1));
        }

        if (b1 != 0) { // reduce the second row to 0 in the first #
            b2 = Double.parseDouble(formatting.format(b2 - (a2 * b1)));//MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            d2 = Double.parseDouble(formatting.format(d2 - (d1 * b1)));
            b1 = Double.parseDouble(formatting.format(b1 - (a1 * b1)));
        }

        //COLUMN ONE DONE
        if (b2 != 1 && b2 != 0) { //if a not equal to one or zero
            b2 = Double.parseDouble(formatting.format(b2 / b2));//MAKE THE FIRST NUMBER 1
            d2 = Double.parseDouble(formatting.format(d2 / b2));
        }

        if (b2 != 0 && a2 != 0) { // reduce the second row to 0 in the first #
            a2 = Double.parseDouble(formatting.format(a2 - (a2 * b2)));//MAKE THE FIRST NUMBER 0
            d1 = Double.parseDouble(formatting.format(d1 - (d2 * b2)));
        }
        double arr[] = {a1, a2, b1, b2, d1, d2}; // reduced matrix
        return arr;
    }
    private double[] reduceMatrix3x3(double a1, double a2, double a3, double b1, double b2, double b3, double c1, double c2, double c3,double d1, double d2, double d3) {
        a1 = Double.parseDouble(formatting.format(a1)); // format everything
        a2 = Double.parseDouble(formatting.format(a2));
        a3 = Double.parseDouble(formatting.format(a3));
        b1 = Double.parseDouble(formatting.format(b1));
        b2 = Double.parseDouble(formatting.format(b2));
        b3 = Double.parseDouble(formatting.format(b3));
        c1 = Double.parseDouble(formatting.format(c1));
        c2 = Double.parseDouble(formatting.format(c2));
        c3 = Double.parseDouble(formatting.format(c3));
        d1 = Double.parseDouble(formatting.format(d1));
        d2 = Double.parseDouble(formatting.format(d2));
        d3 = Double.parseDouble(formatting.format(d3));
        if (a1 == 0) { // if a equal to zero (switch rows) unless all is zero (a,b,c)
            if (b1 == 0) { // if second row also has zero as the first number
                if (c1 != 0) { // then switch a with c if c not equal zero
                    e1 = Double.parseDouble(formatting.format(a1)); // E holds the number
                    e2 = Double.parseDouble(formatting.format(a2));
                    e3 = Double.parseDouble(formatting.format(a3));
                    e4 = Double.parseDouble(formatting.format(d1));
                    a1 = Double.parseDouble(formatting.format(c1));
                    a2 = Double.parseDouble(formatting.format(c2));
                    a3 = Double.parseDouble(formatting.format(c3));
                    d1 = Double.parseDouble(formatting.format(d3));
                    c1 = Double.parseDouble(formatting.format(e1));
                    c2 = Double.parseDouble(formatting.format(e2));
                    c3 = Double.parseDouble(formatting.format(e3));
                    d3 = Double.parseDouble(formatting.format(d1));
                }
            }
            if (b1 != 0) { // switch a with b if b not equal zero

                e1 = Double.parseDouble(formatting.format(a1));
                e2 = Double.parseDouble(formatting.format(a2));
                e3 = Double.parseDouble(formatting.format(a3));
                e4 = Double.parseDouble(formatting.format(d1));
                a1 = Double.parseDouble(formatting.format(b1));
                a2 = Double.parseDouble(formatting.format(b2));
                a3 = Double.parseDouble(formatting.format(b3));
                d1 = Double.parseDouble(formatting.format(d2));
                b1 = Double.parseDouble(formatting.format(e1));
                b2 = Double.parseDouble(formatting.format(e2));
                b3 = Double.parseDouble(formatting.format(e3));
                d2 = Double.parseDouble(formatting.format(d1));
            }
        }
        if (a1 != 1 && a1 != 0) { //if a not equal to one or zero

            a2 = Double.parseDouble(formatting.format(a2 / a1)); //MAKE THE FIRST NUMBER 1 AND THEN DIVIDE ALL THE NUMBERS IN THAT row
            a3 = Double.parseDouble(formatting.format(a3 / a1));
            d1 = Double.parseDouble(formatting.format(d1 / a1));
            a1 = Double.parseDouble(formatting.format(a1 / a1));
        }
        if (b1 != 0) { // reduce the second row to 0 in the first #

            b2 = Double.parseDouble(formatting.format(b2 - (a2 * b1)));//MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            b3 = Double.parseDouble(formatting.format(b3 - (a3 * b1)));
            d2 = Double.parseDouble(formatting.format(d2 - (d1 * b1)));
            b1 = Double.parseDouble(formatting.format(b1 - (a1 * b1)));
        }
        if (c1 != 0) { // reduce the third row to 0 in the first #

            c2 = Double.parseDouble(formatting.format(c2 - (a2 * c1)));//MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            c3 = Double.parseDouble(formatting.format(c3 - (a3 * c1)));
            d3 = Double.parseDouble(formatting.format(d3 - (d1 * c1)));
            c1 = Double.parseDouble(formatting.format(c1 - (a1 * c1)));
        }
        //COLUMN ONE DONE
        if (b2 == 0) { // if a equal to zero (switch rows) unless all is zero (a,b,c)
            if (c2 != 0) {
                e2 = Double.parseDouble(formatting.format(b2)); // replace b with c if c not zero
                e3 = Double.parseDouble(formatting.format(b3));
                e4 = Double.parseDouble(formatting.format(d2));
                b2 = Double.parseDouble(formatting.format(c2));
                b3 = Double.parseDouble(formatting.format(c3));
                d2 = Double.parseDouble(formatting.format(d3));
                c2 = Double.parseDouble(formatting.format(e2));
                c3 = Double.parseDouble(formatting.format(e3));
                d3 = Double.parseDouble(formatting.format(d2));
            }
            if (b2 == 0 && c2 == 0 && a1 == 0 && a2 != 0) { // if A has 0 in the first position and not zero in second position a switch with b
                e2 = Double.parseDouble(formatting.format(b2));
                e3 = Double.parseDouble(formatting.format(b3));
                e4 = Double.parseDouble(formatting.format(d2));
                b2 = Double.parseDouble(formatting.format(a2));
                b3 = Double.parseDouble(formatting.format(a3));
                d2 = Double.parseDouble(formatting.format(d1));
                a2 = Double.parseDouble(formatting.format(e2));
                a3 = Double.parseDouble(formatting.format(e3));
                d1 = Double.parseDouble(formatting.format(d2));
            }
        }
        if (b2 != 1 && b2 != 0) { //if a not equal to one or zero
            //System.out.println(d2);
            b3 = Double.parseDouble(formatting.format(b3 / b2));//MAKE THE FIRST NUMBER 1 AND THEN DIVIDE ALL THE NUMBERS IN THAT row
            d2 = Double.parseDouble(formatting.format(d2 / b2));
            b2 = Double.parseDouble(formatting.format(b2 / b2));
            //.out.println(d2);
        }

        if (a2 != 0) { // reduce the second row to 0 in the first #
            a3 = Double.parseDouble(formatting.format(a3 - (a2 * b3))); //MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            d1 = Double.parseDouble(formatting.format(d1 - (a2 * d2)));
            a2 = Double.parseDouble(formatting.format(a2 - (a2 * b2)));
        }

        if (c2 != 0) { // reduce the third row to 0 in the first #
            c3 = Double.parseDouble(formatting.format(c3 - (b3 * c2))); //MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            d3 = Double.parseDouble(formatting.format(d3 - (d2 * c2)));
            c2 = Double.parseDouble(formatting.format(c2 - (b2 * c2)));
        }
        if(c3<0.005){
            c3=0;
        }
        // second row done
        if (c3 != 1 && c3 != 0) { //if a not equal to one or zero
            d3 = Double.parseDouble(formatting.format(d3 / c3));
            c3 = Double.parseDouble(formatting.format(c3 / c3));//MAKE THE FIRST NUMBER 1 AND THEN DIVIDE ALL THE NUMBERS IN THAT row

        }

        if (c3 != 0 && b3 != 0) { // reduce the second row to 0 in the first #
            //System.out.println(b3);
            System.out.println(d2);
            System.out.println(d3);
            System.out.println(c3);
            d2 = Double.parseDouble(formatting.format(d2 - (d3 * b3)));
            b3 = Double.parseDouble(formatting.format(b3 - (b3 * c3)));


        }

        if (c3 != 0 && a3 != 0) { // reduce the third row to 0 in the first #
            d1 = Double.parseDouble(formatting.format(d1 - (d3 * a3)));
            a3 = Double.parseDouble(formatting.format(a3 - (a3 * c3)));

        }

        double arr[] = {a1, a2, a3, b1, b2, b3, c1, c2, c3, d1, d2, d3}; // array to hold the matrix
        setA1(a1);
        setA2(a2);
        setA3(a3);
        setB1(b1);
        setB2(b2);
        setB3(b3);
        setC1(c1);
        setC2(c2);
        setC3(c3);
        setD1(d1);
        setD2(d2);
        setD3(d3);

        return arr;
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

    public double getE4() {
        return e4;
    }

    public void setE4(double e4) {
        this.e4 = e4;
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

    public double getD1() {
        return d1;
    }

    public void setD1(double d1) {
        this.d1 = d1;
    }

    public double getD2() {
        return d2;
    }

    public void setD2(double d2) {
        this.d2 = d2;
    }

    public double getD3() {
        return d3;
    }

    public void setD3(double d3) {
        this.d3 = d3;
    }
}