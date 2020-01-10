/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbcrud;

import dbconnect.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


/**
 *
 * @author susanti
 */
public class Buku extends CRUD<MySQLConnection> {
    
    // insert data buku
    @Override
    public void insert(MySQLConnection m){
        // Scanner
        Scanner s = new Scanner(System.in);
        
        System.out.print("Masukan Judul Buku : ");
        String title = s.nextLine();
        System.out.print("Masukan Author Buku : ");
        String author = s.nextLine();
        System.out.print("Masukan Publisher Buku : ");
        String publisher = s.nextLine();
        System.out.print("Masukan Tahun Buku Terbit : ");
        Integer year = s.nextInt();
        
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        // query sql untuk insert data buku
        String sql = "INSERT INTO books (bookTitle, bookAuthor, bookPublisher, yearPublished) VALUES (?, ?, ?, ?)";
 
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya (sesuai urutan)
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, publisher);
            statement.setString(4, year.toString());

            // jalankan query (baca jumlah row affectednya)
            int rowsInserted = statement.executeUpdate();
            // jika ada row affected nya, maka status sukses
            if (rowsInserted > 0) {
                System.out.println("Insert data buku sukses");
            }

        } catch (SQLException ex) {
            // jika query gagal
            System.out.println("Insert data buku gagal");
        }
    }
    
    // delete data buku berdasarkan idbook
    @Override
    public void delete(MySQLConnection m){
        // Scanner
        Scanner s = new Scanner(System.in);
        
        System.out.print("Masukan ID Buku Yang Ingin Dihapus : ");
        Integer idbuku = s.nextInt();
        
        // query sql untuk hapus data buku berdasarkan idbook
        String sql = "DELETE FROM books WHERE idBook=?";
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            PreparedStatement statement;
            statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya
            statement.setString(1, idbuku.toString());
            
            // jalankan query, dan lihat jumlah row affected nya
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Buku sudah berhasil dihapus");
            }
        } catch (SQLException ex) {
            System.out.println("Hapus data buku gagal");
        }
        
    }
    
    // update data buku berdasarkan idbook
    @Override
    public void update(MySQLConnection m){
        
        // Scanner
        Scanner s = new Scanner(System.in);
        
        System.out.print("Masukan ID Buku Yang Ingin Di Update : ");
        Integer idbuku = Integer.parseInt(s.nextLine());
        
        System.out.print("Masukan Judul Buku : ");
        String title = s.nextLine();
        System.out.print("Masukan Author Buku : ");
        String author = s.nextLine();
        System.out.print("Masukan Publisher Buku : ");
        String publisher = s.nextLine();
        System.out.print("Masukan Tahun Buku Terbit : ");
        Integer year = s.nextInt();
        
        
        // query sql untuk update data buku berdasarkan idbook
        String sql = "UPDATE books SET bookTitle=?, bookAuthor=?, bookPublisher=?, yearPublished=? WHERE idBook=?";
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            // mapping nilai parameter ke query sqlnya
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, publisher);
            statement.setString(4, year.toString());
            statement.setString(5, idbuku.toString());

            // jalankan query, dan baca jumlah row affectednya
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update data buku sukses");
            }
        } catch (SQLException ex) {
             System.out.println("Update data buku gagal");
        }
    }
    
    // tampilkan semua data buku
    @Override
    public void select(MySQLConnection m){
        
        // query sql untuk select all data buku
        String sql = "SELECT * FROM books";
        
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            Statement statement = koneksi.createStatement();
            // jalankan query
            ResultSet result = statement.executeQuery(sql);

            // membuat header table untuk output
            System.out.println("==============================================================================");
            String header = "%3s %20s %20s %20s %4s";
            System.out.println(String.format(header, "ID", "JUDUL", "PENGARANG", "PENERBIT", "THN TERBIT"));
            System.out.println("------------------------------------------------------------------------------");
            
            // looping untuk baca data per record
            while (result.next()){
                // baca data buku per record
                String idBook = result.getString("idBook");
                String title = result.getString("bookTitle");
                String author = result.getString("bookAuthor");
                String publisher = result.getString("bookPublisher");
                String year = result.getString("yearPublished");
                // tampilkan data buku per record
                String output = "%3s %20s %20s %20s %4s";
                System.out.println(String.format(output, idBook, title, author, publisher, year));
            }
            
            System.out.println("==============================================================================");
            
        } catch (SQLException ex){
            System.out.println("Tampil data buku gagal");
        }
        
    }

}
