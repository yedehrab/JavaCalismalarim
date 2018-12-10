/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nypfinal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author Yunus Emre
 */
public class FileWrite {
    public static void menu(){
        String sentence = "Weather is nice today.";
        
        try{
            FileOutputStream fos = new FileOutputStream("file.txt");
            PrintStream printer = new PrintStream(fos);
            
            printer.println(sentence);
            printer.print(4);
            printer.print('%');
            
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
