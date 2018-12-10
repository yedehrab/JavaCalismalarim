/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

public class SpiderRobot extends GezginRobot {
    // Bacak sayısı
    private int bacaksayisi;

    @Override
    public boolean engeldenGecebilirMi() {
        return false;
    }

    @Override
    public float engeldenGecmeSuresiBul() {
        return 0f;
    }
    
    @Override
    public void hizAyarla() {
        super.hiz = YAVAS_HIZ;
    }

    public int getBacaksayisi() {
        return bacaksayisi;
    }

    public void setBacaksayisi(int bacaksayisi) {
        this.bacaksayisi = bacaksayisi;
    }

    
}
