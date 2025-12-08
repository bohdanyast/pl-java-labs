import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Test 1: [one, two, six]
        String[] testArray = {"one", "two", "three", "four", "five", "six"};
        String[] stringsLowerThanAvg = StringLengthAnalyzer.lowerThanAvgItemsLength(testArray);
        System.out.println(Arrays.toString(stringsLowerThanAvg) + "\n");

        // Test 2: [short, medium, long]
        String[] testArray2 = {"short", "medium", "a very long string", "long"};
        String[] stringsLowerThanAvg2 = StringLengthAnalyzer.lowerThanAvgItemsLength(testArray2);
        System.out.println(Arrays.toString(stringsLowerThanAvg2) + "\n");

        // Test 3: []
        String[] testArray3 = {"superlongword"};
        String[] stringsLowerThanAvg3 = StringLengthAnalyzer.lowerThanAvgItemsLength(testArray3);
        System.out.println(Arrays.toString(stringsLowerThanAvg3) + "\n");

        // Test 4: [short, tiny]
        String[] testArray4 = {"this is a very long string", "short", "medium length string", "tiny"};
        String[] stringsLowerThanAvg4 = StringLengthAnalyzer.lowerThanAvgItemsLength(testArray4);
        System.out.println(Arrays.toString(stringsLowerThanAvg4) + "\n");

        // Test 5: [abc, abcd]
        String[] testArray5 = {"abc", "abcd", "abcdef", "abcdefg", "abcdefgh"};
        String[] stringsLowerThanAvg5 = StringLengthAnalyzer.lowerThanAvgItemsLength(testArray5);
        System.out.println(Arrays.toString(stringsLowerThanAvg5) + "\n");

        // Test 6: [hello, world, java]
        String[] testArray6 = {"hello", "world", "java", "programming"};
        String[] stringsGreaterThanAvg6 = StringLengthAnalyzer.lowerThanAvgItemsLength(testArray6);
        System.out.println(Arrays.toString(stringsGreaterThanAvg6) + "\n");

        // Test 7: []
        String[] testArray7 = {"dog", "cat", "bat", "rat"};
        String[] stringsLowerThanAvg7 = StringLengthAnalyzer.lowerThanAvgItemsLength(testArray7);
        System.out.println(Arrays.toString(stringsLowerThanAvg7) + "\n");

        // Test 8: [bb, c, ee]
        String[] testArray8 = {"aaa", "bb", "c", "dddd", "ee"};
        String[] stringsLowerThanAvg8 = StringLengthAnalyzer.lowerThanAvgItemsLength(testArray8);
        System.out.println(Arrays.toString(stringsLowerThanAvg8) + "\n");

        // Test 9: [a, aa]
        String[] testArray9 = {"a", "aa", "aaa", "aaaa", "aaaaa"};
        String[] stringsLowerThanAvg9 = StringLengthAnalyzer.lowerThanAvgItemsLength(testArray9);
        System.out.println(Arrays.toString(stringsLowerThanAvg9) + "\n");

        // Test 10: [hi, ok, no]
        String[] testArray10 = {"hi", "ok", "no", "yes", "maybe"};
        String[] stringsLowerThanAvg10 = StringLengthAnalyzer.lowerThanAvgItemsLength(testArray10);
        System.out.println(Arrays.toString(stringsLowerThanAvg10) + "\n");

        // Test 11: [date, fig]
        String[] testArray11 = {"apple", "banana", "cherry", "date", "fig"};
        String[] stringsLowerThanAvg11 = StringLengthAnalyzer.lowerThanAvgItemsLength(testArray11);
        System.out.println(Arrays.toString(stringsLowerThanAvg11) + "\n");

        // Test 12: [test, java, code]
        String[] testArray12 = {"test", "java", "code", "ragatha", "plumber"};
        String[] stringsLowerThanAvg12 = StringLengthAnalyzer.lowerThanAvgItemsLength(testArray12);
        System.out.println(Arrays.toString(stringsLowerThanAvg12) + "\n");

//        // Test 13: Exception
//        String[] testArray13 = {};
//        String[] stringsLowerThanAvg13 = StringLengthAnalyzer.lowerThanAvgItemsLength(testArray13);
//        System.out.println(Arrays.toString(stringsLowerThanAvg13) + "\n");

    }
}