package sk.uniza.fri.wof.prostredie.vybavenie;

import sk.uniza.fri.wof.prostredie.predmety.ObycajnyPredmet;
import sk.uniza.fri.wof.prostredie.predmety.Pouzitelny;
import sk.uniza.fri.wof.zaklad.Hrac;

public class Automat implements Vybavenie, Pouzitelny {
    @Override
    public String getNazov() {
        return "automat";
    }

    @Override
    public String getPopis() {
        return "Výpredaj bagiet a minerálok";
    }

    @Override
    public void pouzi(Hrac hrac) {
        hrac.pridajDoInventara(new ObycajnyPredmet("bageta"));
        System.out.println("Dostal si bagetu");
    }
}
