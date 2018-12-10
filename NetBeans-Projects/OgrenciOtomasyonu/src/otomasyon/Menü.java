/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otomasyon;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Yunus Emre
 */
public abstract class Menü extends Varsayilan{
    public Scanner input = new Scanner(System.in);
    
    public static int işlemTipi;
    
    public static final int CIKIS = -2;
    public static final int GERI = -1;
    public static final int ILERI = 0;
    
    public static final String ILERI_MESAJI = "İleri"; 
    public static final String CIKIS_MENU =  "[" + CIKIS + "] Kaydet ve Çık\n";
    public static final String GERI_MENU =   "[" + GERI + "] Geri Dön\n";
    public static final String ILERI_MENU = " [" + ILERI + "] " + ILERI_MESAJI + "\n";
    public static final String SATIR_MENU = "-----------------------------------------\n";
    public static final String MENU_SATIR_AT = "@@@@@@@@@@@@@@@@@@@@@@@\n";
    public static final String INPUT_MENU = "-> ";
    
    public abstract void menü();
    
    /**
     * Çıkmadan önceki son işlemler
     */
    public void çıkış(){       
            verileriYenile();
            System.exit(0);
    }
    
    public void satırÇiz(){
        System.out.print(SATIR_MENU);
    }
    
    public void gMenü(String öğeler){
        System.out.print(
            SATIR_MENU +
            öğeler + "\n" +
            SATIR_MENU +
            GERI_MENU +
            CIKIS_MENU +
            SATIR_MENU +
            INPUT_MENU
        );
    }
    
    public static void bekletme(String str){
        System.out.println(str + " Devam etmek için ENTER'e basınız.");
        new Scanner(System.in).nextLine();
    }
    
    /**
     * Ders dizisi öğelerini sıralı (1'den başlayarak) olarak menüde gösterme
     * @param dersler Sıralanması istenen ders dizisi
     */
    public void gMenü(ArrayList<Ders> dersler){
        String öğeler = "";
        int i = 1;
        for (Ders ders : dersler){
            öğeler += " [" + i + "] " + ders.ad + "\n";
            i++;
        }
        System.out.print(
            SATIR_MENU +
            öğeler +
            SATIR_MENU +
            GERI_MENU +
            CIKIS_MENU +
            SATIR_MENU +
            INPUT_MENU
        );
    }
    
    public void gMenü(ArrayList<Ders> dersler, String str){
        String öğeler = "";
        int i = 1;
        for (Ders ders : dersler){
            öğeler += " [" + i + "] " + ders.ad + "\n";
            i++;
        }
        System.out.print(
            SATIR_MENU +
            öğeler +
            SATIR_MENU +
            str + "\n" +
            GERI_MENU +
            CIKIS_MENU +
            SATIR_MENU +
            INPUT_MENU
        );
    }
    
    public void gMenü(String[] öğeDizisi){
        String öğeler = "";
        int i = 1;
        for (String öğe : öğeDizisi){
            öğeler += " [" + i + "] " + öğe + "\n";
            i++;
        }
        
        System.out.print(
            SATIR_MENU +
            öğeler +
            SATIR_MENU +
            GERI_MENU +
            CIKIS_MENU +
            SATIR_MENU +
            INPUT_MENU
        );
    }
    
    public void giMenü(String öğeler){
        System.out.print(
            SATIR_MENU +
            öğeler + "\n" +
            SATIR_MENU +
            ILERI_MENU +
            GERI_MENU +
            CIKIS_MENU +
            SATIR_MENU +
            INPUT_MENU
        );
    }
    
    public void giMenü(String[] öğeDizisi){
        String öğeler = "";
        int i = 1;
        for (String öğe : öğeDizisi){
            öğeler += " [" + i + "] " + öğe + "\n";
            i++;
        }
        
        System.out.print(
            SATIR_MENU +
            öğeler +
            SATIR_MENU +
            ILERI_MENU +
            GERI_MENU +
            CIKIS_MENU +
            SATIR_MENU +
            INPUT_MENU
        );
    }
    
    public void çMenü(String öğeler){
        System.out.print(
            SATIR_MENU +
            öğeler + "\n" +
            SATIR_MENU +
            CIKIS_MENU +
            SATIR_MENU +
            INPUT_MENU
        );
    }
    
    public void giMenü(String öğeler, int tip){
        System.out.print(
            SATIR_MENU +
            öğeler + "\n" +
            SATIR_MENU +
            GERI_MENU +
            CIKIS_MENU +
            SATIR_MENU +
            INPUT_MENU
        );
        
        String girdi = input.nextLine();
                
        switch (girdi) {
            case "" + GERI:
                System.out.println("");
                break;
            case "" + CIKIS:
                çıkış();
            default:
                iMenüİşlemi(girdi, tip);
                break;
        }
    }
    
    public void iMenüİşlemi(String girdi, int tip){
        System.out.println("Tanımlanmamış işlem");
    }
        
    
}
