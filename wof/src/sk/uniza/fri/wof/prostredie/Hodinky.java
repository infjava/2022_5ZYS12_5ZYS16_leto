package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.zaklad.Hrac;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hodinky implements Predmet {
    @Override
    public String getNazov() {
        return "hodinky";
    }

    @Override
    public void pouziPredmet(Hrac hrac) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }
}
