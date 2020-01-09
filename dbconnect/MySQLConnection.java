/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnect;

import dbcrud.Buku;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUSS
 */
public class MySQLConnection implements setting.MySQL {
    
    public Connection conn;
    
    // constructor -> sekaligus melakukan koneksi ke mysql
    public MySQLConnection(){
        // setting nilai atribut koneksi
        
        try {
            // membuat url string connection ke mysql 
            String dbURL = "jdbc:mysql://"+ this.dbHost +":3306/" + this.dbName;
            this.conn = DriverManager.getConnection(dbURL, this.dbUser, this.dbPass);
            // jika konek
            if (this.conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            // jika gagal konek
            System.out.println(ex);
        }
    }
    
    
    // method untuk menutup koneksi mysql
    public void close(){
        try {
            // tutup koneksi
            this.conn.close();
        } catch (SQLException ex) {
            System.out.println("Penutupan koneksi gagal");
        }
    }
    
}
