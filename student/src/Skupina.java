import java.util.ArrayList;

public class Skupina {
    private final String cisloSkupiny;
    private final ArrayList<Student> zoznamStudentov;

    public Skupina(String cisloSkupiny) {
        this.cisloSkupiny = cisloSkupiny;
        this.zoznamStudentov = new ArrayList<>();
    }

    public String getCisloSkupiny() {
        return this.cisloSkupiny;
    }

    public void pridaj(Student student) {
        this.zoznamStudentov.add(student);
    }

    public void vypis() {
        for (Student student : this.zoznamStudentov) {
            System.out.println(student);
        }
    }
}
