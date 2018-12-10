/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

public class TekerlekliRobot extends GezginRobot {
    // Tekerlek sayısı
    private int tekerlekSayisi;
  
    
    @Override
    public void hizAyarla() {
        super.hiz = HIZLI_HIZ;
    }

    @Override
    public boolean engeldenGecebilirMi() {
        return true;
    }

    @Override
    public float engeldenGecmeSuresiBul() {
        return (float) HIZLI_GECIS;
    }

    public int getTekerlekSayisi() {
        return tekerlekSayisi;
    }

    public void setTekerlekSayisi(int tekerlekSayisi) {
        this.tekerlekSayisi = tekerlekSayisi;
    }
}
