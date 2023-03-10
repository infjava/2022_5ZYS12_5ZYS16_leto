package sk.uniza.fri.wof.prostredie.predmety;

import sk.uniza.fri.wof.zaklad.Hrac;

public class Radio implements Predmet, ReakciaNaChodenie {
    private boolean mamVlozeneBaterky;

    @Override
    public String getNazov() {
        return "radio";
    }

    @Override
    public boolean mozemPolozit() {
        return true;
    }

    public void vlozBaterky() {
        this.mamVlozeneBaterky = true;
    }

    @Override
    public void zmenaMiestnosti() {
        if (this.mamVlozeneBaterky) {
            System.out.println("♩♩♫♫♬♬♪\uD83C\uDFB6\uD83C\uDFB5");
        }
    }
}
