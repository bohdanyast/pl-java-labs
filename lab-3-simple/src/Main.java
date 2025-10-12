public class Main {
    public static void main(String[] args) {
        ShapeView view = new ShapeView();
        ShapeDb db = new ShapeDb();
        ShapeController controller = new ShapeController(view, db);

        // All shapes showcasing
        controller.showShapes();

        // Overall shapes area
        controller.showTotalArea();

        // Відображення площі конкретних фігур (наприклад, для трикутників)
        controller.showTotalAreaByShapeType("Triangle");

        // Відображення фігур, відсортованих за площею
        controller.showShapesSortedByArea();

        // Відображення фігур, відсортованих за кольором
        controller.showShapesSortedByColor();
    }
}
