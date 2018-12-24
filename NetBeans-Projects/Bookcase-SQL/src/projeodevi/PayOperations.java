/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeodevi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class PayOperations implements PaymentsTableAttiributes{
    public static void listPayments(String username) {
        try {
            String sql = "SELECT * FROM " + TABLE_NAME;

            Statement s = DB.getConnection().createStatement();
            ResultSet rs = s.executeQuery(sql);

            System.out.printf("%-9s %-9s\n", "USERNAME", "PRICE");
            while (rs.next()) {
                System.out.printf("%-9s %-9s\n", rs.getString(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static int calculatePrice(String username) {
        try {
            String sql = "SELECT sum(price) FROM " + BooksTableAttiributes.TABLE_NAME + " WHERE id IN " + UserData.toArrayString();
            Statement s = DB.getConnection().createStatement();
            ResultSet rs = s.executeQuery(sql);
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        
        return -1;
    }
    
    public static void createPayment(String username) {
        try {
            // SQL sorgusu oluşturma
            String sql = "INSERT INTO " + TABLE_NAME + " VALUES ('" + username + "', '" + calculatePrice(username) + "')";

            Statement s = DB.getConnection().createStatement();
            s.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
}
