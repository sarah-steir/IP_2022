package pack.Controller;


import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.TextField;
import pack.View.View1;
import pack.View.iView;


public class Controller1 {

    public static View1 sleView = new View1();
    public static int sizeMatrix;
    public static String UI;

    public static void SleFinalCalc(ArrayList <TextField> f) {

        // setting the size of the matrix
        sizeMatrix = sizeOfMatrix(sizeMatrix);
        // getting the user input  for the coefficients of each variable and putting it into matrix form

        //write a function to get the input will be below

        ///okay so these should be entered into the textfields and then be added to an arraylist in said order inside of IP_2022

        Double[][] mat = new Double[sizeMatrix][sizeMatrix];
        Double[][] constants = new Double[sizeMatrix][1];

        //input
        for (int i = 0; i < sizeMatrix; i++) {

            for (int j = 0; j < sizeMatrix; j++) {
                UI = f.get(i).getText();
                Double input = Double.parseDouble(f.get(i).getText());
                mat[i][j] = input;
            }
            UI = ((TextField)(f.get(i))).getText();
            Double input = Double.parseDouble(f.get(i).getText());
            constants[i][0] = input;
        }


        //finding the inverse of the matrix
       Double inverted_mat[][] = invertMatrix(mat);
        for (int i = 0; i < sizeMatrix; ++i) {
            for (int j = 0; j < sizeMatrix; ++j) {
            }
        }
        //Multiplying inverse and constants
        Double result[][] = new Double[sizeMatrix][1];
        for (int i = 0; i < sizeMatrix; i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < sizeMatrix; k++) {
                   result[i][j] = result[i][j] + inverted_mat[i][k] * constants[k][j];
                }
            }
        }

        System.out.println("The answer is: ");
        for (int i = 0; i < sizeMatrix; i++) {
            System.out.println(result[i][0]);
        }


        //not sure what to do im too tired
    }

    //if the 2x2 option is selected then there are 6 textfield cells and 2 SLE's to solve(the following code does that np)
    //if the 3x3 option is selected then there are 12 textfield cells and 3 SLE's to solve


    public static int sizeOfMatrix(int sizeMat) {
        if (View1.twoo.isSelected()) {
            sizeMat = 2; //2 SLE 'S

            if (View1.threee.isSelected()) {
                sizeMat = 3; //3 SLE'S
            } else {
                System.out.println("idk how the hell you got here considering there are literally 2 buttons to select but " +
                        "congrats on finding some backdoor, sadly you need to go back and re-evaluate your life " +
                        "choices if this is the code you chose to hack.");
            }
        }
        return sizeMat;
    }

    //Method to inverse the matrix to perform aX=b => X=ba^-1 (solving for a^-1)
    //where X is the variable matrix [x1,x2...]

    public static Double[][] invertMatrix ( Double a[][]){
        int n = a.length;
        Double x[][] = new Double[n][n];
        Double b[][] = new Double[n][n];
        int index[] = new int[n];
        for (int i = 0; i < n; ++i)
           //b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussianElim(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];
        // Perform backward substitutions
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    // Method to carry out the partial-pivoting Gaussian elimination.  Here index[] stores the order in which the rows of the matrix are reduced
    //eliminates methodically based on pivoting order
    public static void gaussianElim ( Double a[][], int index[]){

        int n = index.length;
        double c[] = new double[n];


        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;


        // Find the rescaling factors, one from each row( factoring out (4 6 8) is equal to 2(2 3 4) )
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
        // Search the pivoting element from each column(making the elements below or above a leading 1 a 0)
        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }
            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];
                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;
                // Modify other elements accordingly
                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }


}