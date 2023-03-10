package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.prostredie.predmety.Predmet;
import sk.uniza.fri.wof.prostredie.vybavenie.Automat;
import sk.uniza.fri.wof.prostredie.vybavenie.Vybavenie;
import sk.uniza.fri.wof.prostredie.vychody.ObycanyVychod;
import sk.uniza.fri.wof.prostredie.vychody.Vychod;

import java.util.HashMap;
import java.util.Optional;

/**
 * Trieda sk.uniza.fri.wof.prostredie.Miestnost realizuje jednu miestnost/priestor v celom priestore hry.
 * Kazda "miestnost" je z inymi miestnostami spojena vychodmi. 
 * Vychody z miestnosti su oznacovane svetovymi stranami sever, vychod, juh
 * a zapad. Pre kazdy vychod si miestnost pamata odkaz na susednu miestnost
 * alebo null, ak tym smerom vychod nema.
 *
 * @author  Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */
public class Miestnost {
    private final String popisMiestnosti;
    private final HashMap<String, Vychod> vychody;
    private final HashMap<String, Predmet> predmety;
    private final HashMap<String, Vybavenie> vybavenie;

    /**
     * Vytvori miestnost popis ktorej je v parametrom.
     * Po vytvoreni miestnost nema ziadne vychody. Popis miesnost strucne 
     * charakterizuje.
     * 
     * @param popis text popisu miestnosti.
     */
    public Miestnost(String popis) {
        this.popisMiestnosti = popis;
        this.vychody = new HashMap<>();
        this.predmety = new HashMap<>();
        this.vybavenie = new HashMap<>();
    }

    public void nastavVychod(String smer, Miestnost miestnost) {
        this.nastavVychod(smer, new ObycanyVychod(miestnost));
    }

    public void nastavVychod(String smer, Vychod vychod) {
        this.vychody.put(smer, vychod);
    }

    /**
     * polozi predmet do miestnosti
     * @param predmet pokladany predmet
     */
    public void polozPredmet(Predmet predmet) {
        this.predmety.put(predmet.getNazov(), predmet);
    }

    /**
     * zoberie predmet z miestnosti
     * @param nazov nazov zdvihaneho predmetu
     * @return zdvihnuty predmet
     */
    public Optional<Predmet> zoberPredmet(String nazov) {
        return Optional.ofNullable(this.predmety.remove(nazov));
    }

    public void vypisInfoOMiestnosti() {
        System.out.println("Teraz si v miestnosti " + this.popisMiestnosti);
        System.out.print("Vychody: ");
        for (var vychod : this.vychody.keySet()) {
            System.out.printf("%s ", vychod);
        }
        System.out.println();

        if (!this.predmety.isEmpty()) {
            System.out.print("Predmety v miestnosti: ");
            for (var predmet : this.predmety.keySet()) {
                System.out.printf("%s ", predmet);
            }
            System.out.println();
        }

        if (!this.vybavenie.isEmpty()) {
            System.out.println("Vybavenie miestnosti: ");
            for (var vybavenie : this.vybavenie.values()) {
                System.out.printf("    %s - %s%n", vybavenie.getNazov(), vybavenie.getPopis());
            }
        }
    }

    public Optional<Vychod> getVychodVSmere(String smer) {
        return Optional.ofNullable(this.vychody.get(smer));
    }

    public void postavVybavenie(Vybavenie vybavenie) {
        this.vybavenie.put(vybavenie.getNazov(), vybavenie);
    }

    public Optional<Vybavenie> najdiVybavenie(String nazov) {
        return Optional.ofNullable(this.vybavenie.get(nazov));
    }
}
