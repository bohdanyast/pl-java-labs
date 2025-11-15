import java.util.Arrays;
import java.util.OptionalDouble;

public class StringLengthAnalyzer {
    /**
     * Returns an array of strings, that have lower length than average line length og the input array
     * @param items the array to work with
     * @return an array based on method description
     */
    public static String[] lowerThanAvgItemsLength(String[] items) {
        double avgLength = getAverageItemsLength(items);
        if (items.length == 0) {
            throw new IllegalArgumentException("items array is empty");
        }

        return Arrays
                .stream(items)
                .filter(item -> item.length() < avgLength)
                .toArray(String[]::new);
    }

    /**
     * Returns the average items length of the array
     * @param items the array to work with
     * @return the average items length of the array
     */
    private static double getAverageItemsLength(String[] items) {
        if (items.length == 0) {
            throw new IllegalArgumentException("items array is empty");
        }

        return Arrays
                .stream(items)
                .mapToInt(String::length)
                .average()
                .orElse(0);
    }
}
