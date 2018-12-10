/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

/**
 *
 * @author yemre
 */
public interface Kol {
    int TASIMA_HIZI_YAVAS = 10;
    int TASIMA_HIZI_HIZLI = 20;
    
    int SERI_KAPASITE = 10;
    int PARALEL_KAPASITE = 15;
    
    int PARALEL_KOL = 50;
    int SERI_KOL = 20;
    
    public abstract float komutlarlaKoluIlerlet();
    public abstract boolean kolUzayabilirMi(int x, int y);
    public abstract void yukVer();
    public abstract boolean kaldirabilirMi(int yuk);
    public abstract void tasimaKapasitesiAyarla(); 
    public abstract void kolUzunluguAyarla();
    public abstract void koluAyarla();
    public abstract void tasimaHiziAyarla();
}
