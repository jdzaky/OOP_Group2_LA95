package aplikasiPencariDokter;

public class pasien {
	private String nama;

	public pasien() {
		
	}
	public pasien( String nama) {
		this.nama = nama;
	}
	public String getNamaPasien() {
    	return nama;
    }
    public void setNamaPasien(String kodeDokter) {
    	this.nama = kodeDokter;
    }
}
