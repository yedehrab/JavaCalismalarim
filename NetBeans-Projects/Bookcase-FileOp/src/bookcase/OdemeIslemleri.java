/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcase;


import java.util.Scanner;

public abstract class OdemeIslemleri {
    /**
     * Ödeme oluşturma
     * @param uye Ödeme oluşturan kişi
     */
    public static void olustur(Uye uye) {
        // Her olan siparis için ödeme oluşturma
        Odeme odeme = new Odeme();

        // Siparis bilgilerini ayarlama
        odeme.id = yeniOdemeNoAl();
        odeme.kullaniciAdi = uye.kullaniciAdiAl();
        odeme.fiyat = SiparisIslemleri.toplamSiparisFiyatiniHesapla(SiparisIslemleri.siparisIdleriAl(uye));

        // Siparisi dosyaya kaydetme
        odeme.kaydet();
    }

    /**
     * Son sipariş no'nun 1 fazlasını alma
     * @return Yeni sipariş no
     */
    public static int yeniOdemeNoAl() {
        int no = 1;

        // Dosya tarayıcısı tanımlama
        Scanner dosyaTarayici = KayitDosyasi.dosyaTariyicAl(new Odeme());
        if (dosyaTarayici != null) {
            while (dosyaTarayici.hasNext()) {
                dosyaTarayici.nextLine();
                no++;
            }
        }
        return no;
    }



    public static void odemeGecmisi(Uye uye) {
        // Kitaplar için dosya tarayıcısı alma
        Scanner dosyaTariyici = KayitDosyasi.dosyaTariyicAl(new Odeme());

        if (dosyaTariyici != null) {
            System.out.printf("%-5s %-15s %-7s \n", "NO", "KULLANICI ADI", "FİYAT");
            while (dosyaTariyici.hasNext()) {
                String satir = dosyaTariyici.nextLine();
                String[] sutunlar = satir.split(",");

                if (uye.kullaniciAdiAl().equals(sutunlar[1])) {
                    System.out.printf("%-5s %-15s %-7s \n", sutunlar[0], sutunlar[1], sutunlar[2]);
                }
            }

            // Tarayıcıyı kapatma
            dosyaTariyici.close();
        }
    }
}

