package aplikasiPencariDokter;


public class Appointment {
	private String idPasien;
    private String namaPasien;
    private String kodeDokter;
    private String tanggalJanji;
    private String keluhanPasien;

    public Appointment() {
    }

    public Appointment(String idPasien, String namaPasien, String kodeDokter, String tanggalJanji, String keluhanPasien) {
        this.idPasien = idPasien;
        this.namaPasien = namaPasien;
        this.kodeDokter = kodeDokter;
        this.tanggalJanji = tanggalJanji;
        this.keluhanPasien = keluhanPasien;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getKodeDokter() {
        return kodeDokter;
    }

    public void setKodeDokter(String kodeDokter) {
        this.kodeDokter = kodeDokter;
    }

    public String getTanggalJanji() {
        return tanggalJanji;
    }

    public void setTanggalJanji(String tanggalJanji) {
        this.tanggalJanji = tanggalJanji;
    }

    public String getKeluhanPasien() {
        return keluhanPasien;
    }

    public void setKeluhanPasien(String keluhanPasien) {
        this.keluhanPasien = keluhanPasien;
    }

}
