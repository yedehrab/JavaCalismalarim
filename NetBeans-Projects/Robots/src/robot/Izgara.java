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
    public static final int KARE_SAYISI = 20;
    public static final int KENAR_UZUNLUGU = 30; // 10m
    
    private final int uzunluk;
    private final int genislik;
    
    private final ArrayList<Robot> robotlar = new ArrayList<Robot>();
    private final ArrayList<Point> engeller = new ArrayList<Point>();
    private final ArrayList<Point> yukler = new ArrayList<Point>();
    
    /**
     * Başlangıç işlemleri
     * @param args Kullanılmamaktadır. 
     */
    public static void main(String args[]) {
        Izgara izgara = new Izgara();
        izgara.robotlariTanimla();
        izgara.engelEklensinMi();
        izgara.robotHareketEttir();

        JFrame frame = new JFrame("Robotlar");

        // Izgareyı çerçeveye ekleme
        frame.add(izgara);
        // Çerçeveyi ortada gösterme
        frame.setLocationRelativeTo(null);
        // X'a basılınca process'i kapatma
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Otomatik boyutlandırma
        frame.pack();
        // Çerçeveyi görünür kılma
        frame.setVisible(true);
    }
    
    public Izgara() {
        super();
        
        // Uzunluğu ve genişliği hesaplama
        uzunluk = genislik = KARE_SAYISI * KENAR_UZUNLUGU;
    }
    
    /**
     * İzgara üzerinde robotları tanımlayamayı sağlar.
     */
    public void robotlariTanimla() {
        // Kullanıcıdan alınacak değerleri tutacak değişkenler
        Scanner scan = new Scanner(System.in);
       
        int i,robotSayisi, robotTuru;
        
        // Arayüz
        System.out.println("Tanımlanacak robot sayısı: ");
        robotSayisi = scan.nextInt();
        scan.nextLine(); // '\n' yakalama

        for (i = 0; i < robotSayisi; i++) {
            System.out.println((i + 1) + ". robotun tipi: ");
            System.out.println("-------------------");
            System.out.println("1- Hibrit (GezginManipulator) ");
            System.out.println("2- Gezgin ");
            System.out.println("3- Manipulatör");
            robotTuru = scan.nextInt();
            scan.nextLine(); // '\n' yakalama

            switch (robotTuru) {
                case 1:
                    gezginManipulatorEkle();
                    break;
                case 2:
                    gezginRobotEkle();
                    break;
                case 3:
                    manipulatorRobotEkle();
                    break;
                default:
                    System.out.println("Robot tipi hatalı girildi tekrar deneyin.");
                    i--;
                    break;
            }
        }
    }
    
    /**
     * İstenen bir robotu hareket ettirmeyi sağlar.
     */
    public void robotHareketEttir() {
        // Kullanıcıdan alınacak değerleri tutacak değişkenler
        Scanner scan = new Scanner(System.in);
        int robotNo;
        
        // Arayüz
        System.out.println("Hangi siradaki robot hareket ettirelecek: (1'denb başlar)");
        robotNo = scan.nextInt() - 1;
        scan.nextLine(); // '\n' yakalama

        if (robotNo < robotlar.size()) {
            robotlar.get(robotNo).baslat(engeller);
            yukler.add(robotlar.get(robotNo).yukAl());
        } else {
            System.out.println("Robot sayısından fazla değer giremezsiniz.");
            robotHareketEttir();
        }
    }
    
    /**
     * İzgaraya gezgin robot ekleme menüsü
     */
    public void gezginManipulatorEkle() {
        GezginManipulatorRobot gmr = GezginManipulatorRobot.olustur();
        gmr.yerlestir();
        robotlar.add(gmr);
    }
    
    /**
     * İzgaraya manipülator robot ekleme menüsü
     */
    public void manipulatorRobotEkle() {
        ManipulatorRobot mr = ManipulatorRobot.olustur(true);
        mr.yerlestir();
        robotlar.add(mr);
    }
    
    /**
     * İzgarae gezgin robot ekleme menüsü
     */
    public void gezginRobotEkle() {
        GezginRobot gr = GezginRobot.olustur(true);
        gr.yerlestir();
        robotlar.add(gr);
    }
    
    /**
     * İzgaraya engel ekleme menüsü
     */
    public void engelEklensinMi() {
        // Kullanıcı girdisini tutacak değişkenler
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
    
    

    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Izgaraları gri renkte çizme
        g2d.setColor(Color.gray);
        for (int i = 0; i < KARE_SAYISI; i++) {
            for (int j = 0; j < KARE_SAYISI; j++) {
                g2d.draw(kareOlustur(new Point(i, j)));
            }
        }
        
        // Engelleri kırmızı renkte çizme
        g2d.setColor(Color.red);
        engeller.forEach((engel) -> {
            g2d.fill(kareOlustur(engel));
        });
        
        // Yükleri gri renkte çizme
        g2d.setColor(Color.gray);
        yukler.forEach((yuk) -> {
            g2d.fill(kareOlustur(yuk));
        });
        
        // Robotları yeşil renkte çizme
        g2d.setColor(Color.green);
        robotlar.forEach((robot) -> {
            g2d.fill(kareOlustur(robot.konum));
        });
        
        g2d.dispose();
    }
   
    /**
     * Verilen nokta ile ızgarada kare üretir
     * @param p Nokta
     * @return Izgaraya çizilebilir kare verisi
     */
    public Rectangle kareOlustur(Point p) {
        return new Rectangle(
            p.x * KENAR_UZUNLUGU,
            p.y * KENAR_UZUNLUGU,
            KENAR_UZUNLUGU,
            KENAR_UZUNLUGU
        );
    }
    
    // Panelin boyutunu ayarlama
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(uzunluk, genislik);
    }
}
