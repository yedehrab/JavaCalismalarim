/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

public class PaletliRobot extends GezginRobot {
    // Palet sayısı
    private int paletSayisi;

    @Override
        public boolean engeldenGecebilirMi() {
        return true;
    }

    @Override
    public float engeldenGecmeSuresiBul() {
        return (float) YAVAS_GECIS;
    }
    
    public void setPaletSayisi(int paletSayisi) {
        this.paletSayisi = paletSayisi;
    }

    @Override
    public void hizAyarla() {
        super.hiz = NORMAL_HIZ;
    }
}
