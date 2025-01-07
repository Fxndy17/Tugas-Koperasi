package com.fxndy.projekkoprasi;

import java.io.IOException;
import java.util.*;

class DataKoperasi {
    
    String nama;
    int nomorInduk;
    String nomorTelepon;
    String alamat;
    String jenisKelamin;
    double simpanan;
    double bunga;
    String pekerjaan;

    public DataKoperasi(String nama, int nomorInduk, String nomorTelepon, String alamat, String jenisKelamin, double simpanan, double bunga, String pekerjaan) {
        this.nama = nama;
        this.nomorInduk = nomorInduk;
        this.nomorTelepon = nomorTelepon;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
        this.simpanan = simpanan;
        this.bunga = bunga;
        this.pekerjaan = pekerjaan;
    }
    
    // ubah data
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNomorInduk(int nomorInduk) {
        this.nomorInduk = nomorInduk;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setSimpanan(double simpanan) {
        this.simpanan = simpanan;
    }

    public void setBunga(double bunga) {
        this.bunga = bunga;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    // Ngambil data
    public String getNama() {
        return nama;
    }

    public int getNomorInduk() {
        return nomorInduk;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public double getSimpanan() {
        return simpanan;
    }

    public double getBunga() {
        return bunga;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

}

public class ProjekKoprasi {
    
    private static final Scanner input = new Scanner(System.in);
    static ArrayList < DataKoperasi > database = new ArrayList < > ();
    static boolean isRunning = true;
    
    static void showMenu() throws IOException {
        System.out.println("========= KOPERASI =========");
        System.out.println("[1] Isi Data");
        System.out.println("[2] Lihat Laporan");
        System.out.println("[3] Koreksi / edit data");
        System.out.println("[4] Hapus Data");
        System.out.println("[5] Keluar");
        System.out.print("PILIH MENU > ");
        
        int selectedMenu = Integer.parseInt(input.next());

        switch (selectedMenu) {
            case 1 -> addData();
            case 5 -> System.exit(0);
            default -> {
                System.out.println("Pilihan Salah!");
                showMenu();
            }
        }
    }
    
    static void addData() throws IOException {
        boolean lanjut = true;
        
        do {
            System.out.print("Nama: ");
            String nama = input.next();
            
            System.out.print("Nomor Induk: ");
            int induk = input.nextInt();
            
            System.out.print("Nomor Telepon: ");
            String nomorTelepon = input.next();
            
            System.out.print("Alamat: ");
            String alamat = input.next();
            
            System.out.print("Jenis Kelamin (L/P): ");
            String jenisKelamin = input.next();
            
            if (!jenisKelamin.toLowerCase().equals("l") || !jenisKelamin.toLowerCase().equals("p")) {
                System.out.println("Input tidak valid. Harap masukkan L untuk Laki-laki atau P untuk Perempuan.");
                addData();
                break;
            }

            System.out.print("Jumlah Simpanan: ");
            double simpanan = input.nextDouble();
            
            System.out.print("Masukkan Bunga: ");
            double bunga = input.nextDouble();
            
            System.out.print("Pekerjaan: ");
            String pekerjaan = input.next();
            
            database.add(new DataKoperasi(nama, induk, nomorTelepon, alamat, jenisKelamin, simpanan, bunga, pekerjaan));

            System.out.print("Tambah data lagi? (y/t): ");
            String jawab = input.next();
            if (!jawab.equalsIgnoreCase("y")) {
                lanjut = false;
            }
        } while (lanjut);
    }
    
    
    public static void main(String[] args) throws IOException {
        do {
            showMenu();
        } while (isRunning);
    }
}