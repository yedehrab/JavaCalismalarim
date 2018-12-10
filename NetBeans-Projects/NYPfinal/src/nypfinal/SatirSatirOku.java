/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nypfinal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author Yunus Emre
 */
public class SatirSatirOku {
    public static void menu(){
        try{
            FileInputStream fi = new FileInputStream("file.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fi));
            
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
            
        } catch(IOException e) {
            System.out.println("Dosya mevcut değil.");
        }
    }
}
