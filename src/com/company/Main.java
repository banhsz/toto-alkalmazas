package com.company;

public class Main
{
    public static void main(String[] args)
    {
        Szolgaltatas sz1 = new Szolgaltatas();
        System.out.println("1. feladat");
        System.out.println(String.format("A legnagyobb rögzített nyeremény: %d Ft",sz1.LegnagyobbNyeremeny()));

        System.out.println("2. feladat");
        System.out.println(String.format("Statisztika:"));
        System.out.println(String.format("#1 csapat nyert: %f százalék", sz1.Megoszlas(Eredmeny._1)));
        System.out.println(String.format("#2 csapat nyert: %f százalék", sz1.Megoszlas(Eredmeny._2)));
        System.out.println(String.format("#x: %f százalék", sz1.Megoszlas(Eredmeny.x)));

        System.out.println("3. feladat");
        sz1.Bekeros();
    }
}
