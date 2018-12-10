/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

public interface Kol {
    int TASIMA_HIZI_YAVAS = 10;
    int TASIMA_HIZI_HIZLI = 20;
    
    int SERI_KAPASITE = 10;
    int PARALEL_KAPASITE = 15;
    
    int PARALEL_KOL = 50;
    int SERI_KOL = 20;
    
    /**
     * Kullanıcıdan alınan verilerle kolu ilerletmeyi sağlar
     * @return Geçen süre
     */
    public abstract float komutlarlaKoluIlerlet();
    
    /**
     * Kolun uzayabilirliğinin kontrolü
     * @param x Dikey uzama
     * @param y Yatay uzama
     * @return Uzayabilirse true
     */
    public abstract boolean kolUzayabilirMi(int x, int y);
    
    /**
     * Verilen yükün kaldırılabilirliğinin kontrolü
     * @param yuk Yük değeri
     * @return Kaldırılırsa true
     */
    public abstract boolean kaldirabilirMi(int yuk);
    
    /**
     * Yük verme eylemi
     */
    public abstract void yukVer();
    
    /**
     * Taşıma kapasitesini ayarlama
     */
    public abstract void tasimaKapasitesiAyarla(); 
    
    /**
     * Kolun uzunluğunu ayarlama
     */
    public abstract void kolUzunluguAyarla();
    
    /**
     * Kolun konumunu ayarlama
     */
    public abstract void kolunKonumuAyarla();
    
    /** 
     * Taşıma hızını ayarlama
     */
    public abstract void tasimaHiziAyarla();
}
