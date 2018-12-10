/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;


public abstract class GezginRobot extends Robot implements Hareket {
    // Hız değeri (Tekerlekli > paletli > Spider)
    protected int hiz;    
    
    public GezginRobot() {
        // Hız ayarlama
        hizAyarla();
    }
    
    /**
     * Gezgin robot oluşturur
     * @param soruMetniOlsun Oluşturma arüyüzünde soru metninin görünürlüğü
     * @return Oluşturulan gezgin robot
     */
    public static GezginRobot olustur(boolean soruMetniOlsun) {        
        // Kullanıcı girdisini tutacak değişkenler
        Scanner scan = new Scanner(System.in);
        int tur;
        
        if (soruMetniOlsun)
            System.out.println("Gezgin robotunun turunu secin:");
        
        System.out.println("1- Tekerlekli ( Hız: 30m/s )");
        System.out.println("2- Paletli ( Hız: 20m/s )");
        System.out.println("3- Spider ( Hız: 10m/s )");
        tur = scan.nextInt();
        
        // \n'i yakalama
        scan.nextLine();

        switch(tur) {
            case 1:
                return new TekerlekliRobot();
            case 2:
                return new PaletliRobot();
            case 3:
                return new SpiderRobot();
            default:
                System.out.println("Hatali secim yaptiniz");
                return olustur(soruMetniOlsun);
        }
    }

    @Override
    public Point yukAl() {
        // Yükü üstündedir
        return konum;
    }
    
    @Override
    public float baslat(ArrayList<Point> engeller) {        
        float sure = komutlarlaIlerle(engeller);
        System.out.println("Toplam geçen süre: " + sure + "s");
        return sure;
    }
    
    @Override
    public float komutlarlaIlerle(ArrayList<Point> engeller) {
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
                x = y = 0;
                komutDizgisi = scan.nextLine();
                komut = komutDizgisi.split("\\s+");
                adim = Integer.parseInt(komut[0]);
                
                if (adim == -1){
                    return sure;
                }
                    
                
                switch (komut[2]) {
                    case "ileri":
                        y = -1;
                        break;
                    case "geri":
                        y = 1;
                        break;
                    case "saga":
                        x = 1;
                        break;
                    case "sola":
                        x = -1;
                        break;
                    default:
                        break;
                }
                
                sure += ilerle(x * adim, y * adim, engeller);
            
            } catch (NumberFormatException | java.lang.ArrayIndexOutOfBoundsException e) {
                System.out.println("Komut formatı hatalı");
            }
        }
    }
    
    @Override
    public float ilerle(int x, int y, ArrayList<Point> engeller) {
        // İlerleme kontrolü
        boolean ilerlediMi = false;
        boolean engelVarMi = false;
        
        // Geçen süre
        float sure = 0f;
        
        // Kontrollü x değerini ekleme
        if (x != 0) {
            if (konum.x + x <= KONUM_UST_SINIR && konum.x + x >= KONUM_ALT_SINIR) {
                // Engel kontrolü
                for (Point engel : engeller) {
                    if (konum.x + x == engel.x && konum.y == engel.y) {
                        engelVarMi = true;
                    }
                }

                if (engelVarMi) {
                    if (engeldenGecebilirMi()) {
                        float engeldenGecmeSuresi = engeldenGecmeSuresiBul();
                        sure += engeldenGecmeSuresi;
                        
                        konum.x += x;
                        ilerlediMi = true;
                        
                        System.out.println("Engelden geçme süresi: " + engeldenGecmeSuresi + "s");
                    } else {
                        System.out.println("Engelden geçemez.");
                    }
                } else {
                    konum.x += x;
                    ilerlediMi = true;
                }
            } else {
                System.out.println("Izgara dışına çıkamaz");
            } 
        }
        
        
        // Kontrollü y değerini ekleme
        if (y != 0) {
            if (konum.y + y <= KONUM_UST_SINIR && konum.y + y >= KONUM_ALT_SINIR) {
                // Engel kontrolü
                for (Point engel : engeller) {
                    if (konum.y + y == engel.y && konum.x == engel.x) {
                        engelVarMi = true;
                    }
                }

                if (engelVarMi) {
                    if (engeldenGecebilirMi()) {
                        float engeldenGecmeSuresi = engeldenGecmeSuresiBul();
                        sure += engeldenGecmeSuresi;
                        
                        konum.y += y;
                        ilerlediMi = true;
                        
                        System.out.println("Engelden geçme süresi: " + engeldenGecmeSuresi + "s");
                    } else {
                        System.out.println("Engelden geçemez.");
                    }
                } else {
                    konum.y += y;
                    ilerlediMi = true;
                }
            } else {
                System.out.println("Izgara dışına çıkamaz");
            } 
        }
        
        // İlerlediyse zaman geçer
        if (ilerlediMi) {
            sure += Math.abs(x * (Izgara.IZGARA_UZUNLUGU / (float) hiz)) + Math.abs(y * (Izgara.IZGARA_UZUNLUGU / (float) hiz));
            System.out.println("Adım süresi " + sure + "s");
        }
        
        return sure;
    }
}
