/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcase;

import java.util.Scanner;

public abstract class KitapIslemleri {

    public static void kitaplariListele() {
        // Kitaplar için dosya tarayıcısı alma
        Scanner dosyaTariyici = KayitDosyasi.dosyaTariyicAl(new Kitap());

        if (dosyaTariyici != null) {
            System.out.printf("%-5s %-15s %-15s %-5s \n", "ID", "KONU", "YAZAR", "FIYAT");
            while (dosyaTariyici.hasNext()) {
                String satir = dosyaTariyici.nextLine();
                String[] sutunlar = satir.split(",");
                System.out.printf("%-5s %-15s %-15s %-5s\n", sutunlar[0], sutunlar[1], sutunlar[2], sutunlar[3]);
            }

            // Tarayıcıyı kapatma
            dosyaTariyici.close();
        }
    }

    /**
     * Kitap seçme
     * @param id kitap id'si
     * @param uye Kitabi secen kisi
     * @return Seçilirse true
     */
    public static boolean kitapSec(int id, Uye uye) {
        boolean sonuc = false;
        // Kitaplar için dosya tarayıcısı alma
        Scanner dosyaTariyici = KayitDosyasi.dosyaTariyicAl(new Kitap());

        if (dosyaTariyici != null) {
            while (dosyaTariyici.hasNext()) {
                String satir = dosyaTariyici.nextLine();
                String[] sutunlar = satir.split(",");

                if (id == Integer.parseInt(sutunlar[0])) {
                    sonuc = true;
                    break;
                }
            }

            // Tarayıcıyı kapatma
            dosyaTariyici.close();
        }

        // Kitabı seçilenlere ekleme
        if (sonuc) {
            uye.seciliKitapIdleri.add(id);
        }

        return sonuc;
    }

    /**
     * idsi verilen kitabın fiyatını alma
     * @param id Kitap idsi
     * @return Kitabın fiyatı (bulunamazsa  -1)
     */
    public static int kitapFiyatiAl(int id) {
        int fiyat = -1;
        // Kitaplar için dosya tarayıcısı alma
        Scanner dosyaTariyici = KayitDosyasi.dosyaTariyicAl(new Kitap());

        if (dosyaTariyici != null) {
            while (dosyaTariyici.hasNext()) {
                String satir = dosyaTariyici.nextLine();
                String[] sutunlar = satir.split(",");

                if (id == Integer.parseInt(sutunlar[0])) {
                    fiyat = Integer.parseInt(sutunlar[3]);
                    break;
                }
            }

            // Tarayıcıyı kapatma
            dosyaTariyici.close();
        }

        return fiyat;
    }

    /**
     * Konusu verilen kitabın fiyatını alma
     * @param konusu Kitabın konusu
     * @return Kitabın fiyatı (bulunamazsa  -1)
     */
    public static int kitapFiyatiAl(String konusu) {
        int fiyat = -1;
        // Kitaplar için dosya tarayıcısı alma
        Scanner dosyaTariyici = KayitDosyasi.dosyaTariyicAl(new Kitap());

        if (dosyaTariyici != null) {
            while (dosyaTariyici.hasNext()) {
                String satir = dosyaTariyici.nextLine();
                String[] sutunlar = satir.split(",");

                if (konusu.equals(sutunlar[1])) {
                    fiyat = Integer.parseInt(sutunlar[3]);
                    break;
                }
            }

            // Tarayıcıyı kapatma
            dosyaTariyici.close();
        }

        return fiyat;
    }

    /**
     * Sipariş no ile kitap idsi bulma
     * @param siparisNo Siparis id
     * @return Bulunamazsa -1
     */
    public static int siparisNoIleKitapIdAl(int siparisNo) {
        int kitapId = -1;

        // Kitaplar için dosya tarayıcısı alma
        Scanner dosyaTariyici = KayitDosyasi.dosyaTariyicAl(new Siparis());

        if (dosyaTariyici != null) {
            while (dosyaTariyici.hasNext()) {
                String satir = dosyaTariyici.nextLine();
                String[] sutunlar = satir.split(",");

                // Siparis id'si verilen siparisi bulma
                if (siparisNo == Integer.parseInt(sutunlar[0])) {
                    // Siparişten kitapId'sini alma
                    kitapId = Integer.parseInt(sutunlar[2]);
                }

            }

            // Tarayıcıyı kapatma
            dosyaTariyici.close();
        }

        return kitapId;
    }
}
