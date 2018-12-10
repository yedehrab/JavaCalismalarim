/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Robot {
    
    // Konum bilgileri
    protected Point konum = new Point();
       
    // Motor sayısı
    protected int motorSayisi;
    
    // Yük miktarı (Gezgin'de sınırlama yok.)
    protected int yukMiktari;
   
    /**
     * Robotu kullanıcıdan alınan verilerle ızgaraya yerleştirme
     */
    public void yerlestir() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Robotunun baslangic bilgileri:");
        System.out.print("X -> ");
        konum.x = scan.nextInt();
        System.out.print("Y -> ");
        konum.y = scan.nextInt();
    }
    
    /**
     * Robotu başlatma
     * @param engeller Izgardaki engeller
     * @return Geçen süre
     */
    public abstract float baslat(ArrayList<Point> engeller);
    
    /**
     * Robotun yükünün konumunu döndürür.
     * @return Yükün konumu
     */
    public abstract Point yukAl();    
}
