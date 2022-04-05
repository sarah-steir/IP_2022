package pack.Model;

import java.text.DecimalFormat;

public class Model1 {
    static final DecimalFormat formatting = new DecimalFormat("0.000");// format the number to 3 decimals
    private double e1, e2, e3; // to hold values in the reduce matrix
    private double a1, a2, a3; // first line of matrix
    private double b1, b2, b3;//second line
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
}
