import java.util.Scanner;

class Uye {

    String name;
    String phone;
    String password;

    public void uyelikArayuzu(Scanner input) {
        // Kullanıcının cevabı
        int answer = 1;

        // Kullanıcıdan girdi alma
        // Scanner input = new Scanner(System.in);

        while(answer != 0) {
            System.out.println("1 - Kayıt Ol");
            System.out.println("2 - Giriş");
            System.out.println("0 - Geri");

            // Kullanıcıdan tepki alma
            answer = input.nextInt();

            if (answer == 1) {
                kayitOl();

            } else if (answer == 2) {
                giris();
            }
        
        }

        // Girdi alıcıyı kapatma
        // input.close();
    }

    public void kayitOl() {
        // Verileri almak için değişken
        Scanner input = new Scanner(System.in);

        // Gerekli verileri alma

        System.out.println("Adınız: ");
        this.name = input.nextLine();

        System.out.println("Tel: ");
        this.phone = input.nextLine();

        System.out.println("Sifre: ");
        this.password = input.nextLine();
    }

    public void giris() {
        // Verileri almak için değişken
        Scanner input = new Scanner(System.in);

        // Gerekli verileri alma

        System.out.println("Tel: ");
        this.phone = input.nextLine();

        System.out.println("Sifre: ");
        this.password = input.nextLine();

        // Eğer giriş başarılıysa
        if(girisKontrolu()) {
            // Liste arayüzü
            listeArayuzu();
        }
    }

    public boolean girisKontrolu() {
        // Dosyalara (database'e) bak. Varsa true
        return true;
    }

    public void listeArayuzu() {
        // Kullanıcının cevabı
        int answer = 1;

        // Kullanıcıdan girdi alma
        Scanner input = new Scanner(System.in);

        while(answer != 0) {
            System.out.println("1 - Kitap Goster");
            System.out.println("2 - Kitap Sec");
            System.out.println("3 - Secilenleri Al");
            System.out.println("4 - Secilenleri Temizle");

            // Kullanıcıdan tepki alma
            answer = input.nextInt();

            if (answer == 1) {
                kayitOl();

            } else if (answer == 2) {
                giris();
            }
        
        }

        // Girdi alıcıyı kapatma
        input.close();
    }
}