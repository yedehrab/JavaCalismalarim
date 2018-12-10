/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otomasyon;

/**
 * Gereksiz
 * @author Yunus Emre
 */
public final class Not extends DataBase{
    public int[] notlar = {BOS_INT, BOS_INT, BOS_INT, BOS_INT, BOS_INT, BOS_INT};

    public Not(int vize, int mazeret, int Final, int bütünleme, int ödev, int proje) {
        notlar = new int[]{vize, mazeret, Final, bütünleme, ödev, proje};
    }
    
    public Not(){
        
    }
    
    public int al(int notTürü){
        if(notTürü < 6)
            return notlar[notTürü];
        else
            // Hata kodu
            return HATA_INT;
    }
    
    public void ayarla(int not, int notTürü){
        if(notTürü < 6 && not < 101 && not > -1)
            notlar[notTürü] = not;
    }
    
    public int[] hepsiniAl(){
        return notlar;
    }
    
    
    
    
}
