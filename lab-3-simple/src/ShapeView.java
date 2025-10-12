import java.util.List;

public class ShapeView {

    // Метод для відображення всіх фігур у списку
    public void displayShapes(List<Shape> shapes) {
        if (shapes.isEmpty()) {
            System.out.println("No shapes to display.");
        } else {
            for (Shape shape : shapes) {
                System.out.println(shape);
            }
        }
    }

    // Метод для відображення суми площ всіх фігур
    public void displayTotalArea(double totalArea) {
        System.out.println("Total area of all shapes: " + totalArea);
    }

    // Метод для відображення суми площ конкретних фігур за типом
    public void displayTotalAreaByShapeType(double totalArea, String shapeType) {
        System.out.println("Total area of " + shapeType + " shapes: " + totalArea);
    }

    // Метод для відображення результату сортування за площею
    public void displaySortedByArea(List<Shape> shapes) {
        System.out.println("Shapes sorted by area:");
        for (Shape shape : shapes) {
            System.out.println(shape + ": " + shape.calcArea());
        }
    }

    // Метод для відображення результату сортування за кольором
    public void displaySortedByColor(List<Shape> shapes) {
        System.out.println("Shapes sorted by color:");
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }
}
