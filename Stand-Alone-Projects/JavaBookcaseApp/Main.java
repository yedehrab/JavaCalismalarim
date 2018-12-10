import java.util.Scanner;


class Main {

    // Girdiyi tutan değişken
    Scanner input;
        
    public static void main(String args[]) {
        // Ana arayüzü başlatma
        new Main().arayuz();
    }

    public void arayuz() {
        // Kullanıcının cevabı
        int answer = 1;

        // Kullanıcıdan girdi alma
        input = new Scanner(System.in);

        while(answer != 0) {
            System.out.println("1 - Uyelik");
            System.out.println("2 - Üye Islemleri");
            System.out.println("0 - Kaydet ve Cik");

            // Kullanıcıdan tepki alma
            answer = input.nextInt();

            if (answer == 1) {
                Uye uye = new Uye();
                uye.uyelikArayuzu(input);

            } else if (answer == 2) {

            }
        
        }

        // Girdi alıcıyı kapatma
        input.close();
    }
}