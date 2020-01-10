/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import dbconnect.MySQLConnection;
import java.util.Scanner;
import dbcrud.*;

/**
 *
 * @author Naubyra
 */
public class Menu {
    MySQLConnection mys = new MySQLConnection();
    Scanner s = new Scanner(System.in);
    
    public void MainMenu() {
        
        System.out.println("============================");
        System.out.println("APP PERPUSTAKAAN");
        System.out.println("----------------------------");
        System.out.println("1. Daftar Anggota");
        System.out.println("2. Peminjaman Buku");
        System.out.println("3. Tambah Buku");
        System.out.println("4. Keluar");
        System.out.println("============================");
        System.out.print("Pilih Menu : ");
        Integer sel = s.nextInt();
        
        switch(sel) {
            case 1:
            {
                this.MenuAnggota();
                break;
            }
            case 2:
            {
                this.MenuPeminjaman();
                break;
            }
            case 3:
            {
                this.MenuBuku();
                break;
            }
            case 4:
            {
                break;
            }
            default:
            {
                break;
            }
        }
    }
    
    public void MenuAnggota() {
        Anggota a = new Anggota();
        
        
        System.out.println("============================");
        System.out.println("Menu Anggota");
        System.out.println("----------------------------");
        System.out.println("1. Tambah Anggota");
        System.out.println("2. Hapus Anggota");
        System.out.println("3. Update Anggota");
        System.out.println("4. Lihat Anggota");
        System.out.println("5. Exit");
        System.out.println("============================");
        System.out.print("Pilih Menu : ");
        Integer sel = s.nextInt();
        
        switch(sel) {
            case 1:
            {
                a.insert(mys);
                this.MenuAnggota();
                break;
            }
            case 2:
            {
                a.delete(mys);
                this.MenuAnggota();
                break;
            }
            case 3:
            {
                a.update(mys);
                this.MenuAnggota();
                break;
            }
            case 4:
            {
                a.select(mys);
                this.MenuAnggota();
                break;
            }
            case 5:
            {
                this.MainMenu();
                break;
            }
            default:
            {
                break;
            }
        }
        
    }
    
    public void MenuPeminjaman() {
        Peminjaman p = new Peminjaman();
        
        
        System.out.println("============================");
        System.out.println("Menu Anggota");
        System.out.println("----------------------------");
        System.out.println("1. Pinjam Buku");
        System.out.println("2. Hapus Data Peminjaman");
        System.out.println("3. Update Data Peminjaman");
        System.out.println("4. Lihat Data Peminjaman");
        System.out.println("5. Exit");
        System.out.println("============================");
        System.out.print("Pilih Menu : ");
        Integer sel = s.nextInt();
        
        switch(sel) {
            case 1:
            {
                p.insert(mys);
                this.MenuPeminjaman();
                break;
            }
            case 2:
            {
                p.delete(mys);
                this.MenuPeminjaman();
                break;
            }
            case 3:
            {
                p.update(mys);
                this.MenuPeminjaman();
                break;
            }
            case 4:
            {
                p.select(mys);
                this.MenuPeminjaman();
                break;
            }
            case 5:
            {
                this.MainMenu();
                break;
            }
            default:
            {
                break;
            }
        }
        
    }
    
    public void MenuBuku() {
        Buku b = new Buku();
        
        
        System.out.println("============================");
        System.out.println("Menu Buku");
        System.out.println("----------------------------");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Hapus Buku");
        System.out.println("3. Update Buku");
        System.out.println("4. Lihat Buku");
        System.out.println("5. Exit");
        System.out.println("============================");
        System.out.print("Pilih Menu : ");
        Integer sel = s.nextInt();
        
        switch(sel) {
            case 1:
            {
                b.insert(mys);
                this.MenuBuku();
                break;
            }
            case 2:
            {
                b.delete(mys);
                this.MenuBuku();
                break;
            }
            case 3:
            {
                b.update(mys);
                this.MenuBuku();
                break;
            }
            case 4:
            {
                b.select(mys);
                this.MenuBuku();
                break;
            }
            case 5:
            {
                this.MainMenu();
                break;
            }
            default:
            {
                break;
            }
        }
        
    }
}
