package sk.uniza.fri.wof.prostredie.predmety;

public class Navleky implements Predmet {
    private boolean suObute;

    public Navleky() {
        this.suObute = false;
    }

    @Override
    public String getNazov() {
        return "navleky";
    }

    @Override
    public void pouzi() {
        if (this.suObute) {
            this.suObute = false;
            System.out.println("Prave si si vyzul návleky");
        }
        else {
            this.suObute = true;
            System.out.println("Práve si si obul návleky");
        }
    }

    @Override
    public boolean mozemPolozit() {
        return !this.suObute;
    }

    @Override
    public boolean jeNasadeny() {
        return this.suObute;
    }
}
