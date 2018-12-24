/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcase;

import java.util.Scanner;

public interface UyeIslemleri {

    public abstract void kullaniciAdiAyarla();
    public abstract void sifreAyarla();
    public abstract void sifreOlustur();
    public abstract boolean girisYapabilirMi();
    public abstract boolean kayitOlabilirMi();
    public abstract void seciliKitaplariTemizle();
    public abstract String kullaniciAdiAl();
}
