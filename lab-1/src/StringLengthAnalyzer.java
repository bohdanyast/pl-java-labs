import java.util.ArrayList;

public class StringLengthAnalyzer {
    /**
     * Returns an array of strings, that have lower length than average line length og the input array
     * @param items the array to work with
     * @return an array based on method description
     */
    public static String[] lowerThanAvgItemsLength(String[] items) {
        double avgLength = getAverageItemsLength(items);
        ArrayList<String> list = new ArrayList<>();

        if (items.length == 0) {
            throw new IllegalArgumentException("items array is empty");
        }

        for (String item : items) {
            if (item.length() < avgLength) {
                list.add(item);
            }
        }

        System.out.println("Average items length: " + getAverageItemsLength(items));

        return list.toArray(new String[0]);
    }

    /**
     * Returns the average items length of the array
     * @param items the array to work with
     * @return the average items length of the array
     */
    private static double getAverageItemsLength(String[] items) {
        int sumOfLengths = 0;

        if (items.length == 0) {
            throw new IllegalArgumentException("items array is empty");
        }

        for (String item : items) {
            sumOfLengths += item.length();
        }

        return sumOfLengths / (double) items.length;
    }
}
