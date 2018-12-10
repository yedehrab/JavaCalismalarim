/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Point;
import java.util.ArrayList;

public final class GezginManipulatorRobot extends Robot {
    // Hareketli konumdan sabit konuma geçmesi için gerekli süre
    private final float sabitlenmeSuresi = 0.4f;
    
    // Yükü robotun üzerinden alıp, robot koluna geçirmek için
    private final float gecisSuresi = 1.2f;
    
    private GezginRobot hareketliKisim;
    private ManipulatorRobot hareketsizKisim;
    
    /**
     * Gezgin manipğlator robot oluşturur
     * @return Oluşturulan gezgin manipülatör (hibrit) robot 
     */
    public static GezginManipulatorRobot olustur() {
        // Gezgin manipulator (hibrit) motor tanımlama
        GezginManipulatorRobot gmr = new GezginManipulatorRobot();
        
        // Hareketli kısmı tanımlama
        System.out.println("Hibrit robot için hareketli kısım: ");
        gmr.hareketliKisim = GezginRobot.olustur(false);
        
        // Hareketsiz kısmı tanımlama
        System.out.println("Hibrit robot için hareketsiz kısım: ");
        gmr.hareketsizKisim = ManipulatorRobot.olustur(false);
        
        return gmr;
    }
    
    @Override
    public void yerlestir() {
        // Hareketli kısımda robotu oluşturma
        super.yerlestir();
        
        // Robotun tüm konumları birbirine eştir. Adressleri ortak kılıyoruz.
        hareketsizKisim.konum = hareketliKisim.konum = konum;
    }
    
    @Override
    public Point yukAl() {
        // Yükün son hali hareketsiz kısmın kolundadır
        return hareketsizKisim.kol;
    }
    
    @Override
    public float baslat(ArrayList<Point> engeller) {
        float sure = 0f;
        
        sure += hareketliKisim.komutlarlaIlerle(engeller);
        sure += sabitlenmeSuresi;        
        System.out.println("Sabitlenme Süresi: " + sabitlenmeSuresi + "s");
        
        // Kolun konumunu, robotun konumuna göre ayarlama
        hareketsizKisim.kolunKonumuAyarla();
        
        hareketsizKisim.yukVer();
        sure += gecisSuresi;
        System.out.println("GeçişSüresi: " + gecisSuresi + "s");
        
        sure += hareketsizKisim.komutlarlaKoluIlerlet();
        System.out.println("Toplam geçen süre: " + sure + "s");
        
        return sure;
    }  
}
