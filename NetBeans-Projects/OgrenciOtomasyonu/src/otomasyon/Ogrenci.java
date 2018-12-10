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
public class Ogrenci extends Uye implements Serializable{
    int bölüm;
    
    public static boolean girişYapıldı = false;

    public static final Ogrenci kalıpOluştur(String no, String isim, int bölüm, Ders[] dersler){
        Ogrenci öğrenci = new Ogrenci();
        öğrenci.bölüm = bölüm;
        öğrenci.bilgiler[NO] = no;
        öğrenci.bilgiler[ISIM] = isim;
        
        for (Ders ders : dersler){
            öğrenci.dersler.add(ders);
        }
        
        return öğrenci;
    }

    @Override
    public void menü() {        
        girişYapıldı = true;
        
        while(true){
            consoluTemizle();

            gMenü(
                "Öğrenci İşlemleri [" + this.bilgiler[ISIM] + "] \n" +
                SATIR_MENU +
                " [1]  Ders Programını Gör\n" +
                " [2]  Sınav Programını Gör\n" +
                " [3]  Notlarım\n" +
                " [4]  Ders seçme\n" +
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
                        notMenüsü();
                        break;
                    case "4":
                        dersSeçmeMenüsü();
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
                GERI_MENU +
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
                        if (!mevcut && kayıtlarDers.get(Integer.parseInt(answer) - 1).bölüm == bölüm){
                            dersler.add(kayıtlarDers.get(Integer.parseInt(answer) - 1));
                        }
                    }                    
            }
        }   
    }
    
    private void dersleriGöster(){
        consoluTemizle();
        
        derslerimiGöster();
        
        System.out.println("Bölüm dersleri: ");
        satırÇiz();       
        for (int i = 0; i < kayıtlarDers.size(); i++){
            if (kayıtlarDers.get(i).bölüm == this.bölüm){
                System.out.println(" [" + (i + 1) + "] " + kayıtlarDers.get(i).ad);
            }
        }
        satırÇiz();
        
        bekletme("");
    }

    public void notMenüsü(){
         while(true) {
            consoluTemizle();
            String answer;
            
            gMenü(
                "Lütfen görmek istediğiniz not bölümünü seçiniz\n" +
                SATIR_MENU +
                " [" + (VIZE + 1) + "] Vize \n" +
                " [" + (MAZERET + 1) + "] Mazeret \n" +
                " [" + (FINAL + 1) + "] Final \n" +
                " [" + (BUTUNLEME + 1) + "] Bütünleme \n" +
                " [" + (ODEV + 1) + "] Ödev \n" +
                " [" + (PROJE + 1) + "] Proje" 
            );
            
            do {
                answer = input.nextLine();
            } while (answer.equals(""));
            
            switch(answer){
                case "" + (VIZE + 1):
                case "" + (MAZERET + 1):
                case "" + (FINAL + 1):
                case "" + (BUTUNLEME + 1):
                case "" + (ODEV + 1):
                case "" + (PROJE + 1):
                    notGöster(Integer.parseInt(answer) - 1);
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
    
    private void notGöster(int notTipi){
        System.out.println(NOT_ISMI[notTipi] + " notları:");
        satırÇiz();
        for (Ders ders : dersler){
            if (ders.not[notTipi] != BOS_INT)
                System.out.println(ders.ad + " -> " + ders.not[notTipi]);
        }
        satırÇiz();
        
        bekletme("");
    }
    
    public void yönetimİşlemleri(){
        girişYapıldı = false;
        super.üyeTipi = UYE_OGRENCI;
        super.menü();
    }
    
    @Override
    public void oluştur() {
        if(!(Ogrenci.girişYapıldı || Ogretmen.girişYapıldı)){
            String answer;
                do {
                    consoluTemizle();

                    giMenü(
                        UYE[üyeTipi] + " işlemleri (" + this.bilgiler[ISIM] + ")\n" +
                        SATIR_MENU +
                        "Bolum: " + BolumDersleri.BOLUM[bölüm] + "\n" +
                        SATIR_MENU +
                        "Lütfen " + UYE[üyeTipi] + " bölümünü seçiniz \n" +
                        SATIR_MENU +
                        "[1] Bilgisayar Mühendisliği \n" +
                        "[2] Endüstri Mühendisliği"
                    );

                    do {
                        answer = input.nextLine();
                    } while (answer.equals(""));

                    switch(answer){
                        case "" + ILERI:
                            break;
                        case "" + GERI:
                            return;
                        case "" + CIKIS:
                            çıkış();
                        case "1":
                        case "2":
                            this.bölüm = Integer.parseInt(answer);
                            break;
                        default:
                            bekletme("Hatalı tuşlama yaptınız.");
                    }
                } while(!answer.equals("" + ILERI));
        }
        
        üyeTipi = UYE_OGRENCI;
        super.oluştur();
        
        if(girişYapıldı){
            bekletme("Bilgiler güncellendi.");
        }
        else if (!güncelleme) {       
                for (boolean uygun : uygunMu){
                    if(!uygun){
                        bekletme("Öğrenci oluşturulamadı, bilgiler uygun değil veya iptal edildi.");
                        return;
                    }
                }
            
            kayıtlarÖğrenci.add(this);
            bekletme("Öğrenci oluşturuldu.");
        }
    }
    
    @Override
    public void sil() {
        while(true){
            consoluTemizle();
            
            String answer;
            
            giMenü(
                "Lütfen silmek istediğiniz öğrencinin no'sunu giriniz.\n" +
                SATIR_MENU +
                " [0] Öğrencileri Göster"
            );
            
            answer = input.nextLine();
            
            switch(answer){
                case "" + ILERI:
                    tümÖğrencileriGöster();
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
                "Lütfen güncellemek istediğiniz öğrencinin no'sunu giriniz.\n" +
                SATIR_MENU +
                " [0] Öğrencileri Göster"
            );
            
            answer = input.nextLine();
            
            switch(answer){
                case "" + ILERI:
                    tümÖğrencileriGöster();
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
        for (Ogrenci öğrenci : kayıtlarÖğrenci){
            if(öğrenci.bilgiler[NO].equals(no)){
                güncelleme = true;
                öğrenci.oluştur();
                
                bekletme("Öğrenci güncellendi.");
                return;
            }
        }
        bekletme("Öğrenci bulunamadı.");
    }
    private void kontrollüSil(String no){
        for (int i = 0; i < kayıtlarÖğrenci.size(); i++){
            if (kayıtlarÖğrenci.get(i).bilgiler[NO].equals(no)){
                kayıtlarÖğrenci.remove(i);
                
                bekletme("Öğrenci silindi.");
                return;
            }
        }
        bekletme("Öğrenci bulunamadı.");
    }
    
    private void tümÖğrencileriGöster(){
        consoluTemizle();
        satırÇiz();
        for (Ogrenci öğrenci : kayıtlarÖğrenci){
                System.out.println(" [" + öğrenci.bilgiler[NO] + "] " + öğrenci.bilgiler[ISIM]);
        }
        satırÇiz();
        bekletme("");
    }

}
