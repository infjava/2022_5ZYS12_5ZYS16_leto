package sk.uniza.fri.wof.prostredie.predmety;

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
    public boolean mozemPolozit() {
        return true;
    }
}
