package sk.uniza.fri.wof.zaklad;

import sk.uniza.fri.wof.prikazy.Parser;
import sk.uniza.fri.wof.prikazy.Prikaz;
import sk.uniza.fri.wof.prikazy.VykonavacPrikazov;
import sk.uniza.fri.wof.prostredie.HernySvet;
import sk.uniza.fri.wof.prostredie.Miestnost;

/**
 * Trieda sk.uniza.fri.wof.zaklad.Hra je hlavna trieda aplikacie "World of FRI".
 * "World of FRI" je velmi jednoducha textova hra - adventura. 
 * sk.uniza.fri.wof.zaklad.Hrac sa moze prechadzat po niektorych priestoroch - miestnostiach fakulty
 * a zbierat/pokladat predmety. To je v tejto verzii vsetko. Hru treba skutocne
 * znacne rozsirit, aby bola zaujimava.
 *
 * sk.uniza.fri.wof.zaklad.Hra vytvori a inicializuje vsetky potebne objekty:
 * vytvori vsetky miestnosti, vytvori parser a zacne hru. sk.uniza.fri.wof.zaklad.Hra tiez vyhodnocuje
 * a vykonava prikazy, ktore vrati parser.
 * 
 * @author  Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
*/
 
public class Hra  {
    private final VykonavacPrikazov vykonavacPrikazov;
    private final Parser parser;
    private final Hrac hrac;
    private final HernySvet hernySvet;

    /**
     * Vytvori a inicializuje hru.
     */
    public Hra() {
        this.hernySvet = new HernySvet();
        Miestnost startovaMiestnost = this.hernySvet.getStartovaciaMiestnost();
        this.vykonavacPrikazov = new VykonavacPrikazov();
        this.parser = new Parser(this.vykonavacPrikazov);
        this.hrac = new Hrac(startovaMiestnost);
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
            jeKoniec = this.vykonavacPrikazov.vykonajPrikaz(prikaz, this.hrac);
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
        this.hrac.getAktualnaMiestnost().vypisInfoOMiestnosti();
    }
}
