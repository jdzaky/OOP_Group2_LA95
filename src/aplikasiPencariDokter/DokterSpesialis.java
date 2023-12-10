package aplikasiPencariDokter;

public class DokterSpesialis extends Dokter {
	private String bidangSpesialisasi;

    public DokterSpesialis() {
    }

    public DokterSpesialis(String kodeDokter, String namaDokter, String spesialisasi, String jadwalPraktik, String bidangSpesialisasi) {
        super(kodeDokter, namaDokter, spesialisasi, jadwalPraktik);
        this.bidangSpesialisasi = bidangSpesialisasi;
    }

    public String getBidangSpesialisasi() {
        return bidangSpesialisasi;
    }

    public void setBidangSpesialisasi(String bidangSpesialisasi) {
        this.bidangSpesialisasi = bidangSpesialisasi;
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
        return "DokterSpesialis{" +
                "kodeDokter='" + kodeDokter + '\'' +
                ", namaDokter='" + namaDokter + '\'' +
                ", spesialisasi='" + spesialisasi + '\'' +
                ", jadwalPraktik='" + jadwalPraktik + '\'' +
                ", bidangSpesialisasi='" + bidangSpesialisasi + '\'' +
                '}';
    }


}
