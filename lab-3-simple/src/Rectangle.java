public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String shapeColor, double width, double height) {
        super(shapeColor);

        if (width <= 0) {
            throw new IllegalArgumentException("width must be positive");
        }

        if (height <= 0) {
            throw new IllegalArgumentException("height must be positive");
        }

        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void draw() {

    }

    @Override
    public double calcArea() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Rectangle [color=" + super.toString() + ", width=" + width + ", height=" + height + "]";
    }
}
