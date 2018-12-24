/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeodevi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserOperations implements UsersTableAttiributes {
        
    public static boolean isUserExist(String username) {
        // Veritabanına girme
        try {
            String sql = "SELECT username FROM " + TABLE_NAME + " WHERE username = '" + username + "';";

            // Sorguyu derleme
            Statement s = DB.getConnection().createStatement();
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public static boolean canLogin(String username, String password) {
        try {
            // SQL sorgusu oluşturma
            String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = '" + username + "' AND password = '" + password + "'";

            // Sorguyu derleme
            Statement s = DB.getConnection().createStatement();
            ResultSet rs = s.executeQuery(sql);
            
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static void createUser(String username, String password) {
        try {
            // SQL sorgusu oluşturma
            String sql = "INSERT INTO " + TABLE_NAME + " VALUES ('" + username + "', '" + password + "')";

            // Sorguyu derleme
            Statement s = DB.getConnection().createStatement();
            s.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
