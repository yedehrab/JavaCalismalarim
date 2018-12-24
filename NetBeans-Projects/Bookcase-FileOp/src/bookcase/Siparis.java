/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcase;

public class Siparis extends KayitDosyasi {
    int id;
    String kullaniciAdi;
    int kitapId;

    @Override
    public String metneDonustur() {
        return id + "," + kullaniciAdi + "," + kitapId + "\n";
    }

    @Override
    public String dosyaIsmiAl() {
        return DOSYA_ISMI_SIPARIS;
    }

    @Override
    public void kaydet() {
        super.kaydet(this);
    }
}