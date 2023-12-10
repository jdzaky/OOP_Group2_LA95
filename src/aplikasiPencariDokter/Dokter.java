package aplikasiPencariDokter;

public abstract class Dokter {
	protected String kodeDokter;
    protected String namaDokter;
    protected String spesialisasi;
    protected String jadwalPraktik;
    
    public Dokter() {
    	
    }
    public Dokter(String kodeDokter, String namaDokter, String spesialisasi, String jadwalPraktik) {
        this.kodeDokter = kodeDokter;
        this.namaDokter = namaDokter;
        this.spesialisasi = spesialisasi;
        this.jadwalPraktik = jadwalPraktik;
    }
    
    public String getKodeDokter() {
    	return kodeDokter;
    }
    public void setKodeDokter(String kodeDokter) {
    	this.kodeDokter = kodeDokter;
    }
    public String getNamaDokter() {
    	return namaDokter;
    }
    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }
    public String getSpesialisasi() {
        return spesialisasi;
    }
    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }
    public String getJadwalPraktik() {
        return jadwalPraktik;
    }
    public void setJadwalPraktik(String jadwalPraktik) {
        this.jadwalPraktik = jadwalPraktik;
    }

    public abstract String getId();
    public abstract void setId(String id);
}
