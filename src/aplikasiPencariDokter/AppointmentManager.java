package aplikasiPencariDokter;

import java.sql.SQLException;

public class AppointmentManager {
	private DoctorManager doctorManager;
	private KoneksiDB koneksiDB;

	public AppointmentManager(DoctorManager doctorManager) {
        this.doctorManager = doctorManager;
        this.koneksiDB = new KoneksiDB();
    }

    public void bookAppointment(Appointment appointment) throws SQLException {
        Dokter dokter = doctorManager.getDoctorById(appointment.getKodeDokter());
        if (dokter.getJadwalPraktik().contains(appointment.getTanggalJanji())) {
        	koneksiDB.addAppointment(appointment);
            System.out.println("Janji temu berhasil dibuat");
		    System.out.println("Istirahat yang cukup dan makan makanan bergizi");
        }else {
		    koneksiDB.deleteAppointment(appointment.getIdPasien(), appointment.getKeluhanPasien());
            System.out.println("Dokter tidak tersedia pada tanggal tersebut.");
        }
        
    }
}
