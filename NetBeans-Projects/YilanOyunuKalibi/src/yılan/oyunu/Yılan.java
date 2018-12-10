
package yılan.oyunu;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Yılan implements KeyListener{
    
    Dörtgen[] yılan;
    
    int yılanuzunluğu = 500; // Varsayılan yılan uzunluğu
    int yılangenişliği = 1; // Varsayılan yılan kalınlığı
    int xbkonum = 300 - (yılangenişliği * yılanuzunluğu) / 2; // Başlangıç x konumu
    int xbaşkonum = xbkonum + (yılanuzunluğu - 1) * yılangenişliği;
    int ybaşkonum = 200;
    int sıçrama = 2;
    
    boolean sıçramaizni = true;
    
    public Yılan(){
        
        super();
        
        yılan = new Dörtgen[yılanuzunluğu];
        for(int i = 0; i < yılanuzunluğu; i++){
    
            yılan[i] = new Dörtgen(xbkonum + i * yılangenişliği,ybaşkonum,yılangenişliği,yılangenişliği); // Ekrana çizildiğinde, kareler üst üste gelmemesi için her yeni dörtgen eklendiğinde genişlik kadar kaydırılır.
            
        }
        
    }
    
    
    
    
    public void ekranaÇiz(Graphics g){
        
        for(int i = 0; i < yılanuzunluğu; i++){
            
            yılan[i].ekranaÇiz(g);
        }
    }
    
    public Dörtgen[] alYılan(){
        
        return yılan;
    }
    
    public void hareketEt(Dörtgen çerçeve){
        
        for(int i = 0; i < yılanuzunluğu; i++){
                        
            yılan[i].hareketEt(xbaşkonum, ybaşkonum, çerçeve, başXHızAl(), başYHızAl());
            
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        /*
        if(e.getKeyChar() == ' ' && sıçramaizni == true){
            
            başXHızAyarla(başXHızAl() * sıçrama);
            başYHızAyarla(başYHızAl() * sıçrama);
            
            this.sıçramaizni = false;
        }
        /*
        else if(e.getKeyChar() == 's'){
            
            if(başXHızAl() > 1 || başYHızAl() > 1){
                
                başXHızAyarla(başXHızAl() / sıçrama);
                başYHızAyarla(başYHızAl() / sıçrama);
            }
            
        }
        */
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyChar() == ' ' && sıçramaizni == true){
            
            başXHızAyarla(başXHızAl() * sıçrama);
            başYHızAyarla(başYHızAl() * sıçrama);
            
            this.sıçramaizni = false;
            System.out.println("Hızlandı.");
        }
        if(e.getKeyChar() == 's' && sıçramaizni == false){
            
            başXHızAyarla(başXHızAl() / sıçrama);
            başYHızAyarla(başYHızAl() / sıçrama);
            
            this.sıçramaizni = true;
            System.out.println("Yavaşladı.");
        }
        
        else if(e.getKeyCode() == e.VK_UP){
            
            if(başXHızAl() > 0)
                başYHızAyarla(-başXHızAl());
            else if(başXHızAl() < 0)
                başYHızAyarla(başXHızAl());
            else if (başYHızAl() > 0)
                başYHızAyarla(-başYHızAl());
            başXHızAyarla(0);
        }
        
        else if(e.getKeyCode() == e.VK_DOWN){
            
            if(başXHızAl() > 0)
                başYHızAyarla(başXHızAl());
            else if(başXHızAl() < 0)
                başYHızAyarla(-başXHızAl());
            else if (başYHızAl() < 0)
                başYHızAyarla(-başYHızAl());
            başXHızAyarla(0);
        }
        
        else if(e.getKeyCode() == e.VK_LEFT){
            
            if(başYHızAl() > 0)
                başXHızAyarla(-başYHızAl());
            else if(başYHızAl() < 0)
                başXHızAyarla(başYHızAl());
            else if (başXHızAl() > 0)
                başXHızAyarla(-başXHızAl());
            
            başYHızAyarla(0);
        }
        
        else if(e.getKeyCode() == e.VK_RIGHT){
            
            if(başYHızAl() > 0)
                başXHızAyarla(başYHızAl());
            else if(başYHızAl() < 0)
                başXHızAyarla(-başYHızAl());
            else if (başXHızAl() < 0)
                başXHızAyarla(-başXHızAl());
            başYHızAyarla(0);
        }
        
        xbaşkonum = başXAl();
        ybaşkonum = başYAl();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    public int başXAl(){
        return yılan[yılanuzunluğu -1].xAl();
    }
    
    public int başYAl(){
        return yılan[yılanuzunluğu-1].yAl();
    }
    
    public int başXHızAl(){
        return yılan[yılanuzunluğu - 1].xHızAl();
    }
    
    public int başYHızAl(){
        return yılan[yılanuzunluğu - 1].yHızAl();
    }
    
    public void başYHızAyarla(int hız){
        yılan[yılanuzunluğu - 1].yHızAyarla(hız);
    }
    
    public void başXHızAyarla(int hız){
        yılan[yılanuzunluğu - 1].xHızAyarla(hız);
    }
}
