/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nypfinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Yunus Emre
 */
public class ReadingLine {
    
    public static void menu(){
        System.out.println("Hello, what's your name? ");
        String name = readLine();
        System.out.println("Hello, " + name);
        System.out.println("");
        System.out.println("More tutarials in http// www.java.net");
    }
    
    public static String readLine(){
        String s = "";
        
        try{
            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);
            s = in.readLine();
        } catch(IOException e){
            System.out.println("Error Exception: " + e);
        }
        
        return s;
    }
}
