/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcase;

public class Odeme extends KayitDosyasi implements DosyaIslemleri{
    int id;
    String kullaniciAdi;
    int fiyat;

    @Override
    public String metneDonustur() {
        return id + "," + kullaniciAdi + "," + fiyat + "\n";
    }

    @Override
    public String dosyaIsmiAl() {
        return DOSYA_ISMI_ODEME;
    }

    @Override
    public void kaydet() {
        kaydet(this);
    }
}
