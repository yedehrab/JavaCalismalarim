/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author yemre
 */
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

    public abstract void hizAyarla();
    public abstract float ilerle(int x, int y, ArrayList<Point> engeller);
    public abstract float komutlarlaIlerle(ArrayList<Point> engeller);
}
