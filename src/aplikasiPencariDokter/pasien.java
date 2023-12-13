package aplikasiPencariDokter;

public class pasien {
	private String id, nama;

	public pasien() {
		
	}
	public pasien(String id, String nama) {
		this.id = id;
		this.nama = nama;
	}
	public String getNamaPasien() {
    	return nama;
    }
    public void setNamaPasien(String kodeDokter) {
    	this.nama = kodeDokter;
    }
	public String getIdPasien() {
    	return id;
    }
    public void setIdPasien(String kodeDokter) {
    	this.id = kodeDokter;
    }
}
