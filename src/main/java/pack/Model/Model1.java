package pack.Model;

import javafx.geometry.Point3D;

import java.util.ArrayList;

public class Model1 {

    double a1, a2, a3; // Row 1
    double b1, b2, b3; // Row 2
    double c1, c2, c3; // Row 3
    double d1, d2, d3; // Last Column

//Ax=B

    // storing arraylist variables for the SLE calculation 2x2
    //A matrix storage
    private static final double[][] matrixA_2x2 = {{1, 1}, {1, 1}};
    //B matrix storage
    private static final double[] matrixB_2x2 = {1, 1};

    // storing arraylist variables for the SLE calculation 3x3
    //A matrix storage
    private static final double[][] matrixA_3x3 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    //B matrix storage
    private static final double[] matrixB_3x3 = {1, 1, 1};

    //Storing A matrix variables
    public Model1(ArrayList<Double> matrixOfCoefficients, boolean is2by2) {
        if (is2by2) {
            // A matrix for 2x2
            //Row 1
            this.a1 = matrixOfCoefficients.get(0);
            matrixA_2x2[0][0] = this.a1;
            this.a2 = matrixOfCoefficients.get(1);
            matrixA_2x2[0][1] = this.a2;

            //Row2
            this.b1 = matrixOfCoefficients.get(2);
            matrixA_2x2[1][0] = this.b1;
            this.b2 = matrixOfCoefficients.get(3);
            matrixA_2x2[1][1] = this.b2;

            //B Matrix
            this.d1 = matrixOfCoefficients.get(4);
            matrixB_2x2[0] = this.d1;
            this.d2 = matrixOfCoefficients.get(5);
            matrixB_2x2[1] = this.d2;
        } else {
            // A matrix for 3x3
            //Row 1
            this.a1 = matrixOfCoefficients.get(0);
            matrixA_3x3[0][0] = this.a1;
            this.a2 = matrixOfCoefficients.get(1);
            matrixA_3x3[0][1] = this.a2;
            this.a3 = matrixOfCoefficients.get(2);
            matrixA_3x3[0][2] = this.a3;

            //Row 2
            this.b1 = matrixOfCoefficients.get(3);
            matrixA_3x3[1][0] = this.b1;
            this.b2 = matrixOfCoefficients.get(4);
            matrixA_3x3[1][1] = this.b2;
            this.b3 = matrixOfCoefficients.get(5);
            matrixA_3x3[1][2] = this.b3;

            //Row 3
            this.c1 = matrixOfCoefficients.get(6);
            matrixA_3x3[2][0] = this.c1;
            this.c2 = matrixOfCoefficients.get(7);
            matrixA_3x3[2][1] = this.c2;
            this.c3 = matrixOfCoefficients.get(8);
            matrixA_3x3[2][2] = this.c3;


            //B Matrix
            this.d1 = matrixOfCoefficients.get(9);
            matrixB_3x3[0] = this.d1;
            this.d2 = matrixOfCoefficients.get(10);
            matrixB_3x3[1] = this.d2;
            this.d3 = matrixOfCoefficients.get(11);
            matrixB_3x3[2] = this.d3;


        }

    }

    public static double[][] getMatrixA_2x2() {
        return matrixA_2x2;
    }

    public static double[] getMatrixB_2x2() {
        return matrixB_2x2;
    }

    public static double[][] getMatrixA_3x3() {
        return matrixA_3x3;
    }

    public static double[] getMatrixB_3x3() {
        return matrixB_3x3;
    }


// SLE SOLVER

    // Gaussian elimination with partial pivoting
    public double[] SLESolve(double[][] A, double[] b) {
        int n = b.length; // Matrix dimension

        // Eliminate non-zero elements below the diagonal
        for (int p = 0; p < n; p++) {
            int i;

            // find pivot row
            i = FindPivot(A, p, n);

            // If pivot row is not current row, swap
            if (i != p) SwapRow(A, b, p, i, n);

            // Now normalize if needed
            //param p, A, B
            NormRow(A, b, p, n);


            // Now zero out rows below
            LowerTri(A, b, p, n);

        }
        // Normalize last row
        // Avoid divide by zero
        NormRow(A, b, n - 1, n);


        // printMat(A,b,n);

        // Now eliminate off-diagonal elements above the diagonal
        // Start at bottom row and work way up
        UpperTri(A, b, n);

        //here should be the place we check for da free variables getting the rank is the step number uno
        int checkSol = 0;
        int[] freeVar = new int[n];
        for (int i = 0; i < n; i++) {
            int j = checkRow(A, b, i);
            if (j == 1) {
                freeVar[i] = 1;
            } else {
                freeVar[i] = 0;
            }
            checkSol += j;
        }

        //check for solutions + free variables
        int ns;
        if (n == 2) ns = n + 1;
        else ns = n + 2;
        double[] solution = new double[ns];

        if (checkSol < 0) {
            for (int i = ns - 1; i >= 0; i--)
                solution[i] = 0.0;
            // return solution;
        }
        if (checkSol == 0) {
            //back substitution

            HumptyDumptyIsHungry(A, b, n, solution);
            if (n == 2) solution[n] = 1;
            else {
                solution[n] = 1;
                solution[n + 1] = 1;
            }
        }
        if (checkSol > 0) {
            int sum = 0;
            // for index less than size of the size of matrix
            for (int i = 0; i < n; i++)
                //adding up number of free variables(check meth freeVar)
                sum += freeVar[i];


            //if the size is 2 and there is 1 free variable
            //the first element in the solution is just whatever was found for x
            solution[0] = b[0];
            if (n == 2) { //this is for 2x2
                //second element is the coefficient of the free variable and is brought to the other side of the equation hence the negative
                solution[1] = -A[0][1];
                //third element is just a placeholder to show that there is in fact a free variable in the solution, other than that it doesn't do much
                solution[2] = sum + 1;
            } else { //this is just for 3x3
                //showing that the first element in the solution is just the first element of the b-matrix
                //if there is one free variable
                if (sum == 1) {
                    //second element in the solution is the coefficient for the free variable for the 1st row(x1)
                    solution[1] = -A[0][2];
                    //the second element in the b-matrix is the first part of the y-comp because there 1 free variable
                    solution[2] = b[1];
                    //second coefficient for free variable(for the y-comp)
                    solution[3] = -A[1][2];
                } else {//if there are 2 free variables
                    //both sol1 and sol2 are coefficients for
                    solution[1] = -A[0][1];
                    solution[2] = -A[0][2];
                }
                //just to add a space so that there will be the proper length to store all teh variables
                solution[4] = sum + 1;
            }
            //just a little PSA if you felt like reading through all of this and
            // still don't really get it, and you really want to understand for some reason. you can ask me but the
            // chances I remember are low. this should be pretty self-explanatory tho
        }
        return solution;


    }

