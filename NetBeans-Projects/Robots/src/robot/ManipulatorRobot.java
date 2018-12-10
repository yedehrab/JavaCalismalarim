/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Point;
import java.util.Scanner;


// Konumu sabittir
public abstract class ManipulatorRobot extends Robot implements Kol, KolOzellikleri{
    // Yük taşıma kapasitesi (Belli ağırlıkta yük taşıyabilir.)
    protected int yukTasimaKapasitesi;
    
    // KOl uzunluğu (YÜkün hangi mesafeye taşınacağını belirler.)
    protected int kolUzunlugu;
    
    // Taşıma hızı (Seri çok yavaş, paralel çok hızlı)
    protected int tasimaHizi;
    
    // Kol konumu
    protected Point kol;
    
    public ManipulatorRobot() {
        tasimaHiziAyarla();
        koluAyarla();
        tasimaKapasitesiAyarla();
        kolUzunluguAyarla();
    }

    @Override
    public final void koluAyarla() {
        this.kol = new Point(konum);
    }

    @Override
    public float komutlarlaKoluIlerlet() {
        return komutlarlaKoluIlerlet(kol, tasimaHizi, this);
    }
    
    /**
     * Komutlarla robotun kolunu ilerletme
     * @param kol Kol'un noktasal konumu
     * @param tasimaHizi Taşıma hızı
     * @param k Kol özellikleri
     * @return 
     */
    public static float komutlarlaKoluIlerlet(Point kol, int tasimaHizi, KolOzellikleri k) {
        Scanner scan = new Scanner(System.in);
        
        String komutDizgisi;
        String[] komut;
        
        System.out.println("Yon bilgisi (3 izgara ileri / geri / saga / sola):");
        System.out.println("Komutu kapatmak icin -1 yazin:");
        
        int adim, x, y;
        float sure = 0f;
        
        // Komutu yorumlama
        while(true) {
            try {
                // İlerlemeyi sıfırlama
                x = y = 0;
                
                // Komutu işleme
                komutDizgisi = scan.nextLine();
                komut = komutDizgisi.split("\\s+");
                adim = Integer.parseInt(komut[0]);
                
                if (adim == -1){
                    return sure;
                }
                
                
                switch (komut[2]) {
                    case "ileri":
                        y = -1 * adim;
                        break;
                    case "geri":
                        y = 1 * adim;
                        break;
                    case "saga":
                        x = 1 * adim;
                        break;
                    case "sola":
                        x = -1 * adim;
                        break;
                    default:
                        System.out.println("Komut formatı hatalı");
                        continue;
                }
                
                if (k.kolUzayabilirMi(x, y)) {
                    kol.x += x;
                    kol.y += y;
                    float anlikSure = Math.abs(x) * (Izgara.IZGARA_UZUNLUGU / (float) tasimaHizi) + Math.abs(y) * (Izgara.IZGARA_UZUNLUGU / (float) tasimaHizi);
                    sure += anlikSure;
                    System.out.println("Taşıma hızı: " + tasimaHizi + "m/s Geçen Sure: " + anlikSure + "s Toplam Süre: " + sure + "s") ;
                } else {
                    System.out.println("Kol uzunluğunu aşamazsınız.");
                }
               
            
            } catch (NumberFormatException | java.lang.ArrayIndexOutOfBoundsException e) {
                System.out.println("Komut formatı hatalı");
            }
        }
    }
    
    /**
     * Kolun uzayabilme kontorlü
     * @param konum Robotun noktasal konumu
     * @param kol Kolun noktasal konumu
     * @param x Dikey uzama
     * @param y Yatay uzama
     * @param kolUzunlugu En yüksek kol uzunluğu
     * @return 
     */
    public static boolean kolUzayabilirMi(Point konum, Point kol, int x, int y, int kolUzunlugu) {
        // Kolun yeni konumunu hesaplama
        Point yeniKol = new Point(kol.x + x, kol.y + y);
        // Kol uzunluğu hesaplama (Birim cinsinsen olduğu için izgara uzunluğu ile çarpmamız lazım)
        double yeniKolUzunlugu = Math.sqrt((Math.pow(yeniKol.x - konum.x, 2) + Math.pow(yeniKol.y - konum.y, 2))) * Izgara.IZGARA_UZUNLUGU;
        System.out.println("Kol uzunlugu: " + yeniKolUzunlugu + "m En fazla: " + kolUzunlugu + "m");
        return kolUzunlugu >= yeniKolUzunlugu;
    }
    
    /**
     * Robota vük verir
     * @param robot Robot
     * @param yuk Yük
     * @param k Kol özellikleri
     */
    public static void yukVer(Robot robot, int yuk, KolOzellikleri k) {
        Scanner scan = new Scanner(System.in);
        boolean dongu = true;
        
        while(dongu) {
            System.out.println("Robotunun yuk miktari: (En fazla: paralel " + PARALEL_KAPASITE + "kg, seri " + SERI_KAPASITE + "kg)");
            System.out.print("Kg -> ");
            int kg = scan.nextInt();

            if (k.kaldirabilirMi(kg)) {
                robot.setYukMiktari(kg);
                dongu = false;
            } else {
                System.out.println("Bu yuk miktarini kaldiramaz, yeniden girin");
            }
        }
    }
    
    @Override
    public boolean kolUzayabilirMi(int x, int y) {
        return kolUzayabilirMi(konum, kol, x, y, kolUzunlugu);
    }
    
    @Override
    public void yukVer() {
        yukVer(this, yukMiktari, this);
    }

    @Override
    public final boolean kaldirabilirMi(int yuk) {
        return this.yukTasimaKapasitesi >= yuk;
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
