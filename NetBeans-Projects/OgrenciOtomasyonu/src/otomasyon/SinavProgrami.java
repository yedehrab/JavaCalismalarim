/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otomasyon;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Bitti
 * 03.12.2017
 * @author Yunus Emre
 */
public final class SinavProgrami extends BolumDersleri implements AnaIslemler, DersVeSinavSabitleri, Serializable{
    public int sınavTipi;
    public Tarih enErken = new Tarih();
    
    public static final int MAX_SINAV_GUNU = 14;
    
    @Override
    public void hepsiniGöster(){
        consoluTemizle();
        
        enErkenTarihiAyarla();
        
        for (int g = 1; g <= MAX_SINAV_GUNU; g++){
            System.out.println("Tarih: " + (enErken.ertesi(g - 1).gün) + "." + (enErken.ertesi(g - 1).ay) + "." + (enErken.ertesi(g - 1).yıl));
            satırÇiz();
            güneGöreGöster(g);
            
        }
        
        bekletme("");
    }
    
    /**
     * @param sınavGünü 1 ile 14 arası
     */
    @Override
    public void güneGöreGöster(int sınavGünü){
        if(sınavGünü <= MAX_SINAV_GUNU && sınavGünü > 0){
            güneGöreAyarla(sınavGünü);
            
            for (String h : SAAT_BASLANGIC){
                for (Ders ders : günDersleri){
                    if (ders.sınav[sınavTipi].başlangıç.equals(h)){
                        System.out.println(ders.ad);
                        System.out.println(ders.sınav[sınavTipi].başlangıç + " - " + ders.sınav[sınavTipi].bitiş);
                        System.out.println("--------------------------");
                    }
                }
            }
        }
    }
    
    
    @Override
    public void güneGöreAyarla(int sınavGünü){
        günDersleri.clear();
        
        for (Ders ders : dersler){
            if(
                ders.sınav[sınavTipi].yıl == enErken.ertesi(sınavGünü - 1).yıl &&
                ders.sınav[sınavTipi].ay == enErken.ertesi(sınavGünü - 1).ay &&
                ders.sınav[sınavTipi].gün == enErken.ertesi(sınavGünü - 1).gün 
            ){
               günDersleri.add(ders);
            }
        }
    }
    
    /**
     * En erken sınav tarihini bulur.
     */
    public void enErkenTarihiAyarla(){
        enErken.yıl = Tarih.MAX_YIL;
        enErken.ay = Tarih.MAX_AY;
        enErken.gün = Tarih.MAX_GUN;
        
        for (Ders ders : dersler){
            if (ders.sınav[sınavTipi].yıl != BOS_INT && ders.sınav[sınavTipi].yıl < enErken.yıl)
                enErken.yıl = ders.sınav[sınavTipi].yıl;
        }
        for (Ders ders : dersler){
            if(ders.sınav[sınavTipi].yıl == enErken.yıl){
                if (ders.sınav[sınavTipi].ay != BOS_INT && ders.sınav[sınavTipi].ay < enErken.ay)
                    enErken.ay = ders.sınav[sınavTipi].ay;
            }
        }
        for (Ders ders : dersler){
            if(ders.sınav[sınavTipi].yıl == enErken.yıl){
                if (ders.sınav[sınavTipi].ay == enErken.ay)
                    if(ders.sınav[sınavTipi].gün != BOS_INT && ders.sınav[sınavTipi].gün < enErken.gün)
                        enErken.gün = ders.sınav[sınavTipi].gün;
            }
        }
    }

     @Override
    public void menü(){
        String answer;
        while(true) {
            consoluTemizle();
            
            System.out.println(BOLUM[bölümTipi] + " için lütfen sınav türünü seçiniz.");
            gMenü(
                " [" + (VIZE + 1) + "] Vize \n" +
                " [" + (MAZERET + 1) + "] Mazeret \n" +
                " [" + (FINAL + 1) + "] Final \n" +
                " [" + (BUTUNLEME + 1) + "] Bütünleme \n"
            );
            
            do {
                answer = input.nextLine();
            } while (answer.equals(""));
            
            switch(answer){
                case "" + (VIZE + 1):
                case "" + (MAZERET + 1):
                case "" + (FINAL + 1):
                case "" + (BUTUNLEME + 1):
                    this.sınavTipi = Integer.parseInt(answer) - 1;
                    altMenü();
                    break;
                case "" + GERI:
                    return;
                case "" + CIKIS:
                    çıkış();
                default:
                    bekletme("Hatalı tuşlama yaptınız.");
            }
            
        }
    }
    
