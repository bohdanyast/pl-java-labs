import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] testArray = {"dfddgfd", "dfdgfd", "sdggfdg", "fddfgdgf", "dfgdfjkgndg", "ffjkndgd"};
        System.out.println(Arrays.toString(itemLengthLowerThanAvg(testArray)));
    }

    private static String[] itemLengthLowerThanAvg(String[] items) {
        double avgLength = getAverageLength(items);
        String[] result = new String[items.length];

        for (int i = 0; i < items.length; i++) {
            if (items[i].length() < avgLength) {
                result[i] = items[i];
            }
        }

        return result;

    }

    private static double getAverageLength(String[] items) {
        int sumOfLenghts = 0;

        for (String item : items) {
            sumOfLenghts += item.length();
        }

        return sumOfLenghts / (double) items.length;
    }
}