    static void HumptyDumptyIsHungry(double[][] A, double[] b, int n, double[] solution) {
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++)
                sum += A[i][j] * solution[j];
            solution[i] = (b[i] - sum) / A[i][i];
        }
    }


// FUNCTIONS USED FOR SOLVE

    //CHECK ROW
    private static int checkRow(double[][] A, double[] B, int row) {
        int N = A.length;
        int testA = 0;
        int testB = 0;

        //checking A and b are or parent 0
        for (int i = 0; i < N; i++) {
            if (A[row][i] != 0) {
                testA += 1;
            }
            if (B[row] != 0) {
                testB += 1;
            }
        }
        int plsWork;
        if (testA == 0) {
            if (testB != 0) {
                plsWork = -20;
                //no solutions
            } else {
                //FREE VARIABLE(s) EXISTS
                plsWork = 1; // number of free variables?
            }
        } else {
            //unique solution exits
            plsWork = 0;
        }
        return plsWork;
    }

    // PIVOT rows
    //@param a
    private int FindPivot(double[][] A, int col, int n) {
        int max = col;
        for (int i = col + 1; i < n; i++) {
            if (Math.abs(A[i][col]) > Math.abs(A[max][col])) {
                max = i; // row index with max value
            }
        }
        return max;
    }

    //S-WAP
    private static void SwapRow(double[][] A, double[] b, int row1, int row2, int n) {
        double temp;
        for (int i = 0; i < n; i++) {
            temp = A[row1][i];
            A[row1][i] = A[row2][i];
            A[row2][i] = temp;
        }
        temp = b[row1];
        b[row1] = b[row2];
        b[row2] = temp;
    }


    //NORMALIZE
    private static void NormRow(double[][] A, double[] b, int row, int n) {
        double maxV = A[row][row];
        if (maxV != 0) {
            for (int i = 0; i < n; i++)
                A[row][i] = A[row][i] / maxV;
            b[row] = b[row] / maxV;
        }

    }

    // lower triangular stuff
    private static void LowerTri(double[][] A, double[] b, int col, int n) {
        double factor = A[col][col];
        double alpha;
        if (factor != 0) for (int i = col + 1; i < n; i++) {
            alpha = A[i][col] / factor;
            // Recalculate augmented portion;
            b[i] -= alpha * b[col];
            // Reduce coefficients
            for (int j = 0; j < n; j++)
                A[i][j] -= alpha * A[col][j];
        }

    }

    //upper triangular eliminate stuff
    private static void UpperTri(double[][] A, double[] b, int n) {
        for (int p = n - 1; p >= 0; p--) {
            // Now zero out rows below
            for (int i = p - 1; i >= 0; i--) {
                // Avoid divide by zero and multiplying by zero
                if (A[i][p] != 0 && A[p][p] != 0) {
                    double alpha = A[i][p] / A[p][p];
                    // Recalculate augmented portion;
                    b[i] -= alpha * b[p];

                    // Reduce coefficients
                    for (int j = 0; j < n; j++) {
                        A[i][j] -= alpha * A[p][j];
                    }

                }
            }
        }
    }

    public String[] getConstants(double[] B) {
        return this.round(B);
    }

    public String[][] getCoefficients(double[][] A) {
        return this.round(A);
    }

    public String[] round(double[] x) {
        String[] lol = new String[x.length];
        for (int i = 0; i < x.length; i++) {
            lol[i] = String.format("%.2f", x[i]);
        }
        return lol;
    }

    private String[][] round(double[][] x) {
        String[][] lol = new String[x.length][x[0].length];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                lol[i][j] = String.format("%.2f", x[i][j]);
                if (lol[i][j].equals("-0.00")) {
                    lol[i][j] = "0.00";
                }
            }
        }
        return lol;
    }

    /**
     * This function returns a
     *
     * @param i random number to plug as x-value
     * @return point3D that's on the line that goes through
     */
    public Point3D solutionPointsLineOne(int i) {
        double y = (d1 - a1 * i) / a2;
        return new Point3D(i, y, 0);
    }

    public Point3D solutionPointsLineTwo(int i) {
        double y = (d2 - b1 * i) / b2;
        return new Point3D(i, y, 0);
    }
}
