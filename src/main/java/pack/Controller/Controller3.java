package pack.Controller;

import javafx.scene.text.Text;
import pack.Model.Model3;
import pack.View.Customs.CustomText;
import pack.View.View3;

public class Controller3 {

    View3 view;

    private Model3 model= new Model3();
    public Controller3(View3 view) {
        this.view = view;
    }

    public void getValues() {
        if(view.getRb1().isSelected()) {
        model.bringT(view.getFieldListRb1());
        model.setThingies(); }

        if(view.getRb2().isSelected()) { {model.transform(view.getFieldListRb2());}}}


    public String identifyLines(){

        if(model.parallel()==true) {return "parallel";}

        if(model.det()==true) { return "intersecting"; }

        if(model.det()==false) {return "skew";}

        return null;}


    public void addElementsGraph() {
        if(view.getRb1().isSelected()) {
        view.getGraph().addLine( model.linesPoints(1,0), model.linesPoints(1,2),model.dirVector(1));
        view.getGraph().addLine(model.linesPoints(2,0),model.linesPoints(2,2),model.dirVector(2));
     if(identifyLines()=="intersecting"){view.getGraph().addPoint(model.intersectionLines());}}

      if(view.getRb2().isSelected()) {
            model.crossProduct(model.n1,model.n2);
            model.solutionPoint();
            view.getGraph().addPlane(model.n1[3]/-model.n1[0],model.n1[3]/-model.n1[1],model.n1[3]/-model.n1[2],"Plane 1");
            view.getGraph().addPlane(model.n2[3]/-model.n2[0],model.n2[3]/-model.n2[1],model.n2[3]/-model.n2[2],"Plane 2");
            view.getGraph().addLine(model.solutions[0], model.solutions[1],model.crossProduct(model.n1,model.n2));}
    }


    public CustomText[] GenericTexts() {
        if(view.getRb1().isSelected()) {
            CustomText top= new CustomText("Vector equations of the lines: ");
            Text l = (Text) view.getGraph().labelsList.get(2);
            CustomText c = new CustomText(l.getText());
            Text l2 = (Text) view.getGraph().labelsList.get(5);
            CustomText c2 = new CustomText(l2.getText());
            CustomText[] custom = {top,c, c2};
            return custom;}

        else {
            CustomText top= new CustomText(" Equations of the planes: ");
            CustomText c= new CustomText(model.planeEq(1));
            CustomText c2= new CustomText(model.planeEq(2));
            CustomText [] custom= {top,c,c2};
            return custom;}
    }

    public CustomText[] SolutionTexts() {
        if(view.getRb1().isSelected()) {
        if(identifyLines()=="intersecting"){
            CustomText l= new CustomText("Solution: "+"S="+model.x[0]+"\n"+ "T="+model.x[1]+"\n");
            CustomText l2= new CustomText("Intersection point: <"+model.intersectionLines().getX()+", "+model.intersectionLines().getY()
           +", "+model.intersectionLines().getZ()+">");
            CustomText [] custom= {l,l2};
            return custom;}

        if(identifyLines()=="skew"){
            CustomText l= new CustomText("These two lines are skew"+"\n"
                                          +"The closest distance between" +"\n"+
                                          "them is: "+model.distanceSkew());
            CustomText [] custom= {l};

            return custom;}

        if(identifyLines()=="parallel"){
            CustomText l= new CustomText("These two lines are parallel"+"\n"
                                          +"No intersection point");
            CustomText [] custom= {l};

            return custom;

        }  }

        else {
            CustomText c=new CustomText("Line of intersection:");
            CustomText c1=new CustomText("Direction vector: <"+model.getCrossProduct()[0]+", "+model.getCrossProduct()[1]+", "+model.getCrossProduct()[2]+">");

            String s1=String.valueOf(model.solutions[0].getX());
            String s2=String.valueOf(model.solutions[0].getY());
            String s3=String.valueOf(model.solutions[0].getZ());


            if(model.getCrossProduct()[0]==0&&model.getCrossProduct()[1]==0&&model.getCrossProduct()[1]==0) {
                CustomText c4=new CustomText("Paralel planes. No intersection line");
                CustomText [] custom= {c4};

                return custom;
            }


            CustomText c2=new CustomText("Point: <"+model.solutions[0].getX()+", "+model.solutions[0].getY()+", "+model.solutions[0].getZ()+">");
            CustomText [] custom= {c,c1,c2};

            return custom;
        }

        return null;

    }








}