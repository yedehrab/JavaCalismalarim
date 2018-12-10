
package yılan.oyunu;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class OyunAlanı extends JPanel implements ActionListener{
        
    Yılan yılan = new Yılan();
    Dörtgen  çerçeve;
    Yem yem;
    
    int yfrekans = 100; // Ekran yenilenme frekansı
    int xhız = 1;
    int yhız = 0;
    
    Timer tetikleyici = new Timer(1000 / yfrekans, this);
    
    public OyunAlanı(int x, int y){
        
        super();
        
        çerçeve = new Dörtgen(x, y);
        yem = new Yem(x, y);
        
        tetikleyici.start();
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        çerçeve.ekranaÇiz(g);
        yılan.ekranaÇiz(g);
        yem.ekranaÇiz(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        yılan.hareketEt(çerçeve);
        repaint();
    }
    
    public void xhızAyarla(int hız){
        
        this.xhız = hız;
    }
    
    public void yhızAyarla(int hız){
        
        this.yhız = hız;
    }
    
    public void yFrekansAyarla(int yfrekans){
        
        this.yfrekans = yfrekans;
    }
    
    public Yılan alYılan(){
        
        return yılan;
    }
}
