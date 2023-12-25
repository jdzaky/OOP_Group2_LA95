package aplikasiPencariDokter;

public class pasien {
	private String nama;
	private int idPasien;
	public pasien() {
		
	}
	public pasien( String nama) {
		this.nama = nama;
	}
	public String getNamaPasien() {
    	return nama;
    }
	public int getidPasien() {
		return idPasien;
	}
    public void setNamaPasien(String kodeDokter) {
    	this.nama = kodeDokter;
    }
}
