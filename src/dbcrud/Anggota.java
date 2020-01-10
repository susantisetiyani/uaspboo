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
 * @author Diena 
 */
public class Anggota extends CRUD<MySQLConnection> {
    String a;
    // insert data buku
    @Override
    public void insert(MySQLConnection m){
        // Scanner
        Scanner s = new Scanner(System.in);
        
        System.out.print("Masukan Nama Anggota : ");
        String name = s.nextLine();
        System.out.print("Masukan Alamat Anggota : ");
        String alamat = s.nextLine();
        System.out.print("Masukan Nomor Handphone : ");
        Integer nohp = s.nextInt();
        
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        // query sql untuk insert data buku
        String sql = "INSERT INTO anggota (namaAnggota, alamat, noHp) VALUES (?, ?, ?)";
 
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya (sesuai urutan)
            statement.setString(1, name);
            statement.setString(2, alamat);
            statement.setString(3, nohp.toString());

            // jalankan query (baca jumlah row affectednya)
            int rowsInserted = statement.executeUpdate();
            // jika ada row affected nya, maka status sukses
            if (rowsInserted > 0) {
                System.out.println("Insert data anggota sukses");
            }

        } catch (SQLException ex) {
            // jika query gagal
            System.out.println("Insert data anggota gagal");
        }
    }
    
    // delete data buku berdasarkan idbook
    @Override
    public void delete(MySQLConnection m){
        // Scanner
        Scanner s = new Scanner(System.in);
        
        System.out.print("Masukan ID Anggota Yang Ingin Dihapus : ");
        Integer idang = s.nextInt();
        
        // query sql untuk hapus data buku berdasarkan idbook
        String sql = "DELETE FROM anggota WHERE idAnggota=?";
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            PreparedStatement statement;
            statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya
            statement.setString(1, idang.toString());
            
            // jalankan query, dan lihat jumlah row affected nya
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("anggota sudah berhasil dihapus");
            }
        } catch (SQLException ex) {
            System.out.println("Hapus data anggota gagal");
        }
        
    }
    
    // update data buku berdasarkan idbook
    @Override
    public void update(MySQLConnection m){
        
        // Scanner
        Scanner s = new Scanner(System.in);
        
        System.out.print("Masukan ID Anggota Yang Ingin Di Update : ");
        Integer idang = Integer.parseInt(s.nextLine());
        
        System.out.print("Masukan Nama Anggota : ");
        String name = s.nextLine();
        System.out.print("Masukan Alamat Anggota : ");
        String alamat = s.nextLine();
        System.out.print("Masukan Nomor Handphone : ");
        Integer nohp = s.nextInt();
        
        
        // query sql untuk update data buku berdasarkan idbook
        String sql = "UPDATE books SET bookTitle=?, bookAuthor=?, bookPublisher=?, yearPublished=? WHERE idBook=?";
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            // mapping nilai parameter ke query sqlnya
            statement.setString(1, name);
            statement.setString(2, alamat);
            statement.setString(3, nohp.toString());
            statement.setString(4, idang.toString());

            // jalankan query, dan baca jumlah row affectednya
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update data anggota sukses");
            }
        } catch (SQLException ex) {
             System.out.println("Update data anggota gagal");
        }
    }
    
    // tampilkan semua data buku
    @Override
    public void select(MySQLConnection m){
        
        // query sql untuk select all data buku
        String sql = "SELECT * FROM anggota";
        
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            Statement statement = koneksi.createStatement();
            // jalankan query
            ResultSet result = statement.executeQuery(sql);

            // membuat header table untuk output
            System.out.println("==============================================================================");
            String header = "%3s %20s %20s %15s";
            System.out.println(String.format(header, "ID", "NAMA", "ALAMAT", "NO HP"));
            System.out.println("------------------------------------------------------------------------------");
            
            // looping untuk baca data per record
            while (result.next()){
                // baca data buku per record
                String idang = result.getString("idAnggota");
                String nama = result.getString("namaAnggota");
                String alamat = result.getString("alamat");
                String nohp = result.getString("noHp");
                // tampilkan data buku per record
                String output = "%3s %20s %20s %15s";
                System.out.println(String.format(output, idang, nama, alamat, nohp));
            }
            
            System.out.println("==============================================================================");
            
        } catch (SQLException ex){
            System.out.println("Tampil data buku gagal");
        }
        
    }
}
