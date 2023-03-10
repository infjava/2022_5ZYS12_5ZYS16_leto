package sk.uniza.fri.wof.prostredie.predmety;

public class ObycajnyPredmet implements Predmet {
    private final String nazov;

    public ObycajnyPredmet(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public String getNazov() {
        return this.nazov;
    }

    @Override
    public void pouzi() {
        System.out.printf("Predmet %s sa použiť nedá%n", this.nazov);
    }

    @Override
    public boolean mozemPolozit() {
        return true;
    }

    @Override
    public boolean jeNasadeny() {
        return false;
    }
}