    public void altMenü(){
         while (true) {
            consoluTemizle();
            
            System.out.println(SINAV[sınavTipi] + " sınavı için ders seçiniz.");
            gMenü(dersler, " [0] Sınav Programını Göster");
            
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
                if (i == Integer.parseInt(answer) - 1){
                    tarihMenüsü(i);
                    break;
                }
            }      
        }
    }
    
    public final void tarihMenüsü(int dersİndexi){
        Ders ders = dersler.get(dersİndexi);
        
        // Tarih
        int[] answer = new int[3];
        do {
            consoluTemizle();
            
            System.out.println(ders.ad);
            satırÇiz();
            
            if (ders.sınav[sınavTipi].gün == BOS_INT || ders.sınav[sınavTipi].ay == BOS_INT || ders.sınav[sınavTipi].yıl == BOS_INT)
                System.out.println("Tarih bilgisi mevcut değil.");
            else
                System.out.println("Sınavın Tarihi: " + ders.sınav[sınavTipi].gün + "." + ders.sınav[sınavTipi].ay + "." + ders.sınav[sınavTipi].yıl);
            
            giMenü(
                "Lütfen tarih bilgisini aralarında boşluk olacak şekilde (GG AA YY) yazınız. (13 11 2011)\n"
            );
            answer[0] = input.nextInt();
            
            switch(answer[0]){
                case GERI:
                    return;
                case CIKIS:
                    çıkış();
            }
            
            if (answer[0] != ILERI){
                answer[1] = input.nextInt();
                answer[2] = input.nextInt();
                input.nextLine();

                ders.sınav[sınavTipi].gün = answer[0];
                ders.sınav[sınavTipi].ay = answer[1];
                ders.sınav[sınavTipi].yıl = answer[2];

                System.out.println(Arrays.toString(answer));
            }
            
        } while(answer[0] != ILERI);
        
        // Başlangı Saati
        do {
            consoluTemizle();
            
            System.out.println(ders.ad);
            satırÇiz();
            
            if (!ders.sınav[sınavTipi].başlangıç.equals(BOS_STR))
                System.out.println("Başlangıç saati: " + ders.sınav[sınavTipi].başlangıç);
            else
                System.out.println("Başlangıç saati mevcut değil.");
            
            giMenü(SAAT_BASLANGIC);
            answer[0] = input.nextInt();
            input.nextLine();
            
            switch(answer[0]){
                case GERI:
                    return;
                case CIKIS:
                    çıkış();
            }
            
            if(answer[0] != ILERI){
                for (int i = 0; i < SAAT_BASLANGIC.length; i++){
                    if (i == answer[0] -1){
                        ders.sınav[sınavTipi].başlangıç = SAAT_BASLANGIC[i];
                        dersler.set(dersİndexi, ders);
                        break;
                    }
                }
            }
            
        } while (answer[0] != ILERI);
        
        // Bitiş Saati
        do {
            consoluTemizle();
            
            System.out.println(ders.ad);
            satırÇiz();
            if (!ders.sınav[sınavTipi].bitiş.equals(BOS_STR))
                System.out.println("Bitiş saati: " + ders.sınav[sınavTipi].bitiş);
             else
                System.out.println("Bitiş saati mevcut değil.");
            
            giMenü(SAAT_BITIS);
            answer[0] = input.nextInt();
            input.nextLine();
            
            switch(answer[0]){
                case ILERI:
                case GERI:
                    return;
                case CIKIS:
                    çıkış();
            }
            
            if (answer[0] != ILERI){
                for (int i = 0; i < SAAT_BITIS.length; i++){
                    if (i == answer[0] -1){
                        ders.sınav[sınavTipi].bitiş = SAAT_BITIS[i];
                        dersler.set(dersİndexi, ders);
                        break;
                    }
                }
            }
        } while (answer[0] != ILERI);
        
        aktar(ders);
    }

    @Override
    public void iMenüİşlemi(String girdi, int tip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static final SinavProgrami kalıpOluştur(int bölümTipi){
        SinavProgrami sp = new SinavProgrami();
        sp.bölümTipi = bölümTipi;
        sp.dersleriAl();
        
        return sp;
    }

    @Override
    public void oluştur() {
        // Devam et
        super.menü();
    }
    
    @Override
    public void sil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void güncelle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void aktar(Ders ders) {
            for (Ders d : kayıtlarDers){
                if (ders.ad.equals(d.ad)){
                    d.sınav = ders.sınav;
                }
            }
        
    }    
    
}
    
    

