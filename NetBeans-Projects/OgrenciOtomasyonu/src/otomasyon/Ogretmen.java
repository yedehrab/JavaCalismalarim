/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otomasyon;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Yunus Emre
 */
public class Ogretmen extends Uye implements AnaIslemler, Serializable{    
    public final ArrayList<Ogrenci> öğrenciler = new ArrayList<>();
    
    public static boolean girişYapıldı = false;
    
    public static final Ogretmen kalıpOluştur(String no, String isim, Ders[] dersler){
        Ogretmen öğretmen = new Ogretmen();
        öğretmen.bilgiler[NO] = no;
        öğretmen.bilgiler[ISIM] = isim;
        
        for (Ders ders : dersler){
            öğretmen.dersler.add(ders);           
        }
        
        return öğretmen;
    }
    
     @Override
    public void menü() {        
         girişYapıldı = true;
         
         öğrencileriGüncelle();
        
        while(true){
            consoluTemizle();

            gMenü(
                "Öğretmen İşlemleri [" + this.bilgiler[ISIM] + "] \n" +
                SATIR_MENU +
                " [1]  Ders Programını Gör\n" +
                " [2]  Sınav Programını Gör\n" +
                " [3]  Not Sorgula\n" +
                " [4]  Not ver\n" +
                " [5]  Profil Güncelleme"
            );
            String answer = input.nextLine();
            switch(answer){
                    case "1":
                        super.dersProgramıGöster();
                        break;
                    case "2":
                        super.sınavProgramınıGöster();
                        break;
                    case "3":
                        notSorgulamaMenüsü();
                        break;
                    case "4":
                        notVermeMenüsü();
                        break;
                    case "5":
                        this.oluştur();
                        break;
                    case "" + GERI:
                        return;
                    case "" + CIKIS:
                       çıkış();
                    default:
                        bekletme("Hatalı tuşlama yaptınız :/");
                        break;
            }
        }
    }    
    
    public void yönetimİşlemleri(){
        girişYapıldı = false;
        super.üyeTipi = UYE_OGRETMEN;         
        super.menü();
    }
    
    private void notVermeMenüsü(){
         while(true){
            consoluTemizle();

            gMenü(
                "Lütfen notunu vermek istediğiniz öğrencinin no'sunu yazınız\n" +
                SATIR_MENU +
                " [0] Öğrencilerimi Göster"
            );
            String answer = input.nextLine();
            
            switch(answer){
                case "0":
                    öğrencileriGöster();
                    break;
                case "" + GERI:
                    return;
                case "" + CIKIS:
                    çıkış();
                default:
                    int[] indexler = öğrenciveDersKontrolü(answer);
                    
                    if (indexler != null)
                        notVermeAltMenüsü(indexler);
            }
        }
    }
    
    private int[] öğrenciveDersKontrolü(String no){
        for (int i = 0; i < öğrenciler.size(); i++){
            if (öğrenciler.get(i).bilgiler[NO].equals(no)){
                for (int j = 0; j < dersler.size(); j++){
                    for (Ders ders : öğrenciler.get(i).dersler){
                        if(ders.ad.equals(dersler.get(j).ad)){
                             return new int[]{i ,j};
                        }
                    }
                }
            } 
        }
        bekletme("Kaydı olmayan veya öğrenciniz olmayan bir öğrenci no'su girdiniz.");
        return null;
    }
    
    private void notVermeAltMenüsü(int[] indexler){
        while(true) {
            consoluTemizle();
            String answer;

            gMenü(
                "Lütfen vermek istediğiniz not bölümünü seçiniz ( " + öğrenciler.get(indexler[0]).bilgiler[ISIM] + " ) \n" +
                SATIR_MENU +
                " [" + (VIZE + 1) + "] Vize \n" +
                " [" + (MAZERET + 1) + "] Mazeret \n" +
                " [" + (FINAL + 1) + "] Final \n" +
                " [" + (BUTUNLEME + 1) + "] Bütünleme \n" +
                " [" + (ODEV + 1) + "] Ödev \n" +
                " [" + (PROJE + 1) + "] Proje" 
            );
            answer = input.nextLine();

                switch(answer){
                    case "" + (VIZE + 1):
                    case "" + (MAZERET + 1):
                    case "" + (FINAL + 1):
                    case "" + (BUTUNLEME + 1):
                    case "" + (ODEV + 1):
                    case "" + (PROJE + 1):
                        notVerme(indexler, Integer.parseInt(answer));
                        break;
                    case "" + GERI:
                        return;
                    case "" + CIKIS:
                        çıkış();
                }
            }
    }
    
