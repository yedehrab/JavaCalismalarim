/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeodevi;

import static projeodevi.ScannerMethods.*;

public class ProjeOdevi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DB.connectDB();
        
        int answer;
        while (true) {
           System.out.println("1) Login");
           System.out.println("2) SignUp");
           System.out.println("3) Çıkış");

           try {
               answer = getInt();
               
               switch (answer) {
                    case 1:
                        logIn();
                        break;
                    case 2:
                        signUp();
                        break;
                    case 3:
                        // Sistemi kapatma
                        System.exit(0);
                }
               
           } catch (NumberFormatException e) {
               System.out.println("Girdi hatalı!");
           }
        }
    }
    
    private static void logIn() {
        String username;
        String password;

        while (true) {
            System.out.println("Username: ");
            System.out.println("-1) Back");
            username = getString();

            if (username.equals("-1"))
                return;

            if (!UserOperations.isUserExist(username)) {
                System.out.println("Username undefined!");
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("Password:");
            System.out.println("-1) Back");
            password = getString();

            if (password.equals("-1"))
                return;

            if (!UserOperations.canLogin(username, password)) {
                System.out.println("Password undefined!");
            } else {
                break;
            }
        }
        
        // Üyeyinin id'sini saklama
        UserData.username = username;
        System.out.println("Welcome " + username);
        userMenu();
    }
    
    private static void signUp() {
        String username;
        String password;
        
        while (true) {
            System.out.println("Username: ");
            System.out.println("-1) Back");
            username = getString();

            if (username.equals("-1"))
                return;

            if (UserOperations.isUserExist(username)) {
                System.out.println("Username exists!");
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("Password:");
            System.out.println("-1) Back");
            password = getString();

            if (password.equals("-1"))
                return;

            System.out.println("Retype password:");
            if (!password.equals(getString())) {
                System.out.println("Password doesnt match!");
            } else {
                break;
            }
        }
        
        // Üyeyi oluşturma ve id'sini saklama
        UserOperations.createUser(username, password);
        System.out.println("User created, welcome " + username);
        UserData.username = username;
        userMenu();
    }
    
    private static void userMenu() {
        int answer;
        
        while (true) {
            System.out.println("1) Select Book");
            System.out.println("2) Create orders");
            System.out.println("3) Buy orders");
            System.out.println("0) Logout");

            // Numara alma
            answer = getInt();

            switch (answer) {
                case 0:
                    // Üyeyi kaldırma
                    UserData.clear();
                    return;
                case 1:
                    BookOperations.listBooks();
                    selectBook();
                    break;
                case 2:
                    OrderOperations.createOrders();
                    break;
                case 3:
                    buyMenu();
                    break;

            }
        }
    }
    
    public static void selectBook() {
        while (true) {
            System.out.println("ID of Book:");
            System.out.println("0) Exit");
            int id = getInt();
            if (id == 0)
                return;

            if (BookOperations.isBookExist(id)) {
                System.out.println("ID unknown!");
            } else {
                UserData.selectedBooks.add(id);
                System.out.println("Book added!");
            }
        }
    }
    
    public static void buyMenu() {
        int answer;

        while (true) {
            System.out.println("1 Show history");
            System.out.println("2 Confirm buying");
            System.out.println("0 Back");

            answer = getInt();

            switch (answer) {
                case 0:
                    return;
                case 1:
                    PayOperations.listPayments(UserData.username);
                    break;
                case 2:
                    PayOperations.createPayment(UserData.username);
                    System.out.println("Succesfully!");
                    break;
            }
        }
    }
   
}
