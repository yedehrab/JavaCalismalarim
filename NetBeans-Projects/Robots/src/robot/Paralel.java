/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

public class Paralel extends ManipulatorRobot {
    
    @Override 
    public void tasimaHiziAyarla() {
        super.tasimaHizi = TASIMA_HIZI_HIZLI;
    }
    
    @Override
    public void tasimaKapasitesiAyarla() {
        super.yukTasimaKapasitesi = PARALEL_KAPASITE;
    }

    @Override
    public void kolUzunluguAyarla() {
        super.kolUzunlugu = PARALEL_KOL;
    }
}
