package aplikasiPencariDokter;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DoctorManager {
	private List<Dokter> daftarDokter;
	private KoneksiDB koneksiDB;
	
	public DoctorManager() {
		this.daftarDokter = new ArrayList<>();
		this.koneksiDB = new KoneksiDB();
	}
	
	public void addDoctor(Dokter dokter) throws SQLException {
			this.koneksiDB.addDoctor(dokter);
			daftarDokter.add(dokter);
	}
	
	public void updateDoctor(String id, Dokter updatedDokter) throws SQLException {
			this.koneksiDB.updateDoctor(id, updatedDokter);
			for(int i=0;i<daftarDokter.size();i++) {
				if (daftarDokter.get(i).getId().equals(id)) {
                    daftarDokter.set(i, updatedDokter);
                    break;
                }
			}
		
	}
	
	public void deleteDoctor(String id) throws SQLException {
            this.koneksiDB.deleteDoctor(id);
            for (int i = 0; i < daftarDokter.size(); i++) {
                if (daftarDokter.get(i).getId().equals(id)) {
                    daftarDokter.remove(i);
                    break;
                }
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
	
    
	public Dokter getDoctorById(String kodeDokter) throws SQLException {
		 Dokter foundDoctor = null;

		    for (Dokter dokter : daftarDokter) {
		        if (dokter.getKodeDokter().equals(kodeDokter)) {
		            foundDoctor = dokter;
		            break;
		        }
		    }

		    if (foundDoctor == null) {
		        foundDoctor = koneksiDB.getDoctorById(kodeDokter);
		        if (foundDoctor != null) {
		            daftarDokter.add(foundDoctor);
		        }
		    }

		    return foundDoctor;
	}
}
