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
public interface DersVeSinavSabitleri {
    public final static int VIZE = 0;
    public final static int MAZERET = 1;
    public final static int FINAL= 2;
    public final static int BUTUNLEME = 3;
    public final static int ODEV = 4;
    public final static int PROJE = 5;
    public final static int SINAV_SAYISI = 4;
    
    public static final String SINAV[] = {
        "Vize",
        "Mazeret",
        "Final",
        "Bütünleme"
    };
    
    public static final String NOT_ISMI[] = {
        "Vize",
        "Mazeret",
        "Final",
        "Bütünleme",
        "Ödev",
        "Proje"
    };
}
