package pack.Model;

import java.util.ArrayList;

public class Model2for3x3 {

    private static double x1, x2, x3; // EigenValues
    private double first, second, third, fourth; //cubic function
    private final double a1Initial, a2Initial, a3Initial;// hold initial value first row
    private final double b1Initial, b2Initial, b3Initial;// hold initial value second row
    private final double c1Initial, c2Initial, c3Initial;// hold initial value third row
    double[] m1, m2, m3; // reduced matrix
    static ArrayList<Double> s1, s2, s3;//eigenvectors

    /**
     * will find the eigenvector and eigenvalue for a 3x3 matrix
     *
     * @param matrixCoefficients CONTAINS THE NUMBERS FROM THE TEXT FIELDS
     */
    public Model2for3x3(ArrayList<Double> matrixCoefficients) {
        double a1 = matrixCoefficients.get(0);
        double a2 = matrixCoefficients.get(1);
        // first line of matrix
        double a3 = matrixCoefficients.get(2);
        double b1 = matrixCoefficients.get(3);
        double b2 = matrixCoefficients.get(4);
        //second line
        double b3 = matrixCoefficients.get(5);
        double c1 = matrixCoefficients.get(6);
        double c2 = matrixCoefficients.get(7);
        // third line
        double c3 = matrixCoefficients.get(8);
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
        answers3x3(x1, x2, x3);

    }

    /**
     * find the cubic equation
     *
     * @param a1 first row first column
     * @param a2 first row second column
     * @param a3 first row third column
     * @param b1 second row first column
     * @param b2 second row second column
     * @param b3 second row third column
     * @param c1 third row first column
     * @param c2 third row second column
     * @param c3 third row third column
     */
    private void findTheCubicEquation3x3(double a1, double a2, double a3, double b1, double b2, double b3, double c1, double c2, double c3) {
        //additions
        //SEPARATED A1*B2*C3 according to the exponent of lambda
        double a1b2c3 = (a1 * b2 * c3);
        double a1b2 = -(a1 * b2);// ^1
        double a1b2Cal1 = -(a1 + b2);// needed in further steps
        double a1b2Cal2 = (a1b2Cal1 * c3);
        double a1b2Cal3 = -(a1b2Cal1); // ^2
        double toTheCube = -1; // the lambda ^3 always neg
        // the 2 that don't have lambda in them
        double a2b3c1 = a2 * b3 * c1;
        double a3b1c2 = a3 * b1 * c2;
        //subtraction
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
        second = a1b2Cal3 + c3; // x^2
        first = toTheCube; // x^3
    }

    /**
     * get the roots so the eigenvalues
     *
     * @param A number before x^3
     * @param B number before x^2
     * @param C number before x
     * @param D last number
     */
    private void roots3x3(double A, double B, double C, double D) {

        double a = B / A;
        double b = C / A;
        double c = D / A;

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

    }

    /**
     * find the eigenvectors and finds reduce matrix PUTS EVERYTHING TOGETHER
     *
     * @param x1 first eigenvalue
     * @param x2 second eigenvalue
     * @param x3 third eigenvalue
     */
    private void answers3x3(double x1, double x2, double x3) {
        double aa1 = a1Initial - x1;//WITH FIRST LAMBA
        double bb2 = b2Initial - x1;
        double cc3 = c3Initial - x1;
        m1 = reduceMatrix3x3(aa1, a2Initial, a3Initial, b1Initial, bb2, b3Initial, c1Initial, c2Initial, cc3); // reduced matrix
        s1 = findEigenVectors3x3(m1); // find the vector

        aa1 = a1Initial - x2;//WITH second LAMBA
        bb2 = b2Initial - x2;
        cc3 = c3Initial - x2;
        m2 = reduceMatrix3x3(aa1, a2Initial, a3Initial, b1Initial, bb2, b3Initial, c1Initial, c2Initial, cc3);
        s2 = findEigenVectors3x3(m2);

        aa1 = a1Initial - x3;//WITH third LAMBA
        bb2 = b2Initial - x3;
        cc3 = c3Initial - x3;
        m3 = reduceMatrix3x3(aa1, a2Initial, a3Initial, b1Initial, bb2, b3Initial, c1Initial, c2Initial, cc3);
        s3 = findEigenVectors3x3(m3);

    }

    /**
     * reduces the matrix
     *
     * @param a1 first row first column
     * @param a2 first row second column
     * @param a3 first row third column
     * @param b1 second row first column
     * @param b2 second row second column
     * @param b3 second row third column
     * @param c1 third row first column
     * @param c2 third row second column
     * @param c3 third row third column
     * @return the reduced matrix
     */
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
        double e2;
        // to hold values in the reduced matrix
        double e3;
        if (a1 == 0) { // if a equal to zero (switch rows) unless all is zero (a,b,c)
            double e1;
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
            a1 = Double.parseDouble(String.valueOf(1.0));
        }
        if (b1 != 0) { // reduce the second row to 0 in the first #

            b2 = Double.parseDouble(String.valueOf(b2 - (a2 * b1)));//MAKE THE FIRST NUMBER 0 AND THEN subtract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            b3 = Double.parseDouble(String.valueOf(b3 - (a3 * b1)));
            b1 = Double.parseDouble(String.valueOf(b1 - (a1 * b1)));
        }
        if (c1 != 0) { // reduce the third row to 0 in the first #

            c2 = Double.parseDouble(String.valueOf(c2 - (a2 * c1)));//MAKE THE FIRST NUMBER 0 AND THEN subtract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
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
            b2 = Double.parseDouble(String.valueOf(1.0));
        }

