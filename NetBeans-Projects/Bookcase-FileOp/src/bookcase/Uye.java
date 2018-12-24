/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcase;

import java.util.ArrayList;
import java.util.Scanner;

public class Uye extends KayitDosyasi implements UyeIslemleri, UyeArayuzleri {

    private String kullaniciAdi;
    private String sifre;

    // Geçici değişkenler
    public final ArrayList<Integer> seciliKitapIdleri = new ArrayList<>();

    public Uye() {}

    @Override
    public boolean girisYapabilirMi() {
        // Sonuç değişkeni
        boolean sonuc = false;

        // Kullanıcı adı ve şifre tanımlıysa işlem yapma
        if (kullaniciAdi != null && sifre != null) {
            // Dosya tarayıcısı ile dosyayı tarama
            Scanner dosyaTarayici =  dosyaTariyicAl(this);
            // Dosya tarayıcı tanımsız değilse devam etme
            if (dosyaTarayici != null) {
                // Dosya sonuna kadar tarama
                while (dosyaTarayici.hasNext()) {
                    // Taranan satırları ',' e göre parçalama
                    String satir = dosyaTarayici.nextLine();
                    String[] veriler = satir.split(",");

                    // Sonucu ayarlama
                    if (kullaniciAdi.equals(veriler[0]) && sifre.equals(veriler[1])){
                        sonuc = true;
                        break;
                    }
                }

                // Tarayıcıyı kapatma
                dosyaTarayici.close();
            }
        }
        return sonuc;
    }

    @Override
    public boolean kayitOlabilirMi() {
        // Sonuç değişkeni
        boolean sonuc = false;

        // Kullanıcı adı ve şifre tanımlıysa işlem yapma
        if (kullaniciAdi != null && sifre != null) {
            // Dosya tarayıcısı ile dosyayı tarama
            Scanner dosyaTarayici = dosyaTariyicAl(this);

            // Dosya tarayıcı tanımsız değilse devam etme
            if (dosyaTarayici != null) {
                // Sonucu ayarlama
                sonuc = true;

                // Dosya sonuna kadar tarama
                while (dosyaTarayici.hasNext()) {
                    // Taranan satırları ',' e göre parçalama
                    String satir = dosyaTarayici.nextLine();
                    String[] veriler = satir.split(",");

                    // Kullanıcı adı bulunursa sonucu güncelleme
                    if (kullaniciAdi.equals(veriler[0])) {
                        sonuc = false;
                        break;
                    }
                }

                // Tarayıcıyı kapatma
                dosyaTarayici.close();
            }
        }

        return sonuc;
    }

    @Override
    public void kullaniciAdiAyarla() {
        // Kullanıcı adı alma
        System.out.println("Kullanıcı Adı: (Geri gitmek için -1)");
        String kullaniciAdi = tarayici.nextLine();

        if (!kullaniciAdi.equals("-1")) {
            this.kullaniciAdi = kullaniciAdi;
        }
    }

    @Override
    public void sifreAyarla() {
        if (kullaniciAdi != null) {
            System.out.println("Şifre: ");
            this.sifre = tarayici.nextLine();
        } else {
            System.out.println("Kullanıcı adı tanımsız!");
        }
    }

    @Override
    public void sifreOlustur() {
        if (kullaniciAdi != null) {
            System.out.println("Şifre: ");
            String geciciSifre = tarayici.nextLine();

            System.out.println("Şifreyi tekrarlayın: ");
            if (geciciSifre.equals(tarayici.nextLine())) {
                this.sifre = geciciSifre;
            } else {
                System.out.println("Sifreler uyusmamakta!");
            }
        } else {
            System.out.println("Kullanıcı adı tanımsız!");
        }
    }

    @Override
    public void girisYapArayuzu() {
        kullaniciAdiAyarla();
        sifreAyarla();
    }

    @Override
    public void kayitOlArayuzu() {
        kullaniciAdiAyarla();
        sifreOlustur();
    }

    @Override
    public String metneDonustur() {
        return kullaniciAdi + "," + sifre + "\n";
    }

    @Override
    public String dosyaIsmiAl() {
        return DOSYA_ISMI_UYE;
    }

    @Override
    public void kaydet() {
        super.kaydet(this);
    }

    @Override
    public void seciliKitaplariTemizle() {
        seciliKitapIdleri.clear();
    }

    @Override
    public String kullaniciAdiAl() {
        return kullaniciAdi;
    }
}
