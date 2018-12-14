/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;


// Konumu sabittir
public abstract class ManipulatorRobot extends Robot implements Kol {
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
        kolunKonumuAyarla();
        tasimaKapasitesiAyarla();
        kolUzunluguAyarla();
    }
    
    /**
     * Manipülatör robot oluşturur
     * @param soruMetniOlsun Oluşturma arüyüzünde soru metninin görünürlüğü
     * @return Oluşturulan manipülatör robot
     */
    public static ManipulatorRobot olustur(boolean soruMetniOlsun) {
        // Kullanıcı girdisini tutacak değişkenler
        Scanner scan = new Scanner(System.in);
        int tur;
        
        if (soruMetniOlsun)
            System.out.println("Manipulator robotunun turunu secin:");
        
        System.out.println("1- Paralel (Yük Sınırı: 15kg)");
        System.out.println("2- Seri (Yük Sınırı: 10kg)");
        tur = scan.nextInt();
        
        // \n'i yakalama
        scan.nextLine();

        switch(tur) {
            case 1:
                return new Paralel();
            case 2:
                return new Seri();
            default:
                System.out.println("Hatali secim yaptiniz");
                return olustur(soruMetniOlsun);
        }
    }

    @Override
    public Point yukAl() {
        // Yükü kollarındadır.
        return kol;
    }
    
    @Override
    public float baslat(ArrayList<Point> engeller) {
        // Yükü verme
        yukVer();
        
        // Süreyi bulma
        float sure = komutlarlaKoluIlerlet();
        
        // Ekranda gösterme
        System.out.println("Toplam geçen süre: " + sure + "s");
        
        // Süreyi döndürme
        return sure;
    }

    @Override
    public final void kolunKonumuAyarla() {
        this.kol = new Point(konum);
    }
    
    @Override
    public float komutlarlaKoluIlerlet() {
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
                
                if (kolUzayabilirMi(x, y)) {
                    kol.x += x;
                    kol.y += y;
                    float anlikSure = Math.abs(x) * (Izgara.IZGARA_UZUNLUGU / (float) tasimaHizi) + Math.abs(y) * (Izgara.IZGARA_UZUNLUGU / (float) tasimaHizi);
                    sure += anlikSure;
                    System.out.println("Taşıma hızı: " + tasimaHizi + "m/s Geçen Sure: " + anlikSure + "s Toplam Süre: " + sure + "s") ;
                }
                
            } catch (NumberFormatException | java.lang.ArrayIndexOutOfBoundsException e) {
                System.out.println("Komut formatı hatalı");
            }
        }
    }
    
    @Override
    public boolean kolUzayabilirMi(int x, int y) {
        // Kolun yeni konumunu hesaplama
        Point yeniKol = new Point(this.kol.x + x, this.kol.y + y);
        
        // Izgara dışına çıkmazsa kol uzunluğunu kontrol edeceğiz
        if (yeniKol.x >= 0 && yeniKol.y >= 0 && yeniKol.x < Izgara.KARE_SAYISI && yeniKol.y < Izgara.KARE_SAYISI) {
            // Kol uzunluğu hesaplama (Birim cinsinsen olduğu için izgara uzunluğu ile çarpmamız lazım)
            double yeniKolUzunlugu = Math.sqrt((Math.pow(yeniKol.x - konum.x, 2) + Math.pow(yeniKol.y - konum.y, 2))) * Izgara.IZGARA_UZUNLUGU;
            System.out.println("Kol uzunlugu: " + yeniKolUzunlugu + "m En fazla: " + this.kolUzunlugu + "m");
            
            if (kolUzunlugu < yeniKolUzunlugu) {
                System.out.println("Kol uzunluğunu aşamazsınız!");
                return false;
            }
            
            return true;
        }
        
        System.out.println("Izgara dışına çıkamazsınız!");
        return false;
    }
    
    @Override
    public void yukVer() {
        Scanner scan = new Scanner(System.in);
        boolean dongu = true;
        
        while(dongu) {
            System.out.println("Robotunun yuk miktari: (En fazla: paralel " + PARALEL_KAPASITE + "kg, seri " + SERI_KAPASITE + "kg)");
            int kg = scan.nextInt();
            scan.nextLine(); // '\n' yakalama

            if (kaldirabilirMi(kg)) {
                super.yukMiktari = kg;
                dongu = false;
            } else {
                System.out.println("Bu yuk miktarini kaldiramaz, yeniden girin");
            }
        }
    }

    @Override
    public final boolean kaldirabilirMi(int yuk) {
        return this.yukTasimaKapasitesi >= yuk;
    }
}
