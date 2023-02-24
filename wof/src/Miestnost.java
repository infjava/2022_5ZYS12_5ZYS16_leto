import java.util.ArrayList;

/**
 * Trieda Miestnost realizuje jednu miestnost/priestor v celom priestore hry.
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
    private Miestnost severnyVychod;
    private Miestnost juznyVychod;
    private Miestnost vychodnyVychod;
    private Miestnost zapadnyVychod;
    private final ArrayList<Predmet> predmety;

    /**
     * Vytvori miestnost popis ktorej je v parametrom.
     * Po vytvoreni miestnost nema ziadne vychody. Popis miesnost strucne 
     * charakterizuje.
     * 
     * @param popis text popisu miestnosti.
     */
    public Miestnost(String popis) {
        this.popisMiestnosti = popis;
        this.predmety = new ArrayList<>();
    }

    /**
     * Nastavi vychody z miestnosti. Kazdy vychod je urceny bud odkazom 
     * na miestnost alebo hodnotou null, ak vychod tym smerom neexistuje.
     * 
     * @param sever miestnost smerom na sever.
     * @param vychod miestnost smerom na vychod.
     * @param juh miestnost smerom na juh.
     * @param zapad miestnost smerom na zapad.
     */
    public void nastavVychody(Miestnost sever, Miestnost vychod, Miestnost juh, Miestnost zapad) {
        if (sever != null) {
            this.severnyVychod = sever;
        }
        if (vychod != null) {
            this.vychodnyVychod = vychod;
        }
        if (juh != null) {
            this.juznyVychod = juh;
        }
        if (zapad != null) {
            this.zapadnyVychod = zapad;
        }
    }

    /**
     * @return textovy popis miestnosti.
     */
    public String getPopis() {
        return this.popisMiestnosti;
    }

    /**
     * polozi predmet do miestnosti
     * @param predmet pokladany predmet
     */
    public void polozPredmet(Predmet predmet) {
        this.predmety.add(predmet);
    }

    /**
     * zoberie predmet z miestnosti
     * @param nazov nazov zdvihaneho predmetu
     * @return zdvihnuty predmet
     */
    public Predmet zoberPredmet(String nazov) {
        for (Predmet kontrolovanyPredmet : this.predmety) {
            if (kontrolovanyPredmet.getNazov().equals(nazov)) {
                this.predmety.remove(kontrolovanyPredmet);
                return kontrolovanyPredmet;
            }
        }

        return null;
    }

    public Miestnost getSevernyVychod() {
        return this.severnyVychod;
    }

    public Miestnost getJuznyVychod() {
        return this.juznyVychod;
    }

    public Miestnost getVychodnyVychod() {
        return this.vychodnyVychod;
    }

    public Miestnost getZapadnyVychod() {
        return this.zapadnyVychod;
    }

    public ArrayList<Predmet> getPredmety() {
        return this.predmety;
    }
}
