/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeodevi;

import java.util.Scanner;

public abstract class ScannerMethods {
    
    public static int getInt() {
        Scanner scan = new Scanner(System.in);
        try {
            return Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Girdi Hatalı!");
            return getInt();
        }
    }
    
    public static String getString() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
}
