/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbçalışma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yunus Emre
 */
public class DbÇalışma {
    
    String url = "jdbc:derby://localhost:1527/DataBaseCalisma";
    String user = "yunus";
    String password = "yunus";
    
    Connection bağlantı = null;
            
    public static final String NAME_DATABASE = "YUNUS";
    public static final String NAME_TABLE = "TABLE1";
    public static final String LOCATION_TABLE = "YUNUS.TABLE1";
    public static final String NAME_COLUMN_1 = "Isimler";
    public static final String NAME_COLUMN_2 = "Numara";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DbÇalışma ders1 = new DbÇalışma();      
        ders1.dbVeriEkle(NAME_TABLE, NAME_COLUMN_1, NAME_COLUMN_2);
        ders1.dbVeriAl(NAME_TABLE, NAME_COLUMN_1, NAME_COLUMN_2);
    }
    
    public void dbVeriAl(String table, String column1, String column2){
        try{
            bağlantıSağla();
            
            String sql = "SELECT * FROM " + table;
            Statement durum = bağlantı.createStatement();
            ResultSet sonuçKümesi = durum.executeQuery(sql);
            
            while(sonuçKümesi.next()){
                System.out.println("Numara: " + sonuçKümesi.getString(column1)); 
                System.out.println("İsim: " + sonuçKümesi.getString(column2));
            }          
         
        
            
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        
    }
    
    public void dbVeriEkle(String table, String column1, String column2){
        try{
          bağlantıSağla();
          
          String sql1 = "INSERT INTO " + table + " (" + column1 + ", " + column2 + ") VALUES (?, ?)";
          
          PreparedStatement durum = bağlantı.prepareStatement(sql1);
          durum.setString(1, "İlk denemde");
          durum.setString(2, "numaram dok :/");
          
          if(durum.executeUpdate() > 0){
              System.out.println("Yazma başarılı");
          }
          
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void bağlantıSağla() throws SQLException{
        bağlantı = DriverManager.getConnection(url, user, password);
    }
    
}
