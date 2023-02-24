import java.util.ArrayList;

/**
 * Trieda Hra je hlavna trieda aplikacie "World of FRI".
 * "World of FRI" je velmi jednoducha textova hra - adventura. 
 * Hrac sa moze prechadzat po niektorych priestoroch - miestnostiach fakulty
 * a zbierat/pokladat predmety. To je v tejto verzii vsetko. Hru treba skutocne
 * znacne rozsirit, aby bola zaujimava.
 *
 * Hra vytvori a inicializuje vsetky potebne objekty:
 * vytvori vsetky miestnosti, vytvori parser a zacne hru. Hra tiez vyhodnocuje
 * a vykonava prikazy, ktore vrati parser.
 * 
 * @author  Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
*/
 
public class Hra  {
    private final Parser parser;
    private Miestnost aktualnaMiestnost;
    private ArrayList<Predmet> inventar;
    
    /**
     * Vytvori a inicializuje hru.
     */
    public Hra() {
        this.inventar = new ArrayList<>();
        this.vytvorMiestnosti();
        this.parser = new Parser();
    }

    /**
     * Vytvori mapu hry - miestnosti.
     */
    private void vytvorMiestnosti() {
        // vytvorenie miestnosti
        Miestnost terasa = new Miestnost("terasa - hlavny vstup na fakultu");
        Miestnost aula = new Miestnost("aula");
        Miestnost bufet = new Miestnost("bufet");
        Miestnost labak = new Miestnost("pocitacove laboratorium");
        Miestnost kancelaria = new Miestnost("kancelaria spravcu pocitacoveho laboratoria");
        
        // inicializacia miestnosti = nastavenie vychodov
        terasa.nastavVychody(null, aula, labak, bufet);
        aula.nastavVychody(null, null, null, terasa);
        bufet.nastavVychody(null, terasa, null, null);
        labak.nastavVychody(terasa, kancelaria, null, null);
        kancelaria.nastavVychody(null, null, null, labak);

        terasa.polozPredmet(new Predmet("kamen"));
        labak.polozPredmet(new Predmet("mys"));
        bufet.polozPredmet(new Predmet("bageta"));

        this.aktualnaMiestnost = terasa;  // startovacia miestnost hry
    }

    /**
     *  Hlavna metoda hry.
     *  Cyklicky opakuje kroky hry, kym hrac hru neukonci.
     */
    public void hraj() {            
        this.vypisPrivitanie();

        // Vstupny bod hlavneho cyklu.
        // Opakovane nacitava prikazy hraca
        // vykonava ich kym hrac nezada prikaz na ukoncenie hry.
                
        boolean jeKoniec;
        
        do {
            Prikaz prikaz = this.parser.nacitajPrikaz();
            jeKoniec = this.vykonajPrikaz(prikaz);
        } while (!jeKoniec);
        
        System.out.println("Maj sa fajn!");
    }

    /**
     * Vypise privitanie hraca do terminaloveho okna.
     */
    private void vypisPrivitanie() {
        System.out.println();
        System.out.println("Vitaj v hre World of FRI!");
        System.out.println("World of FRI je nova, neuveritelne nudna adventura.");
        System.out.println("Zadaj 'pomoc' ak potrebujes pomoc.");
        System.out.println();
        System.out.println("Teraz si v miestnosti " + this.aktualnaMiestnost.getPopis());
        System.out.print("Vychody: ");
        if (this.aktualnaMiestnost.getSevernyVychod() != null) {
            System.out.print("sever ");
        }
        if (this.aktualnaMiestnost.getVychodnyVychod() != null) {
            System.out.print("vychod ");
        }
        if (this.aktualnaMiestnost.getJuznyVychod() != null) {
            System.out.print("juh ");
        }
        if (this.aktualnaMiestnost.getZapadnyVychod() != null) {
            System.out.print("zapad ");
        }

        System.out.println();

        if (!this.aktualnaMiestnost.getPredmety().isEmpty()) {
            System.out.print("Predmety v miestnosti: ");
            for (Predmet predmet : this.aktualnaMiestnost.getPredmety()) {
                System.out.printf("%s ", predmet.getNazov());
            }
            System.out.println();
        }
    }

