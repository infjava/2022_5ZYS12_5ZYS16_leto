package sk.uniza.fri.wof.prostredie.predmety;

import sk.uniza.fri.wof.zaklad.Hrac;

public interface Predmet {
    String getNazov();

    void pouzi(Hrac hrac);

    boolean mozemPolozit();
}
