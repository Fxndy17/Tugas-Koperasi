package com.fxndy.projekkoprasi;

import java.io.IOException;
import java.util.*;

public class ProjekKoprasi {

    private static final Scanner input = new Scanner(System.in);
    static ArrayList<DataKoperasi> database = new ArrayList<>();
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
            case 2 -> showAll(false);
            case 3 -> editData();
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
            String jenisKelamin = input.next().toLowerCase();

            if (!Arrays.asList("l", "p").contains(jenisKelamin)) {
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

    static void showAll(boolean readOnly) throws IOException {

        if (database.isEmpty()) {
            System.out.println("Belum Ada Data!");
            return;
        }

        double totalGrandSimpanan = 0;
        double totalGrandBunga = 0;
        int page = 1;
        int recordsPerPage = 3;
        int totalPages = (int) Math.ceil((double) database.size() / recordsPerPage);
        int no = 1;
        int baris = 133;

        while (true) {
            double totalSimpanan = 0, totalBunga = 0;
                
            System.out.println("LAPORAN KOPERASI");
            System.out.printf("%-100s %-2s\n", "7 Januari 2025", "Hal " + page);
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
                double simpanan = data.getSimpanan();
                double bunga = data.getBunga();
                String pekerjaan = data.getPekerjaan();
                System.out.printf(
                    "| %-2s | %-10s | %-12s | %-11s | %-13s |         %-9s | %-19s |     %-8s |    %-6s |\n",
                    no, nama, alamat, jenisKelamin, pekerjaan, nomorInduk, nomorTelepon, simpanan, bunga);
                
                double total = simpanan * (1.0D + bunga / 100.0D);
                totalSimpanan += total;
                totalBunga += total;
                no++;
            }

            totalGrandSimpanan += totalSimpanan;
            totalGrandBunga += totalBunga;

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

            if (jawab.equalsIgnoreCase("n") && page < totalPages) {
                page++;
            } else if (jawab.equalsIgnoreCase("p") && page > 1) {
                page--;
            } else if (jawab.equalsIgnoreCase("q")) {
                break;
            } else {
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
        double simpanan = input.nextDouble();
        data.setSimpanan(simpanan);

        System.out.print("Masukkan Bunga: ");
        double bunga = input.nextDouble();
        data.setBunga(bunga);

        System.out.print("Pekerjaan: ");
        String pekerjaan = input.next();
        data.setPekerjaan(pekerjaan);

        System.out.println("Berhasil mengedit data!");
    }

    static void deleteData() throws IOException {
        showAll(true);

        System.out.print("Pilih Nomer Data: ");
        int index = Integer.parseInt(input.next()) - 1;

        database.remove(index);

        System.out.println("Berhasil menghapus data!");
    }

    public static void main(String[] args) throws IOException {
        
        database.add(new DataKoperasi("nama", 2, "123", "3", "P", 0.9, 0.9, "09"));
        showAll(false);
        // do {
        //     showMenu();
        // } while (isRunning);
    }
}