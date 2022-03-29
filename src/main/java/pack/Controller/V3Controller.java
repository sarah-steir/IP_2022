package pack.Controller;

import javafx.geometry.Point3D;
import javafx.scene.control.TextField;
import java.util.ArrayList;

public class V3Controller {
    static int n = 3;
    public static ArrayList<Double> n1=new ArrayList<Double>();
    public static ArrayList<Double> n2=new ArrayList<Double>();
    static double crossProduct[] = new double[3];



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

    public static Point3D point(int i) {

        double x=i;
        double z= ((n2.get(1)/n1.get(1))*(n1.get(0)*x+n1.get(3))-n2.get(0)*x- n2.get(3)) /(n2.get(2)-n1.get(2)*n2.get(1)/n1.get(1));
        double y=(-n1.get(2)*z-n1.get(0)*x-n1.get(3))/n1.get(1);
        //double z= ((-n2.get(1)/n1.get(1))*(n1.get(0)*x+n1.get(3))-n2.get(0)*x- -n2.get(3)) /(n2.get(2)-n1.get(2)*n2.get(1)/n1.get(1));
        //double z=((-n2.get(1/n1.get(1)))*n1.get(3)-n2.get(3))/((n2.get(2)*n2.get(1)/n1.get(1))*n1.get(2));
        //double y=(-n1.get(3)-n1.get(2)*z)/n1.get(1);

        System.out.println("x: "+x+" y: "+y+" z: "+z);

        Point3D point1 = new Point3D(x, y, z);


        return point1;

    }


    public static String st (int i) {

        switch (i) {
            case 1:
                String st= n1.get(0).toString()+"x +"+n1.get(1).toString()+"y +"+ n1.get(3).toString()+"z ="+ n1.get(4).toString();
                return st;


            case 2:
                String st2= n2.get(0).toString()+"x +"+n2.get(1).toString()+"y +"+ n2.get(3).toString()+"z ="+ n2.get(4).toString();
                return st2;

          }


        return null ;
    }






}
