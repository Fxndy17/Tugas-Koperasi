/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fxndy.projekkoprasi;

/**
 *
 * @author felovy
 */
public class DataKoperasi {
    
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
