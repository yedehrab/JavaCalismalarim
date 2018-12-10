/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otomasyon;

import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Yunus Emre AK
 * 1306150001
 */

/**
 * Biten Kısımlar
 * Ders Programı Class
 * Sınav Programı Class
 * Tarih Class
 * 
 * @author Yunus Emre
 */
public abstract class DataBase {
    /**
     * Dosya işlemleri
     */
    public static File dosya;
    public static FileOutputStream yazıcı;
    public static FileInputStream okuyucu;
    public static ObjectOutputStream objeYazıcı;
    public static ObjectInputStream objeOkuyucu;
    
    /**
     * Kayıt verileri
     */
    
    public static ArrayList<Ders> kayıtlarDers;
    public static ArrayList<Ogrenci> kayıtlarÖğrenci;
    public static ArrayList<Ogretmen> kayıtlarÖğretmen;
    public static ArrayList<DersProgrami> kayıtlarDersProgramı;
    public static ArrayList<SinavProgrami> kayıtlarSınavProgramı;
    public static HashMap<String, Admin> kayıtlarAdmin;


    /*
    public static ArrayList KAYITLAR[] = {
        kayıtlarDers,
        kayıtlarÖğrenciler,
        kayıtar
    };
    
    // HashSet kullan
    // Kullanıcı adı ve şifre için HashMap  kullan
    // kullanıcıAdı+şifre keyi ile kaydet 
    */
       
    public static final String KLASOR_ADI = "Kayıtlar"; 
    
    
    /**
     * Dosya Adları
     */
    public static final String[] DOSYA_ADI= new String[]{
        KLASOR_ADI + "\\" + "öğrenciler.db",
        KLASOR_ADI + "\\" + "öğretmenler.db",
        KLASOR_ADI + "\\" + "dersler.db",
        KLASOR_ADI + "\\" + "sınavlar.db",
        KLASOR_ADI + "\\" + "ders programları.db",
        KLASOR_ADI + "\\" + "sınav programları.db",      
    };
    
    /**
     * Dosya numaraları
     */
    public static final int DOSYA_OGRENCILER = 0;
    public static final int DOSYA_OGRETMENLER = 1;
    public static final int DOSYA_DERSLER = 2;
    public static final int DOSYA_SINAVLAR = 3;
    public static final int DOSYA_DERS_PROGRAMI = 4;
    public static final int DOSYA_SINAV_PROGRAMI = 5;
    public static final int DOSYA_SAYISI = 6;
    
    public static final int BOLUM_BOS = 0;
    public static final int BOLUM_BILGISAYAR = 1;
    public static final int BOLUM_ENDUSTRI = 2;
    
    public static boolean yeni = true;
    
    /**
    * Hata sabitleri 
    */
    public final static int HATA_INT = -101;
    
    /**
     * Boş sabitleri
     */
    public static final int BOS_INT = -100;
    public static final String BOS_STR = "?";
    
    private static final boolean TEMIZ_KONSOL = true;
    
    public static final void dosyaOluşturAç(int dosyaKodu) throws IOException{
        dosya = new File(DOSYA_ADI[dosyaKodu]); 
        
        if(!dosya.exists())
            dosya.createNewFile();        
    }
    
    public static final void yazıcıOluştur(int dosyaKodu) throws IOException{
        dosyaOluşturAç(dosyaKodu);
        yazıcı = new FileOutputStream(dosya);
    }
    
    public static final void objeYazıcıOluştur(int dosyaKodu) throws IOException{
        yazıcıOluştur(dosyaKodu);
        objeYazıcı = new ObjectOutputStream(yazıcı);
    }
    
    public static final void objeYaz(int dosyaKodu, Object obje) throws IOException{
        objeYazıcıOluştur(dosyaKodu);
        dosya.delete();
        dosya.createNewFile();
        objeYazıcı.writeObject(obje);
        objeYazıcı.flush(); 
    }
    
    public static final void okuyucuOluştur(int dosyaKodu) throws IOException{
        dosyaOluşturAç(dosyaKodu);
        okuyucu = new FileInputStream(dosya);
    }
    
    public static final void objeOkuyucuOluştur(int dosyaKodu) throws IOException{
        okuyucuOluştur(dosyaKodu);
        objeOkuyucu = new ObjectInputStream(okuyucu);
    }
    
    public static final Object objeOku(int dosyaKodu) throws IOException, ClassNotFoundException{
        objeOkuyucuOluştur(dosyaKodu);
        return objeOkuyucu.readObject();
    }
    
    public static final void verileriAl() throws IOException, ClassNotFoundException {
        kayıtlarDers = (ArrayList<Ders>) objeOku(DOSYA_DERSLER);
        kayıtlarÖğrenci = (ArrayList<Ogrenci>) objeOku(DOSYA_OGRENCILER);
        kayıtlarÖğretmen = (ArrayList<Ogretmen>) objeOku(DOSYA_OGRETMENLER);
        kayıtlarDersProgramı = (ArrayList<DersProgrami>) objeOku(DOSYA_DERS_PROGRAMI);
        kayıtlarSınavProgramı = (ArrayList<SinavProgrami>) objeOku(DOSYA_SINAV_PROGRAMI);
    }
    /**
     * Tek bir veri dosyasını yenilemeyi sağlar.
     * @param dosyaKodu Yenilenecek veri dosyası kodu
     */
    public static final void veriYenile(int dosyaKodu){
        try{
            switch(dosyaKodu){
                case DOSYA_DERSLER:
                    objeYaz(dosyaKodu, kayıtlarDers);
                    break;
                case DOSYA_OGRENCILER:
                    objeYaz(dosyaKodu, kayıtlarÖğrenci);
                    break;
                case DOSYA_OGRETMENLER:
                    objeYaz(dosyaKodu, kayıtlarÖğretmen);
                    break;
                case DOSYA_DERS_PROGRAMI:
                    objeYaz(dosyaKodu, kayıtlarDersProgramı);
                    break;
                case DOSYA_SINAV_PROGRAMI:
                    objeYaz(dosyaKodu, kayıtlarSınavProgramı);
                    break;
            }
        } catch(IOException e){}
    }
    
    /**
     * Tüm veri dosyalarını yeniler
     */
    public static final void verileriYenile(){
        for (int i = 0; i < DOSYA_SAYISI; i++){
            veriYenile(i);
        }
    }
    
    
    
    public static void consoluTemizle(){
        if(TEMIZ_KONSOL){
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
