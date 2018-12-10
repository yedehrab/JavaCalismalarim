/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nypfinal;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Yunus Emre
 */
public class KelimeKelimeOku {
    public static void menu(){
        try{
            
            FileInputStream fis = new FileInputStream("file.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            
            String line;
            StringTokenizer st;
            
            while ((line = br.readLine()) != null){
                st = new StringTokenizer(line);
                
                while (st.hasMoreTokens()){
                    System.out.println(st.nextToken());
                }
            }
            
        } catch(IOException s){
            System.out.println(s);
        }
    }
}
    
