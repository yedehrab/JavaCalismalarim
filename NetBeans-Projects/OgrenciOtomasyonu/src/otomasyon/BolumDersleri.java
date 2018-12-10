/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otomasyon;

import java.util.ArrayList;

/**
 *
 * @author Yunus Emre
 */
public abstract class BolumDersleri extends Menü implements GunVeSaatSabitleri{
    public int bölümTipi;
    public ArrayList<Ders> dersler = new ArrayList<>();
    public final ArrayList<Ders> günDersleri = new ArrayList<>();
    
    public static final String BOLUM[] = {
        "Tanımsız bölüm",
        "Bilgisayar Mühendisliği",
        "Endüstri Mühendisliği"
    };
    
    @Override
    public void menü(){
        while (true) {
            consoluTemizle();
            
            gMenü(
                "Lütfen bölüm seçiniz:\n" +
                SATIR_MENU +
                " [" + BOLUM_BILGISAYAR + "] Bilgisayar Mühendisliği\n" +
                " [" + BOLUM_ENDUSTRI + "] Endüstri Mühendisliği"
            );
            String answer = input.nextLine();
            
            switch(answer){
                case "" + CIKIS:
                    çıkış();
                case "" + GERI:
                    return;
                case "" + BOLUM_BILGISAYAR:
                    bölümTipi = BOLUM_BILGISAYAR;
                    dersleriAl();
                    menü();
                    break;
                case "" + BOLUM_ENDUSTRI:
                    bölümTipi = BOLUM_ENDUSTRI;
                    dersleriAl();
                    menü();
                    break;
                default:
                    bekletme("Hatalı tuşladınız.");
                    satırÇiz();
            }
            
        }
    }
    
    public final void dersleriAl(){
        dersler.clear();
        
        for (Ders ders : kayıtlarDers){
            if (ders.bölüm == bölümTipi){
                dersler.add(ders);
            }
        }
    }
    
    public final void dersleriAyarla(ArrayList<Ders> dersler){       
        this.dersler.clear();
        
        for (Ders öd : dersler){
            for (Ders kd : kayıtlarDers){
                if (öd.ad.equals(kd.ad)){
                    this.dersler.add(kd);
                }
            }
        }
    }
    
    
    public abstract void hepsiniGöster();
    public abstract void güneGöreGöster(int gün);
    public abstract void güneGöreAyarla(int gün);
    
   
    
}
