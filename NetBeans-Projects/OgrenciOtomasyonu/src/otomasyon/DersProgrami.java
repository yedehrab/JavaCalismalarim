/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otomasyon;

import java.io.Serializable;

/**
 * 03.12.2017
 * @author Yunus Emre
 */
public final class DersProgrami extends BolumDersleri implements AnaIslemler, Serializable{
    
    @Override
    public void hepsiniGöster(){
        consoluTemizle();
        
        for (int i = 0; i < GUN.length; i++){
            güneGöreGöster(i);
        }
        
        bekletme("");
    }
    
    @Override
    public void güneGöreGöster(int günNo){        
        güneGöreAyarla(günNo);
        System.out.println(GUN[günNo]);
        satırÇiz();
        for (String saat : SAAT_BASLANGIC) {
            for (Ders ders : günDersleri) {
                if (ders.başlangıç.equals(saat)) {
                    System.out.println(ders.ad);
                    System.out.println(ders.başlangıç + " - " + ders.bitiş + "\n");
                }
            }
        } 
    }
    
    @Override
    public void güneGöreAyarla(int günNo){
        günDersleri.clear();
        
        for(Ders ders : dersler){
            if(ders.gün.equals(GUN[günNo])){
                günDersleri.add(ders);
            }
        }
    }
    
    public static final DersProgrami kalıpOluştur(int bölümTipi){
        DersProgrami dp = new DersProgrami();
        dp.bölümTipi = bölümTipi;
        dp.dersleriAl();
        
        return dp;
    }

    @Override
    public void oluştur() {
            // Devam Et.
            super.menü();
    }

    @Override
    public void sil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void güncelle() {
        
    }

    @Override
    public void menü() {
        while (true) {
            consoluTemizle();
           
            System.out.println(BOLUM[bölümTipi] + " için lütfen ders seçiniz.");
            gMenü(dersler, " [0] Ders Programını göster");
            
            String answer;
            do {
                answer = input.nextLine();
            } while (answer.equals(""));
            
            switch(answer){
                case "0":
                    hepsiniGöster();
                    break;
                case "" + GERI:
                    return;
                case "" + CIKIS:
                    çıkış();
            }
            
            int i;
            for (i = 0; i < dersler.size(); i++){
                if (i == (Integer.parseInt(answer) - 1)){
                    saatMenüsü(i);
                    break;
                }
            }
        }
    }

    public final void saatMenüsü(int dersİndexi){
        Ders ders = dersler.get(dersİndexi);
        
        // Tarih
        String answer;
        do {
            consoluTemizle();
            
            System.out.println(ders.ad);
            satırÇiz();
            
            if (!ders.gün.equals(BOS_STR))
                System.out.println("Dersin Günü: " + ders.gün);
            else
                System.out.println("Gün bilgisi mevcut değil.");
            
            giMenü(GUN);
            
            do {
                answer = input.nextLine();
            } while (answer.equals(""));
            
            switch(answer){
                case "" + GERI:
                    return;
                case "" + CIKIS:
                    çıkış();
            }
            
            if(!answer.equals("" + ILERI)){
                for (int i = 0; i < GUN.length; i++){
                    if (i == Integer.parseInt(answer) - 1){
                        ders.gün = GUN[i];
                        dersler.set(dersİndexi, ders);
                        break;
                    }
                }
            }
            
        } while(!answer.equals("" + ILERI));
        
        // Başlangı Saati
        do {
            consoluTemizle();
            
            System.out.println(ders.ad);
            satırÇiz();
            
            if (!ders.başlangıç.equals(BOS_STR))
                System.out.println("Başlangıç saati: " + ders.başlangıç);
            else
                System.out.println("Başlangıç saati mevcut değil.");
            
            giMenü(SAAT_BASLANGIC);
            
            do {
                answer = input.nextLine();
            } while (answer.equals(""));
            
            switch(answer){
                case "" + GERI:
                    return;
                case "" + CIKIS:
                    çıkış();
            }
            
            if(!answer.equals("" + ILERI)){
                for (int i = 0; i < SAAT_BASLANGIC.length; i++){
                    if (i == Integer.parseInt(answer) - 1){
                        ders.başlangıç = SAAT_BASLANGIC[i];
                        dersler.set(dersİndexi, ders);
                        break;
                    }
                }
            }
            
        } while (!answer.equals("" + ILERI));
        
        // Bitiş Saati
        do {
            consoluTemizle();
            
            System.out.println(ders.ad);
            satırÇiz();
            if (!ders.bitiş.equals(BOS_STR))
                System.out.println("Bitiş saati: " + ders.bitiş);
             else
                System.out.println("Bitiş saati mevcut değil.");
            
            giMenü(SAAT_BITIS);
            
            do {
                answer = input.nextLine();
            } while (answer.equals(""));
            
            switch(answer){
                case "" + ILERI:
                case "" + GERI:
                    return;
                case "" + CIKIS:
                    çıkış();
            }
            
            if (!answer.equals("" + ILERI)){
                for (int i = 0; i < SAAT_BITIS.length; i++){
                    if (i == Integer.parseInt(answer) - 1){
                        ders.bitiş = SAAT_BITIS[i];
                        dersler.set(dersİndexi, ders);
                        break;
                    }
                }
            }
        } while (!answer.equals("" + ILERI));
        
        aktar(ders);
    }

 
    private void aktar(Ders ders) {
            for (Ders d : kayıtlarDers){
                if (ders.ad.equals(d.ad)){
                    d.gün = ders.gün;
                    d.başlangıç = ders.başlangıç;
                    d.bitiş = ders.bitiş;
                }
            }
        
    }    
}
