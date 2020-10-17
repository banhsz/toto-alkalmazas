package com.company;

public class Talalat
{

    int talalatokSzama;
    int nyertTalalatokSzama;
    int nyeremeny;

    public Talalat(int talalatokSzama, int nyertTalalatokSzama, int nyeremeny) {
        this.talalatokSzama = talalatokSzama;
        this.nyertTalalatokSzama = nyertTalalatokSzama;
        this.nyeremeny = nyeremeny;
    }

    public int getTalalatokSzama() {
        return talalatokSzama;
    }
    public void setTalalatokSzama(int talalatokSzama) {
        this.talalatokSzama = talalatokSzama;
    }
    public int getNyertTalalatokSzama() {
        return nyertTalalatokSzama;
    }
    public void setNyertTalalatokSzama(int nyertTalalatokSzama) {
        this.nyertTalalatokSzama = nyertTalalatokSzama;
    }
    public int getNyeremeny() {
        return nyeremeny;
    }
    public void setNyeremeny(int nyeremeny) {
        this.nyeremeny = nyeremeny;
    }
}