    private void notVerme(int[] indexler, int notTipi){
        while(true){
            consoluTemizle();
            
            String notu = "?";
            
            if (öğrenciler.get(indexler[0]).dersler.get(indexler[1]).not[notTipi - 1] != BOS_INT)
                notu = Integer.toString(öğrenciler.get(indexler[0]).dersler.get(indexler[1]).not[notTipi - 1]);
            
            gMenü(
                "Lütfen not giriniz\n" +
                SATIR_MENU +
                "Not: " + notu
            );
            
            int not = input.nextInt();
            input.nextLine();
            switch(not){
                case GERI:
                    return;
                case CIKIS:
                    çıkış();
                default:
                    if (not > -1 && not < 101){
                        öğrenciler.get(indexler[0]).dersler.get(indexler[1]).not[notTipi - 1] = not;
                    } else {
                        bekletme("Geçersiz bir not girdiniz.");
                    }
            }
        }
    }

    
    private void notSorgulamaMenüsü(){
        while(true){
            consoluTemizle();

            gMenü(
                "Lütfen notunu sorgulamak istediğiniz öğrencinin no'sunu yazınız\n" +
                SATIR_MENU +
                " [0] Öğrencilerimi Göster"
            );
            String answer = input.nextLine();
            
            switch(answer){
                case "0":
                    öğrencileriGöster();
                    break;
                case "" + GERI:
                    return;
                case "" + CIKIS:
                    çıkış();
                default:
                    boolean hata = true;
                    for (Ogrenci öğrenci : öğrenciler){
                        if (öğrenci.bilgiler[NO].equals(answer)){
                            öğrenci.notMenüsü();
                            hata = false;
                            break;
                        }
                    }
                    if(hata)
                        bekletme("Kayıtlı olmayan veya dersiniz olmadığı bir öğrencinin numarasını girdiniz.");                       
            }
        }
    }
    
    private void öğrencileriGöster(){
        consoluTemizle();
        
        System.out.println("Öğrencileriniz:");
        satırÇiz();
        for (Ogrenci öğrenci : öğrenciler){
            System.out.println(" [" + öğrenci.bilgiler[NO] + "] " + öğrenci.bilgiler[ISIM]);
        }
        satırÇiz();
        bekletme("");
    }
    
    @Override
    public void oluştur() {        
        üyeTipi = UYE_OGRETMEN;
        super.oluştur();
        
        if (uygunMu[4])
            dersSeçmeMenüsü();
        
        if (girişYapıldı){
            bekletme("Bilgiler güncellendi.");
        }
        else if (!güncelleme) {
            for (boolean uygun : uygunMu){
                if(!uygun){
                    bekletme("Öğretmen oluşturulamadı, bilgiler uygun değil veya iptal edildi.");
                    return;
                }
            }

            kayıtlarÖğretmen.add(this);
            bekletme("Öğretmen oluşturuldu.");
        }
        
        
    }

    @Override
    public void sil() {
        while(true){
            consoluTemizle();
            
            String answer;
            
            giMenü(
                "Lütfen silmek istediğiniz öğretmenin no'sunu giriniz.\n" +
                SATIR_MENU +
                " [0] Öğretmenleri Göster"
            );
            
            answer = input.nextLine();
            
            switch(answer){
                case "" + ILERI:
                    tümÖğretmenleriGöster();
                    break;
                case "" + GERI:
                    return;
                case "" + CIKIS:
                    çıkış();
                default:
                    kontrollüSil(answer);
            }
        }
    }

