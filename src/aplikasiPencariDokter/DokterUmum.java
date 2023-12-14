package aplikasiPencariDokter;

public class DokterUmum extends Dokter {
	private String gelar;
	
	public DokterUmum() {
		
	}
	public DokterUmum(String kodeDokter, String namaDokter, String spesial, String jadwal) {
		super(kodeDokter, namaDokter, spesial, jadwal);
	}
	public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }
    
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.kodeDokter;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.kodeDokter = id;
	}
	
	@Override
    public String toString() {
        return "DokterUmum{" +
                "kodeDokter='" + kodeDokter + '\'' +
                ", namaDokter='" + namaDokter + '\'' +
                ", spesialisasi='" + spesialisasi + '\'' +
                ", jadwalPraktik='" + jadwalPraktik + '\'' +
                " }";
    }
	
}