    /**
     * Prevezne prikaz a vykona ho.
     * 
     * @param prikaz prikaz, ktory ma byt vykonany.
     * @return true ak prikaz ukonci hru, inak vrati false.
     */
    private boolean vykonajPrikaz(Prikaz prikaz) {
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
                this.chodDoMiestnosti(prikaz);
                return false;
            case "zober":
                this.zoberPredmet(prikaz);
                return false;
            case "inventar":
                this.vypisInventar();
                return false;
            case "poloz":
                this.polozPredmet(prikaz);
                return false;
            case "ukonci":
                return this.ukonciHru(prikaz);
            default:
                return false;
        }
    }

    // implementacie prikazov:

    /**
     * Vypise text pomocnika do terminaloveho okna.
     * Text obsahuje zoznam moznych prikazov.
     */
    private void vypisNapovedu() {
        System.out.println("Zabludil si. Si sam. Tulas sa po fakulte.");
        System.out.println();
        System.out.println("Mozes pouzit tieto prikazy:");
        System.out.println("   chod zober poloz inventar ukonci pomoc");
    }

    /** 
     * Vykona pokus o prechod do miestnosti urcenej danym smerom.
     * Ak je tym smerom vychod, hrac prejde do novej miestnosti.
     * Inak sa vypise chybova sprava do terminaloveho okna.
     */
    private void chodDoMiestnosti(Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Chod kam?");
            return;
        }

        String smer = prikaz.getParameter();

        // Pokus o opustenie aktualnej miestnosti danym vychodom.
        Miestnost novaMiestnost = null;
        switch (smer) {
            case "sever":
                novaMiestnost = this.aktualnaMiestnost.getSevernyVychod();
                break;
            case "vychod":
                novaMiestnost = this.aktualnaMiestnost.getVychodnyVychod();
                break;
            case "juh":
                novaMiestnost = this.aktualnaMiestnost.getJuznyVychod();
                break;
            case "zapad":
                novaMiestnost = this.aktualnaMiestnost.getZapadnyVychod();
                break;
        }

        if (novaMiestnost == null) {
            System.out.println("Tam nie je vychod!");
        } else {
            this.aktualnaMiestnost = novaMiestnost;
            System.out.println("Teraz si v miestnosti " + this.aktualnaMiestnost.getPopis());
            System.out.print("Vychody: ");
            if (this.aktualnaMiestnost.getSevernyVychod() != null) {
                System.out.print("sever ");
            }
            if (this.aktualnaMiestnost.getVychodnyVychod() != null) {
                System.out.print("vychod ");
            }
            if (this.aktualnaMiestnost.getJuznyVychod() != null) {
                System.out.print("juh ");
            }
            if (this.aktualnaMiestnost.getZapadnyVychod() != null) {
                System.out.print("zapad ");
            }
            System.out.println();

            if (!this.aktualnaMiestnost.getPredmety().isEmpty()) {
                System.out.print("Predmety v miestnosti: ");
                for (Predmet predmet : this.aktualnaMiestnost.getPredmety()) {
                    System.out.printf("%s ", predmet.getNazov());
                }
                System.out.println();
            }
        }
    }

    /**
     * zoberie predmet z miestnosti
     */
    private void zoberPredmet(Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno co zobrat
            System.out.println("Zober co?");
            return;
        }

        String predmet = prikaz.getParameter();

        Predmet zdvihnutyPredmet = this.aktualnaMiestnost.zoberPredmet(predmet);
        this.inventar.add(zdvihnutyPredmet);
    }

    /**
     * polozi predmet do miestnosti
     */
    private void polozPredmet(Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno co polozit
            System.out.println("Poloz co?");
            return;
        }

        String predmet = prikaz.getParameter();

        Predmet pokladanyPredmet = null;
        for (Predmet kontrolovanyPredmet : this.inventar) {
            if (kontrolovanyPredmet.getNazov().equals(predmet)) {
                pokladanyPredmet = kontrolovanyPredmet;
                break;
            }
        }

        this.inventar.remove(pokladanyPredmet);
        this.aktualnaMiestnost.polozPredmet(pokladanyPredmet);
    }

    /**
     * vypise inventar do terminalu
     */
    private void vypisInventar() {
        if (this.inventar.isEmpty()) {
            System.out.println("Zbytocne pozeras, ved tu nic nie je");
        } else {
            System.out.println("Tvoj inventar obsahuje:");
            for (Predmet predmet : this.inventar) {
                System.out.printf("- %s%n", predmet.getNazov());
            }
        }
    }

    /** 
     * Ukonci hru.
     * Skotroluje cely prikaz a zisti, ci je naozaj koniec hry.
     * Prikaz ukoncenia nema parameter.
     * 
     * @return true, ak prikaz konci hru, inak false.
     */
    private boolean ukonciHru(Prikaz prikaz) {
        if (prikaz.maParameter()) {
            System.out.println("Ukonci, co?");
            return false;
        } else {
            return true;
        }
    }
}
