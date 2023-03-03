package sk.uniza.fri.wof.prostredie;

import java.util.HashMap;

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
    private final HashMap<String, Miestnost> vychody;
    private final HashMap<String, Predmet> predmety;

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
    }

    public void nastavVychod(String smer, Miestnost miestnost) {
        this.vychody.put(smer, miestnost);
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
    public Predmet zoberPredmet(String nazov) {
        return this.predmety.remove(nazov);
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
    }

    public Miestnost getMiestnostVSmere(String smer) {
        return this.vychody.get(smer);
    }
}
