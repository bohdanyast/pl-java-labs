/**
 * Interface for keeping all the constants
 */
public interface AppConstants {
    String RULES =
            """
            Вітаємо у консольному додатку!
            Для додавання студента необхідні: прізвище, ім'я, дата народження, номер телефону, адреса.
            Патерни вводу даних:
                - Прізвище та ім'я: Підтримують ввід від двох кириличних чи латинських літер.
                Також можливі дефіс та апостроф (приклад: Косач-Квітка, Мар'яна).
                - День народження: dd-mm-YYYY.
                - Телефон: початок з +, код країни будь-який.
                - Адреса: [назва вулиці], [будинок], [квартира].
           
            Примітки:
                - Назва вулиці: Підтримують ввід від одної української кириличної літери.
                Також можливі дефіс, крапка, пробіл та апостроф (приклад: вул. Лариси Косач-Квітки)
                - Будинок: цифра / цифра + буква
                - Квартира: цифра
           
            Команди:
                - /students - перегляд всіх студентів
                - /remove [id] - видалення студента з [id]-ідентифікатором (початок з 0)
                - /exit - вихід із програми
           
           """;

    // Prompts' texts
    String SURNAME_PROMPT = "Введіть прізвище:";
    String NAME_PROMPT = "Введіть ім'я:";
    String BIRTHDAY_PROMPT = "Введіть день народження:";
    String PHONE_PROMPT = "Введіть номер телефону:";
    String ADDRESS_PROMPT = "Введіть адресу:";

    // Regexps for all the fields
    String INITIALS_REGEX = "^[a-zA-Zа-яїєґіА-ЯЇЄҐІ'-]{2,}$";
    String BIRTHDAY_REGEX = "(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19|20)\\d{2}";
    String PHONE_REGEX = "^[+]\\d+";
    String ADDRESS_REGEX = "^[а-яїєґіА-ЯЇЄҐІ'\\s-.\\d]+, \\d+[а-яїєґіА-ЯЇЄҐІ'\\s-.]*, [1-9]+$";

    // Error-output messages
    String SURNAME_OUTPUT_ERROR = "Прізвище містить менше 2 літер, або містить неукраїнські і нелатинські символи.";
    String NAME_OUTPUT_ERROR = "Ім1я містить менше 2 літер, або містить неукраїнські і нелатинські символи.";
    String BIRTHDAY_OUTPUT_ERROR = "Формат дати mm-dd-YYYY / Можливе неправильне введення місяця, дати чи року.";
    String PHONE_OUTPUT_ERROR = "Телефон має починатися з + / Неправильне написання";
    String ADDRESS_OUTPUT_ERROR = "Адреса: [назва вулиці], [будинок], [квартира]. Дивіться примітки.";
    String NON_NUMERIC_ID = "Неправильне написання ID, має бути номер після /remove.";
    String STUDENT_NOT_FOUND = "Студента з таким ID не знайдено.";

    // Commands for using in the app
    String EXIT_COMMAND = "/exit";
    String GET_STUDENTS_COMMAND = "/students";
    String REMOVE_STUDENT_COMMAND = "/remove";

    // Success-output messages
    String STUDENT_ADDED = "Студента додано успішно!";
    String STUDENT_DELETED = "Студента видалено успішно!";

    // Exception messages
    String EXIT_COMMAND_MESSAGE = "Thanks for using!";

}
