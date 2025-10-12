public class Triangle extends Shape {
    private double a;
    private double b;
    private double c;

    public Triangle(String shapeColor, double a, double b, double c) {
        super(shapeColor);
        this.a = a;
        this.b = b;
        this.c = c;

        if (a <= 0) {
            throw new IllegalArgumentException("Side 'a' must be positive.");
        }
        if (b <= 0) {
            throw new IllegalArgumentException("Side 'b' must be positive.");
        }
        if (c <= 0) {
            throw new IllegalArgumentException("Side 'c' must be positive.");
        }
        if (a + b < c || a + c < b || b + c < a) {
            throw new IllegalArgumentException("The sum of any two sides must be greater than the third side.");
        }

    }

    public double getA() {
        return a;
    }
    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override
    public void draw() {

    }

    @Override
    public double calcArea() {
        double p = 0.5 * (a + b + c);

        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public String toString() {
        return "Triangle [color=" + super.toString() + ", a=" + a + ", b=" + b + ", c=" + c + "]";
    }
}
