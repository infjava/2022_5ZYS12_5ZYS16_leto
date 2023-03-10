package sk.uniza.fri.wof.prostredie.predmety;

import sk.uniza.fri.wof.zaklad.Hrac;

public class Baterky implements Predmet, Pouzitelny {
    @Override
    public String getNazov() {
        return "baterky";
    }

    @Override
    public void pouzi(Hrac hrac) {
        var radio = hrac.najdiPredmet("radio");
        if (radio.isEmpty()) {
            System.out.println("Predmet baterky sa neda pouzit");
        } else {
            System.out.println("Baterky si vlozil do radia");
            ((Radio) radio.get()).vlozBaterky();
        }
    }

    @Override
    public boolean mozemPolozit() {
        return true;
    }
}
