/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeodevi;

import java.sql.SQLException;
import java.sql.Statement;

public abstract class OrderOperations implements OrdersTableAttiributes {
    
    public static void createOrder(String username, int bookID) {
        try {
            // SQL sorgusu oluşturma
            String sql = "INSERT INTO " + TABLE_NAME + " VALUES ('" + username + "', '" + bookID + "')";

            Statement s = DB.getConnection().createStatement();
            s.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
    
    public static void createOrders() {
        System.out.println("Selected Book IDs:");
        UserData.selectedBooks.forEach(id -> System.out.println("ID: " + id + " Name: " + BookOperations.getBookName(id)));

        UserData.selectedBooks.forEach(id -> createOrder(UserData.username, id));
        System.out.println("Orders created!");
    }
}
