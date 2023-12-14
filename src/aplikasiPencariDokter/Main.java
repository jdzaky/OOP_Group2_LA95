package aplikasiPencariDokter;

import java.util.List;
import java.util.Scanner;
import java.sql.*;

public class Main {

	public static void main(String[] args) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		
		DoctorManager doctorManager = new DoctorManager();
		int x, pilihan;
		do {
			System.out.println("Selamat datang\nInput role anda");
			System.out.print("1. Admin\n2. User\n0. Exit\n=>");
			 x = scanner.nextInt();
			 switch(x) {
			 case 1:
				 do {
						printMenuAdmin();
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
			                System.out.println("Keluar dari Admin");
			                break;
			            default:
			                System.out.println("Pilihan tidak valid");
						}
					}while (pilihan != 0);
				 break;
			 case 2:
				 do {
						printmenuUser();
						pilihan = scanner.nextInt();
						switch(pilihan) {
						case 1: 
							addAppoint(scanner, doctorManager);
							break;
						case 2: 
			                searchDokter(scanner, doctorManager);
							break;
						case 3: 
			                listDokter(doctorManager);
							break;
						case 4 :
							register(scanner);
							break;
						case 0:
							System.out.println("Keluar dari User");
							break;
						default :
							System.out.println("Pilihan tidak valid");
						}
						
					}while (pilihan != 0);
				 break;
			 case 0 :
					System.out.println("Keluar dari sistem, Terima kasih");
				 break;
			 default :
					System.out.println("Pilihan tidak valid");
			 }
		}while (x != 0);
	}
	
	private static void printMenuAdmin() {
        System.out.println("Menu");
        System.out.println("1. Tambah dokter");
        System.out.println("2. Ubah dokter");
        System.out.println("3. Hapus dokter");
        System.out.println("4. Daftar dokter");
        System.out.println("5. Cari dokter");
        System.out.println("0. Keluar");
        System.out.print("Pilih: ");
    }
	private static void printmenuUser() {
		System.out.println("Menu");
		System.out.println("1. Buat Janji");
		System.out.println("2. Cari dokter");
		System.out.println("3. List dokter");
		System.out.println("4. Register");
		System.out.println("0. keluar ");
		System.out.println("Pilih: ");
	}
	
	private static void register(Scanner scanner) {
		System.out.print("Masukkan Nama: ");
        String nama = scanner.next();
		pasien pasienn = new pasien(nama);
		
		try {
		    KoneksiDB koneksiDB = new KoneksiDB();
		    koneksiDB.addPatient(pasienn);
			  System.out.println("Pasien " + nama + " berhasil didaftarkan");
		  } catch (SQLException e) {
		    System.out.println("Error registering patient: " + e.getMessage());
		  }

	}
	
	private static void addDokter(Scanner scanner, DoctorManager doctorManager) {
		scanner.nextLine();
		System.out.print("Masukkan kode dokter: ");
        String kodeDokter = scanner.nextLine();
        System.out.print("Masukkan nama dokter: ");
        String namaDokter = scanner.nextLine();
        System.out.print("Masukkan spesialisasi: (jika belum ada lewati) :");
        String spesialisasi = scanner.nextLine();
        System.out.print("Masukkan jadwal praktik: ");
        String jadwalPraktik = scanner.nextLine();
        
        Dokter dokter = null;
        if(spesialisasi.isEmpty()) {
        	dokter = new DokterUmum(kodeDokter, namaDokter, spesialisasi, jadwalPraktik);
        } else {
            dokter = new DokterSpesialis(kodeDokter, namaDokter, spesialisasi, jadwalPraktik);
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
		scanner.nextLine();
        Dokter dokter = null;
		try {
			dokter = doctorManager.getDoctorById(kodeDokter);
			if(dokter == null) {
	        	System.out.println("Dokter tidak ditemukan");
	            return;
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        System.out.print("Masukkan nama dokter baru (kosongkan jika tidak ingin mengubah): ");
        String namaDokterBaru = scanner.nextLine();
        if (!namaDokterBaru.isEmpty()) {
            dokter.setNamaDokter(namaDokterBaru);
        }

        System.out.print("Masukkan spesialisasi baru (kosongkan jika tidak ingin mengubah): ");
        String spesialisasiBaru = scanner.nextLine();
        if (!spesialisasiBaru.isEmpty()) {
            dokter.setSpesialisasi(spesialisasiBaru);
        }

        System.out.print("Masukkan jadwal praktik baru (kosongkan jika tidak ingin mengubah): ");
        String jadwalPraktikBaru = scanner.nextLine();
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
	
	private static void addAppoint(Scanner scanner, DoctorManager doctorManager) {

		  System.out.print("Masukkan id pasien: ");
		  String idPasien = scanner.next();
		  System.out.print("Masukkan kode dokter: ");
		  String kodeDokter = scanner.next();
		  System.out.print("Masukkan tanggal janji: ");
		  String tanggalJanji = scanner.next();
		  scanner.nextLine();
		  System.out.print("Masukkan keluhan: ");
		  String keluhan = scanner.nextLine();

		  Appointment appointment = new Appointment();
		  appointment.setIdPasien(idPasien);
		  appointment.setKodeDokter(kodeDokter);
		  appointment.setTanggalJanji(tanggalJanji);
		  appointment.setKeluhanPasien(keluhan);

		  try {
		    AppointmentManager appointmentManager = new AppointmentManager(doctorManager);
		    appointmentManager.bookAppointment(appointment);
		    System.out.println("Janji temu berhasil dibuat");
		    System.out.println("Istirahat yang cukup dan makan makanan bergizi");
		  } catch (SQLException e) {
			    System.out.println("Jadwal dokter tidak ada yang memenuhi");
		    System.out.println("Error: " + e.getMessage());
		  }
	}

}


