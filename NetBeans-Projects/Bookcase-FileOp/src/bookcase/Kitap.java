/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcase;

public class Kitap extends KayitDosyasi {

    int id;
    String konu;
    String yazar;
    int fiyat;

    @Override
    public String metneDonustur() {
        return id + "," + konu + "," + yazar + "," + fiyat + "\n";
    }

    @Override
    public String dosyaIsmiAl() {
        return DOSYA_ISMI_KITAP;
    }

    @Override
    public void kaydet() {
        super.kaydet(this);
    }
}