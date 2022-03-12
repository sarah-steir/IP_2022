package pack.Controller;

import javafx.scene.control.TextField;
import pack.View.View3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class V2Controller {
    static int n = 3;
    static ArrayList<Double> n1=new ArrayList<Double>();
    static ArrayList<Double> n2=new ArrayList<Double>();
    static double crossProduct[] = new double[3];

    public static void main(String[] args) {

        Integer n[] = {  1, 2, -1 ,-5}; //note to self. When getting the last result from textfield. Make it negative OR on textfield go x+y+z+d=0
        Integer m[] = {  1, -4, 1, -3};





       // point(n,m);

    }

    public static void transform(ArrayList <TextField> f){
        for (int i=0; i<4;i++){
           Double d= Double.parseDouble( f.get(i).getText());
           n1.add(d);
        }

        for (int j=4;j<f.size();j++){
            Double d2= Double.parseDouble( f.get(j).getText());
            n2.add(d2);

        }

    }


    public static void crossProduct() {
        crossProduct[0] = n1.get(1) * n2.get(2) - n1.get(2) * n2.get(1);
        crossProduct[1] = n1.get(2) * n2.get(0) - n1.get(0) * n2.get(2);
        crossProduct[2] = n1.get(0) * n2.get(1) - n1.get(1) * n2.get(0);

        for (int i = 0; i < 3; i++) {
            System.out.print(crossProduct[i] + " ");}
    }

    public static void point() {
        Random rn=new Random();
        int x=rn.nextInt(10) + 1;
        double z= ((n2.get(1)/n1.get(1))*(n1.get(0)*x+n1.get(3))-n2.get(0)*x- n2.get(3)) /(n2.get(2)-n1.get(2)*n2.get(1)/n1.get(1));
        double y=(-n1.get(2)*z-n1.get(0)*x-n1.get(3))/n1.get(1);
        //double z= ((-n2.get(1)/n1.get(1))*(n1.get(0)*x+n1.get(3))-n2.get(0)*x- -n2.get(3)) /(n2.get(2)-n1.get(2)*n2.get(1)/n1.get(1));
        //double z=((-n2.get(1/n1.get(1)))*n1.get(3)-n2.get(3))/((n2.get(2)*n2.get(1)/n1.get(1))*n1.get(2));
        //double y=(-n1.get(3)-n1.get(2)*z)/n1.get(1);


        System.out.println("x: "+x+" y: "+y+" z: "+z);

    }

    public static void clearLists(){
        n1.clear();
        n2.clear();
    }

}
