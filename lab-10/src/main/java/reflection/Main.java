package reflection;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String str1 = "Hello world";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Рядок з клавіатури: ");
        String str2 = scanner.nextLine();
        scanner.close();

        System.out.println("\nПеред зміною:");
        System.out.println("Строковий літерал: " + str1);
        System.out.println("З клавіатури: " + str2);

        changeStringValue(str1, "CocaCola");
        changeStringValue(str2, "Fanta");

        System.out.println("\nПісля зміни:");
        System.out.println("Строковий літерал: " + str1);
        System.out.println("З клавіатури: " + str2);
    }

    private static void changeStringValue(String str, String newValue) throws NoSuchFieldException, IllegalAccessException {
        Class<?> stringClass = String.class;

        Field valueField = stringClass.getDeclaredField("value");
        valueField.setAccessible(true);

        byte[] valueArray = newValue.getBytes(StandardCharsets.UTF_8);
        byte[] newBackingArray = new byte[valueArray.length];
        System.arraycopy(valueArray, 0, newBackingArray, 0, valueArray.length);
        valueField.set(str, newBackingArray);
    }
}