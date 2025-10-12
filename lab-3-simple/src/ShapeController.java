import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class for controller
 */
public class ShapeController {
    private ShapeDb db;
    private ShapeView view;

    public ShapeController(ShapeView view, ShapeDb db) {
        this.view = view;
        this.db = db;
    }

    /**
     * Displays shapes from View Component
     */
    public void showShapes() {
        try {
            view.displayShapes(getShapes());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays total area of all shapes
     */
    public void showTotalArea() {
        double totalArea = sumAllShapesArea();
        view.displayTotalArea(totalArea);
    }

    /**
     * Displays total area by classes.Shape name
     * @param shapeName classes.Shape name
     */
    public void showTotalAreaByShapeType(String shapeName) {
        double totalArea = sumConcreteShapesArea(shapeName);
        view.displayTotalAreaByShapeType(totalArea, shapeName);
    }

    /**
     * Displays Shapes sorted by area
     */
    public void showShapesSortedByArea() {
        List<Shape> sortedShapes = sortByArea();
        view.displaySortedByArea(sortedShapes);
    }

    /**
     * Displays Shapes sorted by color
     */
    public void showShapesSortedByColor() {
        List<Shape> sortedShapes = sortByColor();
        view.displaySortedByColor(sortedShapes);
    }

    /**
     * Returns all Shapes
     * @return all Shapes
     */
    public List<Shape> getShapes() {
        return db.getShapes();
    }

    /**
     * Returns sum of all Shapes' area
     * @return sum of all Shapes' area
     */
    public double sumAllShapesArea() {
        return getShapes()
                .stream()
                .mapToDouble(Shape::calcArea)
                .sum();
    }

    /**
     * Returns sum of all Shapes' area by type
     * @param shapeName Shape name
     * @return sum of all Shapes' area by type
     */
    public double sumConcreteShapesArea(String shapeName) {
        return getShapes()
                .stream()
                .filter(shape -> shape.getClass().getSimpleName().equals(shapeName))
                .mapToDouble(Shape::calcArea)
                .sum();
    }

    /**
     * Returns ArrayList of Shapes sorted by area
     * @return ArrayList of Shapes sorted by area
     */
    public List<Shape> sortByArea() {
        Comparator<Shape> comparator = Comparator.comparingDouble(Shape::calcArea);
        getShapes().sort(comparator);

        return new ArrayList<>(getShapes());
    }

    /**
     * Returns ArrayList of Shapes sorted by color
     * @return ArrayList of Shapes sorted by color
     */
    public List<Shape> sortByColor() {
        Comparator<Shape> comparator = Comparator.comparing(Shape::getShapeColor);
        getShapes().sort(comparator);

        return new ArrayList<>(getShapes());
    }


}