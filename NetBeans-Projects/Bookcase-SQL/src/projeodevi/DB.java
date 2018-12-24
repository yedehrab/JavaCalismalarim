/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeodevi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DB {
    
    public static final String DB_NAME = "mydb";
    public static final String USERNAME = "admin";
    public static final String PASS = "123";

    private static Connection conn;
    
    public static void connectDB() {      
        // Hata çıkabilmesi durumunda programın kapanmasını engelleme
        try {
            // Driveri yükleme
            Class.forName("org.postgresql.Driver");

            // Bağlantı url'i tanımlama
            String url = "jdbc:postgresql://localhost/" + DB_NAME;

            // Veritabanı bağlantı özelliklerini ayarlarma
            Properties props = new Properties();
            props.setProperty("user", USERNAME);
            props.setProperty("password", PASS);

            // PostgreSQL veritabanına bağlanma
            conn = DriverManager.getConnection(url, props);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Veritabanına bağlanılamadı :(" + e);
            System.exit(1);
        }
    }
    
    public static Connection getConnection() {
        if (conn == null) {
            connectDB();
        }
        return conn;
    }
}
