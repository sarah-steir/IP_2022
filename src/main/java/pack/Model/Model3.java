package pack.Model;

import javafx.geometry.Point3D;
import pack.View.Customs.CustomTextField;

import java.util.ArrayList;

public class Model3 {
    //Planes
    public  ArrayList<Double> n1 = new ArrayList<>();
    public  ArrayList<Double> n2 = new ArrayList<>();
     double crossProduct[] = new double[3];
     private ArrayList<Double> numbers =new ArrayList<>();

    /**
     * Empty constructor for the planes
     */
    public Model3(){

     }

    /**
     * The function divides the textfields into 2 groups (for plane 1 and for plane 2) and stores the double value of the text on
     * their respective double arrayList
     * @param f The ArrayList that contains the Textfields on the plane section
     */
    public  void transform(ArrayList<CustomTextField> f) {

        for (int i = 0; i < 4; i++) {
            Double d = Double.parseDouble(f.get(i).getText());
            n1.add(d);
        }
        for (int j = 4; j < f.size(); j++) {
            Double d2 = Double.parseDouble(f.get(j).getText());
            n2.add(d2);
        }
    }

    //TODO GCD of the elements of the cross product
    /**
     * Calculates the direction vector of the string
     * @return an array with the direction vector (not simplified)
     */

    public  double[] crossProduct() {
        crossProduct[0] = n1.get(1) * n2.get(2) - n1.get(2) * n2.get(1);
        crossProduct[1] = n1.get(2) * n2.get(0) - n1.get(0) * n2.get(2);
        crossProduct[2] = n1.get(0) * n2.get(1) - n1.get(1) * n2.get(0);

        for (int i = 0; i < 3; i++) {
            System.out.print(crossProduct[i] + " ");
        }
        return  crossProduct;
    }

    /**
     * Nat's version do not erase yet, there is something I want to try
     * @param i
     * @return
     */
    public Point3D solutionPoints(int i) {
        double x = i;
        double z = ((n2.get(1) / n1.get(1)) * (n1.get(0) * x + n1.get(3)) - n2.get(0) * x - n2.get(3)) / (n2.get(2) - n1.get(2) * n2.get(1) / n1.get(1));
        double y = (-n1.get(2) * z - n1.get(0) * x - n1.get(3)) / n1.get(1);
        //double z= ((-n2.get(1)/n1.get(1))*(n1.get(0)*x+n1.get(3))-n2.get(0)*x- -n2.get(3)) /(n2.get(2)-n1.get(2)*n2.get(1)/n1.get(1));
        //double z=((-n2.get(1/n1.get(1)))*n1.get(3)-n2.get(3))/((n2.get(2)*n2.get(1)/n1.get(1))*n1.get(2));
        //double y=(-n1.get(3)-n1.get(2)*z)/n1.get(1);
        System.out.println("x: " + x + " y: " + y + " z: " + z);
        Point3D point1 = new Point3D(x, y, z);
        return point1;}

    /**
     * @param i  wether one or two
     * @return the equation of the plane given by i (1 or 2)
     */

    public  String planeEq(int i) {
        switch (i) {
            case 1:
                String st = n1.get(0).toString() + "x +" + n1.get(1).toString() + "y +" + n1.get(3).toString() + "z =" + n1.get(4).toString();
                return st;
            case 2:
                String st2 = n2.get(0).toString() + "x +" + n2.get(1).toString() + "y +" + n2.get(3).toString() + "z =" + n2.get(4).toString();
                return st2;}
        return null;}


    //Lines

    private static final double EPSILON = 1e-10;
    double a1, a2; // Row 1
    double b1, b2; // Row 2
    double d1, d2; // Last Column
    int n;

    // storing arraylist variables for the SLE calculation 2x2
    //A matrix storage
    public static double[][] matrixA_2x2 = {{1, 1}, {1, 1}};
    //B matrix storage
    public static double[] matrixB_2x2 = {1, 1};

