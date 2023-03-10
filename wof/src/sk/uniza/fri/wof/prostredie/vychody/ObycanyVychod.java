package sk.uniza.fri.wof.prostredie.vychody;

import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.zaklad.Hrac;

public class ObycanyVychod implements Vychod {
    private final Miestnost miestnost;

    public ObycanyVychod(Miestnost miestnost) {
        this.miestnost = miestnost;
    }

    @Override
    public Miestnost dajMiestnost() {
        return this.miestnost;
    }

    @Override
    public boolean mozemVstupit(Hrac hrac) {
        return true;
    }
}
