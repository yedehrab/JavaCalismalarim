/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeodevi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookOperations implements BooksTableAttiributes {
    public static boolean isBookExist(int id) {
        try {
            String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + id;
            Statement s = DB.getConnection().createStatement();
            ResultSet rs = s.executeQuery(sql);
            System.out.println(rs.next());
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String getBookName(int id) {
        try {
            String sql = "SELECT name FROM " + TABLE_NAME + " WHERE id = " + id;

            Statement s = DB.getConnection().createStatement();
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Bulunamadı";
    }
    
    public static void listBooks() {
        try {
            String sql = "SELECT * FROM " + TABLE_NAME;

            Statement s = DB.getConnection().createStatement();
            ResultSet rs = s.executeQuery(sql);

            System.out.printf("%-7s %-20s %-3s \n", "ID", "NAME", "PRICE");
            while (rs.next()) {
                System.out.printf("%-7s %-20s %-3s\n", rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
