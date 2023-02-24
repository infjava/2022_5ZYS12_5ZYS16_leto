public class Main {
    public static void main(String[] args) {
        Student fero = new Student("Ferko", "Mrkvicka", "543");
        Skupina skupina = new Skupina("F007");
        skupina.pridaj(fero);
        skupina.vypis();

    }
}
