/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otomasyon;

/**
 *
 * @author Yunus Emre
 */
public interface AnaIslemler {
    public void oluştur();
    public void sil();
    public void güncelle();
    
    public static final int OLUSTUR = 0;
    public static final int SIL = 1;
    public static final int GUNCELLE = 2;
}
