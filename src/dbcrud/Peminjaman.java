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
 * @author Naubyra
 */
public class Peminjaman extends CRUD<MySQLConnection> {
    // insert data buku
    @Override
    public void insert(MySQLConnection m){
        // Scanner
        Scanner s = new Scanner(System.in);
        
        System.out.print("Masukan ID Anggota : ");
        Integer idang = s.nextInt();
        System.out.print("Masukan ID Buku Yang Ingin Dipinjam : ");
        Integer idbuku = s.nextInt();
        
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        // query sql untuk insert data buku
        String sql = "INSERT INTO peminjaman (idAnggota, idBook) VALUES (?, ?)";
 
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya (sesuai urutan)
            statement.setString(1, idang.toString());
            statement.setString(2, idbuku.toString());

            // jalankan query (baca jumlah row affectednya)
            int rowsInserted = statement.executeUpdate();
            // jika ada row affected nya, maka status sukses
            if (rowsInserted > 0) {
                System.out.println("Insert data peminjaman sukses");
            }

        } catch (SQLException ex) {
            // jika query gagal
            System.out.println("Insert data peminjaman gagal");
        }
    }
    
    // delete data buku berdasarkan idbook
    @Override
    public void delete(MySQLConnection m){
        // Scanner
        Scanner s = new Scanner(System.in);
        
        System.out.print("Masukan ID Peminjaman : ");
        Integer idpem = s.nextInt();
        
        // query sql untuk hapus data buku berdasarkan idbook
        String sql = "DELETE FROM peminjaman WHERE idPeminjaman=?";
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            PreparedStatement statement;
            statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya
            statement.setString(1, idpem.toString());
            
            // jalankan query, dan lihat jumlah row affected nya
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("data peminjaman sudah berhasil dihapus");
            }
        } catch (SQLException ex) {
            System.out.println("Hapus data peminjaman gagal");
        }
        
    }
    
    // update data buku berdasarkan idbook
    @Override
    public void update(MySQLConnection m){
        
        // Scanner
        Scanner s = new Scanner(System.in);
        
        System.out.print("Masukan ID Peminjaman Yang Ingin Di Update : ");
        Integer idpem = Integer.parseInt(s.nextLine());
        
        System.out.print("Masukan ID Anggota : ");
        Integer idang = Integer.parseInt(s.nextLine());
        System.out.print("Masukan ID Buku : ");
        Integer idbuku = Integer.parseInt(s.nextLine());
        
        
        // query sql untuk update data buku berdasarkan idbook
        String sql = "UPDATE books SET idAnggota=?, idBook=? WHERE idPeminjaman=?";
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            // mapping nilai parameter ke query sqlnya
            statement.setString(1, idang.toString());
            statement.setString(2, idbuku.toString());
            statement.setString(3, idpem.toString());

            // jalankan query, dan baca jumlah row affectednya
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update data peminjaman sukses");
            }
        } catch (SQLException ex) {
             System.out.println("Update data peminjaman gagal");
        }
    }
    
    // tampilkan semua data buku
    @Override
    public void select(MySQLConnection m){
        
        // query sql untuk select all data buku
        String sql = "SELECT * FROM peminjaman";
        
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            Statement statement = koneksi.createStatement();
            // jalankan query
            ResultSet result = statement.executeQuery(sql);

            // membuat header table untuk output
            System.out.println("==================================================");
            String header = "%3s %3s %3s";
            System.out.println(String.format(header, "ID", "ID ANGGOTA", "ID BUKU"));
            System.out.println("--------------------------------------------------");
            
            // looping untuk baca data per record
            while (result.next()){
                // baca data buku per record
                String idpem = result.getString("idBook");
                String idang = result.getString("bookTitle");
                String idbuku = result.getString("bookAuthor");
                // tampilkan data buku per record
                String output = "%3s %3s %3s";
                System.out.println(String.format(output, idpem, idang, idbuku));
            }
            
            System.out.println("====================================================");
            
        } catch (SQLException ex){
            System.out.println("Tampil data buku gagal");
        }
        
    }
}
