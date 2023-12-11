package aplikasiPencariDokter;

import java.util.List;
import java.util.Scanner;
import java.sql.*;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		DoctorManager doctorManager = new DoctorManager();
		
		int pilihan;
		do {
			printMenu();
			pilihan = scanner.nextInt();
			
			switch(pilihan) {
			case 1:
                addDokter(scanner, doctorManager);
                break;
            case 2:
                updateDokter(scanner, doctorManager);
                break;
            case 3:
                deleteDokter(scanner, doctorManager);
                break;
            case 4:
                listDokter(doctorManager);
                break;
            case 5:
                searchDokter(scanner, doctorManager);
                break;
            case 0:
                System.out.println("Keluar dari aplikasi");
                break;
            default:
                System.out.println("Pilihan tidak valid");
			}
		}while (pilihan != 0);
	}
	
	private static void printMenu() {
        System.out.println("Menu");
        System.out.println("1. Tambah dokter");
        System.out.println("2. Ubah dokter");
        System.out.println("3. Hapus dokter");
        System.out.println("4. Daftar dokter");
        System.out.println("5. Cari dokter");
        System.out.println("0. Keluar");
        System.out.print("Pilih: ");
    }
	
	private static void addDokter(Scanner scanner, DoctorManager doctorManager) {
		System.out.print("Masukkan kode dokter: ");
        String kodeDokter = scanner.next();
        System.out.print("Masukkan nama dokter: ");
        String namaDokter = scanner.next();
        System.out.print("Masukkan spesialisasi: ");
        String spesialisasi = scanner.next();
        System.out.print("Masukkan jadwal praktik: ");
        String jadwalPraktik = scanner.next();
        
        Dokter dokter;
        if(spesialisasi.isEmpty()) {
        	dokter = new DokterUmum(kodeDokter, namaDokter, spesialisasi, jadwalPraktik, null);
        }else {
            dokter = new DokterSpesialis(kodeDokter, namaDokter, spesialisasi, jadwalPraktik, null);
        }
        
        try {
            doctorManager.addDoctor(dokter);
            System.out.println("Dokter berhasil ditambahkan");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
	}
	
	private static void updateDokter(Scanner scanner, DoctorManager doctorManager) {
		System.out.print("Masukkan kode dokter: ");
        String kodeDokter = scanner.next();
        
        Dokter dokter = doctorManager.getDoctorById(kodeDokter);
        if(dokter == null) {
        	System.out.println("Dokter tidak ditemukan");
            return;
        }
        
        System.out.print("Masukkan nama dokter baru (kosongkan jika tidak ingin mengubah): ");
        String namaDokterBaru = scanner.next();
        if (!namaDokterBaru.isEmpty()) {
            dokter.setNamaDokter(namaDokterBaru);
        }

        System.out.print("Masukkan spesialisasi baru (kosongkan jika tidak ingin mengubah): ");
        String spesialisasiBaru = scanner.next();
        if (!spesialisasiBaru.isEmpty()) {
            dokter.setSpesialisasi(spesialisasiBaru);
        }

        System.out.print("Masukkan jadwal praktik baru (kosongkan jika tidak ingin mengubah): ");
        String jadwalPraktikBaru = scanner.next();
        if (!jadwalPraktikBaru.isEmpty()) {
            dokter.setJadwalPraktik(jadwalPraktikBaru);
        }

        try {
            doctorManager.updateDoctor(kodeDokter, dokter);
            System.out.println("Dokter berhasil diubah");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
	}
	
	private static void deleteDokter(Scanner scanner, DoctorManager doctorManager) {
        System.out.print("Masukkan kode dokter: ");
        String kodeDokter = scanner.next();

        try {
            doctorManager.deleteDoctor(kodeDokter);
            System.out.println("Dokter berhasil dihapus");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
	
	private static void listDokter(DoctorManager doctorManager) {
		try {
			List<Dokter> daftarDokter = doctorManager.getAllDoctors();
			System.out.println("Daftar dokter");
			for(Dokter dokter : daftarDokter) {
				System.out.println(dokter);
			}
		}catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	private static void searchDokter(Scanner scanner, DoctorManager doctorManager) {
	    System.out.print("Masukkan keyword: ");
	    String keyword = scanner.next();

	    try {
	        List<Dokter> hasilPencarian = doctorManager.searchDoctors(keyword);
	        System.out.println("Hasil Pencarian:");
	        for (Dokter dokter : hasilPencarian) {
	            System.out.println(dokter);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	}

}


