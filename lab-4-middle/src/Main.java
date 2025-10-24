import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};

        MyList<String> brands = new MyArrayList<>();
        brands.add("Microsoft");
        brands.add("W3Schools");
        brands.add("Apple");

        brands.addAll(1, cars);

        System.out.println(brands);

        Object[] brandsArr = brands.toArray();
        System.out.println(Arrays.toString(brandsArr));

        MyList<String> brands2 = new MyLinkedList<>();
        brands2.add("Microsoft");
        brands2.add("W3Schools");
        brands2.add("Apple");
        brands2.add("Mazda");
    }
}
