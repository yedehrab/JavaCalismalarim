/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcase;

import java.util.ArrayList;
import java.util.Scanner;


public abstract class SiparisIslemleri {

    /**
     * Sipariş oluşturma
     * @param uye Siparişi oluşturan kişi
     */
    public static void olustur(Uye uye) {
        // Her seçili kitap için sipariş oluşturma
        uye.seciliKitapIdleri.forEach(id -> {
            Siparis siparis = new Siparis();

            // Siparis bilgilerini ayarlama
            siparis.id = yeniSiparisNoAl();
            siparis.kitapId = id;
            siparis.kullaniciAdi = uye.kullaniciAdiAl();

            // Siparisi dosyaya kaydetme
            siparis.kaydet();
        });
    }

    public static int toplamSiparisFiyatiniHesapla(ArrayList<Integer> siparisIdleri) {
        return siparisIdleri.stream().mapToInt(id -> KitapIslemleri.kitapFiyatiAl(KitapIslemleri.siparisNoIleKitapIdAl(id))).sum();
    }


    public static ArrayList<Integer> siparisIdleriAl(Uye uye) {
        ArrayList<Integer> siparisListesi = new ArrayList<>();

        // Kitaplar için dosya tarayıcısı alma
        Scanner dosyaTariyici = KayitDosyasi.dosyaTariyicAl(new Siparis());

        if (dosyaTariyici != null) {
            while (dosyaTariyici.hasNext()) {
                String satir = dosyaTariyici.nextLine();
                String[] sutunlar = satir.split(",");

                // Bulunan sipariş idlerini listeye ekleme
                if (uye.kullaniciAdiAl().equals(sutunlar[1])) {
                    siparisListesi.add(Integer.parseInt(sutunlar[0]));
                }

            }

            // Tarayıcıyı kapatma
            dosyaTariyici.close();
        }

        return siparisListesi;
    }

    /**
     * Üyenin siparişlerini listeleme
     * @param uye üye
     */
    public static void siparisleriListele(Uye uye) {
        // Kitaplar için dosya tarayıcısı alma
        Scanner dosyaTariyici = KayitDosyasi.dosyaTariyicAl(new Siparis());

        if (dosyaTariyici != null) {
            System.out.printf("%-5s %-10s %-5s\n", "ID", "KITAP NO", "FIYAT");
            while (dosyaTariyici.hasNext()) {
                String satir = dosyaTariyici.nextLine();

                String[] sutunlar = satir.split(",");
                if (uye.kullaniciAdiAl().equals(sutunlar[1])) {
                    System.out.printf("%-5s %-10s %-5s\n", sutunlar[0], sutunlar[2], KitapIslemleri.kitapFiyatiAl(Integer.parseInt(sutunlar[2])));
                }

            }

            // Tarayıcıyı kapatma
            dosyaTariyici.close();
        }
    }


    /**
     * Son sipariş no'nun 1 fazlasını alma
     * @return Yeni sipariş no
     */
    public static int yeniSiparisNoAl() {
        int no = 1;

        // Dosya tarayıcısı tanımlama
        Scanner dosyaTarayici = KayitDosyasi.dosyaTariyicAl(new Siparis());
        if (dosyaTarayici != null) {
            while (dosyaTarayici.hasNext()) {
                dosyaTarayici.nextLine();
                no++;
            }
        }
        return no;
    }
}
