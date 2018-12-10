/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nypfinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/**
 *
 * @author Yunus Emre
 */
public class HarfHarfOku {
    public static void menu(){
        StringBuffer icerik = new StringBuffer("");
        
        try{
            File f = new File("file.txt");
            FileInputStream fi = new FileInputStream(f);
            
            int k;
            
            while ((k = fi.read()) != -1)
                icerik.append((char)k);
            
        } catch(IOException e){
            System.out.println(e);
        }
    }
}
