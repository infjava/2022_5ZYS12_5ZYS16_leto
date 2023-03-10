package sk.uniza.fri.wof.prikazy;

import sk.uniza.fri.wof.zaklad.Hrac;

/**
 * Trieda NazvyPrikazov udrzuje zoznam nazvov platnych prikazov hry. 
 * Za ulohu ma rozpoznavat platne prikazy.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */

public class VykonavacPrikazov {
    // konstantne pole nazvov prikazov
    private static final String[] PLATNE_PRIKAZY = {
        "chod", "zober", "poloz", "inventar", "pouzi", "ukonci", "pomoc"
    };

    /**
     * Kontroluje, ci nazov v parametri je platny prikaz.
     *  
     * @return true ak je parameter platny prikaz,
     * false inak.
     */
    public boolean jePrikaz(String nazov) {
        for (int i = 0; i < VykonavacPrikazov.PLATNE_PRIKAZY.length; i++) {
            if (VykonavacPrikazov.PLATNE_PRIKAZY[i].equals(nazov)) {
                return true;
            }
        }
        // ak algoritmus dosiahne tento bod, parameter nie je platny prikaz
        return false;
    }

    /**
     * Prevezne prikaz a vykona ho.
     *
     * @param prikaz prikaz, ktory ma byt vykonany.
     * @param hrac   hrac, ktory vykonava prikaz
     * @return true ak prikaz ukonci hru, inak vrati false.
     */
    public boolean vykonajPrikaz(Prikaz prikaz, Hrac hrac) {
        if (prikaz.jeNeznamy()) {
            System.out.println("Nerozumiem, co mas na mysli...");
            return false;
        }

        String nazovPrikazu = prikaz.getNazov();

        switch (nazovPrikazu) {
            case "pomoc":
                this.vypisNapovedu();
                return false;
            case "chod":
                this.chodDoMiestnosti(prikaz, hrac);
                return false;
            case "zober":
                this.zoberPredmet(prikaz, hrac);
                return false;
            case "inventar":
                hrac.vypisInventar();
                return false;
            case "pouzi":
                this.pouziPredmet(prikaz, hrac);
                return false;
            case "poloz":
                this.polozPredmet(prikaz, hrac);
                return false;
            case "ukonci":
                return this.ukonciHru(prikaz);
            default:
                return false;
        }
    }

    /**
     * Ukonci hru.
     * Skotroluje cely prikaz a zisti, ci je naozaj koniec hry.
     * sk.uniza.fri.wof.prikazy.Prikaz ukoncenia nema parameter.
     *
     * @param prikaz
     * @return true, ak prikaz konci hru, inak false.
     */
    boolean ukonciHru(Prikaz prikaz) {
        if (prikaz.maParameter()) {
            System.out.println("Ukonci, co?");
            return false;
        } else {
            return true;
        }
    }

    /**
     * polozi predmet do miestnosti
     *
     * @param prikaz
     * @param hrac
     */
    void polozPredmet(Prikaz prikaz, Hrac hrac) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno co polozit
            System.out.println("Poloz co?");
            return;
        }

        String predmet = prikaz.getParameter();
        hrac.polozPredmet(predmet);
    }

    /**
     * zoberie predmet z miestnosti
     *
     * @param prikaz
     * @param hrac
     */
    void zoberPredmet(Prikaz prikaz, Hrac hrac) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno co zobrat
            System.out.println("Zober co?");
            return;
        }

        String predmet = prikaz.getParameter();
        hrac.zoberPredmet(predmet);
    }

    private void pouziPredmet(Prikaz prikaz, Hrac hrac) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno co zobrat
            System.out.println("Pouzi co?");
            return;
        }

        String predmet = prikaz.getParameter();

        hrac.pouziPredmet(predmet);
    }

    /**
     * Vykona pokus o prechod do miestnosti urcenej danym smerom.
     * Ak je tym smerom vychod, hrac prejde do novej miestnosti.
     * Inak sa vypise chybova sprava do terminaloveho okna.
     *
     * @param prikaz
     * @param hrac
     */
    void chodDoMiestnosti(Prikaz prikaz, Hrac hrac) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Chod kam?");
            return;
        }

        String smer = prikaz.getParameter();
        // Pokus o opustenie aktualnej miestnosti danym vychodom.
        hrac.posunVSmere(smer);
    }

    /**
     * Vypise text pomocnika do terminaloveho okna.
     * Text obsahuje zoznam moznych prikazov.
     */
    void vypisNapovedu() {
        System.out.println("Zabludil si. Si sam. Tulas sa po fakulte.");
        System.out.println();
        System.out.println("Mozes pouzit tieto prikazy:");
        System.out.println("   chod zober poloz inventar ukonci pomoc");
    }
}
