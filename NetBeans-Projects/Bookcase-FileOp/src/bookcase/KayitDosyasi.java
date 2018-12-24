/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcase;

import java.io.*;
import java.util.Scanner;

public abstract class KayitDosyasi implements DosyaIslemleri{

    public static final String DOSYA_ISMI_UYE = "uyeler";
    public static final String DOSYA_ISMI_KITAP = "kitaplar";
    public static final String DOSYA_ISMI_SIPARIS = "siparisler";
    public static final String DOSYA_ISMI_ODEME = "odemeler";

    public abstract String metneDonustur();
    public abstract String dosyaIsmiAl();

    /**
     * Verileri dosyaya kaydetme
     * @param kayitDosyasi Kaydedilecek veri
     */
    public final void kaydet(KayitDosyasi kayitDosyasi) {
        try {
            // Dosyayı ekleme modunda açıp içine yazmayı sağlayan değişken tanımlama
            File dosya = new File(kayitDosyasi.dosyaIsmiAl() + ".txt");
            if (!dosya.exists()) {
                if (dosya.createNewFile()) {
                    FileOutputStream yazici = new FileOutputStream(dosya, true);

                    // Dosyaya kaydı yazma ve yazıcıyı kapatma
                    yazici.write(kayitDosyasi.metneDonustur().getBytes());
                    yazici.close();
                } else throw new IOException();
            } else {
                FileOutputStream yazici = new FileOutputStream(dosya, true);

                // Dosyaya kaydı yazma ve yazıcıyı kapatma
                yazici.write(kayitDosyasi.metneDonustur().getBytes());
                yazici.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Kayıt dosyasını okuyan tarayıcıyı alma
     * @param kayitDosyasi Okunacak dosya
     * @return Dosya tarayıcı
     */
    public static final Scanner dosyaTariyicAl(KayitDosyasi kayitDosyasi) {
        try {
            // Dosya yoksa oluşturma
            File dosya = new File(kayitDosyasi.dosyaIsmiAl() + ".txt");
            if (!dosya.exists()) {
                if (dosya.createNewFile()) {
                    // Dosyayı okuma
                    return new Scanner(dosya);
                } else throw new IOException();
            } else {
                return new Scanner(dosya);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
