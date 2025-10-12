import java.util.ArrayList;

/**
 * Class for keeping ArrayList of classes.Shape objects
 */
public class ShapeDb {
    private ArrayList<Shape> shapes;

    public ShapeDb() {
        shapes = new ArrayList<>();
        shapes.add(new Triangle("Green", 3, 4, 5));
        shapes.add(new Triangle("Yellow", 6, 8, 10));
        shapes.add(new Triangle("Blue", 13, 5, 12));
        shapes.add(new Triangle("Red", 10, 6, 12));

        shapes.add(new Circle("Green", 4));
        shapes.add(new Circle("Yellow", 5));
        shapes.add(new Circle("Blue", 6));
        shapes.add(new Circle("Red", 7));

        shapes.add(new Rectangle("Green", 12, 13));
        shapes.add(new Rectangle("Yellow", 10, 43));
        shapes.add(new Rectangle("Blue", 6, 23));
        shapes.add(new Rectangle("Red", 7, 33));

    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }
}
