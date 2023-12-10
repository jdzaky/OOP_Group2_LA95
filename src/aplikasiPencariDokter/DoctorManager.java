package aplikasiPencariDokter;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DoctorManager {
	private String status = "not granted";
	private List<Dokter> daftarDokter;
	private KoneksiDB koneksiDB;
	
	public DoctorManager() {
		this.daftarDokter = new ArrayList<>();
		this.koneksiDB = new KoneksiDB();
	}
	
	public void addDoctor(Dokter dokter) throws SQLException {
		if(status.equals("granted")) {
			this.koneksiDB.addDoctor(dokter);
			daftarDokter.add(dokter);
		}else {
			System.out.println("Hanya admin yang dapat menambahkan dokter baru.");
		}
	}
	
	public void updateDoctor(String id, Dokter updatedDokter) throws SQLException {
		if(status.equals("granted")) {
			this.koneksiDB.updateDoctor(id, updatedDokter);
			for(int i=0;i<daftarDokter.size();i++) {
				if (daftarDokter.get(i).getId().equals(id)) {
                    daftarDokter.set(i, updatedDokter);
                    break;
                }
			}
		}else {
			System.out.println("Hanya admin yang dapat memperbarui data dokter.");
		}
	}
	
	public void deleteDoctor(String id) throws SQLException {
        if (status.equals("granted")) {
            this.koneksiDB.deleteDoctor(id);
            for (int i = 0; i < daftarDokter.size(); i++) {
                if (daftarDokter.get(i).getId().equals(id)) {
                    daftarDokter.remove(i);
                    break;
                }
            }
        } else {
        	System.out.println("Hanya admin yang dapat menghapus data dokter.");
        }
    }
	
	public List<Dokter> getAllDoctors() throws SQLException{
		daftarDokter = this.koneksiDB.getAllDoctors();
		return daftarDokter;
	}
	
	public List<Dokter> searchDoctors(String keyword) throws SQLException {
        List<Dokter> hasilPencarian = new ArrayList<>();
        for (Dokter dokter : daftarDokter) {
            if (dokter.getNamaDokter().toLowerCase().contains(keyword.toLowerCase()) ||
                    dokter.getSpesialisasi().toLowerCase().contains(keyword.toLowerCase()) ||
                    dokter.getJadwalPraktik().toLowerCase().contains(keyword.toLowerCase())) {
                hasilPencarian.add(dokter);
            }
        }
        return hasilPencarian;
    }
	
	public void cekRole(String role) {
		if(role.equals("ADMIN_ROLE")) {
			status = "granted";
		}
	}
    
	public Dokter getDoctorById(String kodeDokter) throws SQLException {
		 Dokter foundDoctor = null;

		    // Check if the doctor is already in the list
		    for (Dokter dokter : daftarDokter) {
		        if (dokter.getKodeDokter().equals(kodeDokter)) {
		            foundDoctor = dokter;
		            break;
		        }
		    }

		    // If not found in memory, fetch from the database
		    if (foundDoctor == null) {
		        foundDoctor = koneksiDB.getDoctorById(kodeDokter);
		        if (foundDoctor != null) {
		            daftarDokter.add(foundDoctor);
		        }
		    }

		    return foundDoctor;
	}
}
