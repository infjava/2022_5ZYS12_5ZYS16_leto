package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.zaklad.Hrac;

public class Navleky implements Predmet {
    private boolean navlekyObute;

    public Navleky() {
        this.navlekyObute = false;
    }

    @Override
    public String getNazov() {
        return "navleky";
    }

    @Override
    public void pouziPredmet(Hrac hrac) {
        if (!this.navlekyObute) {
            System.out.println("Obul si si navleky");
        } else {
            System.out.println("Vyzul si si navleky");
        }
        this.navlekyObute = !this.navlekyObute;
    }

    @Override
    public boolean daSaPolozit() {
        return !this.navlekyObute;
    }
}