    @Override
    public void güncelle() {
        while(true){
            consoluTemizle();
            
            String answer;
            
            giMenü(
                "Lütfen güncellemek istediğiniz öğretmen no'sunu giriniz.\n" +
                SATIR_MENU +
                " [0] Öğretmenleri Göster"
            );
            
            answer = input.nextLine();
            
            switch(answer){
                case "0":
                    tümÖğretmenleriGöster();
                    break;
                case "" + GERI:
                    return;
                case "" + CIKIS:
                    çıkış();
                default:
                    kontrollüGüncelle(answer);
            }
        }
    }
    
    private void kontrollüGüncelle(String no){
        for (Ogretmen öğretmen : kayıtlarÖğretmen){
            if(öğretmen.bilgiler[NO].equals(no)){
                güncelleme = true;
                öğretmen.oluştur();
                bekletme("Öğretmen güncellendi.");
                return;
            }
        }
        bekletme("Öğretmen bulunamadı.");
    }
    
    private void kontrollüSil(String no){
        for (int i = 0; i < kayıtlarÖğretmen.size(); i++){
            if (kayıtlarÖğretmen.get(i).bilgiler[NO].equals(no)){
                kayıtlarÖğretmen.remove(i);
                
                bekletme("Öğretmen silindi.");
                return;
            }
        }
        bekletme("Öğretmen bulunamadı.");
    }
    
    private void tümÖğretmenleriGöster(){
        consoluTemizle();
        satırÇiz();
        for (Ogretmen öğretmen : kayıtlarÖğretmen){
                System.out.println(" [" + öğretmen.bilgiler[NO] + "] " + öğretmen.bilgiler[ISIM]);
        }
        satırÇiz();
        bekletme("");
    }

    private void öğrencileriGüncelle() {
        öğrenciler.clear();
        
        for (Ders ders : dersler){
            for (Ogrenci öğrenci : kayıtlarÖğrenci){
                for (Ders d : öğrenci.dersler){
                    if (ders.ad.equals(d.ad) && !öğrenciler.contains(öğrenci)){
                        öğrenciler.add(öğrenci);
                        break;
                    }
                }
            }
        }
    }
    
    private void dersSeçmeMenüsü(){
        while(true){
            consoluTemizle();
            
            System.out.print(
                SATIR_MENU +
                "Lütfen, seçmek / silmek istediğiniz dersin no'sunu giriniz.\n" +
                SATIR_MENU
            );
            
            derslerimiGöster();
            
            System.out.print(
                " [0] Dersleri göster" + "\n" +
                SATIR_MENU +
                "[-1] Kaydet \n" +
                CIKIS_MENU +
                SATIR_MENU +
                INPUT_MENU
            );
            
            String answer;
            
            do {
                answer = input.nextLine();
            } while (answer.equals(""));
            
            switch(answer){
                case "0":
                    dersleriGöster();
                    break;
                case "" + GERI:
                    verileriYenile();
                    return;
                case "" + CIKIS:
                    çıkış();
                default:
                    if (Integer.parseInt(answer) <= kayıtlarDers.size()){
                        boolean mevcut = false;
                        for (int i = 0; i < dersler.size(); i++){
                            if (dersler.get(i).ad.equals(kayıtlarDers.get(Integer.parseInt(answer) - 1).ad)){
                                dersler.remove(i);
                                mevcut = true;
                            }
                        }
                        if(!mevcut){
                            dersler.add(kayıtlarDers.get(Integer.parseInt(answer) - 1));
                        }
                    }                    
            }
        }
    }
    
    private void dersleriGöster(){
        consoluTemizle();
        
        derslerimiGöster();
        
        System.out.println("Dersler: ");
        satırÇiz();       
        for (int i = 0; i < kayıtlarDers.size(); i++){
            System.out.println(" [" + (i + 1) + "] " + kayıtlarDers.get(i).ad);
        }
        satırÇiz();
        
        bekletme("");
    }
}
