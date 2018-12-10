/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbtreeaugmentedtree;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author yemre
 */
public class HomeworkTree {
    
    /**
     * Main method
     * @param args 
     */
    public static void main(String[] args) {
        consoleUI();
    }    
    
    public static void consoleUI() {
        Scanner scan = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.print("\n"
                    + "1.- RB-Tree\n"
                    + "2.- Augmented RB-Tree\n"
                    + "0.- Exit\n");
            
            // Rewant input if the given input mismatch
            try {
                choice = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Input mismatch !");
                consoleUI();
                break;
            }
            
            // Capture '\n'
            scan.nextLine();
            
            System.out.println();
            switch (choice) {
                case 1:
                    new RBTree().consoleUI();
                    break;
                case 2:
                    new AugmentedRBTree().consoleUI();
                    break;            
            }
        }
    }
}
