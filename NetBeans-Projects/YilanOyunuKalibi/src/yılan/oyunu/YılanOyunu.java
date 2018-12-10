package yılan.oyunu;

import javax.swing.JFrame;

public class YılanOyunu {

    public static void main(String[] args) {
        
        JFrame.setDefaultLookAndFeelDecorated(true); // Tema
        
        JFrame oyun = new JFrame("Yılan Oyunu");
        oyun.setBounds(320, 120, 641, 484); // Ekranda görülme; başlangıç (x,y), Bitiş (x,y)
        // 11,34 çerçeveye gidiyor.
        OyunAlanı oyunalanı = new OyunAlanı(630, 450);
        oyun.add(oyunalanı);
        oyun.addKeyListener(oyunalanı.alYılan());
        
        oyun.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X'a basınca sistemi kapatma
        oyun.setResizable(true); // Yeniden boyutlanabilme
        oyun.setVisible(true); // Görünürlük
        
    } 
    
}
