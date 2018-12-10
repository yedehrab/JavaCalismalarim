
package yılan.oyunu;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;

public class Dörtgen extends JPanel{
    
    Rectangle dörtgen = new Rectangle();
    
    int xhız = 1;
    int yhız = 0;
    
    public Dörtgen(){ // Parametresiz constructer
        
        super(); // JPanel'in varsayılan işlemleri yapılsın
        
        dörtgen.setBounds(0, 0, 5, 5);
    }
    
    public Dörtgen(int x, int y, int l, int h){
        
        super(); // JPanel'in varsayılan işlemleri yapılsın
        
        dörtgen.setBounds(x, y, l, h);
    }
    
    public Dörtgen(int l, int h){
        
        super(); // JPanel'in varsayılan işlemleri yapılsın
        
        dörtgen.setBounds(0, 0, l, h);
    }
    
    public void ekranaÇiz(Graphics g){ 
               
        g.drawRect((int)dörtgen.getX(), (int)dörtgen.getY(), (int)dörtgen.getWidth(), (int)dörtgen.getHeight()); // Tanımlı bilgilerde ekrana dörtgen çizme
    }
        
    public void hareketEt(Dörtgen çerçeve){
        
        Rectangle geçici = new Rectangle(dörtgen);
        geçici.setLocation((int)geçici.getX() + xhız, (int)geçici.getY() + yhız);
        
        boolean çarpışma = false; // Aynı zaman kaybettim mi kontrolü olabilir.
        
        if(
            (int)geçici.getX() < 0 ||
            (int)(geçici.getX() + geçici.getWidth()) > çerçeve.alUzunluk() ||
            (int)geçici.getY() < 0 ||
            (int)(geçici.getY() + geçici.getHeight()) > çerçeve.alGenişlik()
        ){
            System.out.println(çerçeve.alUzunluk());
            çarpışma = true;
        }
        if(çarpışma == false)
            dörtgen.setLocation((int)dörtgen.getX() + xhız, (int)dörtgen.getY() + yhız);
    }
    // Her karenin hareket etmesi  
    public void hareketEt(int x, int y, Dörtgen çerçeve, int xhız, int yhız){
        
        kuyrukKontrol(x, y, xhız, yhız);
        
       // System.out.println("Dörtgen.getX() = " + (int)dörtgen.getX() + "---" + "Baş X -> " + x + "---" + "Baş Y -> " + y);
        
        if(!çarpKontrol(çerçeve))
            dörtgen.setLocation((int)dörtgen.getX() + this.xhız, (int)dörtgen.getY() + this.yhız);
    }
    
    public boolean yemYeme(int xbaşkonum, int ybaşkonum, Yem yem){
        
        boolean cevap = false;
 
        
        return cevap;
    }
    public void kuyrukKontrol(int x, int y, int xhız, int yhız){
        // Baş'ın konumuna gelene kadar eski hızlarında devam etsinler.
        if((int)dörtgen.getX() == x && (int)dörtgen.getY() == y){
            
            this.xhız = xhız;
            this.yhız = yhız;
        }
    }
    // Köşeyle çarpışma var mı ? Evet Hayır döndürür.
    public boolean çarpKontrol(Dörtgen çerçeve){
        // Duvar'a çarpıyor mu, kontrolü
        Rectangle geçici = new Rectangle(dörtgen);
        geçici.setLocation((int)geçici.getX() + this.xhız, (int)geçici.getY() + this.yhız);
        
        boolean çarpışma = false; // Aynı zaman kaybettim mi kontrolü olabilir.
        
        if(
            (int)geçici.getX() < 0 ||
            (int)(geçici.getX() + geçici.getWidth()) > çerçeve.alUzunluk() ||
            (int)geçici.getY() < 0 ||
            (int)(geçici.getY() + geçici.getHeight()) > çerçeve.alGenişlik()
        )
            çarpışma = true;
        
        return çarpışma;
    }
    
    public Rectangle dörtgenAl(){
        
        return dörtgen;
    }
    
    public int xAl(){
        
        return (int)dörtgen.getX();
    }
    
    public int yAl(){
        
        return (int)dörtgen.getY();
    }
    
    public int alUzunluk(){
        return (int)dörtgen.getWidth();
    }
    
    public int alGenişlik(){
        return (int)dörtgen.getHeight();
    }
    // X konumundaki hız'ı ayarlama
    public int xHızAl(){
        
        return xhız;
    }
    // Y konumundaki hız'ı alma
    public int yHızAl(){
        
        return yhız;
    }
    // X konumundaki hız'ı ayarlama
    public void xHızAyarla(int hız){
        
        this.xhız = hız;
    }
    // Y konumundaki hız'ı ayarlama
    public void yHızAyarla(int hız){
        
        this.yhız = hız;
    }
}
 