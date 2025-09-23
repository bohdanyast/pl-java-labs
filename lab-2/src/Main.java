import java.util.Scanner;

/**
 * The class of the main app
 */
public class Main implements AppConstants {
    static Journal journal = new Journal();

    public static void main(String[] args) {
        System.out.println(RULES);

        Scanner sc = new Scanner(System.in);

        try {
            while (true) {
                String surname = handleInput(SURNAME_PROMPT, INITIALS_REGEX, sc, SURNAME_OUTPUT_ERROR);
                String name = handleInput(NAME_PROMPT, INITIALS_REGEX, sc, NAME_OUTPUT_ERROR);
                String birthday = handleInput(BIRTHDAY_PROMPT, BIRTHDAY_REGEX, sc, BIRTHDAY_OUTPUT_ERROR);
                String phone = handleInput(PHONE_PROMPT, PHONE_REGEX, sc, PHONE_OUTPUT_ERROR);
                String address = handleInput(ADDRESS_PROMPT, ADDRESS_REGEX, sc, ADDRESS_OUTPUT_ERROR);

                journal.addStudent(new Student(surname, name, birthday, phone, address));

                System.out.println(STUDENT_ADDED);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            sc.close();
        }
    }

    /**
     * Handles scanner input
     * @param prompt message to show in console before typing
     * @param regex regex for input match checking
     * @param sc current scanner
     * @param mistakeMessage message to show if it doesn't match given regex
     * @return String with output
     * @throws RuntimeException if user typed /exit for exiting the app
     */
    private static String handleInput(String prompt, String regex, Scanner sc, String mistakeMessage) {
        String input; // Getting the input

        while (true) {
            System.out.println(prompt); // Printing a prompt
            input = sc.nextLine(); // Current line from scanner

            if (input.matches(regex)) { // If matching regex. then returning the output for handling other fields
                return input;
            } else if (input.equals(GET_STUDENTS_COMMAND)) { // If /students, print all students
                journal.printStudents();
            } else if (input.equals(EXIT_COMMAND)) { // If /exit, close the app
                throw new RuntimeException(EXIT_COMMAND_MESSAGE);
            } else if (input.contains(REMOVE_STUDENT_COMMAND)) { // If /remove [id], then removing the student
                try {
                    // Getting the student ID from the input
                    int studentId = Integer.parseInt(input.split(" ")[1]);

                    Student student = journal.getStudents().get(studentId);
                    journal.removeStudent(student);

                    System.out.println(STUDENT_DELETED);
                } catch (NumberFormatException e) { // If ID is not int
                    System.out.println(NON_NUMERIC_ID);
                } catch (IndexOutOfBoundsException e) { // If ID is out of bounds
                    System.out.println(STUDENT_NOT_FOUND);
                }
            } else {
                System.out.println(mistakeMessage);
            }
        }
    }

}