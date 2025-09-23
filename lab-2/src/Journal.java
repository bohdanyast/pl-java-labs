import java.util.ArrayList;
import java.util.List;

/**
 * Journal for keeping all the students
 */
public class Journal {
    private List<Student> students;

    public Journal() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void printStudents() {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println(i + ") " + student);
        }
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }
}
