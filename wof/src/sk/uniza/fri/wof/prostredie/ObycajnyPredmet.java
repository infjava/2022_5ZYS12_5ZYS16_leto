package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.zaklad.Hrac;

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
    public void pouziPredmet(Hrac hrac) {
        System.out.printf("%s sa neda pouzit%n", this.nazov);
    }

    @Override
    public boolean daSaPolozit() {
        return true;
    }
}
