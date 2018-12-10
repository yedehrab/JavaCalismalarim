/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otomasyon;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Yunus Emre
 */
public abstract class Uye extends Menü implements AnaIslemler, Serializable, DersVeSinavSabitleri{
    public final ArrayList<Ders> dersler = new ArrayList<>();
    
    public String[] bilgiler = {
        BOS_STR,
        BOS_STR,
        BOS_STR,
        BOS_STR,
        BOS_STR
    };
    
    public int üyeTipi;
    
    public static boolean güncelleme = false;
    public static boolean uygunMu[] = {
        false,
        false,
        false,
        false,
        false
    };
    
    public static final int UYE_OGRENCI = 0;
    public static final int UYE_OGRETMEN = 1;
    
    
    public static final int NO = 0;
    public static final int KULLANICI_ADI = 1;
    public static final int SIFRE = 2;
    public static final int E_MAIL = 3;
    public static final int ISIM = 4;
    
    public static final String[] UYE = {
        "Öğrenci",
        "Öğretmen"
    };

    @Override
    public void menü() {
        while (true){
            güncelleme = false;
            
            
            consoluTemizle();
            
            gMenü(UYE[üyeTipi] + " yönetim işlemleri\n" +
                SATIR_MENU +
                " [1]  Oluştur \n" +
                " [2]  Güncelle \n" + 
                " [3]  Sil" 
            );
            
            String answer = input.nextLine();

            
            switch(answer){
                    case "1":
                        oluştur();
                        return;
                    case "2":
                        güncelle();
                        return;
                    case "3":
                        sil();
                        break;
                    case "" + GERI:
                        return;
                    case "" + CIKIS:
                       çıkış();
                }
        }
    }

    @Override
    public void oluştur(){
        String answer;
        
        if(!(Ogrenci.girişYapıldı || Ogretmen.girişYapıldı)){
                do {
                    consoluTemizle();

                    giMenü(
                        UYE[üyeTipi] + " işlemleri (" + this.bilgiler[ISIM] + ")\n" +
                        SATIR_MENU +
                        "No: " + this.bilgiler[NO] + "\n" +
                        SATIR_MENU +
                        "Lütfen " + UYE[üyeTipi] + " kimlik numarası giriniz"
                    );

                    answer = input.nextLine();

                    switch(answer){
                        case "" + ILERI:
                            break;
                        case "" + GERI:
                            return;
                        case "" + CIKIS:
                            çıkış();
                        default:
                            if(yeniMi(answer, NO)){
                                this.bilgiler[NO] = answer;
                                uygunMu[NO] = true;
                            }
                    }
                } while(!answer.equals("" + ILERI));
        }
        
        
        do {
            consoluTemizle();
            
            giMenü(
                UYE[üyeTipi] + " işlemleri (" + this.bilgiler[ISIM] + ")\n" +
                SATIR_MENU +
                "Kullanıcı adı: " + this.bilgiler[KULLANICI_ADI] + "\n" +
                SATIR_MENU +
                "Lütfen kullanıcı adını giriniz"
            );
            
            answer = input.nextLine();
            
            switch(answer){
                case "" + ILERI:
                    break;
                case "" + GERI:
                    return;
                case "" + CIKIS:
                    çıkış();
                default:
                    if(yeniMi(answer, KULLANICI_ADI)){
                        this.bilgiler[KULLANICI_ADI] = answer;
                        uygunMu[KULLANICI_ADI] = true;
                    }
            }
        } while(!answer.equals("" + ILERI));
        
        do {
            consoluTemizle();
            
            giMenü(
                UYE[üyeTipi] + " işlemleri (" + this.bilgiler[ISIM] + ")\n" +
                SATIR_MENU +
                "Şifre: " + this.bilgiler[SIFRE] + "\n" +
                SATIR_MENU +
                "Lütfen şifre giriniz"
            );
            
            answer = input.nextLine();
            
            switch(answer){
                case "" + ILERI:
                    break;
                case "" + GERI:
                    return;
                case "" + CIKIS:
                    çıkış();
                default:
                    this.bilgiler[SIFRE] = answer;
                    uygunMu[SIFRE] = true;
                    
            }
        } while(!answer.equals("" + ILERI));
        
        do {
            consoluTemizle();
            
            giMenü(
                UYE[üyeTipi] + " işlemleri (" + this.bilgiler[ISIM] + ")\n" +
                SATIR_MENU +
                "E-Mail: " + this.bilgiler[E_MAIL] + "\n" +
                SATIR_MENU +
                "Lütfen e-mail giriniz"
            );
            
            answer = input.nextLine();
            
            switch(answer){
                case "" + ILERI:
                    break;
                case "" + GERI:
                    return;
                case "" + CIKIS:
                    çıkış();
                default:
                    if(yeniMi(answer, E_MAIL)){
                        this.bilgiler[E_MAIL] = answer;
                        uygunMu[E_MAIL] = true;
                    }
            }
        } while(!answer.equals("" + ILERI));
        
        if(!(Ogrenci.girişYapıldı || Ogretmen.girişYapıldı))
            do {
                consoluTemizle();

                giMenü(
                    UYE[üyeTipi] + " işlemleri (" + this.bilgiler[ISIM] + ")\n" +
                    SATIR_MENU +
                    "Tam isim: " + this.bilgiler[ISIM] + "\n" +
                    SATIR_MENU +
                    "Lütfen tam isim (ad / soyad) giriniz"
                );

                answer = input.nextLine();

                switch(answer){
                    case "" + ILERI:
                        break;
                    case "" + GERI:
                        return;
                    case "" + CIKIS:
                        çıkış();
                    default:
                        this.bilgiler[ISIM] = answer;
                        uygunMu[ISIM] = true;
                        
                }
            } while(!answer.equals("" + ILERI));
    }
    
    protected void sınavProgramınıGöster(){
        SinavProgrami sınavProgramı = new SinavProgrami();
        sınavProgramı.dersleriAyarla(dersler);
        sınavProgramı.hepsiniGöster();
    }    
    
    protected void dersProgramıGöster(){
        DersProgrami dersProgramı = new DersProgrami();
        dersProgramı.dersleriAyarla(dersler);       
        dersProgramı.hepsiniGöster();
    }
    
    public final boolean yeniMi(String bilgi, int bilgiKodu){
        for (Ogrenci öğrenci : kayıtlarÖğrenci) {
            if (öğrenci.bilgiler[bilgiKodu].equals(bilgi) || bilgi.equals("")){
                bekletme("Hatalı veya mevcut olan bir bilgi girdiniz.");
                return false;
            }
        }
        for (Ogretmen öğretmen : kayıtlarÖğretmen) {
            if (öğretmen.bilgiler[bilgiKodu].equals(bilgi) || bilgi.equals("")){
                bekletme("Hatalı veya mevcut olan bir bilgi girdiniz.");
                return false;
            }
        }
        return true;
    }
    
    public void derslerimiGöster(){
        System.out.println("Seçtiğiniz dersler:");
        satırÇiz();
        for (Ders ders : dersler){
            for (int i = 0; i < kayıtlarDers.size(); i++){
                if (ders.ad.equals(kayıtlarDers.get(i).ad))
                    System.out.println(" [" + (i + 1) + "] " + ders.ad);
            }
            
        }
        satırÇiz();
    }
}
