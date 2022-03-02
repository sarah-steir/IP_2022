package pack.Controller;

import java.util.Scanner;
import pack.View.View1;
import pack.View.iView;


public class Controller1 {
    View1 sleView = new View1();

    public static void SleFinalCalc() {
        // setting the value for # of equations based on radioButton value
//        int twoMat = (iView.a.size() + 1) / 3;  // will give a = 2 (the # of eq's)
//        int trèMat = (iView.b.size() + 1) / 4; //will give b = 3 (the # of eq's)


        //if the 2x2 option is selected then there are 6 textfield cells and 2 SLE's to solve(the following code does that np)
        //if the 3x3 option is selected then there are 12 textfield cells and 3 SLE's to solve
        if (View1.twoo.isSelected()) {
            int sizeMat= 2; //2 SLE 'S


            if (View1.threee.isSelected()) {
                sizeMat = 3; //3 SLE'S
            }
                else{
                System.out.println("idk how the hell you got here considering there are literally 2 buttons to select but " +
                        "congrats on finding some backdoor, sadly you need to go back and re-evaluate your life " +
                        "choices if this is the code you chose to hack.");
            }
        }
        /*now here is going to be solving for X, Y, and/or Z
            - first off the number of equations is selected(which automatically sets the # of equations)
            - then the calculate button will be clicked(the entire SleFinalCalc
              function will end up in the eventHandler for the button that
              starts the calculations)
            - then the user input will be injected into a matrix from an arraylist
            - after that the calculations will be done(code is already done for that (solves for x,y,z))
            - then the answer should be spit out on a label/maybe hBox? idfk ill figure that out when we come to it
                    -> the form will take on X=
                                             Y=
                                             Z=
                    -> then maybe ill print the matrix + imverse of the matrix? idk we'll get to that
                        when we come to it but for now this is the plan
         */
































    }

        //Method to inverse the matrix to perform aX=b => X=ba^-1 (solving for a^-1)
        //where X is the variable matrix [x1,x2...]

        public static double[][] invertMatrix ( double a[][]){
            int n = a.length;
            double x[][] = new double[n][n];
            double b[][] = new double[n][n];
            int index[] = new int[n];
            for (int i = 0; i < n; ++i)
                b[i][i] = 1;

            // Transform the matrix into an upper triangle
            gaussianElim(a, index);

            // Update the matrix b[i][j] with the ratios stored
            for (int i = 0; i < n - 1; ++i)
                for (int j = i + 1; j < n; ++j)
                    for (int k = 0; k < n; ++k)
                        b[index[j]][k]
                                -= a[index[j]][i] * b[index[i]][k];
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
        public static void gaussianElim ( double a[][], int index[]){

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
