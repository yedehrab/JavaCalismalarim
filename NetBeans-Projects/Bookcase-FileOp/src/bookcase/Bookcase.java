/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcase;

import java.util.Scanner;

public class Bookcase {

    // System tarayıcısı tanımlama
    private static Scanner tarayici = new Scanner(System.in);
    private static Uye uye = null;

    public static void main(String[] args) {
        anaArayuz();
    }

    public static void anaArayuz() {
        int answer;

        // Program arayüzü
        while (true) {
            System.out.println("1 Giriş Yap");
            System.out.println("2 Kayıt Ol");
            System.out.println("0 Kapat");

            // Tarayıcı ile girdi alma
            answer = tarayici.nextInt();
            tarayici.nextLine();

            switch (answer) {
                case 0:
                    // Programı kapatma
                    System.exit(0);
                case 1:
                    girisYapArayuzu();
                    break;
                case 2:
                    kayitOlArayuzu();
                    break;
            }
        }
    }

    public static void girisYapArayuzu() {
        uye = new Uye();
        uye.girisYapArayuzu();

        if (uye.girisYapabilirMi()) {
            System.out.println("Giriş başarılı!");
            islemArayuzu();
        } else {
            System.out.println("Uye bulunamadı!");
        }
    }

    public static void kayitOlArayuzu() {
        uye = new Uye();
        uye.kayitOlArayuzu();

        if (uye.kayitOlabilirMi()) {
            uye.kaydet();
            System.out.println("Kayıt başarılı, otomatik giriş yapıldı!");
            islemArayuzu();
        } else {
            System.out.println("Uye eşsiz değil ya da oluşturulamadı!");
        }
    }

    public static void islemArayuzu() {
        int answer;

        while (true) {
            System.out.println("1 Kitap İşlemleri");
            System.out.println("2 Sipariş İşlemleri");
            System.out.println("3 Ödeme İşlemleri");
            System.out.println("0 Çıkış");

            // Kullanıcıdan veri alma
            answer = tarayici.nextInt();

            // '\n' yakalama
            tarayici.nextLine();

            switch (answer) {
                case 0:
                    // Üyeyi kaldırma
                    uye = null;
                    return;
                case 1:
                    kitapIslemleri();
                    break;
                case 2:
                    siparisArayuzu();
                    break;
                case 3:
                    odemeArayuzu();
                    break;

            }
        }
    }

    public static void kitapIslemleri() {
        int answer;

        while (true) {
            System.out.println("1 Kitap Seçme");
            System.out.println("2 Kitapları Listele");
            System.out.println("3 Secili Kitapları Listele");
            System.out.println("4 Secili Kitaplari Temizleme");
            System.out.println("0 Geri");

            // Kullanıcıdan veri alma
            answer = tarayici.nextInt();

            // '\n' yakalama
            tarayici.nextLine();

            switch (answer) {
                case 0:
                    return;
                case 1:
                    kitapSecmeArayuzu();
                    break;
                case 2:
                    KitapIslemleri.kitaplariListele();
                    break;
                case 3:
                    System.out.println(uye.seciliKitapIdleri);
                    break;
                case 4:
                    uye.seciliKitaplariTemizle();
                    break;

            }
        }
    }

    public static void kitapSecmeArayuzu() {

        System.out.println("Seçilecek kitabın idsi: (Çıkış için -1)");
        int id = tarayici.nextInt();
        tarayici.nextLine();

        if (id == -1) {
            return;
        }

        // Üyeye kitap seçtirme
        if (KitapIslemleri.kitapSec(id, uye)) {
            System.out.println("Seçim başarılı!");
        } else {
            System.out.println("Kitap bulunamadı!");
        }

        // Yineleme
        kitapSecmeArayuzu();
    }

    public static void siparisArayuzu() {
        int answer;

        while (true) {
            System.out.println("1 Siparisleri Listele");
            System.out.println("2 Sipariş olustur");
            System.out.println("3 Toplam fiyatı göster");
            System.out.println("0 Geri");

            // Kullanıcıdan veri alma
            answer = tarayici.nextInt();

            // '\n' yakalama
            tarayici.nextLine();

            switch (answer) {
                case 0:
                    return;
                case 1:
                    SiparisIslemleri.siparisleriListele(uye);
                    break;
                case 2:
                    SiparisIslemleri.olustur(uye);
                    System.out.println("Siparişler oluşturuldu");
                    break;
                case 3:
                    System.out.println("Toplam fiyat: " + SiparisIslemleri.toplamSiparisFiyatiniHesapla(SiparisIslemleri.siparisIdleriAl(uye)));
            }
        }
    }

    public static void odemeArayuzu() {
        int answer;

        while (true) {
            System.out.println("1 Ödeme geçmişini göster");
            System.out.println("2 Ödemeyi Tamamla");
            System.out.println("0 Geri");

            // Kullanıcıdan veri alma
            answer = tarayici.nextInt();

            // '\n' yakalama
            tarayici.nextLine();

            switch (answer) {
                case 0:
                    return;
                case 1:
                    OdemeIslemleri.odemeGecmisi(uye);
                    break;
                case 2:
                    OdemeIslemleri.olustur(uye);
                    System.out.println("Ödeme tamamlandı!");
                    break;
            }
        }
    }
    
}
