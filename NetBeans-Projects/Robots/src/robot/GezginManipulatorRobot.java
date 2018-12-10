/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Point;
import java.util.ArrayList;

public final class GezginManipulatorRobot extends Robot implements Kol, Hareket, KolOzellikleri, HareketOzellikleri {
    // Hareketli konumdan sabit konuma geçmesi için gerekli süre
    private float sabitlenmeSuresi = 0.4f;
    
    // Yükü robotun üzerinden alıp, robot koluna geçirmek için
    private float gecisSuresi = 1.2f;   
    
    // Hız değeri (Tekerlekli > paletli > Spider)
    private int hiz; 
    
    // Yük taşıma kapasitesi (Belli ağırlıkta yük taşıyabilir.)
    private int yukTasimaKapasitesi;
    
    // KOl uzunluğu (YÜkün hangi mesafeye taşınacağını belirler.)
    private int kolUzunlugu;
    
    // Taşıma hızı (Seri çok yavaş, paralel çok hızlı)
    private int tasimaHizi;
    
    // Kol konumu
    private Point kol;

    public GezginManipulatorRobot() {
        tasimaHiziAyarla();
        hizAyarla();
        tasimaKapasitesiAyarla();
        kolUzunluguAyarla();
    }
    
    public float baslat(ArrayList<Point> engeller) {
        float sure = 0f;
        yukVer();
        sure += komutlarlaIlerle(engeller);
        System.out.println("Toplam geçen süre: " + sure + "s");
        
        sure += sabitlenmeSuresi + gecisSuresi;
        System.out.println("Sabitlenme Süresi: " + sabitlenmeSuresi + "s GeçişSüresi: " + gecisSuresi + "s");
        System.out.println("Toplam geçen süre: " + sure + "s");
        
        sure += komutlarlaKoluIlerlet();
        System.out.println("Toplam geçen süre: " + sure + "s");
        
        return sure;
    }

    @Override
    public void koluAyarla() {
        this.kol = new Point(konum);
    }
    
    @Override
    public float komutlarlaKoluIlerlet() {
        return ManipulatorRobot.komutlarlaKoluIlerlet(kol, tasimaHizi, this);
    }

    @Override
    public boolean kolUzayabilirMi(int x, int y) {
        return ManipulatorRobot.kolUzayabilirMi(konum, kol, x, y, kolUzunlugu);
    }

    @Override
    public void yukVer() {
        ManipulatorRobot.yukVer(this, hiz, this);
    }

    @Override
    public boolean kaldirabilirMi(int yuk) {
        return this.yukTasimaKapasitesi >= yuk;
    }

    @Override
    public void tasimaKapasitesiAyarla() {
        this.yukTasimaKapasitesi = PARALEL_KAPASITE;
    }

    @Override
    public void kolUzunluguAyarla() {
        this.kolUzunlugu = PARALEL_KOL;
    }

    @Override
    public float ilerle(int x, int y, ArrayList<Point> engeller) {
       return GezginRobot.ilerle(konum, hiz, x, y, engeller, this);
    }

    @Override
    public boolean engeldenGecebilirMi() {
        return true;
    }

    @Override
    public float engeldenGecmeSuresiBul() {
         return (float) HIZLI_GECIS;
    }

    @Override
    public float komutlarlaIlerle(ArrayList<Point> engeller) {
        return GezginRobot.komutlarlaIlerle(konum, hiz, engeller, this);
    }

    @Override
    public void hizAyarla() {
        this.hiz = HIZLI_HIZ;
    }
    
    @Override
    public void tasimaHiziAyarla() {
        tasimaHizi = TASIMA_HIZI_HIZLI;
    }

    public float getSabitlenmeSuresi() {
        return sabitlenmeSuresi;
    }

    public void setSabitlenmeSuresi(float sabitlenmeSuresi) {
        this.sabitlenmeSuresi = sabitlenmeSuresi;
    }

    public float getGecisSuresi() {
        return gecisSuresi;
    }

    public void setGecisSuresi(float gecisSuresi) {
        this.gecisSuresi = gecisSuresi;
    }

    public int getHiz() {
        return hiz;
    }

    public void setHiz(int hiz) {
        this.hiz = hiz;
    }

    public int getYukTasimaKapasitesi() {
        return yukTasimaKapasitesi;
    }

    public void setYukTasimaKapasitesi(int yukTasimaKapasitesi) {
        this.yukTasimaKapasitesi = yukTasimaKapasitesi;
    }

    public int getKolUzunlugu() {
        return kolUzunlugu;
    }

    public void setKolUzunlugu(int kolUzunlugu) {
        this.kolUzunlugu = kolUzunlugu;
    }

    public int getTasimaHizi() {
        return tasimaHizi;
    }

    public void setTasimaHizi(int tasimaHizi) {
        this.tasimaHizi = tasimaHizi;
    }

    public Point getKol() {
        return kol;
    }

    public void setKol(Point kol) {
        this.kol = kol;
    }

   
}
