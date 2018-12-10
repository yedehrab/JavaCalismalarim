/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

public class Seri extends ManipulatorRobot {

    @Override
    public void tasimaHiziAyarla() {
        super.tasimaHizi = TASIMA_HIZI_YAVAS;
    }
    
    @Override
    public void tasimaKapasitesiAyarla() {
        super.yukTasimaKapasitesi = SERI_KAPASITE;
    }

    @Override
    public void kolUzunluguAyarla() {
        super.kolUzunlugu = SERI_KOL;
    }
}
