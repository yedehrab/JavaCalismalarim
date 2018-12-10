/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Izgara extends JPanel {
    
    public static final int IZGARA_UZUNLUGU = 10;
    
    private final int kareSayisi = 20;
    private final int kenarUzunlugu = 30; // 10
    private final int uzunluk;
    private final int genislik;
    
    private final ArrayList<Point> engeller = new ArrayList<Point>();
    private Point yuk;
    private Robot robot;
    
    public static void main(String args[]) {
        // Gezgin robot seçme
        Scanner scan = new Scanner(System.in);
        int tur;
        
        while (true) {
            Izgara izgara = new Izgara();
            
            System.out.println("Çözülecek problemi seçin");
            System.out.println("1- Problem1 (Gezgin Robot)");
            System.out.println("2- Problem2 (Manipülatör Robot)");
            System.out.println("3- Problem3 (GezginManipülatör Robot)");
            System.out.println("0- Çıkış");
            tur = scan.nextInt();

            switch (tur) {
                case 0:
                    System.exit(0);
                case 1:
                    izgara.problem1();
                    break;
                case 2:
                    izgara.problem2();
                    break;
                case 3:
                    izgara.problem3();
                    break;
            }
        JFrame frame = new JFrame("Robotlar");
        
        // Izgareyı çerçeveye ekleme
        frame.add(izgara);
        // Çerçeveyi ortada gösterme
        frame.setLocationRelativeTo(null);
        // Otomatik boyutlandırma
        frame.pack();
        // Çerçeveyi görünür kılma
        frame.setVisible(true);
        
        }
    }
    
    public Izgara() {
        super();
        
        // Uzunluğu ve genişliği hesaplama
        uzunluk = genislik = kareSayisi * kenarUzunlugu;
    }
    
    public void problem3() {
        engelEklensinMi();
        gezginManipulatorKullan();
    }
    
    public void problem2() {
        manipulatorRobotKullan();
    }
    
    public void problem1() {
        engelEklensinMi();
        gezginRobotKullan();
    }
    
    public void gezginManipulatorKullan() {
        // Manipulator robot
        System.out.println("Hız: 30m/s Yük Sınırı: 15kg");
        GezginManipulatorRobot gmr = new GezginManipulatorRobot();
        
        // Robotu başlatma
        gmr.baslat(engeller);

        // Yükü tanımlama1
        yuk = gmr.getKol();

        // Robotu tanımlama
        robot = gmr;
        
    }
    
    public void manipulatorRobotKullan() {
        // Manipulator robot
        ManipulatorRobot mr;
        
        // Gezgin robot seçme
        Scanner scan = new Scanner(System.in);
        int tur;
        
        System.out.println("Manipulator robotunun turunu secin:");
        System.out.println("1- Paralel (Yük Sınırı: 15kg)");
        System.out.println("2- Seri (Yük Sınırı: 10kg)");
        System.out.println("0- Çıkış");
        tur = scan.nextInt();
        
        // \n'i yakalama
        scan.nextLine();

        switch(tur) {
            case 0:
                System.exit(0);
            case 1:
                mr = new Paralel();
                break;
            case 2:
                mr = new Seri();
                break;
            default:
                System.out.println("Hatali secim yaptiniz");
                manipulatorRobotKullan();
                return;
        }
        
        if (mr != null) {
            mr.yukVer();
            System.out.println("Toplam sure: " + mr.komutlarlaKoluIlerlet() + "s");
            
            // Yükü tanımlama
            yuk = mr.getKol();
            
            // Robotu tanımlama
            robot = mr;
        }
    }
    
    public void engelEklensinMi() {
        Scanner scan = new Scanner(System.in);
        int cevap;
        
        System.out.println("Engel eklensin mi?");
        System.out.println("1- Ekle");
        System.out.println("2- Ekleme");
        
        cevap = scan.nextInt();
        
        if (cevap == 1) {
            int engelSayisi;
            System.out.println("Engel sayisi?");
            engelSayisi = scan.nextInt();
            
            // \n'i yakalama
            scan.nextLine();
            
            int i;
            for (i = 0; i < engelSayisi; i++) {
                Point p = new Point();
                
                System.out.println((i + 1) + ". Engelin X konumu");
                p.x = scan.nextInt();
                
                System.out.println((i + 1) + ". Engelin Y konumu");
                p.y = scan.nextInt();
                
                engeller.add(p);
            }
        }
    }
    
    public void gezginRobotKullan() {
        // Gezgin robot seçme
        Scanner scan = new Scanner(System.in);
        int tur;
        
        System.out.println("Gezgin robotunun turunu secin:");
        System.out.println("0- Tekerlekli ( Hız: 30m/s )");
        System.out.println("1- Paletli ( Hız: 20m/s )");
        System.out.println("2- Spider ( Hız: 10m/s )");
        tur = scan.nextInt();
        
        // \n'i yakalama
        scan.nextLine();

        switch(tur) {
            case 0:
                TekerlekliRobot tr = new TekerlekliRobot();
                System.out.println("Geçen süre: " + tr.komutlarlaIlerle(engeller));
                robot = tr;
                break;
            case 1:
                PaletliRobot pr = new PaletliRobot();
                System.out.println("Geçen süre: " + pr.komutlarlaIlerle(engeller));
                robot = pr;
                break;
            case 2:
                SpiderRobot sr = new SpiderRobot();
                System.out.println("Geçen süre: " + sr.komutlarlaIlerle(engeller));
                robot = sr;
                break;
            default:
                System.out.println("Hatali secim yaptiniz");
                gezginRobotKullan();
        }
        
    }

    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Izgaraları gri renkte çizme
        g2d.setColor(Color.gray);
        for (int i = 0; i < kareSayisi; i++) {
            for (int j = 0; j < kareSayisi; j++) {
                g2d.draw(kareOlustur(new Point(i, j)));
            }
        }
        
        // Engelleri kırmızı renkte çizme
        g2d.setColor(Color.red);
        engeller.forEach((engel) -> {
            g2d.fill(kareOlustur(engel));
        });
        
        
        // Yük varsa çizdirme
        if (yuk != null) {
            // Yükü gri renkte çizme
            g2d.setColor(Color.gray);
            g2d.fill(kareOlustur(yuk));
        }
        
        
        // Robotu yeşil renkte çizme
        g2d.setColor(Color.green);
        g2d.fill(kareOlustur(robot.konum));
        
        g2d.dispose();
    }
   
    public Rectangle kareOlustur(Point p) {
        return new Rectangle(
            p.x * kenarUzunlugu,
            p.y * kenarUzunlugu,
            kenarUzunlugu,
            kenarUzunlugu
        );
    }
    
    // Panelin boyutunu ayarlama
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(uzunluk, genislik);
    }
}