        if (a2 != 0) { // reduce the second row to 0 in the first #
            a3 = Double.parseDouble(String.valueOf(a3 - (a2 * b3))); //MAKE THE FIRST NUMBER 0 AND THEN subtract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            a2 = Double.parseDouble(String.valueOf(a2 - (a2 * b2)));
        }

        if (c2 != 0) { // reduce the third row to 0 in the first #
            c3 = Double.parseDouble(String.valueOf(c3 - (b3 * c2))); //MAKE THE FIRST NUMBER 0 AND THEN subtract ALL THE NUMBERS IN THAT row by whatever we took of in the first one
            c2 = Double.parseDouble(String.valueOf(c2 - (b2 * c2)));
        }
        if (c3 < 0.005) { // round if the number is too small or else it doesn't work
            c3 = 0;
        }
        // second row done
        if (c3 != 1 && c3 != 0) { //if a not equal to one or zero
            c3 = Double.parseDouble(String.valueOf(1.0));//MAKE THE FIRST NUMBER 1 AND THEN DIVIDE ALL THE NUMBERS IN THAT row
        }

        if (c3 != 0 && b3 != 0) { // reduce the second row to 0 in the first #
            b3 = Double.parseDouble(String.valueOf(b3 - (b3 * c3)));
        }

        if (c3 != 0 && a3 != 0) { // reduce the third row to 0 in the first #
            a3 = Double.parseDouble(String.valueOf(a3 - (a3 * c3)));
        }

        return new double[]{a1, a2, a3, b1, b2, b3, c1, c2, c3};
    }

    /**
     * find eigenvectors
     *
     * @param v1 the reduced matrix
     * @return the eigenvectors
     */
    private ArrayList<Double> findEigenVectors3x3(double[] v1) {
        ArrayList<Double> y1 = new ArrayList<>(); // will contain all the eigenvectors for one eigenvalue
        y1.add(0, 0.0);// will necessarily be 3 number in the arraylist
        y1.add(1, 0.0);
        y1.add(2, 0.0);
        int counterUp = 0; //to get the numbers in the row
        int counter = 0; //check of the whole matrix is all 0
        int lineCounter = 1; // which line of the matrix


        for (double v : v1) { // check if all zero
            if (v == 0) {
                counter++;

            }
        }
        if (counter == 9) { //if all zero it sets the eigenvectors
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

        if (v1[0] == 0 && v1[1] == 0 && v1[2] == 0 && v1[3] == 0 && v1[4] == 0 && v1[5] == 0) { //if first and second rwo all zero

            y1.set(0, 1.0);
            y1.set(1, 0.0);
            y1.set(2, 0.0);
            y1.add(0.0);
            y1.add(1.0);
            y1.add(0.0);
            return y1;
        }


        if (v1[0] == 0 && v1[1] == 0 && v1[2] == 0 && v1[6] == 0 && v1[7] == 0 && v1[8] == 0) { //if first and third row all zero
            y1.set(0, 1.0);
            y1.set(1, 0.0);
            y1.set(2, 0.0);
            y1.add(0.0);
            y1.add(0.0);
            y1.add(1.0);
            return y1;
        }

        if (v1[3] == 0 && v1[4] == 0 && v1[5] == 0 && v1[6] == 0 && v1[7] == 0 && v1[8] == 0) { //if second and third row all zero
            y1.set(0, 0.0);
            y1.set(1, 1.0);
            y1.set(2, 0.0);
            y1.add(0.0);
            y1.add(0.0);
            y1.add(1.0);
            return y1;
        }

        for (int i = 0; i < v1.length; i++) { // loop until we went through all the numbers in the matrix  SHOULD GO THROUGH 3 TIME CUZ 3 ROWS
            double t11 = Double.parseDouble(String.valueOf(v1[i])); // T11 = FIRST NUMBER IN ROW T22= SECOND NUMBER IN ROW T33 THIRD NUMBER IN ROW
            double t22 = Double.parseDouble(String.valueOf(v1[i + 1]));
            double t33 = Double.parseDouble(String.valueOf(v1[i + 2]));
            if (t11 != 0 && t22 != 0 && t33 != 0) { // if the whole row has numbers ( which means other 2 rows are all zero)
                y1.set(0, -t22);
                y1.set(1, 1.0);
                y1.set(2, 0.0);
                y1.add(3, -t33);
                y1.add(4, 0.0);
                y1.add(5, 1.0);
                return y1;
            } else {
                if (t11 == 1 || t22 == 1 || t33 == 1) { //one 0 & one 1
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
                    if (v1[6] == 0 && v1[7] == 0 && v1[8] == 0) { //if the last row all zero
                        y1.set(2, 1.0);
                    }
                }
                if (y1.get(0) == 0 && y1.get(1) == 0 && y1.get(2) == 0) {
                    if (t11 == 0 && t22 == 0 && t33 == 0) { // if the whole row is zero then that position in the vector is 1
                        if (lineCounter == 1) { // first row
                            y1.set(0, 1.0);
                            y1.set(1, 0.0);
                            y1.set(2, 0.0);
                        } else if (lineCounter == 2) { // second row
                            y1.set(0, 0.0);
                            y1.set(1, 1.0);
                            y1.set(2, 0.0);
                        } else { // third row
                            y1.set(0, 0.0);
                            y1.set(1, 0.0);
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

    /**
     * get final eigenvalues
     *
     * @return the final eigenvalues
     */
    public double[] getEigenValues() {
        double[] eigenValues = new double[3];
        eigenValues[0] = x1;
        eigenValues[1] = x2;
        eigenValues[2] = x3;
        return eigenValues;
    }

    /**
     * get final eigenvectors
     *
     * @return the final eigenvectors
     */
    public ArrayList<Double>[] getEigenVectors() {
        ArrayList[] eigenVectors = new ArrayList[3];
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