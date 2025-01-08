package com.fxndy.projekkoprasi;

import java.io.IOException;
import java.util.*;

class DataKoperasi {
    
    String nama;
    int nomorInduk;
    String nomorTelepon;
    String alamat;
    String jenisKelamin;
    int simpanan;
    String pekerjaan;

    public DataKoperasi(String nama, int nomorInduk, String nomorTelepon, String alamat, String jenisKelamin, int simpanan, String pekerjaan) {
        this.nama = nama;
        this.nomorInduk = nomorInduk;
        this.nomorTelepon = nomorTelepon;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
        this.simpanan = simpanan;
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

    public void setSimpanan(int simpanan) {
        this.simpanan = simpanan;
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

    public int getSimpanan() {
        return simpanan;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }
}

public class ProjekKoprasi {

    private static final Scanner input = new Scanner(System.in);
    static ArrayList<DataKoperasi> database = new ArrayList<>();
    static boolean isRunning = true;
    static int bunga = 2;

    static void showMenu() throws IOException {
        System.out.println("========= Koperasi An Nisa =========");
        System.out.println("[1] Isi Data");
        System.out.println("[2] Lihat Laporan");
        System.out.println("[3] Koreksi / edit data");
        System.out.println("[4] Hapus Data");
        System.out.println("[5] Keluar");
        System.out.println("====================================");
        System.out.print("PILIH MENU > ");

        int selectedMenu = Integer.parseInt(input.next());

        switch (selectedMenu) {
            case 1 -> addData();
            case 2 -> showAll(false);
            case 3 -> editData();
            case 4 -> deleteData();
            case 5 -> System.exit(0);
            default -> {
                System.out.println("");
                System.out.println("[!] Pilihan Salah!");
                System.out.println("");
                showMenu();
            }
        }
    }

    static void addData() throws IOException {
        boolean lanjut = true;
        
        do {
            System.out.println("");

            System.out.print("Nama: ");
            String nama = input.next();

            System.out.print("Nomor Induk: ");
            int induk = input.nextInt();

            System.out.print("Nomor Telepon: ");
            String nomorTelepon = input.next();

            System.out.print("Alamat: ");
            String alamat = input.next();

            System.out.print("Jenis Kelamin (L/P): ");
            String jenisKelamin = input.next().toLowerCase();

            if (!Arrays.asList("l", "p").contains(jenisKelamin)) {
                System.out.println("Input tidak valid. Harap masukkan L untuk Laki-laki atau P untuk Perempuan.");
                addData();
                break;
            }

            System.out.print("Jumlah Simpanan: ");
            int simpanan = input.nextInt();

            System.out.print("Pekerjaan: ");
            String pekerjaan = input.next();

            database.add(new DataKoperasi(nama, induk, nomorTelepon, alamat, jenisKelamin, simpanan, pekerjaan));
            
            System.out.print("Tambah data lagi? (y/t): ");
            String jawab = input.next();
            System.out.println("");
            if (!jawab.equalsIgnoreCase("y")) {
                lanjut = false;
            }
        } while (lanjut);
    }

    static void showAll(boolean readOnly) throws IOException {
        System.out.println("");
        if (database.isEmpty()) {
            System.out.println("[!] Belum Ada Data!");
            System.out.println("");
            return;
        }

        int totalGrandSimpanan = 0;
        int totalGrandBunga = 0;
        int page = 1;
        int recordsPerPage = 3;
        int totalPages = (int) Math.ceil((double) database.size() / recordsPerPage);
        int no = 1;
        int baris = 133;

        while (true) {
            int totalSimpanan = 0, totalBunga = 0;

            System.out.println("LAPORAN KOPERASI");
            System.out.printf("%-120s %-2s\n", "7 Januari 2025", "Hal " + page);
            System.out.println("-".repeat(baris));
            System.out.println(
                    "|    |            |              |             |               |                 Nomer                   |              |           |");
            System.out.println(
                    "| No |    Nama    |    Alamat    |   Kelamin   |   Pekerjaan   |-----------------------------------------|   Simpanan   |   Bunga   |");
            System.out.println(
                    "|    |            |              |             |               |       Induk       |       Telepon       |              |           |");
            System.out.println("-".repeat(baris));

            int start = (page - 1) * recordsPerPage;
            int end = Math.min(start + recordsPerPage, database.size());

            for (int i = start; i < end; i++) {

                DataKoperasi data = database.get(i);
                String nama = data.getNama();
                int nomorInduk = data.getNomorInduk();
                String nomorTelepon = data.getNomorTelepon();
                String alamat = data.getAlamat();
                String jenisKelamin = data.getJenisKelamin();
                int simpanan = data.getSimpanan();
                String pekerjaan = data.getPekerjaan();

                int bungaTotal = data.getSimpanan() * bunga / 100;

                System.out.printf(
                        "| %-2s | %-10s | %-12s | %-11s | %-13s |         %-9s | %-19s |     %-8s |    %-6s |\n",
                        no, nama, alamat, jenisKelamin, pekerjaan, nomorInduk, nomorTelepon, simpanan, bungaTotal);

                totalSimpanan += simpanan;
                totalBunga += bungaTotal;
                no++;
            }

            totalGrandSimpanan += totalSimpanan;
            totalGrandBunga += totalBunga;

            System.out.println("-".repeat(baris));
            System.out.printf("| %-2s | %-97s |     %-8s |    %-6s |\n",
            "", "Subtotal Hal Ini", totalSimpanan, totalBunga);
            System.out.println("-".repeat(baris));
            System.out.printf("| %-2s | %-97s |     %-8s |    %-6s |\n",
            "", "Grand total", totalGrandSimpanan, totalGrandBunga);
            System.out.println("-".repeat(baris));

            if (readOnly == true)
                break;

            if (page < totalPages) {
                System.out.print("\nTekan 'n' untuk halaman berikutnya atau 'q' untuk keluar: ");
            } else if (page > 1) {
                System.out.print("\nTekan 'p' untuk halaman sebelumnya atau 'q' untuk keluar: ");
            } else {
                System.out.print("\nTekan 'n' untuk halaman berikutnya atau 'q' untuk keluar: ");
            }

            String jawab = input.next();
            System.out.println("");

            if (jawab.equalsIgnoreCase("n") && page < totalPages) {
                page++;
            } else if (jawab.equalsIgnoreCase("p") && page > 1) {
                page--;
            } else if (jawab.equalsIgnoreCase("q")) {
                break;
            } else {
                showAll(false);
                System.out.println("Input tidak valid. Silakan coba lagi.");
                break;
            }
        }
    }

    static void editData() throws IOException {
        showAll(true);

        System.out.print("Pilih Nomer Data: ");
        int index = Integer.parseInt(input.next()) - 1;
        DataKoperasi data = database.get(index);

        System.out.print("Nama: ");
        String nama = input.next();
        data.setNama(nama);

        System.out.print("Nomor Induk: ");
        int induk = input.nextInt();
        data.setNomorInduk(induk);

        System.out.print("Nomor Telepon: ");
        String nomorTelepon = input.next();
        data.setNomorTelepon(nomorTelepon);

        System.out.print("Alamat: ");
        String alamat = input.next();
        data.setAlamat(alamat);

        System.out.print("Jenis Kelamin (L/P): ");
        String jenisKelamin = input.next();

        if (!jenisKelamin.toLowerCase().equals("l") || !jenisKelamin.toLowerCase().equals("p")) {
            System.out.println("Input tidak valid. Harap masukkan L untuk Laki-laki atau P untuk Perempuan.");
            editData();
            return;
        }

        data.setJenisKelamin(jenisKelamin);

        System.out.print("Jumlah Simpanan: ");
        int simpanan = input.nextInt();
        data.setSimpanan(simpanan);

        System.out.print("Pekerjaan: ");
        String pekerjaan = input.next();
        data.setPekerjaan(pekerjaan);

        System.out.println("[!] Berhasil mengedit data!");

        System.out.println("");
    }

    static void deleteData() throws IOException {
        showAll(true);

        System.out.print("Pilih Nomer Data: ");
        int index = Integer.parseInt(input.next()) - 1;

        database.remove(index);

        System.out.println("[!] Berhasil menghapus data!");
        System.out.println("");
    }

    public static void main(String[] args) throws IOException {
        do {
            showMenu();
        } while (isRunning);
    }
}