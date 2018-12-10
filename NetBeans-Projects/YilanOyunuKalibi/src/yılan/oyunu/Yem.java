
package yılan.oyunu;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;


public class Yem{
    
    Dörtgen yem;
    
    int yılangenişliği = 5;
    
    Random rastgele = new Random();
    
    public Yem(int ençokx, int ençoky){
        
        yem = new Dörtgen(
            rastgele.nextInt(ençokx - yılangenişliği) + yılangenişliği, 
            rastgele.nextInt(ençoky - yılangenişliği) + yılangenişliği, 
            yılangenişliği, yılangenişliği
        );
               
    }
    
    public void ekranaÇiz(Graphics g){
               
        yem.ekranaÇiz(g);
    }
    
    
}
