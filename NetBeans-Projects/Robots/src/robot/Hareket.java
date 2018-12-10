/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Point;
import java.util.ArrayList;

public interface Hareket {
    
    // Konum sınırları
    int KONUM_UST_SINIR = 9;
    int KONUM_ALT_SINIR = 0;
    
    // Hız (saniyede kaç metre)
    int HIZLI_HIZ = 30;
    int NORMAL_HIZ = 20;
    int YAVAS_HIZ = 10;
    
    // Engelden geçme süresi
    int YAVAS_GECIS = 2;
    int HIZLI_GECIS = 1;

    /**
     * Hız değerinin ayarlanması
     */
    public abstract void hizAyarla();
    
    /**
     * Verilen koordinatlarda engele göre ilerleme
     * @param x Dikey ilerleme
     * @param y Yatay ilerleme 
     * @param engeller İzgaradaki engel verisi
     * @return Geçen süre
     */
    public abstract float ilerle(int x, int y, ArrayList<Point> engeller);
    
    /**
     * Engelden geçebilir mi kontrolü
     * @return Geçiyorsa true, aksi halde false
     */
    public abstract boolean engeldenGecebilirMi();
    
    /**
     * Engelden geçerken harcanan süreyi hesaplar
     * @return Geçen süre
     */
    public abstract float engeldenGecmeSuresiBul();
    
    /**
     * Kullanıcıdan alınan komutlarla ilerlemeyi sağlar
     * @param engeller İzgara üzerindeki engeller
     * @return Geçen süre
     */
    public abstract float komutlarlaIlerle(ArrayList<Point> engeller);
}
