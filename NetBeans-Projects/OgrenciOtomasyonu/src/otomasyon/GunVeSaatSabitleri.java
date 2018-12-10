/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otomasyon;

/**
 *
 * @author Yunus Emre
 */
public interface GunVeSaatSabitleri {    
    public static final String SAAT_BASLANGIC[] = {
            "8:30",
            "9:25",
            "10:20",
            "11:15",
            "12:10",
            "13:05",
            "14:00",
            "14:55",
            "15:50"
    };
    public static final String SAAT_BITIS[] = {
            "9:20",
            "10:15",
            "11:10",
            "12:05",
            "13:00",
            "13:55",
            "14:50",
            "15:45",
            "16:40"
    };
    
    public static final int PAZARTESI = 0;
    public static final int SALI = 1;
    public static final int CARSAMBA = 2;
    public static final int PERSEMBE = 3;
    public static final int CUMA = 4;
    
    public static final String GUN[] = {
        "Pazartesi",
        "Salı",
        "Çarşamba",
        "Perşembe",
        "Cuma"        
    };    
}
