package com.company;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Szolgaltatas
{
    List<Fordulo> fordulokLista;

    public Szolgaltatas() {
        fordulokLista = new ArrayList<>();
        try{
            FileReader fr = new FileReader("toto.csv");
            BufferedReader br = new BufferedReader(fr);
            String sor= br.readLine();
            while(sor!=null)
            {
                //Ft eltávolítása
                sor = sor.replace(" Ft","");
                //számok közötti szóköz eltávolítása
                sor = sor.replace(" ","");
                //dátum pontok helyett kötőjel
                sor = sor.replace(".","-");
                //néhány sornál az eredményeknél nem 2 van hanem +2 valamiért?
                sor = sor.replace("+","");

                String[] seged = sor.split(";");
                //találatok init:
                List<Talalat> talalatok = initTalalatokLista(sor);
                //eredmenyek init:
                List<Eredmeny> eredmenyek = initEredmenyekLista(sor);
                //üres FORDULÓ oszlop fix:
                if ((seged[2]).length()==0)
                {
                    seged[2]="1";
                }
                //üres DÁTUM oszlop fix:
                if (seged[3].length()==0)
                {
                    seged[3]=DatumSzamolo(Integer.parseInt(seged[0]),Integer.parseInt(seged[1]));

                }
                //dátum fix:
                seged[3] = seged[3].substring(0, seged[3].length() - 1);

                //igy néz ki végül egy sor ami feldolgozásra megy:
                /*
                for(int i = 0; i < seged.length; i++)
                {
                    System.out.print(seged[i]+" ");
                }
                */
                this.fordulokLista.add(new Fordulo(Integer.parseInt(seged[0]),Integer.parseInt(seged[1]),Integer.parseInt(seged[2]), LocalDate.parse(seged[3]),talalatok,eredmenyek));
                sor=br.readLine();
            }
            br.close();
            fr.close();
        }
        catch (IOException ex)
        {
            System.out.println(ex.toString());
        }
    }

    public List<Fordulo> getFordulokLista() {
        return fordulokLista;
    }
    public void setFordulokLista(List<Fordulo> fordulokLista) {
        this.fordulokLista = fordulokLista;
    }

    private List<Talalat> initTalalatokLista(String sor) {
        List<Talalat> talalatok = new ArrayList<>();
        String[] seged = sor.split(";");
        //bemegy a sor amit szétsplitelek és a 4. és 13. közötti értékek lesznek fontosak
        //14 találat: 4.5. sor
        //13 találat: 6.7. sor
        //12 találat: 8.9. sor
        //11 találat: 10.11. sor
        //10 találat: 12.13. sor
        talalatok.add(new Talalat(14,Integer.parseInt(seged[4]),Integer.parseInt(seged[5])));
        talalatok.add(new Talalat(13,Integer.parseInt(seged[6]),Integer.parseInt(seged[7])));
        talalatok.add(new Talalat(12,Integer.parseInt(seged[8]),Integer.parseInt(seged[9])));
        talalatok.add(new Talalat(11,Integer.parseInt(seged[10]),Integer.parseInt(seged[11])));
        talalatok.add(new Talalat(10,Integer.parseInt(seged[12]),Integer.parseInt(seged[13])));
        return talalatok;
    }
    private List<Eredmeny> initEredmenyekLista(String sor) {
        List<Eredmeny> eredmenyek = new ArrayList<>();
        String[] seged = sor.split(";");
        //bemegy a sor amit szétsplitelek és a 14. és 27. közötti értékek lesznek fontosak
        for (int i = 14; i <=27 ; i++) {
            switch (seged[i])
            {
                case "1":
                    eredmenyek.add(Eredmeny._1);
                    break;
                case "2":
                    eredmenyek.add(Eredmeny._2);
                    break;
                default:
                    eredmenyek.add(Eredmeny.x);
                    break;
            }
        }
        return eredmenyek;
    }
    public String DatumSzamolo(int ev,int het) {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.setMinimalDaysInFirstWeek(4);

        int numWeekofYear = het;  //INPUT
        int year = ev;         //INPUT

        gregorianCalendar.set(Calendar.YEAR , year);
        gregorianCalendar.set(Calendar.WEEK_OF_YEAR , numWeekofYear);
        gregorianCalendar.set(Calendar.DAY_OF_WEEK,2);

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd-");
        fmt.setCalendar(gregorianCalendar);
        String dateFormatted = fmt.format(gregorianCalendar.getTime());
        return dateFormatted;
    }
    public int LegnagyobbNyeremeny() {
        int legnagyobb=0;
        for (int i = 0; i < this.fordulokLista.size(); i++)
        {
            if (this.fordulokLista.get(i).getTalalatok().get(0).nyeremeny>legnagyobb)
            legnagyobb = this.fordulokLista.get(i).getTalalatok().get(0).nyeremeny;
        }
        return legnagyobb;
    }
    public double Megoszlas(Eredmeny eredmeny){
        float szum=fordulokLista.size()*14; //14 eredmény van soronként
        float db=0;
        for (int i = 0; i < this.fordulokLista.size(); i++)
        {
            for (int j=0;j<this.fordulokLista.get(i).getEredmenyek().size();j++)
            {
                if (this.fordulokLista.get(i).getEredmenyek().get(j)==eredmeny)
                {
                    db++;

                }
            }
        }
        return (db/szum)*100.00;
    }
    public void Bekeros() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Adjon meg egy dátumot! (YYYY-MM-DD)");
        String datum = sc.next();
        System.out.println("Adjon meg egy tipp sorozatot! (14db) (212121x12x2121)");
        String tipp = sc.next();

        //dátum keresése
        boolean megvan=false;
        int i=0;
        while(!megvan && i<this.fordulokLista.size())
        {
            if (this.fordulokLista.get(i).getDatum().toString().equals(datum))
            {
                megvan=true;
            }
            else
            {
                i++;
            }
        }
        if (megvan)
        {
            System.out.println("van ilyen datum");
            String seged = "";
            //eredmények stringé alakítása
            for (int j = 0; j < this.fordulokLista.get(i).getEredmenyek().size(); j++) {
                if (this.fordulokLista.get(i).getEredmenyek().get(j)==Eredmeny._1)
                {
                    seged+="1";
                }
                else if (this.fordulokLista.get(i).getEredmenyek().get(j)==Eredmeny._2)
                {
                    seged+="2";
                }
                else
                {
                    seged+="x";
                }
            }
            //összehasonlítás
            int joTipp=0;
            for (int j = 0; j <tipp.length() ; j++)
            {
                if (tipp.charAt(j)==seged.charAt(j))
                {
                    joTipp++;
                }
            }
            int nyeremeny=0;
            switch (joTipp)
            {
                case 14:
                    nyeremeny=this.fordulokLista.get(i).getTalalatok().get(0).nyeremeny;
                    break;
                case 13:
                    nyeremeny=this.fordulokLista.get(i).getTalalatok().get(1).nyeremeny;
                    break;
                case 12:
                    nyeremeny=this.fordulokLista.get(i).getTalalatok().get(2).nyeremeny;
                    break;
                case 11:
                    nyeremeny=this.fordulokLista.get(i).getTalalatok().get(3).nyeremeny;
                    break;
                case 10:
                    nyeremeny=this.fordulokLista.get(i).getTalalatok().get(4).nyeremeny;
                    break;
            }
            System.out.println(String.format("Eredmeny: talalat: %d, nyeremeny: %d Ft",joTipp,nyeremeny));

        }
        else
        {
            System.out.println("nincs ilyen datum az adatbazisban");
        }
    }
}