    public Model3(ArrayList<Double> input,ArrayList<Double>constant) {
            n = 2;
            //Row 1
            this.a1 = input.get(0);
            matrixA_2x2[0][0] = this.a1;
            this.a2 = input.get(1);
            matrixA_2x2[0][1] = this.a2;

            //Row2
            this.b1 = input.get(2);
            matrixA_2x2[1][0] = this.b1;
            this.b2 = input.get(3);
            matrixA_2x2[1][1] = this.b2;

            //B Matrix (Constants)
            this.d1 = constant.get(0);
            matrixB_2x2[0] = this.d1;
            this.d2 = constant.get(1);
            matrixB_2x2[1] = this.d2;}

    public static double[][] getMatrixA_2x2() {return matrixA_2x2;}

    public static double[] getMatrixB_2x2() {return matrixB_2x2;}

    // Gaussian elimination with partial pivoting
    public double[] SLESolve(double[][] A, double[] b) {
        n = b.length;
        for (int p = 0; p < n; p++) {
            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;} }
            double[] temp = A[p];
            A[p] = A[max];
            A[max] = temp;
            double t = b[p];
            b[p] = b[max];
            b[max] = t;

            // singular or almost singular
            if (Math.abs(A[p][p]) <= EPSILON) {
                System.out.println("Matrix is singular or super close to being singular, try again :) ");}

            // pivot within A and b
            for (int i = p + 1; i < n; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];}}
        }

        // back substitution
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];}
            x[i] = (b[i] - sum) / A[i][i];}
        return x;}

    //TODO delete this because Constance figured it out
    /**
     * @return the point of intersection between the two lines (if they intersect)
     */
    public Point3D intersectionLines(){
        double [][]A = Model3.getMatrixA_2x2();
        double[] b = Model3.getMatrixB_2x2();
        double[] x = SLESolve(A, b);
        //S=x[0]

        double xpoint=numbers.get(0)*x[0]+numbers.get(1);
        double ypoint=numbers.get(2)*x[0]+numbers.get(3);
        double zpoint=numbers.get(4)*x[0]+numbers.get(5);

        Point3D solution= new Point3D(xpoint,ypoint,zpoint);

        return solution;}

    /**
     * @param i wether 1 or 2, for line 1 or line 2
     * @param t The value  that the direction vector will be multiplied by to find a point in the line i (x=1+3*t)
     * @return a point in the line i
     */
    public Point3D linesPoints(int i, int t){
        double xpoint=0;
        double ypoint=0;
        double zpoint=0;

        switch (i) {
        case 1:
            xpoint= numbers.get(0)*t+ numbers.get(1);
            ypoint= numbers.get(2)*t+ numbers.get(3);
            zpoint= numbers.get(4)*t+ numbers.get(5);
            return new Point3D(xpoint,ypoint,zpoint);

            case 2:
                xpoint= numbers.get(6)*t+ numbers.get(7);
                ypoint= numbers.get(8)*t+ numbers.get(9);
                zpoint= numbers.get(10)*t+ numbers.get(11);
                return new Point3D(xpoint,ypoint,zpoint);}

        return new Point3D(xpoint,ypoint,zpoint);}


    public double[] dirVector(int i){
        double[] direction= new double[3];
        switch (i) {
            case 1:
                direction[0]= numbers.get(0);
                direction[1]= numbers.get(2);
                direction[2]= numbers.get(4);
                return direction;

            case 2:
                direction[0]= numbers.get(6);
                direction[1]= numbers.get(8);
                direction[2]= numbers.get(10);
                return direction;
    } return null; }



    public boolean det() {
        double[][] d = { {numbers.get(7)- numbers.get(1), numbers.get(9) - numbers.get(3), numbers.get(11) - numbers.get(5)},
                {numbers.get(0), numbers.get(2), numbers.get(4)},
                {numbers.get(6), numbers.get(8), numbers.get(10)}};
        double sum = d[0][0] * ((d[1][1] * d[2][2]) - (d[1][2] * d[2][1]))-
                     d[0][1] * ((d[1][0] * d[2][2]) - (d[1][2] * d[2][0]))+
                   d[0][2] * ((d[1][0] * d[2][1]) - (d[2][0] * d[1][1]));

         if(sum==0) { return true;}
           else{ return false;}}


    public void bringT(ArrayList<CustomTextField>tf){
        for(CustomTextField i:tf){
            numbers.clear();
            numbers.add(Double.parseDouble(i.getText()));}}


}