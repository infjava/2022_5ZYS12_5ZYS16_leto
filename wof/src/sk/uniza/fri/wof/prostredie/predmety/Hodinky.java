package sk.uniza.fri.wof.prostredie.predmety;

import sk.uniza.fri.wof.zaklad.Hrac;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hodinky implements Predmet, Pouzitelny {
    @Override
    public String getNazov() {
        return "hodinky";
    }

    @Override
    public void pouzi(Hrac hrac) {
        var dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        var now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }

    @Override
    public boolean mozemPolozit() {
        return true;
    }
}
