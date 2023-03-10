package sk.uniza.fri.wof.prostredie.vychody;

import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.zaklad.Hrac;

public interface Vychod {
    Miestnost dajMiestnost();

    boolean mozemVstupit(Hrac hrac);
}
