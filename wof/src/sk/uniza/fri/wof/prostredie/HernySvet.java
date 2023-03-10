package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.prostredie.predmety.*;
import sk.uniza.fri.wof.prostredie.vychody.VstupDoLabaku;

public class HernySvet {
    private final Miestnost startovaciaMiestnost;

    public HernySvet() {
        Miestnost terasa = new Miestnost("terasa - hlavny vstup na fakultu");
        Miestnost aula = new Miestnost("aula");
        Miestnost bufet = new Miestnost("bufet");
        Miestnost labak = new Miestnost("pocitacove laboratorium");
        Miestnost kancelaria = new Miestnost("kancelaria spravcu pocitacoveho laboratoria");

        // inicializacia miestnosti = nastavenie vychodov
        terasa.nastavVychod("vychod", aula);
        terasa.nastavVychod("juh", new VstupDoLabaku(labak));
        terasa.nastavVychod("zapad", bufet);
        aula.nastavVychod("zapad", terasa);
        labak.nastavVychod("sever", terasa);
        labak.nastavVychod("vychod", kancelaria);
        kancelaria.nastavVychod("zapad", labak);
        bufet.nastavVychod("vychod", terasa);

        terasa.polozPredmet(new ObycajnyPredmet("kamen"));
        terasa.polozPredmet(new Hodinky());
        terasa.polozPredmet(new Navleky());
        terasa.polozPredmet(new Baterky());
        terasa.polozPredmet(new Radio());
        labak.polozPredmet(new ObycajnyPredmet("mys"));
        bufet.polozPredmet(new ObycajnyPredmet("bageta"));

        this.startovaciaMiestnost = terasa;  // startovacia miestnost hry
    }

    public Miestnost getStartovaciaMiestnost() {
        return this.startovaciaMiestnost;
    }
}
