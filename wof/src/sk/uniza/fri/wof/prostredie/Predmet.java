package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.zaklad.Hrac;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Predmet {
    private final String nazov;
    private boolean navlekyObute;

    public Predmet(String nazov) {
        this.nazov = nazov;
        this.navlekyObute = false;
    }

    public String getNazov() {
        return this.nazov;
    }

    public void pouziPredmet(Hrac hrac) {
        switch (this.nazov) {
            case "navleky" -> {
                if (!this.navlekyObute) {
                    System.out.println("Obul si si navleky");
                } else {
                    System.out.println("Vyzul si si navleky");
                }
                this.navlekyObute = !this.navlekyObute;
            }
            case "hodinky" -> {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println(dtf.format(now));
            }
            default -> System.out.printf("%s sa neda pouzit%n", this.nazov);
        }
    }
}
