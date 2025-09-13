import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] testArray = {"dfddgfd", "dfdgfd", "sdggfdg", "fddfgdgf", "dfgdfjkgndg", "ffjkndgd"};
        String[] stringsLowerThanAvg = StringLengthAnalyzer.lowerThanAvgItemsLength(testArray);
        System.out.println(Arrays.toString(stringsLowerThanAvg));

    }
}