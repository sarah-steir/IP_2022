package pack.Controller;

import javafx.scene.text.Text;
import pack.Model.Model3;
import pack.View.Customs.CustomText;
import pack.View.View3;

public class Controller3 {
    View3 view;
    private final Model3 model = new Model3();

    public Controller3(View3 view) {
        this.view = view;
    }


    /**
     * Gets the values from the  corresponding text fields
     */
    public void getValues() {
        if (view.getRb1().isSelected()) {
            model.bringT(view.getFieldListRb1());
            model.setMatrices();
        }

        if (view.getRb2().isSelected()) {
            model.transform(view.getFieldListRb2());
        }
    }

    /**
     * Identifies the type of line for correct handling
     *
     * @return String with the type of line
     */
    public String identifyLines() {
        if (model.parallel()) {
            return "parallel";
        }
        if (model.det()) {
            return "intersecting";
        }
        if (!model.det()) {
            return "skew";
        }
        return null;
    }

    /**
     * Adds elements of the graph for lines or planes and considers all the cases
     */
    public void addElementsGraph() {
        if (view.getRb1().isSelected()) {
            view.getGraph().addLine(model.linesPoints(1, 0), model.linesPoints(1, 2), model.dirVector(1));
            view.getGraph().addLine(model.linesPoints(2, 0), model.linesPoints(2, 2), model.dirVector(2));
            if (identifyLines().equals("intersecting")) {
                view.getGraph().addPoint(model.intersectionLines());
            }
        }

        if (view.getRb2().isSelected()) {
            model.crossProduct(model.n1, model.n2);
            model.solutionPoint();
            view.getGraph().addPlane(model.n1[3] / -model.n1[0], model.n1[3] / -model.n1[1], model.n1[3] / -model.n1[2]);
            view.getGraph().addPlane(model.n2[3] / -model.n2[0], model.n2[3] / -model.n2[1], model.n2[3] / -model.n2[2]);
            view.getGraph().addLine(model.solutions[0], model.solutions[1], model.crossProduct(model.n1, model.n2));
        }
    }

    /**
     * @return array of custom texts for the generic texts (equation of line and plane)
     */
    public CustomText[] GenericTexts() {
        if (view.getRb1().isSelected()) {
            CustomText top = new CustomText("Vector equations of the lines: ");
            Text l = (Text) view.getGraph().labelsList.get(0);
            CustomText c = new CustomText(l.getText());
            Text l2 = (Text) view.getGraph().labelsList.get(1);
            CustomText c2 = new CustomText(l2.getText());
            return new CustomText[]{top, c, c2};
        } else {
            CustomText top = new CustomText(" Equations of the planes: ");
            CustomText c = new CustomText(model.planeEq(1));
            CustomText c2 = new CustomText(model.planeEq(2));
            return new CustomText[]{top, c, c2};
        }
    }


    /**
     * @return solution text for lines and planes (each case is considered here as well to add
     * the correct output
     */
    public CustomText[] SolutionTexts() {
        if (view.getRb1().isSelected()) {
            if (identifyLines().equals("intersecting")) {
                CustomText l = new CustomText("Solution: " + "S=" + model.x[0] + "\n" + "T=" + model.x[1] + "\n");
                CustomText l2 = new CustomText("Intersection point: <" + model.getPoint()[0] + ", " + model.getPoint()[1] + ", " + model.getPoint()[2] + ">");
                return new CustomText[]{l, l2};
            }

            if (identifyLines().equals("skew")) {
                CustomText l = new CustomText("These two lines are skew" + "\n" + "The closest distance between" + "\n" + "them is: " + model.getDistance()[0]);
                return new CustomText[]{l};
            }

            if (identifyLines().equals("parallel")) {
                CustomText l = new CustomText("These two lines are parallel" + "\n" + "No intersection point");

                return new CustomText[]{l};

            }
        } else {
            CustomText c = new CustomText("Line of intersection:");
            CustomText c1 = new CustomText("Direction vector: <" + model.getCrossProductSt()[0] + ", " + model.getCrossProductSt()[1] + ", " + model.getCrossProductSt()[2] + ">");

            if (model.getCrossProduct()[0] == 0 && model.getCrossProduct()[1] == 0 && model.getCrossProduct()[1] == 0) {
                CustomText c4 = new CustomText("Parallel planes. No intersection line");
                return new CustomText[]{c4};
            }

            CustomText c2 = new CustomText("Point: <" + model.getSolutions()[0] + ", " + model.getSolutions()[1] + ", " + model.getSolutions()[2] + ">");
            return new CustomText[]{c, c1, c2};
        }
        return null;
    }

}