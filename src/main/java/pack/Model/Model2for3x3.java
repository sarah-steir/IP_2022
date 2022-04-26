package pack.Model;

import java.text.DecimalFormat;
import java.util.ArrayList;

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
    static ArrayList<Double> s1, s2, s3;//eigenvectors

    static final DecimalFormat formatting = new DecimalFormat("0.0000"); // format the number to 4 decimals

    public Model2for3x3(ArrayList<Double> matrixCoefficients) {
        this.a1 = matrixCoefficients.get(0);
        this.a2 = matrixCoefficients.get(1);
        this.a3 = matrixCoefficients.get(2);
        this.b1 = matrixCoefficients.get(3);
        this.b2 = matrixCoefficients.get(4);
        this.b3 = matrixCoefficients.get(5);
        this.c1 = matrixCoefficients.get(6);
        this.c2 = matrixCoefficients.get(7);
        this.c3 = matrixCoefficients.get(8);
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
    private void roots3x3(double A, double B, double C, double D) {

        double a = (double) B / A;
        double b = (double) C / A;
        double c = (double) D / A;

        System.out.println("Double values: ");
        System.out.println(a + " " + b + " " + c);
        double p = b - ((a * a) / 3.0);

        double q = (2 * Math.pow(a, 3) / 27.0) - (a * b / 3.0) + c;

        double delta = (Math.pow(q, 2) / 4) + (Math.pow(p, 3) / 27);

        if (delta > 0.001) {

            double mt1, mt2;

            double t1 = (-q / 2.0) + Math.sqrt(delta);
            double t2 = (-q / 2.0) - Math.sqrt(delta);

            if (t1 < 0) {
                mt1 = (-1) * (Math.pow(-t1, (double) 1 / 3));
            } else {
                mt1 = (Math.pow(t1, (double) 1 / 3));
            }

            if (t2 < 0) {
                mt2 = (-1) * (Math.pow(-t2, (double) 1 / 3));
            } else {
                mt2 = (Math.pow(t2, (double) 1 / 3));
            }

            x1 = mt1 + mt2 - (a / 3.0);

        } else if (delta < 0.001 && delta > -0.001) {

            if (q < 0) {

                x1 = 2 * Math.pow(-q / 2, (double) 1 / 3) - (a / 3);
                x2 = -1 * Math.pow(-q / 2, (double) 1 / 3) - (a / 3);

            } else {

                x1 = -2 * Math.pow(q / 2, (double) 1 / 3) - (a / 3);
                x2 = Math.pow(q / 2, (double) 1 / 3) - (a / 3);

            }

        } else {

            x1 = (2.0 / Math.sqrt(3)) * (Math.sqrt(-p) * Math.sin((1 / 3.0) * Math.asin(((3 * Math.sqrt(3) * q) / (2 * Math.pow(Math.pow(-p, (double) 1 / 2), 3)))))) - (a / 3.0);
            x2 = (-2.0 / Math.sqrt(3)) * (Math.sqrt(-p) * Math.sin((1 / 3.0) * Math.asin(((3 * Math.sqrt(3) * q) / (2 * Math.pow(Math.pow(-p, (double) 1 / 2), 3)))) + (Math.PI / 3))) - (a / 3.0);
            x3 = (2.0 / Math.sqrt(3)) * (Math.sqrt(-p) * Math.cos((1 / 3.0) * Math.asin(((3 * Math.sqrt(3) * q) / (2 * Math.pow(Math.pow(-p, (double) 1 / 2), 3)))) + (Math.PI / 6))) - (a / 3.0);

        }


        x1 = Double.parseDouble(String.valueOf(x1)); // FORMAT TO 0.0000
        x2 = Double.parseDouble(String.valueOf(x2));
        x3 = Double.parseDouble(String.valueOf(x3));

        System.out.println("x1: " + x1 + "x2: " + x2 + "x3: " + x3);
    }
    //SEND THIS TO DE CONTROLLA
    // find the eigenvectors and finds reduce matrix PUTS EVERYTHING TOGETHER
    private void answers3x3(double a1, double a2, double a3, double b1, double b2, double b3, double c1, double c2, double c3, double x1, double x2, double x3) {
        double aa1 = a1Initial - x1;//WITH FIRST LAMBA
        double bb2 = b2Initial - x1;//WITH FIRST LAMBA
        double cc3 = c3Initial - x1;//WITH FIRST LAMBA
        m1 = reduceMatrix3x3(aa1, a2Initial, a3Initial, b1Initial, bb2, b3Initial, c1Initial, c2Initial, cc3); // reduced matrix
        s1 = findEigenVectors3x3(m1); // find the vector

        //System.out.println(eigenVectors[1].get(0) + " " +eigenVectors[1].get(1) + " " +eigenVectors[1].get(2));
        //System.out.println(eigenVectors[2].get(0) + " " +eigenVectors[2].get(1) + " " +eigenVectors[2].get(2));
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
        a1 = Double.parseDouble(String.valueOf(a1)); // format everything
        a2 = Double.parseDouble(String.valueOf(a2));
        a3 = Double.parseDouble(String.valueOf(a3));
        b1 = Double.parseDouble(String.valueOf(b1));
        b2 = Double.parseDouble(String.valueOf(b2));
        b3 = Double.parseDouble(String.valueOf(b3));
        c1 = Double.parseDouble(String.valueOf(c1));
        c2 = Double.parseDouble(String.valueOf(c2));
        c3 = Double.parseDouble(String.valueOf(c3));
        if (a1 == 0) { // if a equal to zero (switch rows) unless all is zero (a,b,c)
            if (b1 == 0) { // if second row also has zero as the first number
                if (c1 != 0) { // then switch a with c if c not equal zero
                    e1 = Double.parseDouble(String.valueOf(a1)); // E holds the number
                    e2 = Double.parseDouble(String.valueOf(a2));
                    e3 = Double.parseDouble(String.valueOf(a3));
                    a1 = Double.parseDouble(String.valueOf(c1));
                    a2 = Double.parseDouble(String.valueOf(c2));
                    a3 = Double.parseDouble(String.valueOf(c3));
                    c1 = Double.parseDouble(String.valueOf(e1));
                    c2 = Double.parseDouble(String.valueOf(e2));
                    c3 = Double.parseDouble(String.valueOf(e3));
                }
            }
            if (b1 != 0) { // switch a with b if b not equal zero

                e1 = Double.parseDouble(String.valueOf(a1));
                e2 = Double.parseDouble(String.valueOf(a2));
                e3 = Double.parseDouble(String.valueOf(a3));
                a1 = Double.parseDouble(String.valueOf(b1));
                a2 = Double.parseDouble(String.valueOf(b2));
                a3 = Double.parseDouble(String.valueOf(b3));
                b1 = Double.parseDouble(String.valueOf(e1));
                b2 = Double.parseDouble(String.valueOf(e2));
                b3 = Double.parseDouble(String.valueOf(e3));
            }
        }
        if (a1 != 1 && a1 != 0) { //if a not equal to one or zero

            a2 = Double.parseDouble(String.valueOf(a2 / a1)); //MAKE THE FIRST NUMBER 1 AND THEN DIVIDE ALL THE NUMBERS IN THAT row
            a3 = Double.parseDouble(String.valueOf(a3 / a1));
            a1 = Double.parseDouble(String.valueOf(a1 / a1));
        }
        if (b1 != 0) { // reduce the second row to 0 in the first #

            b2 = Double.parseDouble(String.valueOf(b2 - (a2 * b1)));//MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            b3 = Double.parseDouble(String.valueOf(b3 - (a3 * b1)));
            b1 = Double.parseDouble(String.valueOf(b1 - (a1 * b1)));
        }
        if (c1 != 0) { // reduce the third row to 0 in the first #

            c2 = Double.parseDouble(String.valueOf(c2 - (a2 * c1)));//MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            c3 = Double.parseDouble(String.valueOf(c3 - (a3 * c1)));
            c1 = Double.parseDouble(String.valueOf(c1 - (a1 * c1)));
        }
        //COLUMN ONE DONE
        if (b2 == 0) { // if a equal to zero (switch rows) unless all is zero (a,b,c)
            if (c2 != 0) {
                e2 = Double.parseDouble(String.valueOf(b2)); // replace b with c if c not zero
                e3 = Double.parseDouble(String.valueOf(b3));
                b2 = Double.parseDouble(String.valueOf(c2));
                b3 = Double.parseDouble(String.valueOf(c3));
                c2 = Double.parseDouble(String.valueOf(e2));
                c3 = Double.parseDouble(String.valueOf(e3));
            }
            if (b2 == 0 && c2 == 0 && a1 == 0 && a2 != 0) { // if A has 0 in the first position and not zero in second position a switch with b
                e2 = Double.parseDouble(String.valueOf(b2));
                e3 = Double.parseDouble(String.valueOf(b3));
                b2 = Double.parseDouble(String.valueOf(a2));
                b3 = Double.parseDouble(String.valueOf(a3));
                a2 = Double.parseDouble(String.valueOf(e2));
                a3 = Double.parseDouble(String.valueOf(e3));
            }
        }
        if (b2 != 1 && b2 != 0) { //if a not equal to one or zero
            b3 = Double.parseDouble(String.valueOf(b3 / b2));//MAKE THE FIRST NUMBER 1 AND THEN DIVIDE ALL THE NUMBERS IN THAT row
            b2 = Double.parseDouble(String.valueOf(b2 / b2));
        }

        if (a2 != 0) { // reduce the second row to 0 in the first #
            a3 = Double.parseDouble(String.valueOf(a3 - (a2 * b3))); //MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            a2 = Double.parseDouble(String.valueOf(a2 - (a2 * b2)));
        }

        if (c2 != 0) { // reduce the third row to 0 in the first #
            c3 = Double.parseDouble(String.valueOf(c3 - (b3 * c2))); //MAKE THE FIRST NUMBER 0 AND THEN substract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            c2 = Double.parseDouble(String.valueOf(c2 - (b2 * c2)));
        }
        if (c3 < 0.005) {
            c3 = 0;
        }
        // second row done
        if (c3 != 1 && c3 != 0) { //if a not equal to one or zero
            c3 = Double.parseDouble(String.valueOf(c3 / c3));//MAKE THE FIRST NUMBER 1 AND THEN DIVIDE ALL THE NUMBERS IN THAT row
        }

        if (c3 != 0 && b3 != 0) { // reduce the second row to 0 in the first #
            b3 = Double.parseDouble(String.valueOf(b3 - (b3 * c3)));
        }

        if (c3 != 0 && a3 != 0) { // reduce the third row to 0 in the first #
            a3 = Double.parseDouble(String.valueOf(a3 - (a3 * c3)));
        }
        double arr[] = {a1, a2, a3, b1, b2, b3, c1, c2, c3}; // array to hold the matrix

        return arr;
    }

    // find eigenvectors
    private ArrayList<Double> findEigenVectors3x3(double[] v1) {
        ArrayList<Double> y1 = new ArrayList<>(); // if only one vector for that one eigen value
        y1.add(0, 0.0);
        y1.add(1, 0.0);
        y1.add(2, 0.0);
        int counterUp = 0;
        int counter = 0;
        int lineCounter = 1;
        boolean firstLine = false;
        boolean secondLine = false;
        boolean thirdLine = false;

        for (int j = 0; j < v1.length; j++) {
            if (v1[j] == 0) {
                counter++;

            }
        }
        if (counter == 9) {
            y1.set(0, 1.0);
            y1.set(1, 0.0);
            y1.set(2, 0.0);
            y1.add(3, 0.0);
            y1.add(4, 1.0);
            y1.add(5, 0.0);
            y1.add(6, 0.0);
            y1.add(7, 0.0);
            y1.add(8, 1.0);

            return y1;
        }

        if (v1[0] == 0 && v1[1] == 0 && v1[2] == 0 && v1[3] == 0 && v1[4] == 0 && v1[5] == 0) {

            y1.set(0, 1.0);
            y1.set(1, 0.0);
            y1.set(2, 0.0);
            y1.add(0.0);
            y1.add(1.0);
            y1.add(0.0);
            return y1;
        }


        if (v1[0] == 0 && v1[1] == 0 && v1[2] == 0 && v1[6] == 0 && v1[7] == 0 && v1[8] == 0) {
            y1.set(0, 1.0);
            y1.set(1, 0.0);
            y1.set(2, 0.0);
            y1.add(0.0);
            y1.add(0.0);
            y1.add(1.0);
            return y1;
        }

        if (v1[3] == 0 && v1[4] == 0 && v1[5] == 0 && v1[6] == 0 && v1[7] == 0 && v1[8] == 0) {
            y1.set(0, 0.0);
            y1.set(1, 1.0);
            y1.set(2, 0.0);
            y1.add(0.0);
            y1.add(0.0);
            y1.add(1.0);
            return y1;
        }

        for (int i = 0; i < v1.length; i++) { // loop until we went through all the numbers in the matrix  SHOULD GO THROUGH 3 TIME CUZ 3 ROWS
            double t11 = Double.parseDouble(String.valueOf(v1[i])); // T11 = FIRST NUMBER IN ROW T22= SECOND NUMBER IN ROW T33 THRID NUMBER IN ROW
            double t22 = Double.parseDouble(String.valueOf(v1[i + 1]));
            double t33 = Double.parseDouble(String.valueOf(v1[i + 2]));
            if (t11 != 0 && t22 != 0 && t33 != 0) { // if the whole row has numbers ( which means other 2 rows are all zero
                y1.set(0, -t22); // x2
                y1.set(1, 1.0); //  vector for second number (x2) free number
                y1.set(2, 0.0);
                y1.add(3, -t33); // x3
                y1.add(4, 0.0);
                y1.add(5, 1.0);// vector for third number (x3) free number
                return y1;
            } else {
                if ((t11 == 0 || t22 == 0 || t33 == 0) && (t11 == 1 || t22 == 1 || t33 == 1)) { //one 0 & one 1
                    if (t11 == 1) { // FIND WHERE IS THE ONE
                        if (t22 == 0) { // FIND WHERE IS THE ZERO
                            if (t33 == 0) { // IF THERE IS ANOTHER ZERO THEN 0 IN THE VECTOR FOR THAT ROW (COUNTER =1 THEN ROW ONE AND THE TOP POSITION IN VECTOR)
                                y1.set(counterUp, 0.0);

                            } else {
                                y1.set(counterUp, -t33);// else that position in the vector is the value of which ever number is not zero and not the first 1 free number
                            }
                        }

                        if (t33 == 0) {
                            if (t22 == 0) {
                                y1.set(counterUp, 0.0);
                            } else {
                                y1.set(counterUp, -t22);
                            }
                        }
                    }
                    if (t22 == 1) {
                        if (t33 == 0) {
                            if (t11 == 0) {
                                y1.set(counterUp, 0.0);
                            } else {

                                y1.set(counterUp, -t11);
                            }
                        }
                        if (t11 == 0) {
                            if (t33 == 0) {
                                y1.set(counterUp, 0.0);
                            } else {
                                y1.set(counterUp, -t33);
                            }
                        }
                    }
                    if (t33 == 1) {
                        if (t11 == 0) {
                            if (t22 == 0) {
                                y1.set(counterUp, 0.0);
                            } else {
                                y1.set(counterUp, -t22);
                            }
                        }
                        if (t22 == 0) {
                            if (t11 == 0) {
                                y1.set(counterUp, 0.0);
                            } else {
                                y1.set(counterUp, -t11);
                            }
                        }
                    }
                    if (v1[6] == 0 && v1[7] == 0 && v1[8] == 0) {
                        y1.set(2, 1.0);
                    }
                }
                if (y1.get(0) == 0 && y1.get(1) == 0 && y1.get(0) == 0) {
                    if (t11 == 0 && t22 == 0 && t33 == 0) { // if the whole row is zero then that position in the vector is 1
                        if (lineCounter == 1) {
                            y1.set(0, 1.0); // x2
                            y1.set(1, 0.0); //  vector for second number (x2) free number
                            y1.set(2, 0.0);
                        } else if (lineCounter == 2) {
                            y1.set(0, 0.0); // x2
                            y1.set(1, 1.0); //  vector for second number (x2) free number
                            y1.set(2, 0.0);
                        } else {
                            y1.set(0, 0.0); // x2
                            y1.set(1, 0.0); //  vector for second number (x2) free number
                            y1.set(2, 1.0);
                        }
                    }

                }
            }

            counterUp++;
            i++; // up by two to switch row
            i++;
            lineCounter++;

        }
        return y1;
    }

    public double[] getEigenValues() {
        double[] eigenValues = new double[3];
        eigenValues[0] = x1;
        eigenValues[1] = x2;
        eigenValues[2] = x3;
        return eigenValues;
    }

    public ArrayList<Double>[] getEigenVectors() {
        ArrayList<Double>[] eigenVectors = new ArrayList[3];
        eigenVectors[0] = s1;
        eigenVectors[1] = s2;
        eigenVectors[2] = s3;

        return eigenVectors;
    }

    public static ArrayList<Double> getS2() {
        return s2;
    }

    public static ArrayList<Double> getS3() {
        return s3;
    }
}