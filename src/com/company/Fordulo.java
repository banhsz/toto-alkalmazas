package com.company;

import java.time.LocalDate;
import java.util.List;

public class Fordulo
{
    int ev;
    int het;
    int forudloAHeten;
    LocalDate datum;
    List<Talalat> talalatok;
    List<Eredmeny> eredmenyek;

    public Fordulo(int ev, int het, int forudloAHeten, LocalDate datum, List<Talalat> talalatok, List<Eredmeny> eredmenyek) {
        this.ev = ev;
        this.het = het;
        this.forudloAHeten = forudloAHeten;
        this.datum = datum;
        this.talalatok = talalatok;
        this.eredmenyek = eredmenyek;
    }

    public int getEv() {
        return ev;
    }
    public void setEv(int ev) {
        this.ev = ev;
    }
    public int getHet() {
        return het;
    }
    public void setHet(int het) {
        this.het = het;
    }
    public int getForudloAHeten() {
        return forudloAHeten;
    }
    public void setForudloAHeten(int forudloAHeten) {
        this.forudloAHeten = forudloAHeten;
    }
    public LocalDate getDatum() {
        return datum;
    }
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
    public List<Talalat> getTalalatok() {
        return talalatok;
    }
    public void setTalalatok(List<Talalat> talalatok) {
        this.talalatok = talalatok;
    }
    public List<Eredmeny> getEredmenyek() {
        return eredmenyek;
    }
    public void setEredmenyek(List<Eredmeny> eredmenyek) {
        this.eredmenyek = eredmenyek;
    }

}
