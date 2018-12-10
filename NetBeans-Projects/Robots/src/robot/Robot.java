/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Point;
import java.util.Scanner;

public abstract class Robot {
    
    // Konum bilgileri
    protected Point konum = new Point();
       
    // Motor sayısı
    protected int motorSayisi;
    
    // Yük miktarı (Gezgin'de sınırlama yok.)
    protected int yukMiktari;
    
    public Robot() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Robotunun baslangic bilgileri:");
        System.out.print("X -> ");
        konum.x = scan.nextInt();
        System.out.print("Y -> ");
        konum.y = scan.nextInt();
    }
    
    public void setKonum(Point konum) {
        this.konum = konum;
    }
    
    public Point getKonum() {
        return this.konum;
    }

    public int getMotorSayisi() {
        return motorSayisi;
    }

    public void setMotorSayisi(int motorSayisi) {
        this.motorSayisi = motorSayisi;
    }

    public int getYukMiktari() {
        return yukMiktari;
    }

    public void setYukMiktari(int yukMiktari) {
        this.yukMiktari = yukMiktari;
    }
    
    
    
}
